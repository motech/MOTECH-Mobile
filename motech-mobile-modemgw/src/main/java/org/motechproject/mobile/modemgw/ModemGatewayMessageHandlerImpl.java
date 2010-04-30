package org.motechproject.mobile.modemgw;

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

public class ModemGatewayMessageHandlerImpl implements GatewayMessageHandler {

	CoreManager coreManager;

	MStatus defaultStatus = MStatus.PENDING;
	MStatus defaultResponse = MStatus.SCHEDULED;
	Map<String, MStatus> responseMap;
	Map<String, MStatus> statusMap;

	public CoreManager getCoreManager() {
		return coreManager;
	}

	public void setCoreManager(CoreManager coreManager) {
		this.coreManager = coreManager;
	}

	public void setResponseMap(Map<String, MStatus> responseMap) {
		this.responseMap = responseMap;
	}

	public MStatus lookupResponse(String responseCode) {
		MStatus responseStatus = responseMap.get(responseCode);
		return responseStatus != null ? responseStatus : defaultResponse;
	}

	public void setStatusMap(Map<String, MStatus> statusMap) {
		this.statusMap = statusMap;
	}

	public MStatus lookupStatus(String statusCode) {
		MStatus status = responseMap.get(statusCode);
		return status != null ? status : defaultStatus;
	}

	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> parseMessageResponse(GatewayRequest msg,
			String gatewayResponse, MotechContext context) {

		Set<GatewayResponse> responses = new HashSet<GatewayResponse>();

		GatewayResponse response = coreManager.createGatewayResponse(context);

		// Use the gateway request id as gateway message id
		response.setGatewayMessageId(msg.getRequestId());
		response.setGatewayRequest(msg);
		response.setRecipientNumber(msg.getRecipientsNumber());
		response.setRequestId(msg.getRequestId());
		response.setResponseText(gatewayResponse);
		response.setMessageStatus(lookupStatus(gatewayResponse));
		response.setDateCreated(new Date());

		responses.add(response);

		return responses;
	}

	public MStatus parseMessageStatus(String gatewayResponse) {
		return lookupStatus(gatewayResponse);
	}

}
