package org.motechproject.mobile.omp.manager;

import java.util.HashSet;
import java.util.Set;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.service.MotechContext;

public class CompositeGatewayManager implements GatewayManager {
	
	private GatewayManager voiceGatewayManager;
	private GatewayManager textGatewayManager;
	
	private CompositeGatewayMessageHandler compositeMessageHandler;
	
	public String getMessageStatus(GatewayResponse response) {

		if ( response
				.getGatewayRequest()
				.getMessageRequest()
				.getMessageType() == MessageType.VOICE ) {
			return voiceGatewayManager.getMessageStatus(response);
		}
		
		if ( response
				.getGatewayRequest()
				.getMessageRequest()
				.getMessageType() == MessageType.TEXT ) {
			return textGatewayManager.getMessageStatus(response);
		}
		
		return null;
		
	}

	public MStatus mapMessageStatus(GatewayResponse response) {
		
		if ( response
				.getGatewayRequest()
				.getMessageRequest()
				.getMessageType() == MessageType.VOICE ) {
			return voiceGatewayManager.mapMessageStatus(response);
		}
		
		if ( response
				.getGatewayRequest()
				.getMessageRequest()
				.getMessageType() == MessageType.TEXT ) {
			return textGatewayManager.mapMessageStatus(response);
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails,
			MotechContext context) {
		
		if ( messageDetails
				.getMessageRequest()
				.getMessageType() == MessageType.VOICE ) {
			return voiceGatewayManager.sendMessage(messageDetails, context);
		}
		
		if ( messageDetails
				.getMessageRequest()
				.getMessageType() == MessageType.TEXT ) {
			return textGatewayManager.sendMessage(messageDetails, context);
		}
		
		return new HashSet<GatewayResponse>();
	}

	public GatewayMessageHandler getMessageHandler() {
		return compositeMessageHandler;
	}
	
	public void setMessageHandler(GatewayMessageHandler messageHandler) {
		if ( messageHandler instanceof CompositeGatewayMessageHandler )
			this.compositeMessageHandler = (CompositeGatewayMessageHandler)messageHandler;
		else 
			this.compositeMessageHandler = null;
	}

	public CompositeGatewayMessageHandler getCompositeMessageHandler() {
		return compositeMessageHandler;
	}

	public void setCompositeMessageHandler(
			CompositeGatewayMessageHandler compositeMessageHandler) {
		this.compositeMessageHandler = compositeMessageHandler;
	}

	public GatewayManager getVoiceGatewayManager() {
		return voiceGatewayManager;
	}

	public void setVoiceGatewayManager(GatewayManager voiceGatewayManager) {
		this.voiceGatewayManager = voiceGatewayManager;
	}

	public GatewayManager getTextGatewayManager() {
		return textGatewayManager;
	}

	public void setTextGatewayManager(GatewayManager textGatewayManager) {
		this.textGatewayManager = textGatewayManager;
	}

}
