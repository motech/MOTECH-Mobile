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

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSServiceImpl implements SMSService {
    private SMSCacheService cache;
    private GatewayManager gatewayManager;
    private GatewayMessageHandler handler;

    /**
     *
     * @see sendTextMessage(MessageDetails messageDetails)
     */
    public Long sendTextMessage(MessageDetails messageDetails) {
        this.cache.saveMessage(messageDetails);
        
        String gatewayResponse = this.gatewayManager.sendMessage(messageDetails);
        Set<ResponseDetails> responseList = handler.parseMessageResponse(messageDetails, gatewayResponse);
        messageDetails.setResponseDetails(responseList);

        this.cache.updateMessage(messageDetails);

        return messageDetails.getId();
    }

    /**
     * @see SMSService.sendTextMessage(string messageDetails)
     */
    public Long sendTextMessage(String messageDetails) {
        return sendTextMessage(handler.prepareMessage(messageDetails));
    }

    public String getMessageStatus(String gatewayMessageId){
        return handler.parseMessageStatus(gatewayManager.getMessageStatus(gatewayMessageId));
    }

    /**
     * @return the cache
     */
    public SMSCacheService getCache() {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(SMSCacheService cache) {
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
        this.handler = handler;
    }

}
