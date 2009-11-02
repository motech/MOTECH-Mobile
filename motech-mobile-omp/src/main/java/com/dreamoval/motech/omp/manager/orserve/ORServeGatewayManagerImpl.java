package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.util.MotechException;
import com.dreamoval.motech.omp.manager.GatewayManager;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import com.outreachcity.orserve.messaging.SMSMessenger;
import com.outreachcity.orserve.messaging.SMSMessengerSoap;
import java.net.URL;
import java.util.Set;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * Handles all interactions with the OutReach Server message gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Jul 15, 2009
 */
public class ORServeGatewayManagerImpl implements GatewayManager {
    private String productCode;
    private String senderId;
    private GatewayMessageHandler messageHandler;
    private static Logger logger = Logger.getLogger(ORServeGatewayManagerImpl.class);

    /**
     *
     * @see GatewayManager.send
     */
    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails, MotechContext context) {
        String gatewayResponse;

        if(messageDetails == null)
            return null;

        logger.info("Building ORServe message gateway webservice proxy class");
        URL wsdlURL = null;
        try {
          wsdlURL = new URL("http://www.outreachcity.com/orserve/messaging/smsmessenger.asmx?WSDL");
        } catch ( MalformedURLException e ) {
          logger.error("Error creating web service client", e);
          throw new MotechException(e.getMessage());
        }
        SMSMessenger messenger = new SMSMessenger(wsdlURL, new QName("http://www.outreachcity.com/ORServe/Messaging/", "SMSMessenger"));
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();
        
        logger.info("Calling sendMessage method of ORServe message gateway");
        logger.debug(messageDetails);
        try{
            gatewayResponse = soap.sendMessage(messageDetails.getMessage(), messageDetails.getRecipientsNumber(), getSenderId(), getProductCode(), String.valueOf(messageDetails.getGatewayRequestDetails().getNumberOfPages()));
        }
        catch(Exception ex){
            logger.error("Error sending message", ex);
            throw new MotechException(ex.getMessage());
        }
        messageDetails.setDateSent(new Date());
        
        logger.info("Parsing gateway response");
        return messageHandler.parseMessageResponse(messageDetails, gatewayResponse, context);
    }

    /**
     *
     * @see GatewayManager.getMessageStatus
     */
    public String getMessageStatus(GatewayResponse response) {
        String gatewayResponse;

        logger.info("Checking message delivery status");

        logger.info("Building ORServe message gateway webservice proxy class");
        URL wsdlURL = null;
        try {
          wsdlURL = new URL("http://www.outreachcity.com/orserve/messaging/smsmessenger.asmx?WSDL");
        } catch ( MalformedURLException e ) {
          logger.error("Error creating web service client", e);
          gatewayResponse = e.getMessage();
        }
        SMSMessenger messenger = new SMSMessenger(wsdlURL, new QName("http://www.outreachcity.com/ORServe/Messaging/", "SMSMessenger"));
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();

        logger.info("Calling getMessageStatus method of ORServe message gateway");
        try{
            gatewayResponse = soap.getMessageStatus(response.getGatewayMessageId(), productCode);
        }
        catch(Exception ex){
            logger.error("Error querying message", ex);
            gatewayResponse = ex.getMessage();
        }
        return gatewayResponse;
    }

    public MStatus mapMessageStatus(GatewayResponse response) {
        return messageHandler.parseMessageStatus(response.getResponseText());
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
        logger.debug("Setting SMSMessagingServiceImpl.handler:");
        logger.debug(messageHandler);
        this.messageHandler = messageHandler;
    }

}
