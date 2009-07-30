/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 15-JUL-2009
 *
 * <p>Handles preparation and parsing of messages and responses from a message gateway</p>
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
     * Constructs a ResponseDetails object from a response recieved from a message gateway
     * @param gatewayResponse
     * @return
     */
    public ResponseDetails parseResponse(String gatewayResponse);
}
