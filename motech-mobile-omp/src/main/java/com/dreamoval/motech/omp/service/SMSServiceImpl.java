/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.GatewayManager;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 15-JUL-2009
 */
public class SMSServiceImpl implements SMSService {
    private SMSCacheService cache;
    private GatewayManager gatewayManager;

    /**
     *
     * @see sendTextMessage(MessageDetails messageDetails)
     */
    public Long sendTextMessage(MessageDetails messageDetails) {
        this.cache.saveMessage(messageDetails);
        this.gatewayManager.send(messageDetails);
        return messageDetails.getId();
    }

    /**
     * @see SMSService.sendTextMessage(string messageDetails)
     */
    public Long sendTextMessage(String messageDetails) {
        return this.gatewayManager.send(messageDetails).getMessageId().getId();
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

}
