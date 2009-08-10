/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;

/**
 * <p>A dummy gateway manager for testing purposes</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 */
public class DummyGatewayManagerImpl implements GatewayManager{


    /**
     *
     * @see GatewayManager.send
     */
    public String sendMessage(MessageDetails messageDetails) {
        return "sent";
    }

    /**
     *
     * @see GatewayManager.getMessageStatus
     */
    public String getMessageStatus(String gatewayMessageId) {
        return "sent";
    }

}
