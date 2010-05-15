package org.motechproject.mobile.omp.manager.intellivr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.motechproject.mobile.core.dao.MessageRequestDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import org.motechproject.mobile.omp.manager.utils.MessageStatusStore;
import org.motechproject.ws.server.RegistrarService;
import org.motechproject.ws.server.ValidationException;
import org.springframework.core.io.Resource;

public class IntellIVRBean implements GatewayManager, GetIVRConfigRequestHandler, ReportHandler {
	
	private GatewayMessageHandler messageHandler;
	protected String reportURL;
	private String apiID;
	private String method;
	private String defaultLanguage;
	private String defaultTree;
	private String defaultReminder;
	private IntellIVRServer ivrServer;
	private MessageStatusStore statusStore;
	protected Map<Long, IVRNotificationMapping> ivrNotificationMap;
	protected Map<String, List<GatewayRequest>> bundledGatewayRequests;
	protected Map<String, IVRSession> ivrSessions;
	private Timer timer;
	private long bundlingDelay;
	private int retryDelay;
	private int maxAttempts;
	private Resource mappingResource;
	private CoreManager coreManager;
	private RegistrarService registrarService;
	
	private Log log = LogFactory.getLog(IntellIVRBean.class);
	
	@SuppressWarnings("unused")
	private void init() {

		ivrNotificationMap = new HashMap<Long, IVRNotificationMapping>();
		
		try {
			
			File f = mappingResource.getFile();
			
			log.debug("Looking for Notification to IVR entity mappings in " + f.getName());
			
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			Pattern p = Pattern.compile("([0-9]+)=([IiRr]{1}),(.+)");
			Matcher m;
			
			String line = "";
			
			while ( (line = br.readLine()) != null ) {
				
				m = p.matcher(line);				
				
				if ( m.matches() ) {
				
					long mapID = Long.parseLong(m.group(1));
					String ivrType = m.group(2).toUpperCase();
					String ivrEntity = m.group(3);
					
					log.debug("Found IVR entity mapping: " + mapID + " => " + ivrType + "," + ivrEntity);
					
					IVRNotificationMapping i = new IVRNotificationMapping();
					i.setId(mapID);
					i.setType(ivrType);
					i.setIvrEntityName(ivrEntity);
					ivrNotificationMap.put(mapID, i);
					
				}
				
			}
			
		} catch (IOException e) {
			log.error("IOException creating IVR to Notification Map - default tree and message will be used"); 
		}
		
		timer = new Timer();
		
		bundledGatewayRequests = new HashMap<String, List<GatewayRequest>>();
		
		ivrSessions = new HashMap<String, IVRSession>();
		
	}
	
	public String getMessageStatus(GatewayResponse response) {
		log.debug("Received getMessagesStatus request for " + response);
		log.debug("Returning " + statusStore.getStatus(response.getGatewayMessageId()) + " for " + response);
		return statusStore.getStatus(response.getGatewayMessageId());
	}

	public MStatus mapMessageStatus(GatewayResponse response) {
		log.debug("Received mapMessageStatus for " + response);
		log.debug("Returning " + messageHandler.lookupStatus(response.getResponseText()) + " for " + response);
		//when called and the response status is RETRY, may need to remove or set to PENDING before returning value
		return messageHandler.lookupStatus(response.getResponseText());
	}

	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> sendMessage(GatewayRequest gatewayRequest,
			MotechContext context) {

		log.debug("Received GatewayRequest:" + gatewayRequest);

		initializeGatewayRequest(gatewayRequest);
		
		IVRServerTimerTask task = null;
		
		String recipientID = gatewayRequest
			.getMessageRequest()
			.getRecipientId();
		
		String phone = gatewayRequest
			.getRecipientsNumber();
		
		Language language = gatewayRequest
			.getMessageRequest().getLanguage();
		
		String status = StatusType.OK.value();
		if ( recipientID == null || gatewayRequest.getMessageRequest().getMessageType() == MessageType.TEXT ) {
			status = StatusType.ERROR.value();
		} else {

			if ( !gatewayRequest.getMessageRequest().getPhoneNumberType().equalsIgnoreCase("PUBLIC") ) {
			
				IVRSession session = new IVRSession(recipientID, phone, language.getName());
				session.addGatewayRequest(gatewayRequest);
				
				if ( !ivrSessions.containsKey(session.getSessionId()) ) {

					ivrSessions.put(session.getSessionId(), session);
					
					task = new IVRServerTimerTask(session);

				} else {
					ivrSessions
						.get(session.getSessionId())
						.addGatewayRequest(gatewayRequest);
				}
				
			}

		}
		
		Set<GatewayResponse> responses = messageHandler
			.parseMessageResponse(gatewayRequest, status, context);
		
		for ( GatewayResponse response : responses )
			statusStore.updateStatus(response.getGatewayMessageId(),
					response.getResponseText());
		
		if ( task != null && bundlingDelay >= 0 )
			timer.schedule(task, bundlingDelay);
		
		return responses;
	}

