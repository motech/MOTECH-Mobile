package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Set;

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
	
	private Log log = LogFactory.getLog(IntellIVRBean.class);
	
	public String getMessageStatus(GatewayResponse response) {
		return statusStore.getStatus(response.getGatewayMessageId());
	}

	public MStatus mapMessageStatus(GatewayResponse response) {
		return messageHandler.lookupStatus(response.getResponseText());
	}

	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails,
			MotechContext context) {

		RequestType r = new RequestType();
		r.setApiId(this.apiID);
		r.setCallee(messageDetails.getRecipientsNumber());
		r.setMethod(this.method);
		r.setLanguage(this.defaultLanguage);
		r.setPrivate(messageDetails.getRequestId());
		r.setReportUrl(this.reportURL);
		r.setTree(this.defaultTree);
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType audio = new AudioType();
		audio.setSrc(this.defaultReminder);
		vxml.getPrompt().getAudioOrBreak().add(audio);
		r.setVxml(vxml);
		
		ResponseType response = ivrServer.requestCall(r);
		
		log.info("Reponse: " + response.toString());
		
		return null;
	}

	public void setMessageHandler(GatewayMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public GatewayMessageHandler getMessageHandler() {
		return messageHandler;
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
		ResponseType r = new ResponseType();
		r.setStatus(StatusType.OK);
		return r;
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
