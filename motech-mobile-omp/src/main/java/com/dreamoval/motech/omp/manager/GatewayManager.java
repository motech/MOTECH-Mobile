package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import java.util.Set;

/**
 * Handles all interactions with a message gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface GatewayManager {

    /**
     * Sends a message through a remote gateway
     *
     * @param messageDetails Object containing details of message to send
     * @return Formatted object containing information about the gateway's response
     */
    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails);

    /**
     *
     * @param response the id of the message returned by the message gateway
     * @return
     */
    public String getMessageStatus(String gatewayMessageId);

    /**
     * @return the messageHandler
     */
    public GatewayMessageHandler getMessageHandler();

    /**
     * @param messageHandler the messageHandler to set
     */
    public void setMessageHandler(GatewayMessageHandler messageHandler);
}
