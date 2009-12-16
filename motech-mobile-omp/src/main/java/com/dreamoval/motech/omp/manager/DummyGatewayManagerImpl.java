/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.util.MotechIDGenerator;
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
    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails, MotechContext context){
        String msgResponse = (messageDetails.getRecipientsNumber().length() < 8) ? "failed" : "ID: " + MotechIDGenerator.generateID(10);
        return messageHandler.parseMessageResponse(messageDetails, msgResponse, context);
    }

    /**
     *
     * @see GatewayManager.getMessageStatus
     */
    public String getMessageStatus(GatewayResponse response) {
        return "004";
    }

    public MStatus mapMessageStatus(GatewayResponse response) {
        return messageHandler.parseMessageStatus(response.getResponseText());
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
