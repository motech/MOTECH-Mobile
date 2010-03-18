package org.motechproject.mobile.omp.service;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayManager;
import java.util.Map;
import java.util.Set;

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
     * @param messageDetails GatewayRequest object containing information about the message to be sent
     * @return The id of the message sent
     */
    public Map<Boolean, Set<GatewayResponse>> sendMessage(GatewayRequest messageDetails, MotechContext context);

    /**
     * Sends a message with the provided details
     *
     * @param messageDetails GatewayRequestDetails object containing information about the message to be sent
     * @return The id of the message sent
     */
    public Long sendMessage(GatewayRequestDetails messageDetails, MotechContext context);
            
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
