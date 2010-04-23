package org.motechproject.mobile.omp.manager.intellivr;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import org.motechproject.mobile.omp.manager.utils.MessageStatusStore;

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
	private Map<String, String> reminderFileNames;
	private Map<String, String> treeNames;
	
	private Log log = LogFactory.getLog(IntellIVRBean.class);
	
	private void init() {

		/*
		 * Temporary hack.  It will be populated by a config file. 
		 */
		reminderFileNames = new HashMap<String, String>();
		reminderFileNames.put("test1", "test1.wav");
		reminderFileNames.put("test2", "test2.wav");
		reminderFileNames.put("IDconfirmation", "IDconfirmation.wav");
	
		/*
		 * Temporary hack.  It will be populated by a config file.
		 */
		treeNames = new HashMap<String, String>();
		treeNames.put("1", "api_test");
		
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
		
		RequestType ivrRequest = createIVRRequest(gatewayRequest);
		
		log.debug("Sending IVR request: " + ivrRequest);
		
		ResponseType ivrResponse = ivrServer.requestCall(ivrRequest);
		
		log.info("Received IVR response: " + ivrResponse.toString());
		
		String responseCode = ivrResponse.getStatus() == StatusType.OK ? StatusType.OK.value() : ivrResponse.getErrorCode().value();
		
		Set<GatewayResponse> responses = messageHandler
			.parseMessageResponse(gatewayRequest, responseCode, context);
		
		for ( GatewayResponse response : responses )
			statusStore.updateStatus(response.getGatewayMessageId(),
					response.getResponseText());
		
		return responses;
	}

	public ResponseType handleRequest(GetIVRConfigRequest request) {
		log.info("Received request for id " + request.getUserid());
		ResponseType r = new ResponseType();
		r.setStatus(StatusType.ERROR);
		r.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
		r.setErrorString("Invalid user id");
		return r;
	}

	public ResponseType handleReport(ReportType report) {
		log.info("Received call report: " + report.toString());
		
		if ( report.getPrivate() == null )
			log.error("Unable to identify call in report: " + report.toString());
		else 
			statusStore.updateStatus(report.getPrivate(), report.getStatus().value());
		
		ResponseType r = new ResponseType();
		r.setStatus(StatusType.OK);
		return r;
	}
	
	private RequestType createIVRRequest(GatewayRequest gwRequest) {

		RequestType ivrRequest = new RequestType();
		
		/*
		 * These first three values are fixed
		 */
		ivrRequest.setApiId(this.apiID);
		ivrRequest.setMethod(this.method);
		ivrRequest.setReportUrl(this.reportURL);

		/*
		 * recipient's phone number
		 */
		ivrRequest.setCallee(gwRequest.getRecipientsNumber());
	
		/*
		 * Internal ID - will be returned by IVR system with status reports
		 */
		ivrRequest.setPrivate(gwRequest.getRequestId());

		/*
		 * Reminder messages, week number, and language are parsed from the message
		 */
		String message = gwRequest.getMessage();

		Pattern p = Pattern.compile("([0-9a-zA-Z\\-\\,\\.]+)\\:([0-9a-zA-Z]+)\\:([a-zA-Z]+)");
		Matcher m = p.matcher(message);
		if ( !m.matches() ) {
			log.error("Invalid message format received.  Will use defaults.  Message was '" + message + "'.");
			ivrRequest.setLanguage(this.defaultLanguage);
			ivrRequest.setTree(this.defaultTree);
			RequestType.Vxml vxml = new RequestType.Vxml();
			vxml.setPrompt(new RequestType.Vxml.Prompt());
			AudioType audio = new AudioType();
			audio.setSrc(this.defaultReminder);
			vxml.getPrompt().getAudioOrBreak().add(audio);
			ivrRequest.setVxml(vxml);
		} else {
			ivrRequest.setLanguage(m.group(3));
			ivrRequest.setTree(treeNames.get(m.group(2)));
			RequestType.Vxml vxml = new RequestType.Vxml();
			vxml.setPrompt(new RequestType.Vxml.Prompt());
			StringTokenizer tok = new StringTokenizer(m.group(1),",");
			while ( tok.hasMoreTokens() ) {
				AudioType audio = new AudioType();
				audio.setSrc(reminderFileNames.get(tok.nextToken()));
				vxml.getPrompt().getAudioOrBreak().add(audio);
			}
			ivrRequest.setVxml(vxml);			
		}
		
		return ivrRequest;
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
	
}
