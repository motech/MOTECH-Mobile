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

public class IntellIVRBean implements GatewayManager, GetIVRConfigRequestHandler, ReportHandler {
	
	private GatewayMessageHandler messageHandler;
	protected String reportURL;
	private String apiID;
	private String serverURL;
	
	private Log log = LogFactory.getLog(IntellIVRBean.class);
	
	public String getMessageStatus(GatewayResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public MStatus mapMessageStatus(GatewayResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails,
			MotechContext context) {
		// TODO Auto-generated method stub
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
		log.info("Received call report: " + 
				report.getCallee() + "," + 
				report.getPrivate() + "," +
				report.getStatus());
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

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
	
}
