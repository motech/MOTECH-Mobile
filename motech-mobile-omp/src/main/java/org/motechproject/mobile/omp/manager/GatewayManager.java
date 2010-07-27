package org.motechproject.mobile.omp.manager;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
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
     * @param response the GatewayResponse created when the message was sent
     * @return the response message from the gateway
     */
    public String getMessageStatus(GatewayResponse response);
    
    /**
     * 
     * @param response the GatewayResponse created when the message was sent
     * @return the status of the message
     */
    public MStatus mapMessageStatus(GatewayResponse response);

    /**
     * @return the messageHandler
     */
    public GatewayMessageHandler getMessageHandler();

    /**
     * @param messageHandler the messageHandler to set
     */
    public void setMessageHandler(GatewayMessageHandler messageHandler);
}
