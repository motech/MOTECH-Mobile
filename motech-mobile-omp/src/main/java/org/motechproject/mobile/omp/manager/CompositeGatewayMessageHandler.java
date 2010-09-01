package org.motechproject.mobile.omp.manager;

import java.util.Set;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageType;

public class CompositeGatewayMessageHandler implements GatewayMessageHandler {

	private GatewayMessageHandler voiceHandler;
	private GatewayMessageHandler textHandler;
	private CoreManager coreManager;
	
	/**
	 * must directly call either the text or voice handler
	 * @return null
	 */
	public MStatus lookupResponse(String code) {
		return null;
	}

	/**
	 * must directly call either the text or voice handler
	 * @return null
	 */
	public MStatus lookupStatus(String code) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public Set<GatewayResponse> parseMessageResponse(GatewayRequest message,
			String gatewayResponse) {
		
		if ( message
				.getMessageRequest()
				.getMessageType() == MessageType.VOICE)
			return voiceHandler.parseMessageResponse(message, gatewayResponse);
		
		if ( message
				.getMessageRequest()
				.getMessageType() == MessageType.TEXT)
			return textHandler.parseMessageResponse(message, gatewayResponse);
		
		
		return null;
	}

	/**
	 * must directly call either the text or voice handler
	 * @return null
	 */
	public MStatus parseMessageStatus(String messageStatus) {
		return null;
	}

	public CoreManager getCoreManager() {
		return coreManager;
	}
	
	public void setCoreManager(CoreManager coreManager) {
		this.coreManager = coreManager;
	}

	public GatewayMessageHandler getVoiceHandler() {
		return voiceHandler;
	}

	public void setVoiceHandler(GatewayMessageHandler voiceHandler) {
		this.voiceHandler = voiceHandler;
	}

	public GatewayMessageHandler getTextHandler() {
		return textHandler;
	}

	public void setTextHandler(GatewayMessageHandler textHandler) {
		this.textHandler = textHandler;
	}

}
