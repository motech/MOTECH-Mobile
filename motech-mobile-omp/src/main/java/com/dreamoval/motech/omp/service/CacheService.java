/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.OMPManager;

/**
 * <p>Handles all message caching related functions</p>
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
    public void saveMessage(MessageDetails messageDetails);

    /**
     *
     * @param messageDetails a string representation of the MessageDetails object to be saved
     * @see saveMessage(MessageDetails messageDetails)
     */
    public void saveMessage(String messageDetails);

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
