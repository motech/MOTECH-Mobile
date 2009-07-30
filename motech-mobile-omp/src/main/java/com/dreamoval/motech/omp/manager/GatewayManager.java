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
 * <p>Handles all interactions with a message gateway</p>
 */
public interface GatewayManager {

    /**
     * Sends a message through a remote gateway
     *
     * @param messageDetails Object containing details of message to send
     * @return Formatted object containing information about the gateway's response
     */
    public ResponseDetails send(MessageDetails messageDetails);

    /**
     * @param messageDetails String representation of message to send
     * @see send(MessageDetails messageDetails)
     */
    public ResponseDetails send(String messageDetails);
}
