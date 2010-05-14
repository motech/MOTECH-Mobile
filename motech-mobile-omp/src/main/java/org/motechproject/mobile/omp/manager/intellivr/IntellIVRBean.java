package org.motechproject.mobile.omp.manager.intellivr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	private Timer timer;
	private long bundlingDelay;
	private int retryDelay;
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
		
		String status = StatusType.OK.value();
		if ( recipientID == null || gatewayRequest.getMessageRequest().getMessageType() == MessageType.TEXT ) {
			status = StatusType.ERROR.value();
		} else {

			if ( !gatewayRequest.getMessageRequest().getPhoneNumberType().equalsIgnoreCase("PUBLIC") ) {
			
				if ( !bundledGatewayRequests.containsKey(recipientID) ) {

					List<GatewayRequest> newList = new ArrayList<GatewayRequest>();
					newList.add(gatewayRequest);

					bundledGatewayRequests.put(recipientID, newList);

					task = new IVRServerTimerTask(recipientID);

				} else {
					bundledGatewayRequests
					.get(recipientID)
					.add(gatewayRequest);
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
	
	public void sendPending(String recipientID) {
		
		log.debug("Initiating call request for recipient id " + recipientID);			
		
		List<GatewayRequest> pendingRequests = bundledGatewayRequests.get(recipientID);
		
		if ( pendingRequests != null ) {

			bundledGatewayRequests.remove(recipientID);

			RequestType request = createIVRRequest(pendingRequests);

			log.debug("Created IVR Request: " + request);

			ResponseType response = ivrServer.requestCall(request);

			log.debug("Recieved response from IVR Server: " + response);

			String status = response.getStatus() == StatusType.OK ? StatusType.OK.value() : response.getErrorCode().value();

			for (Iterator iterator = pendingRequests.iterator(); iterator.hasNext();) {
				GatewayRequest gatewayRequest = (GatewayRequest) iterator.next();
				statusStore.updateStatus(gatewayRequest.getMessageRequest().getId().toString(), status);
			}

			if ( response.getStatus() == StatusType.OK )
				bundledGatewayRequests.put(request.getPrivate(), pendingRequests);

		}
			
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
		
		String bundleID = report.getPrivate();
		
		if ( bundleID == null )
			log.error("Unable to identify call in report: " + report.toString());
		else {
			if ( !bundledGatewayRequests.containsKey(bundleID)) {
				log.error("Unable to find GatewayRequests for " + bundleID);
			} else {
				List<GatewayRequest> requests = bundledGatewayRequests.get(bundleID);
				for (Iterator iterator = requests.iterator(); iterator.hasNext();) {
					GatewayRequest gatewayRequest = (GatewayRequest) iterator.next();
					/*
					 * if it is not complete set status to OK which will leave the status as PENDING
					 */
					String newStatus = report.getStatus() == ReportStatusType.COMPLETED ? report.getStatus().value() : "OK";
					log.debug("Updating Message Request " + gatewayRequest.getMessageRequest().getId().toString() + " to " + newStatus);
					statusStore.updateStatus(gatewayRequest.getMessageRequest().getId().toString(), newStatus);
				}
				
				/*
				 * if not complete then retry
				 */
				if ( requests.size() > 0  && report.getStatus() != ReportStatusType.COMPLETED ) {
					String recipientId = requests.get(0).getMessageRequest().getRecipientId();
					bundledGatewayRequests.put(recipientId, requests);
					IVRServerTimerTask task = new IVRServerTimerTask(recipientId);
					if ( retryDelay > -1 )
						timer.schedule(task, 1000 * 60 * retryDelay);
				}
				
				bundledGatewayRequests.remove(bundleID);
				
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

		private String recipientID;
		private Log log = LogFactory.getLog(IVRServerTimerTask.class);
		
		protected IVRServerTimerTask(String recipientID) {
			this.recipientID = recipientID;
		}
		
		@Override
		public void run() {

			log.debug("IVR Server timer task expired for recipient id " + recipientID);
			
			sendPending(recipientID);
			
		}
		
	}
	
}
