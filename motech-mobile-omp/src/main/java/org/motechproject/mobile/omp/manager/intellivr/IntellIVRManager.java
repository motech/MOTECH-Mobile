package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Set;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;

public class IntellIVRManager implements GatewayManager {

	private GatewayMessageHandler messageHandler;
	
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

	public GatewayMessageHandler getMessageHandler() {
		return messageHandler;
	}

	public void setMessageHandler(GatewayMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

}
