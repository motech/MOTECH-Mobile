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
import java.net.URL;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;

/**
 * <p>Handles all interactions with the OutReach Server message gateway</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Jul 15, 2009
 */
public class ORServeSMSGatewayManagerImpl implements GatewayManager {
    private String productCode;
    private String senderId;

    /**
     *
     * @see GatewayManager.send
     */
    public String sendMessage(MessageDetails messageDetails) {
        if(messageDetails == null)
            return null;
        
        URL wsdlURL = null;
        try {
          wsdlURL = new URL("http://www.outreachcity.com/orserve/messaging/smsmessenger.asmx?WSDL");
        } catch ( MalformedURLException e ) {
          e.printStackTrace();
        }
        SMSMessenger messenger = new SMSMessenger(wsdlURL, new QName("http://www.outreachcity.com/ORServe/Messaging/", "SMSMessenger"));
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();
        return soap.sendMessage(messageDetails.getMessageText(), messageDetails.getRecipientsNumbers(), getSenderId(), getProductCode(), String.valueOf(messageDetails.getNumberOfPages()));
    }

    /**
     *
     * @see GatewayManager.getMessageStatus
     */
    public String getMessageStatus(String gatewayMessageId) {
        SMSMessenger messenger = new SMSMessenger();
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();
        return soap.getMessageStatus(gatewayMessageId, productCode);
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
}