	private void initializeGatewayRequest(GatewayRequest request) {
		request.getRecipientsNumber();
		request.getMessageRequest().getLanguage().getName();
		request.getMessageRequest().getRecipientId();
		request.getMessageRequest().getNotificationType().getId();
	}

	public void sendPending(IVRSession session) {
		
		session.setAttempts(session.getAttempts() + 1);
		
		RequestType request = createIVRRequest(session);
		
		log.debug("Created IVR Request: " + request);
		
		ResponseType response = ivrServer.requestCall(request);

		log.debug("Recieved response from IVR Server: " + response);

		String status = response.getStatus() == StatusType.OK ? StatusType.OK.value() : response.getErrorCode().value();

		for (GatewayRequest gatewayRequest : session.getGatewayRequests())
			statusStore.updateStatus(gatewayRequest.getMessageRequest().getId().toString(), status);
		
		if ( response.getStatus() == StatusType.ERROR )
			ivrSessions.remove(session.getSessionId());
		
	}

	public RequestType createIVRRequest(List<GatewayRequest> gwRequests) {
		
		log.debug("Creating IVR Request for " + gwRequests);
		
		RequestType ivrRequest = new RequestType();
		
		/*
		 * These first three values are fixed
		 */
		ivrRequest.setApiId(apiID);
		ivrRequest.setMethod(method);
		ivrRequest.setReportUrl(reportURL);

		/*
		 * recipient's phone number
		 */
		ivrRequest.setCallee(gwRequests.get(0)
									   .getRecipientsNumber());
	
		/*
		 * Set language
		 */
		String language = gwRequests.get(0)
									.getMessageRequest()
									.getLanguage()
									.getName();
		ivrRequest.setLanguage(language != null ? language : defaultLanguage);

		/*
		 * Private id
		 */
		ivrRequest.setPrivate(gwRequests.get(0)
										.getMessageRequest()
										.getId()
										.toString());
		
		/*
		 * Create the content
		 */	
		List<String> reminderMessages = new ArrayList<String>();
		for (Iterator iterator = gwRequests.iterator(); iterator.hasNext();) {
			GatewayRequest gatewayRequest = (GatewayRequest) iterator.next();
			
			long notificationId = gatewayRequest.getMessageRequest().getNotificationType().getId();
			
			if ( !ivrNotificationMap.containsKey(notificationId) ) {
				log.debug("No IVR Notification mapping found for " + notificationId);
			} else {
				
				IVRNotificationMapping mapping = ivrNotificationMap.get(notificationId);
				
				if ( mapping.getType().equalsIgnoreCase(IVRNotificationMapping.INFORMATIONAL)) {
					ivrRequest.setTree(mapping.getIvrEntityName());
				} else {
					reminderMessages.add(mapping.getIvrEntityName());
				}
				
			} 
			
		}
		
		RequestType.Vxml vxml = new RequestType.Vxml();	
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		for (Iterator iterator = reminderMessages.iterator(); iterator.hasNext();) {
			String fileName = (String) iterator.next();
			AudioType audio = new AudioType();
			audio.setSrc(fileName);
			vxml.getPrompt()
				.getAudioOrBreak()
				.add(audio);
		}
		ivrRequest.setVxml(vxml);
		
		return ivrRequest;
		
	}

