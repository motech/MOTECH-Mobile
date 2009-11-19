package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import java.util.Date;
import java.util.List;

/**
 * Handles all message caching related functions
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface CacheService {

    /**
     * saves a message to the cache
     * @param messageDetails MessageDetails object to be saved to the cache
     * @return value indicating success. True for success, false for failure
     */
    public void saveMessage(GatewayRequest messageDetails, MotechContext context);
    
    /**
     * saves a message to the cache
     * @param messageDetails MessageDetails object to be saved to the cache
     * @return value indicating success. True for success, false for failure
     */
    public void saveMessage(GatewayRequestDetails messageDetails, MotechContext context);
    
    /**
     * saves a message response to the cache
     * @param messageDetails MessageDetails object to be saved to the cache
     * @return value indicating success. True for success, false for failure
     */
    public void saveResponse(GatewayResponse responseDetails, MotechContext context);

    /**
     * fetches messages matching specified criteria
     * 
     * @param criteria by which messages should be fetched
     * @return list of messages matching specified criteria
     */
    public List<GatewayRequest> getMessages(GatewayRequest criteria, MotechContext context);
      
    /**
     * fetches messages with specified status
     * 
     * @param criteria by which messages should be fetched
     * @return list of messages matching specified criteria
     */
    public List<GatewayRequest> getMessagesByStatus(MStatus criteria, MotechContext context);

    /**
     * fetches messages with specified status
     *
     * @param criteria by which messages should be fetched
     * @return list of messages matching specified criteria
     */
    public List<GatewayRequest> getMessagesByStatusAndSchedule(MStatus criteria, Date schedule, MotechContext context);
            
    /**
     * 
     * fetches all GatewayResponse objects matching the specified criteria
     */
    public List<GatewayResponse> getResponses(GatewayResponse criteria, MotechContext context);
    
    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager();

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager);
}
