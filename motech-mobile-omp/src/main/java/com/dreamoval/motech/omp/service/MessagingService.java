package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.omp.manager.GatewayManager;

/**
 * Handles all SMS related functions including cachine, sending and lookup
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface MessagingService {

    /**
     * Sends an SMS message with the provided details
     *
     * @param messageDetails MessageDetails object containing information about the message to be sent
     * @return The id of the message sent
     */
    public Long sendTextMessage(GatewayRequest messageDetails);

    /**
     * @return the cache
     */
    public CacheService getCache();

    /**
     * @param cache the cache to set
     */
    public void setCache(CacheService cache);

    /**
     * @return the gatewayManager
     */
    public GatewayManager getGatewayManager();

    /**
     * @param gatewayManager the gatewayManager to set
     */
    public void setGatewayManager(GatewayManager gatewayManager);
}
