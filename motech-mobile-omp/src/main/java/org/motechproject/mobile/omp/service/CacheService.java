package org.motechproject.mobile.omp.service;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
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
    public void saveMessage(GatewayRequest messageDetails);
    
    /**
     * saves a message to the cache
     * @param messageDetails MessageDetails object to be saved to the cache
     * @return value indicating success. True for success, false for failure
     */
    public void saveMessage(GatewayRequestDetails messageDetails);
    
    /**
     * saves a message response to the cache
     * @param messageDetails MessageDetails object to be saved to the cache
     * @return value indicating success. True for success, false for failure
     */
    public void saveResponse(GatewayResponse responseDetails);

    /**
     * fetches messages matching specified criteria
     * 
     * @param criteria by which messages should be fetched
     * @return list of messages matching specified criteria
     */
    public List<GatewayRequest> getMessages(GatewayRequest criteria);
      
    /**
     * fetches messages with specified status
     * 
     * @param criteria by which messages should be fetched
     * @return list of messages matching specified criteria
     */
    public List<GatewayRequest> getMessagesByStatus(MStatus criteria);

    /**
     * fetches messages with specified status
     *
     * @param criteria by which messages should be fetched
     * @return list of messages matching specified criteria
     */
    public List<GatewayRequest> getMessagesByStatusAndSchedule(MStatus criteria, Date schedule);
            
    /**
     * 
     * fetches all GatewayResponse objects matching the specified criteria
     */
    public List<GatewayResponse> getResponses(GatewayResponse criteria);
    
    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager();

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager);
}