	public RequestType createIVRRequest(IVRSession session) {
		
		Collection<GatewayRequest> gwRequests = session.getGatewayRequests();
		
		log.debug("Creating IVR Request for " + gwRequests);
		
		RequestType ivrRequest = new RequestType();
		
		/*
		 * These first three values are fixed
		 */
		ivrRequest.setApiId(apiID);
		ivrRequest.setMethod(method);
		ivrRequest.setReportUrl(reportURL);

		/*
		 * recipient's phone number
		 */
		ivrRequest.setCallee(session.getPhone());
	
		/*
		 * Set language
		 */
		String language = session.getLanguage();
		ivrRequest.setLanguage(language != null ? language : defaultLanguage);

		/*
		 * Private id
		 */
		ivrRequest.setPrivate(session.getSessionId());
				
		/*
		 * Create the content
		 */	
		List<String> reminderMessages = new ArrayList<String>();
		for (Iterator iterator = gwRequests.iterator(); iterator.hasNext();) {
			GatewayRequest gatewayRequest = (GatewayRequest) iterator.next();
			
			long notificationId = gatewayRequest.getMessageRequest().getNotificationType().getId();
			
			if ( !ivrNotificationMap.containsKey(notificationId) ) {
				log.debug("No IVR Notification mapping found for " + notificationId);
			} else {
				
				IVRNotificationMapping mapping = ivrNotificationMap.get(notificationId);
				
				if ( mapping.getType().equalsIgnoreCase(IVRNotificationMapping.INFORMATIONAL)) {
					ivrRequest.setTree(mapping.getIvrEntityName());
				} else {
					reminderMessages.add(mapping.getIvrEntityName());
				}
				
			} 
			
		}
		
		RequestType.Vxml vxml = new RequestType.Vxml();	
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		for (Iterator iterator = reminderMessages.iterator(); iterator.hasNext();) {
			String fileName = (String) iterator.next();
			AudioType audio = new AudioType();
			audio.setSrc(fileName);
			vxml.getPrompt()
				.getAudioOrBreak()
				.add(audio);
		}
		ivrRequest.setVxml(vxml);
		
		return ivrRequest;
		
	}

	
	@SuppressWarnings("unchecked")
	public ResponseType handleRequest(GetIVRConfigRequest request) {

		ResponseType r = new ResponseType();
		String userId = request.getUserid();

		log.info("Received ivr config request for id " + userId);

		try {
			
			registrarService.getPatientEnrollments(Integer.parseInt(userId));

			MotechContext context = coreManager.createMotechContext();
			MessageRequestDAO<MessageRequest> mrDAO = coreManager.createMessageRequestDAO(context);

			List<MessageRequest> pendingMessageRequests = mrDAO.getMsgRequestByRecipientAndStatus(request.getUserid(), MStatus.PENDING);		

			if ( pendingMessageRequests.size() == 0 ) {
				log.debug("No pending messages found for " + request.getUserid());
				r.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
				r.setErrorString("Invalid user id");
				r.setStatus(StatusType.ERROR); 
			} else {

				log.debug("Found pending messages for " + request.getUserid() + ": " + pendingMessageRequests);

				List<GatewayRequest> gwRequestList = new ArrayList<GatewayRequest>();

				for (Iterator iterator = pendingMessageRequests.iterator(); iterator.hasNext();) {

					MessageRequest messageRequest = (MessageRequest) iterator.next();

					GatewayRequest gwr = new GatewayRequestImpl();
					gwr.setMessageRequest(messageRequest);

					gwRequestList.add(gwr);

					statusStore.updateStatus(messageRequest.getId().toString(), StatusType.OK.value());

				}

				/*
				 * ResponseType fields are a subset of the RequestType fields
				 * Can create a RequestType based on this criteria and use
				 * only the fields that are needed to create the ResponseType
				 */
				RequestType requestType = createIVRRequest(gwRequestList);

				r.setLanguage(requestType.getLanguage());
				r.setPrivate(requestType.getPrivate());
				r.setReportUrl(requestType.getReportUrl());
				r.setStatus(StatusType.OK);
				r.setTree(requestType.getTree());
				r.setVxml(requestType.getVxml());

				bundledGatewayRequests.put(requestType.getPrivate(), gwRequestList);

			}

		} catch (NumberFormatException e) {
			log.error("Invalid user id: id must be numeric");
			r.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
			r.setErrorString("Invalid user id: id must be numeric");
			r.setStatus(StatusType.ERROR);
		} catch (ValidationException e) {
			log.error("Invalid user id: no such id '" + userId + "' on server");
			r.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
			r.setErrorString("Invalid user id: no such id '" + userId + "' on server");
			r.setStatus(StatusType.ERROR);
		}

		return r;
	}

