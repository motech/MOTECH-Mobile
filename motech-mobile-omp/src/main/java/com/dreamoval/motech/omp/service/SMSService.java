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
 *
 * <p>Handles all SMS related functions including cachine, sending and lookup</p>
 */
public interface SMSService {
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
    public SMSCacheService getCache();

    /**
     * @param cache the cache to set
     */
    public void setCache(SMSCacheService cache);

    /**
     * @return the gatewayManager
     */
    public GatewayManager getGatewayManager();

    /**
     * @param gatewayManager the gatewayManager to set
     */
    public void setGatewayManager(GatewayManager gatewayManager);
}