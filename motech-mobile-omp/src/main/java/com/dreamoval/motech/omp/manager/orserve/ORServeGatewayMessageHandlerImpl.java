/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.util.Set;
import java.util.HashSet;
import org.apache.log4j.Logger;

/**
 * <p>Handles preparation and parsing of messages and responses from the OutReach Server message gateway</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Jul 15, 2009
 */
public class ORServeGatewayMessageHandlerImpl implements GatewayMessageHandler {

    private CoreManager coreManager;
    private static Logger logger = Logger.getLogger(ORServeGatewayMessageHandlerImpl.class);

    /**
     *
     * @see GatewayMessageHandler.prepareMessage
     */
    public MessageDetails prepareMessage(String message) {
        System.out.println("Message:" + message);
        return null;
    }

    /**
     *
     * @see GatewayMessageHandler.parseResponse
     */
    public Set<ResponseDetails> parseMessageResponse(MessageDetails message, String gatewayResponse) {
        logger.info("Parsing message gateway response");
        logger.debug(gatewayResponse);

        if(message == null)
            return null;
        
        if(gatewayResponse.isEmpty())
            return null;

        Set<ResponseDetails> responses = new HashSet<ResponseDetails>();
        String[] responseLines = gatewayResponse.split("\n");

        for(String line : responseLines){
            String[] responseParts = line.split(" ");            

            if(responseParts[0].equalsIgnoreCase("ID:")){
                ResponseDetails response = getCoreManager().createResponseDetails(getCoreManager().createMotechContext());
                response.setGatewayMessageId(responseParts[1]);

                if(responseParts.length == 4)
                    response.setRecipientNumber(responseParts[3]);
                else
                    response.setRecipientNumber(message.getRecipientsNumbers());

                response.setMessageStatus("sent");
                response.setMessageId(message);
                responses.add(response);
            }
        }
        logger.debug(responses);
        return responses;
    }

    /**
     *
     * @see GatewayMessageHandler.parseMessageStatus
     */
    public String parseMessageStatus(String messageStatus) {
        logger.info("Parsing message gateway status response");
        
        String status = messageStatus.trim();

        if (!status.isEmpty())
        {
            if (status.equals("004"))
            {
                return "delivered";
            }
            else if (status.equals("002") || status.equals("003") || status.equals("008") || status.equals("011"))
            {
                return "sent";
            }
            else
            {
                return "failed";
            }
        }
        else
        {
            return "failed";
        }
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        logger.debug("Setting ORServeGatewayMessageHandlerImpl.coreManager");
        logger.debug(coreManager);
        this.coreManager = coreManager;
    }

}
