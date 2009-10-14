package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.util.Set;
import java.util.HashSet;
import org.apache.log4j.Logger;

/**
 * Handles preparation and parsing of messages and responses from the OutReach Server message gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Jul 15, 2009
 */
public class ORServeGatewayMessageHandlerImpl implements GatewayMessageHandler {

    private CoreManager coreManager;
    private static Logger logger = Logger.getLogger(ORServeGatewayMessageHandlerImpl.class);

    /**
     *
     * @see GatewayMessageHandler.parseResponse
     */
    public Set<GatewayResponse> parseMessageResponse(GatewayRequest message, String gatewayResponse) {
        logger.info("Parsing message gateway response");
        logger.debug(gatewayResponse);

        if(message == null)
            return null;
        
        if(gatewayResponse.isEmpty())
            return null;

        Set<GatewayResponse> responses = new HashSet<GatewayResponse>();
        String[] responseLines = gatewayResponse.split("\n");

        for(String line : responseLines){
            String[] responseParts = line.split(" ");            

            if(responseParts[0].equalsIgnoreCase("ID:")){
                GatewayResponse response = getCoreManager().createGatewayResponse(getCoreManager().createMotechContext());
                response.setGatewayMessageId(responseParts[1]);

                if(responseParts.length == 4)
                    response.setRecipientNumber(responseParts[3]);
                else
                    response.setRecipientNumber(message.getRecipientsNumber());

                response.setMessageStatus(MStatus.PENDING);
                response.setGatewayRequest(message);
                responses.add(response);
            }
            else{
                logger.error("Gateway returned error: " + gatewayResponse);
            }
        }
        logger.debug(responses);
        return responses;
    }

    /**
     *
     * @see GatewayMessageHandler.parseMessageStatus
     */
    public MStatus parseMessageStatus(String messageStatus) {
        logger.info("Parsing message gateway status response");
        
        String status;
        String[] responseParts = messageStatus.split(" ");
        
        if(responseParts.length == 4){
            status = responseParts[3];
        }
        else{
            status = "";
        }

        if (!status.isEmpty())
        {
            if (status.equals("004"))
            {
                return MStatus.DELIVERED;
            }
            else if (status.equals("002") || status.equals("003") || status.equals("008") || status.equals("011"))
            {
                return MStatus.PENDING;
            }
            else
            {
                return MStatus.FAILED;
            }
        }
        else
        {
            return MStatus.FAILED;
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
