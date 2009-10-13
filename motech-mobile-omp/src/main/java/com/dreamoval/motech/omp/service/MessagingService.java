package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.omp.manager.GatewayManager;

/**
 * Handles all SMS related functions including cachine, sending and lookup
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface MessagingService {

    /**
     * Queues a message for delivery within the scheduled period
     * 
     * @param message to be scheduled
     */
    public void scheduleMessage(GatewayRequest message, MotechContext context);
    
    /**
     * Queues a message for delivery within the scheduled period
     * 
     * @param message to be scheduled
     */
    public void scheduleMessage(GatewayRequestDetails message, MotechContext context);
    
    /**
     * Sends all messages due for delivery
     *
     */
    public void sendScheduledMessages();
    /**
     * Sends a message with the provided details
     *
     * @param messageDetails MessageDetails object containing information about the message to be sent
     * @return The id of the message sent
     */
    public Long sendMessage(GatewayRequest messageDetails, MotechContext context);

    /**
     * Queries and updates the delivery status of all pending messages
     */
    public void updateMessageStatuses();
    
    /**
     * 
     * @param current message response to be updated
     * @return current status of the message
     */
    public String getMessageStatus(GatewayResponse response);
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
