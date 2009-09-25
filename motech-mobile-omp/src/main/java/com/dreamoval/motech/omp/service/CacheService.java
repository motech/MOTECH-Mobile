package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.omp.manager.OMPManager;

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
     * @return the coreManager
     */
    public CoreManager getCoreManager();

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager);

    /**
     * @return the ompManager
     */
    public OMPManager getOmpManager();

    /**
     * @param ompManager the ompManager to set
     */
    public void setOmpManager(OMPManager ompManager);
}