	public ResponseType handleReport(ReportType report) {
		log.info("Received call report: " + report.toString());
		
		String sessionId = report.getPrivate();
		
		if ( sessionId == null )
			log.error("Unable to identify call in report: " + report.toString());
		else {
			IVRSession session = ivrSessions.get(sessionId);
			if ( session == null ) {
				log.error("Unable to find IVRSession for " + sessionId);
			} else {
				
				String status = report.getStatus().value();
				
				/*
				 * Retry if necessary
				 */
				if ( report.getStatus() == ReportStatusType.COMPLETED ) {
					ivrSessions.remove(sessionId);
				} else {
					
					if ( session.getAttempts() < this.maxAttempts ) {
						if ( retryDelay >=  0 ) {
							log.info("Retrying IVRSession " + session.getSessionId());
							IVRServerTimerTask task = new IVRServerTimerTask(ivrSessions.get(sessionId));
							timer.schedule(task, 1000 * 60 * retryDelay);
						}
					} else {
						ivrSessions.remove(sessionId);
						status = "MAXATTEMPTS";
					}
					
				}
				
				/*
				 * Update message status
				 */
				Collection<GatewayRequest> requests = session.getGatewayRequests();
				for (GatewayRequest gatewayRequest : requests) {

					log.debug("Updating Message Request " 
							+ gatewayRequest.getMessageRequest().getId().toString() 
							+ " to " + report.getStatus().value());
					statusStore.updateStatus(gatewayRequest
												.getMessageRequest()
												.getId()
												.toString()
												, status);

				}
				
			}
			
		}
			
		ResponseType r = new ResponseType();
		r.setStatus(StatusType.OK);
		return r;
	}
	

	public void setMessageHandler(GatewayMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public GatewayMessageHandler getMessageHandler() {
		return messageHandler;
	}

	public String getReportURL() {
		return reportURL;
	}

	public void setReportURL(String reportURL) {
		this.reportURL = reportURL;
	}

	public String getApiID() {
		return apiID;
	}

	public void setApiID(String apiID) {
		this.apiID = apiID;
	}

	public IntellIVRServer getIvrServer() {
		return ivrServer;
	}

	public void setIvrServer(IntellIVRServer ivrServer) {
		this.ivrServer = ivrServer;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getDefaultTree() {
		return defaultTree;
	}

	public void setDefaultTree(String defaultTree) {
		this.defaultTree = defaultTree;
	}

	public String getDefaultReminder() {
		return defaultReminder;
	}

	public void setDefaultReminder(String defaultReminder) {
		this.defaultReminder = defaultReminder;
	}

	public MessageStatusStore getStatusStore() {
		return statusStore;
	}

	public void setStatusStore(MessageStatusStore statusStore) {
		this.statusStore = statusStore;
	}
	
	public long getBundlingDelay() {
		return bundlingDelay;
	}

	public void setBundlingDelay(long bundlingDelay) {
		this.bundlingDelay = bundlingDelay;
	}

	public int getRetryDelay() {
		return retryDelay;
	}

	public void setRetryDelay(int retryDelay) {
		this.retryDelay = retryDelay;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public Resource getMappingResource() {
		return mappingResource;
	}

	public void setMappingResource(Resource mappingsFile) {
		this.mappingResource = mappingsFile;
	}

	public CoreManager getCoreManager() {
		return coreManager;
	}

	public void setCoreManager(CoreManager coreManager) {
		this.coreManager = coreManager;
	}

	public RegistrarService getRegistrarService() {
		return registrarService;
	}

	public void setRegistrarService(RegistrarService registrarService) {
		this.registrarService = registrarService;
	}

	protected class IVRServerTimerTask extends TimerTask {

		private IVRSession session;
		private Log log = LogFactory.getLog(IVRServerTimerTask.class);
		
		protected IVRServerTimerTask(IVRSession session) {
			this.session = session;
		}
		
		@Override
		public void run() {

			log.debug("IVR Server timer task expired for session " + session.getSessionId());
			
			sendPending(session);
			
		}
		
	}
	
}
