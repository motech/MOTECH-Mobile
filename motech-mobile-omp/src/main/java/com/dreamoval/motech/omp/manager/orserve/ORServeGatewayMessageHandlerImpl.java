/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Handles preparation and parsing of messages and responses from the OutReach Server message gateway</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Jul 15, 2009
 */
public class ORServeGatewayMessageHandlerImpl implements GatewayMessageHandler {

    private CoreManager coreManager;

    /**
     *
     * @see GatewayMessageHandler.prepareMessage
     */
    public MessageDetails prepareMessage(String message) {
        System.out.println(message);
        return null;
    }

    /**
     *
     * @see GatewayMessageHandler.parseResponse
     */
    public List<ResponseDetails> parseMessageResponse(MessageDetails message, String gatewayResponse) {
        List<ResponseDetails> responses = new ArrayList<ResponseDetails>();
        String[] responseLines = gatewayResponse.split("\n");

        for(String line : responseLines){
            String[] responseParts = line.split(" ");            

            if(responseParts[0].equalsIgnoreCase("ID:")){
                ResponseDetails response = getCoreManager().createResponseDetails(getCoreManager().createMotechContext());
                response.setGatewayMessageId(responseParts[1]);
                response.setRecipientNumber(responseParts[3]);
                response.setMessageStatus("sent");
                response.setMessageId(message);
                responses.add(response);
            }
        }
        return responses;
    }

    /**
     *
     * @see GatewayMessageHandler.parseMessageStatus
     */
    public String parseMessageStatus(String messageStatus) {
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
        this.coreManager = coreManager;
    }

}
