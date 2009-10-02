package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.omp.manager.GatewayManager;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * An SMS specific implementation of the MessagingService interface
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSMessagingServiceImpl implements MessagingService {
    private CacheService cache;
    private GatewayManager gatewayManager;
    private static Logger logger = Logger.getLogger(SMSMessagingServiceImpl.class);

    /**
     * 
     * @see MessageService.scheduleMessage
     */
    public void scheduleMessage(GatewayRequest message){
        cache.saveMessage(message);
    }
    /**
     *
     * @see MessagingService.sendScheduledMessages
     */
    public void sendScheduledMessages(){
        List<GatewayRequest> scheduledMessages = cache.getMessages(new GatewayRequestImpl());
        for(GatewayRequest message : scheduledMessages){
            sendMessage(message);
        }
    }
    
    /**
     *
     * @see MessagingService.sendMessage(MessageDetails messageDetails)
     */
    public Long sendMessage(GatewayRequest messageDetails) {
        //logger.info("Calling CacheService.saveMessage");
        //logger.debug(messageDetails);
        //this.cache.saveMessage(messageDetails);

        logger.info("Sending message to gateway");
        Set<GatewayResponse> responseList = this.gatewayManager.sendMessage(messageDetails);

        logger.debug(responseList);
        logger.info("Updating message status");
        messageDetails.setResponseDetails(responseList);
        this.cache.saveMessage(messageDetails);

        return messageDetails.getId();
    }

    /**
     * 
     */
    public void updateMessageStatuses() {
        List<GatewayResponse> pendingMessages = cache.getResponses(new GatewayResponseImpl());
        for(GatewayResponse response : pendingMessages){
            response.setResponseText(gatewayManager.getMessageStatus(response));
            response.setMessageStatus(gatewayManager.mapMessageStatus(response));
            cache.saveResponse(response);
        }
    }

    /**
     * 
     * @see MessageService.getMessageStatus
     */
    public String getMessageStatus(GatewayResponse response){
        logger.info("Calling GatewayManager.getMessageStatus");
        return gatewayManager.getMessageStatus(response);
    }

    /**
     * @return the cache
     */
    public CacheService getCache() {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(CacheService cache) {
        logger.debug("Setting SMSMessagingServiceImpl.cache:");
        logger.debug(cache);
        this.cache = cache;
    }

    /**
     * @return the gatewayManager
     */
    public GatewayManager getGatewayManager() {
        return gatewayManager;
    }

    /**
     * @param gatewayManager the gatewayManager to set
     */
    public void setGatewayManager(GatewayManager gatewayManager) {
        logger.debug("Setting SMSMessagingServiceImpl.gatewayManager:");
        logger.debug(gatewayManager);
        this.gatewayManager = gatewayManager;
    }

}
