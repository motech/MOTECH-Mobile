/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.GatewayManager;
import com.outreachcity.orserve.messaging.SMSMessenger;
import com.outreachcity.orserve.messaging.SMSMessengerSoap;
import java.net.URL;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;

/**
 * <p>Handles all interactions with the OutReach Server message gateway</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Jul 15, 2009
 */
public class ORServeGatewayManagerImpl implements GatewayManager {
    private String productCode;
    private String senderId;
    private static Logger logger = Logger.getLogger(ORServeGatewayManagerImpl.class);

    /**
     *
     * @see GatewayManager.send
     */
    public String sendMessage(MessageDetails messageDetails) {
        if(messageDetails == null)
            return null;

        logger.info("Building ORServe message gateway webservice proxy class");
        URL wsdlURL = null;
        try {
          wsdlURL = new URL("http://www.outreachcity.com/orserve/messaging/smsmessenger.asmx?WSDL");
        } catch ( MalformedURLException e ) {
          e.printStackTrace();
        }
        SMSMessenger messenger = new SMSMessenger(wsdlURL, new QName("http://www.outreachcity.com/ORServe/Messaging/", "SMSMessenger"));
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();
        
        logger.info("Calling sendMessage method of ORServe message gateway");
        logger.debug(messageDetails);
        try{
            return soap.sendMessage(messageDetails.getMessageText(), messageDetails.getRecipientsNumbers(), getSenderId(), getProductCode(), String.valueOf(messageDetails.getNumberOfPages()));
        }
        catch(Exception ex){
            logger.fatal("Error sending message", ex);
            throw new RuntimeException("Error sending message");
        }
    }

    /**
     *
     * @see GatewayManager.getMessageStatus
     */
    public String getMessageStatus(String gatewayMessageId) {
        logger.info("Checking message delivery status");

        logger.info("Building ORServe message gateway webservice proxy class");
        URL wsdlURL = null;
        try {
          wsdlURL = new URL("http://www.outreachcity.com/orserve/messaging/smsmessenger.asmx?WSDL");
        } catch ( MalformedURLException e ) {
          e.printStackTrace();
        }
        SMSMessenger messenger = new SMSMessenger(wsdlURL, new QName("http://www.outreachcity.com/ORServe/Messaging/", "SMSMessenger"));
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();

        logger.info("Calling getMessageStatus method of ORServe message gateway");
        try{
            return soap.getMessageStatus(gatewayMessageId, productCode);
        }
        catch(Exception ex){
            logger.fatal("Error sending message", ex);
            throw new RuntimeException("Error sending message");
        }
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
        logger.debug("Setting ORServeGatewayManagerImpl.productCode");
        logger.debug(productCode);
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
        logger.debug("Setting ORServeGatewayManagerImpl.senderId");
        logger.debug(senderId);
        this.senderId = senderId;
    }
}
