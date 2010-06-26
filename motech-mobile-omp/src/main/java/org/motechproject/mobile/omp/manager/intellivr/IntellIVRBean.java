package org.motechproject.mobile.omp.manager.intellivr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Transaction;
import org.motechproject.mobile.core.dao.GatewayRequestDAO;
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
	protected Map<String, Long> ivrReminderIds;
	protected Map<String, IVRSession> ivrSessions;
	private Timer timer;
	private long bundlingDelay;
	private int retryDelay;
	private int maxAttempts;
	private int maxDays;
	private int callCompletedThreshold;
	private boolean accelerateRetries;
	private String noPendingMessagesRecordingName;
	private Resource mappingResource;
	private CoreManager coreManager;
	private RegistrarService registrarService;
	
	private Log log = LogFactory.getLog(IntellIVRBean.class);
	private Log reportLog = LogFactory.getLog(IntellIVRBean.class.getName() + ".reportlog");
	private Log callLog = LogFactory.getLog(IntellIVRBean.class.getName() + ".calllog");
	
	@SuppressWarnings("unused")
	private void init() {

		ivrNotificationMap = new HashMap<Long, IVRNotificationMapping>();
		ivrReminderIds = new HashMap<String, Long>();
		
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
					
					ivrReminderIds.put(ivrEntity, mapID);
					
				}
				
			}
			
		} catch (IOException e) {
			log.error("IOException creating IVR to Notification Map - default tree and message will be used"); 
		}
		
		timer = new Timer();
		
		ivrSessions = new HashMap<String, IVRSession>();

		if ( accelerateRetries ) {
			log.warn("Using accelerated retries.  Configured retry intervals will be ignored.");
			retryDelay = 1;
		}
		
	}
	
	public String getMessageStatus(GatewayResponse response) {
		log.debug("Returning " + statusStore.getStatus(response.getGatewayMessageId()) + " for " + response.getId());
		return statusStore.getStatus(response.getGatewayMessageId());
	}

	public MStatus mapMessageStatus(GatewayResponse response) {
		log.debug("Returning " + messageHandler.lookupStatus(response.getResponseText()) + " for " + response.getId());
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

			if ( gatewayRequest.getMessageRequest().getDateFrom() == null )
				gatewayRequest.getMessageRequest().setDateFrom(new Date());
			
			if ( gatewayRequest.getMessageRequest().getDateTo() == null ) {
				
				GregorianCalendar endTime = new GregorianCalendar();
				endTime.setTime(gatewayRequest.getMessageRequest().getDateFrom());
				endTime.add(GregorianCalendar.DAY_OF_MONTH, maxDays);
				
				gatewayRequest.getMessageRequest().setDateTo(endTime.getTime());
				
			}
			
			String phoneType = gatewayRequest.getMessageRequest().getPhoneNumberType();
			
			if ( phoneType.equalsIgnoreCase("PERSONAL") || phoneType.equalsIgnoreCase("HOUSEHOLD") ) {
				
				IVRSession session = new IVRSession(recipientID, 
						phone, 
						language.getName(),
						gatewayRequest.getMessageRequest().getDaysAttempted());
				session.addGatewayRequest(gatewayRequest);

				if ( !ivrSessions.containsKey(session.getSessionId()) ) {
					log.debug("Using new IVR Session " + session.getSessionId() +
							" for request " + gatewayRequest.getId());
					ivrSessions.put(session.getSessionId(), session);

					task = new IVRServerTimerTask(session);

				} else {
					log.debug("Using existing IVR Session " + session.getSessionId() +
							" for request " + gatewayRequest.getId());
					ivrSessions
					.get(session.getSessionId())
					.addGatewayRequest(gatewayRequest);
				}
				
			} else {
				log.debug("GatewayRequest " + gatewayRequest.getId() + " has phone type " +
						gatewayRequest.getMessageRequest().getPhoneNumberType() + 
						".  Call will not be made and message will remain pending.");
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
		request.getDateFrom();
		request.getDateTo();
		request.getMessageRequest().getDateFrom();
		request.getMessageRequest().getDateTo();
		request.getMessageRequest().getLanguage().getName();
		request.getMessageRequest().getRecipientId();
		request.getMessageRequest().getNotificationType().getId();
		request.getMessageRequest().getDaysAttempted();
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
		
		callLog.info("OUT," +
					 session.getPhone() + "," +
					 session.getUserId() + "," +
					 status);
		
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
		GatewayRequest infoRequest = null;
		List<String> reminderMessages = new ArrayList<String>();
		for (GatewayRequest gatewayRequest : gwRequests) {
			
			long notificationId = gatewayRequest.getMessageRequest().getNotificationType().getId();
			
			if ( !ivrNotificationMap.containsKey(notificationId) ) {
				log.debug("No IVR Notification mapping found for " + notificationId);
			} else {
				
				IVRNotificationMapping mapping = ivrNotificationMap.get(notificationId);
				
				if ( mapping.getType().equalsIgnoreCase(IVRNotificationMapping.INFORMATIONAL)) {
					if ( infoRequest == null )
						infoRequest = gatewayRequest;
					else {
						GregorianCalendar currDateFrom = new GregorianCalendar();
						currDateFrom.setTime(infoRequest.getMessageRequest().getDateFrom());
						GregorianCalendar possibleDateFrom = new GregorianCalendar();
						possibleDateFrom.setTime(gatewayRequest.getMessageRequest().getDateFrom());
						if ( currDateFrom.before(possibleDateFrom) )
							infoRequest = gatewayRequest;
					}
						
				} else {
					reminderMessages.add(mapping.getIvrEntityName());
				}
				
			} 
			
		}

		if ( infoRequest != null ) {
			IVRNotificationMapping infoMapping = ivrNotificationMap
			.get(infoRequest
					.getMessageRequest()
					.getNotificationType()
					.getId());
			ivrRequest.setTree(infoMapping.getIvrEntityName());
		}
		
		RequestType.Vxml vxml = new RequestType.Vxml();	
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		for (String fileName : reminderMessages) {
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

			String[] enrollments = registrarService.getPatientEnrollments(Integer.parseInt(userId));

			if ( enrollments == null || enrollments.length == 0 ) {
				callLog.info("IN,," + request.getUserid() + ",UNENROLLED");
				r.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
				r.setErrorString("Unenrolled user");
				r.setStatus(StatusType.ERROR);
			} else {

				MotechContext context = coreManager.createMotechContext();
				MessageRequestDAO<MessageRequest> mrDAO = coreManager.createMessageRequestDAO(context);

				List<MessageRequest> pendingMessageRequests = mrDAO.getMsgRequestByRecipientAndSchedule(request.getUserid(), new Date());		

				if ( pendingMessageRequests.size() == 0 ) {
					log.debug("No pending messages found for " + request.getUserid());
					callLog.info("IN,," + request.getUserid() + ",NO_PENDING");
					r.setStatus(StatusType.OK);
					RequestType.Vxml vxml = new RequestType.Vxml();
					vxml.setPrompt(new RequestType.Vxml.Prompt());
					AudioType a = new AudioType();
					a.setSrc(noPendingMessagesRecordingName);
					vxml.getPrompt().getAudioOrBreak().add(a);
					r.setVxml(vxml);
				} else {

					log.debug("Found pending messages for " + request.getUserid() + ": " + pendingMessageRequests);

					IVRSession session = new IVRSession(userId);

					for (MessageRequest messageRequest : pendingMessageRequests ) {

						GatewayRequest gwr = new GatewayRequestImpl();
						gwr.setMessageRequest(messageRequest);

						session.addGatewayRequest(gwr);

						statusStore.updateStatus(messageRequest.getId().toString(), StatusType.OK.value());

					}

					/*
					 * ResponseType fields are a subset of the RequestType fields
					 * Can create a RequestType based on this criteria and use
					 * only the fields that are needed to create the ResponseType
					 */
					RequestType requestType = createIVRRequest(session);

					r.setPrivate(requestType.getPrivate());
					r.setReportUrl(requestType.getReportUrl());
					r.setStatus(StatusType.OK);
					r.setTree(requestType.getTree());
					r.setVxml(requestType.getVxml());

					ivrSessions.put(session.getSessionId(), session);

					callLog.info("IN,," + request.getUserid() + "," + StatusType.OK.value());

				}

			}

		} catch (NumberFormatException e) {
			log.error("Invalid user id: id must be numeric");
			callLog.info("IN,," + request.getUserid() + "," + ErrorCodeType.MOTECH_INVALID_USER_ID.name());
			r.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
			r.setErrorString("Invalid user id: id must be numeric");
			r.setStatus(StatusType.ERROR);
		} catch (ValidationException e) {
			log.error("Invalid user id: no such id '" + userId + "' on server");
			callLog.info("IN,," + request.getUserid() + "," + ErrorCodeType.MOTECH_INVALID_USER_ID.name());
			r.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
			r.setErrorString("Invalid user id: no such id '" + userId + "' on server");
			r.setStatus(StatusType.ERROR);
		}

		return r;
	}

	@SuppressWarnings("unchecked")
	public ResponseType handleReport(ReportType report) {
		log.info("Received call report: " + report.toString());
		
		List<String> messages = formatReportLogMessages(report);
		for ( String message : messages )
			reportLog.info(message);
		
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
				if ( report.getStatus() == ReportStatusType.COMPLETED && callExceedsThreshold(report) ) {
					ivrSessions.remove(sessionId);
				} else {
					
					if ( session.isUserInitiated() ) {
						status = null;
						ivrSessions.remove(sessionId);
					} else { 
						
						if ( report.getStatus() == ReportStatusType.COMPLETED )
							status = "BELOWTHRESHOLD";

						if ( session.getAttempts() < this.maxAttempts ) {
							if ( retryDelay >=  0 ) {
								log.info("Retrying IVRSession " + session.getSessionId() + " in " + retryDelay + " minutes. (" + session.getAttempts() + " of " + maxAttempts + ")");
								IVRServerTimerTask task = new IVRServerTimerTask(ivrSessions.get(sessionId));
								timer.schedule(task, 1000 * 60 * retryDelay);
							}
						} else {

							//all attempts for this day have been exhausted - increment days attempted
							session.setDays(session.getDays() + 1);

							MotechContext context = coreManager.createMotechContext();

							if ( session.getDays() < this.maxDays ) {

								GatewayRequestDAO<GatewayRequest> gwReqDAO = coreManager.createGatewayRequestDAO(context);

								for ( GatewayRequest gatewayRequest : session.getGatewayRequests() ) {

									GatewayRequest gatewayRequestDB = gwReqDAO.getById(gatewayRequest.getId());

									Date dateFrom = gatewayRequestDB.getDateFrom();
									Date dateTo = gatewayRequestDB.getDateTo();

									GregorianCalendar newDateFrom = new GregorianCalendar();
									newDateFrom.setTime(dateFrom);
									if ( accelerateRetries )
										newDateFrom.add(GregorianCalendar.MINUTE, 5);
									else
										newDateFrom.add(GregorianCalendar.DAY_OF_MONTH, 1);

									GregorianCalendar newDateTo = new GregorianCalendar();
									if ( dateTo == null ) {
										newDateTo.setTime(dateFrom);
										newDateTo.add(GregorianCalendar.DAY_OF_MONTH, maxDays);
									} else 
										newDateTo.setTime(dateTo);
									
									if ( accelerateRetries )
										newDateTo.add(GregorianCalendar.MINUTE, 5);
									else
										newDateTo.add(GregorianCalendar.DAY_OF_MONTH, 1);


									gatewayRequestDB.setDateFrom(newDateFrom.getTime());
									gatewayRequestDB.setDateTo(newDateTo.getTime());
									gatewayRequestDB.setMessageStatus(MStatus.SCHEDULED);

									Transaction tx = (Transaction) gwReqDAO.getDBSession().getTransaction();
									tx.begin();
									gwReqDAO.save(gatewayRequestDB);
									tx.commit();

								}

							} else {
								status = "MAXATTEMPTS";	
							}
							ivrSessions.remove(sessionId);

							/*
							 * update days attempted in the database
							 */
							MessageRequestDAO<MessageRequest> messageRequestDAO = coreManager.createMessageRequestDAO(context);

							for ( GatewayRequest gatewayRequest : session.getGatewayRequests() ) {

								MessageRequest msgReqDB = messageRequestDAO.getById(gatewayRequest.getMessageRequest().getId());

								msgReqDB.setDaysAttempted(session.getDays());

								Transaction tx = (Transaction) messageRequestDAO.getDBSession().getTransaction();
								tx.begin();
								messageRequestDAO.save(msgReqDB);
								tx.commit();

							}

						}
						
					}
					
				}
				
				/*
				 * Update message status
				 */
				if ( status != null ) {
					Collection<GatewayRequest> requests = session.getGatewayRequests();
					for (GatewayRequest gatewayRequest : requests) {

						log.debug("Updating Message Request " 
								+ gatewayRequest.getMessageRequest().getId().toString() 
								+ " to " + status);
						statusStore.updateStatus(gatewayRequest
								.getMessageRequest()
								.getId()
								.toString()
								, status);

					}
					
				}

			}
			
		}
			
		ResponseType r = new ResponseType();
		r.setStatus(StatusType.OK);
		return r;
	}
	
	private List<String> formatReportLogMessages(ReportType report) {
		
		List<String> result = new ArrayList<String>();
		
		StringBuilder common = new StringBuilder();
		common.append(report.getCallee());
		common.append("," + report.getDuration());
		common.append("," + report.getINTELLIVREntryCount());
		common.append("," + report.getPrivate());
		common.append("," + report.getConnectTime());
		common.append("," + report.getDisconnectTime());
		common.append("," + report.getStatus().value());
		
		for ( IvrEntryType entry : report.getINTELLIVREntry() ) {
			
			StringBuilder message = new StringBuilder();
			message.append(common.toString());
			message.append("," + entry.getFile());
			message.append("," + entry.getKeypress());
			message.append("," + entry.getMenu());
			message.append("," + entry.getDuration());
			message.append("," + entry.getEntrytime());
			
			result.add(message.toString());
			
		}
		
		return result;
	}
	
	private boolean callExceedsThreshold(ReportType report) {
		
		int effectiveCallTime = 0;
		int reminderCount = 0;
		boolean shouldHaveInformationalMessage = false;
		IVRSession session = ivrSessions.get(report.getPrivate());

		for ( GatewayRequest request : session.getGatewayRequests() ) {
			long notificationId = request.getMessageRequest().getNotificationType().getId();
			if ( ivrNotificationMap.containsKey(notificationId) )
				if ( ivrNotificationMap.get(notificationId).getType().equalsIgnoreCase(IVRNotificationMapping.INFORMATIONAL) ) 
					shouldHaveInformationalMessage = true;
		}
		
		IvrEntryType firstInfoEntry = null;
		
		List<IvrEntryType> entries = report.getINTELLIVREntry();
		
		for (IvrEntryType entry : entries)
			if ( ivrReminderIds.containsKey(entry.getMenu()) || entry.getMenu().equalsIgnoreCase("audio") )
				reminderCount++;
			else 
				if ( firstInfoEntry == null && (!session.isUserInitiated() || reminderCount > 0) )
					firstInfoEntry = entry;
		
		if ( firstInfoEntry == null )
			if ( shouldHaveInformationalMessage )
				effectiveCallTime = 0;
			else 
				if ( reminderCount > 0 )
					effectiveCallTime = callCompletedThreshold;
				else 
					effectiveCallTime = report.getDuration();
		else
			effectiveCallTime = firstInfoEntry.getDuration();
		
		return effectiveCallTime >= callCompletedThreshold;
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

	public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	public int getCallCompletedThreshold() {
		return callCompletedThreshold;
	}

	public void setCallCompletedThreshold(int callCompletedThreshold) {
		this.callCompletedThreshold = callCompletedThreshold;
	}

	public boolean isAccelerateRetries() {
		return accelerateRetries;
	}

	public void setAccelerateRetries(boolean accelerateRetries) {
		this.accelerateRetries = accelerateRetries;
	}

	public String getNoPendingMessagesRecordingName() {
		return noPendingMessagesRecordingName;
	}

	public void setNoPendingMessagesRecordingName(
			String noPendingMessagesRecordingName) {
		this.noPendingMessagesRecordingName = noPendingMessagesRecordingName;
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
