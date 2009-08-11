/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.GatewayManager;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;

/**
 * <p>Handles all SMS related functions including cachine, sending and lookup</p>
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
    public Long sendTextMessage(MessageDetails messageDetails);

    /**
     *
     * @see sendTextMessage(MessageDetails messageDetails)
     * @param messageDetails a string containing details of message to be sent
     * @return The id of the message sent
     */
    public Long sendTextMessage(String messageDetails);

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

    /**
     * @return the handler
     */
    public GatewayMessageHandler getHandler();

    /**
     * @param handler the handler to set
     */
    public void setHandler(GatewayMessageHandler handler);
}
