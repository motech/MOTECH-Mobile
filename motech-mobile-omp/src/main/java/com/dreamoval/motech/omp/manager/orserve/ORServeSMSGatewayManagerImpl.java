/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.omp.manager.GatewayManager;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import com.outreachcity.orserve.messaging.SMSMessenger;
import com.outreachcity.orserve.messaging.SMSMessengerSoap;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 15-JUL-2009
 *
 * <p>Handles all interactions with the OutReach Server message gateway</p>
 */
public class ORServeSMSGatewayManagerImpl implements GatewayManager {
    private String productCode;
    private String senderId;
    private GatewayMessageHandler handler;

    /**
     *
     * @see GatewayManager.send
     */
    public ResponseDetails send(MessageDetails messageDetails) {
        if(messageDetails == null)
            return null;
        
        SMSMessenger messenger = new SMSMessenger();
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();
        String gatewayResponse = soap.sendMessage(messageDetails.getMessageText(), messageDetails.getRecipientsNumbers(), getSenderId(), getProductCode(), String.valueOf(messageDetails.getNumberOfPages()));
        return getHandler().parseResponse(gatewayResponse);
    }

    /**
     *
     * @see GatewayManager.send
     */
    public ResponseDetails send(String messageDetails) {
        return this.send(handler.prepareMessage(messageDetails));
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the senderId
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the handler
     */
    public GatewayMessageHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(GatewayMessageHandler handler) {
        this.handler = handler;
    }
}