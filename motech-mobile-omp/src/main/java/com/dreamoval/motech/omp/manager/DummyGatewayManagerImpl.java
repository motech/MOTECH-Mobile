/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import java.util.Set;

/**
 * <p>A dummy gateway manager for testing purposes</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 */
public class DummyGatewayManagerImpl implements GatewayManager{

    private GatewayMessageHandler messageHandler;
    /**
     *
     * @see GatewayManager.send
     */
    public Set<ResponseDetails> sendMessage(MessageDetails messageDetails){
        return messageHandler.parseMessageResponse(messageDetails, "sent");
    }

    /**
     *
     * @see GatewayManager.getMessageStatus
     */
    public String getMessageStatus(String gatewayMessageId) {
        return messageHandler.parseMessageStatus("delivered");
    }

    /**
     * @return the messageHandler
     */
    public GatewayMessageHandler getMessageHandler() {
        return messageHandler;
    }

    /**
     * @param messageHandler the messageHandler to set
     */
    public void setMessageHandler(GatewayMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

}
