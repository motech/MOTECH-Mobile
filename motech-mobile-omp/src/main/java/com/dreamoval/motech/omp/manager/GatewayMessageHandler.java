/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import java.util.List;

/**
 * <p>Handles preparation and parsing of messages and responses from a message gateway</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface GatewayMessageHandler {

    /**
     * Generates a MessageDetails object from a string
     *
     * @param message a string containing message information
     * @return the generated MessageDetails object
     */
    public MessageDetails prepareMessage(String message);

    /**
     * Constructs ResponseDetails objects from a response recieved from a message gateway
     * @param gatewayResponse
     * @return
     */
    public List<ResponseDetails> parseMessageResponse(MessageDetails message, String gatewayResponse);

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
