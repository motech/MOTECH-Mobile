/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.omp.manager.GatewayManager;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSMessagingServiceImpl implements MessagingService {
    private CacheService cache;
    private GatewayManager gatewayManager;
    private GatewayMessageHandler handler;
    private static Logger logger = Logger.getLogger(SMSMessagingServiceImpl.class);

    /**
     *
     * @see sendTextMessage(MessageDetails messageDetails)
     */
    public Long sendTextMessage(MessageDetails messageDetails) {
        logger.info("Calling CacheService.saveMessage");
        logger.debug(messageDetails);
        this.cache.saveMessage(messageDetails);

        logger.info("Sending message to gateway");
        String gatewayResponse = this.gatewayManager.sendMessage(messageDetails);

        logger.info("Parsing gateway response");
        Set<ResponseDetails> responseList = handler.parseMessageResponse(messageDetails, gatewayResponse);
        messageDetails.setResponseDetails(responseList);

        logger.debug(responseList);
        logger.info("Updating message status");
        this.cache.saveMessage(messageDetails);

        return messageDetails.getId();
    }

    public String getMessageStatus(String gatewayMessageId){
        logger.info("Calling GatewayManager.getMessageStatus");
        return handler.parseMessageStatus(gatewayManager.getMessageStatus(gatewayMessageId));
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
        logger.debug("Setting SMSMessagingServiceImpl.handler:");
        logger.debug(handler);
        this.handler = handler;
    }

}
