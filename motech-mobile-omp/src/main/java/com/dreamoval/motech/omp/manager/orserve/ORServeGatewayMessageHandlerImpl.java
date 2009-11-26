package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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
    private Map<MStatus, String> codeStatusMap;
    private Map<MStatus, String> codeResponseMap;

    /**
     *
     * @see GatewayMessageHandler.parseResponse
     */
    public Set<GatewayResponse> parseMessageResponse(GatewayRequest message, String gatewayResponse, MotechContext context) {
        logger.info("Parsing message gateway response");
        logger.debug(gatewayResponse);

        if(message == null)
            return null;
        
        if(gatewayResponse.isEmpty())
            return null;

        Set<GatewayResponse> responses = new HashSet<GatewayResponse>();
        if(gatewayResponse.contains("error:") && gatewayResponse.contains("param:")){
            gatewayResponse = gatewayResponse.trim().replace("\n", "");
        }
        String[] responseLines = gatewayResponse.split("\n");

        for(String line : responseLines){
            String[] responseParts = line.split(" ");            

            if(responseParts[0].equalsIgnoreCase("ID:")){
                GatewayResponse response = getCoreManager().createGatewayResponse(context);
                
                response.setGatewayMessageId(responseParts[1]);
                response.setRequestId(message.getRequestId());
                response.setMessageStatus(MStatus.PENDING);
                response.setGatewayRequest(message);
                response.setResponseText(gatewayResponse);
                response.setDateCreated(new Date());

                if(responseParts.length == 4)
                    response.setRecipientNumber(responseParts[3]);
                else
                    response.setRecipientNumber(message.getRecipientsNumber());

                
                responses.add(response);
            }
            else{
                logger.error("Gateway returned error: " + gatewayResponse);
                
                String errorCode = responseParts[1];
                errorCode.replaceAll(",", "");
                errorCode.trim();
                
                MStatus status = lookupResponse(errorCode);
                
                GatewayResponse response = getCoreManager().createGatewayResponse(context);
                
                response.setRequestId(message.getRequestId());
                response.setMessageStatus(status);
                response.setGatewayRequest(message);
                response.setResponseText(gatewayResponse);
                response.setDateCreated(new Date());
                
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
    public MStatus parseMessageStatus(String gatewayResponse) {
        logger.info("Parsing message gateway status response");
        
        String status;
                
        String[] responseParts = gatewayResponse.split(" ");
        
        if(responseParts.length == 4){
            status = responseParts[3];
        }
        else{
            status = "";
        }
        
        return lookupStatus(status);
    }
    
    /**
     * @see GatewayMessageHandler.lookupStatus
     */
    public MStatus lookupStatus(String code){
        if(code.isEmpty()){
            return MStatus.PENDING;
        }
        
        for(Entry<MStatus, String> entry: codeStatusMap.entrySet()){
            if(entry.getValue().contains(code)){
                return entry.getKey();
            }
        }
        return MStatus.PENDING;
    }
    
    /**
     * @see GatewayMessageHandler.lookupResponse
     */
    public MStatus lookupResponse(String code){
        if(code.isEmpty()){
            return MStatus.SCHEDULED;
        }
        
        for(Entry<MStatus, String> entry: codeResponseMap.entrySet()){
            if(entry.getValue().contains(code)){
                return entry.getKey();
            }
        }
        return MStatus.SCHEDULED;
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

    public Map<MStatus, String> getCodeStatusMap() {
        return codeStatusMap;
    }

    public void setCodeStatusMap(Map<MStatus, String> codeStatusMap) {
        this.codeStatusMap = codeStatusMap;
    }

    public Map<MStatus, String> getCodeResponseMap() {
        return codeResponseMap;
    }

    public void setCodeResponseMap(Map<MStatus, String> codeResponseMap) {
        this.codeResponseMap = codeResponseMap;
    }

}
