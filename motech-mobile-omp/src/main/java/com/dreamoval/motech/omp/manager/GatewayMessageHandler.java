package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import java.util.Set;

/**
 * Handles preparation and parsing of messages and responses from a message gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface GatewayMessageHandler {

    /**
     * Constructs ResponseDetails objects from a response recieved from a message gateway
     * @param gatewayResponse
     * @return
     */
    public Set<GatewayResponse> parseMessageResponse(GatewayRequest message, String gatewayResponse);

    /**
     *
     * @param messageStatus the status of the message returned by the message gateway
     * @return a more reader-friendly message status
     */
    public String parseMessageStatus(String messageStatus);

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager();

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager);
}
