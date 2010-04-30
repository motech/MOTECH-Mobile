package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;

public class IntellIVRGatewayMessageHandler implements GatewayMessageHandler {

	MStatus defaultStatus = MStatus.PENDING;
	MStatus defaultResponse = MStatus.SCHEDULED;
	private Map<String, MStatus> statusMap;
	private Map<String, MStatus> responseMap;
	private CoreManager coreManager;
	
	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> parseMessageResponse(GatewayRequest gatewayRequest,
			String statusMessage, MotechContext context) {

		Set<GatewayResponse> responses = new HashSet<GatewayResponse>();
		
		GatewayResponse gwResponse = coreManager.createGatewayResponse(context);
		
		gwResponse.setGatewayRequest(gatewayRequest);
		gwResponse.setGatewayMessageId(gatewayRequest.getMessageRequest().getId().toString());
		gwResponse.setRecipientNumber(gatewayRequest.getRecipientsNumber());
		gwResponse.setRequestId(gatewayRequest.getRequestId());
		gwResponse.setResponseText(statusMessage);
		gwResponse.setMessageStatus(lookupStatus(statusMessage));
		gwResponse.setDateCreated(new Date());
		
		responses.add(gwResponse);
		
		return responses;
	}

	public MStatus parseMessageStatus(String messageStatus) {
		return lookupStatus(messageStatus);
	}

	public MStatus lookupResponse(String code) {
		MStatus responseStatus = responseMap.get(code);
		return responseStatus != null ? responseStatus : defaultResponse;
	}

	public MStatus lookupStatus(String code) {
		MStatus status = responseMap.get(code);
		return status != null ? status : defaultStatus;
	}
	
	public CoreManager getCoreManager() {
		return coreManager;
	}

	public void setCoreManager(CoreManager coreManager) {
		this.coreManager = coreManager;
	}
	
	public Map<String, MStatus> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, MStatus> statusMap) {
		this.statusMap = statusMap;
	}

	public Map<String, MStatus> getResponseMap() {
		return responseMap;
	}

	public void setResponseMap(Map<String, MStatus> responseMap) {
		this.responseMap = responseMap;
	}

}
