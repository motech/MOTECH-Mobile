package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Set;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;

public class IntellIVRGatewayMessageHandler implements GatewayMessageHandler {

	public CoreManager getCoreManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public MStatus lookupResponse(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public MStatus lookupStatus(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> parseMessageResponse(GatewayRequest message,
			String gatewayResponse, MotechContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	public MStatus parseMessageStatus(String messageStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCoreManager(CoreManager coreManager) {
		// TODO Auto-generated method stub

	}

}
