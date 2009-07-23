/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Domain.ResponseDetails;
import com.dreamoval.motech.omp.manager.SMSGatewayManager;
import com.outreachcity.orserve.messaging.SMSMessenger;
import com.outreachcity.orserve.messaging.SMSMessengerSoap;

/**
 *
 * @author Yoofi
 */
public class ORServeSMSGatewayManagerImpl implements SMSGatewayManager {
    private String productCode;
    private String recipients;
    private String senderId;
    private ORServeGatewayMessageHandlerImpl handler;

    public ResponseDetails send(MessageDetails messageDetails) {
        if(messageDetails == null)
            return null;
        
        SMSMessenger messenger = new SMSMessenger();
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();
        String gatewayResponse = soap.sendMessage(messageDetails.getMessageText(), getRecipients(), getSenderId(), getProductCode(), String.valueOf(messageDetails.getNumberOfPages()));
        return getHandler().parseResponse(gatewayResponse);
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
     * @return the recipients
     */
    public String getRecipients() {
        return recipients;
    }

    /**
     * @param recipients the recipients to set
     */
    public void setRecipients(String recipients) {
        this.recipients = recipients;
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
    public ORServeGatewayMessageHandlerImpl getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(ORServeGatewayMessageHandlerImpl handler) {
        this.handler = handler;
    }
}
