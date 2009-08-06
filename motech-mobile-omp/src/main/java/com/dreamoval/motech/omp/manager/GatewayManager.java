/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;

/**
 * <p>Handles all interactions with a message gateway</p>
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
    public String sendMessage(MessageDetails messageDetails);

    /**
     *
     * @param response the id of the message returned by the message gateway
     * @return
     */
    public String getMessageStatus(String gatewayMessageId);
}
