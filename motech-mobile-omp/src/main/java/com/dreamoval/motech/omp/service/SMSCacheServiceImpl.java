/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import com.dreamoval.motech.omp.manager.OMPManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSCacheServiceImpl implements CacheService {

    private CoreManager coreManager;
    private OMPManager ompManager;
    private static Logger logger = Logger.getLogger(SMSCacheServiceImpl.class);

    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(MessageDetails messageDetails) {
        logger.info("Creating MessageDetailsDAO object");
        MessageDetailsDAO messageDAO = coreManager.createMessageDetailsDAO(coreManager.createMotechContext());
        
        logger.info("Calling MessageDetailsDAO.save");
        logger.debug(messageDetails);
        messageDAO.save(messageDetails);
    }

    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(String messageDetails) {
        GatewayMessageHandler messageHandler = getOmpManager().createGatewayMessageHandler();
        logger.info("Preparing messge for saving");
        saveMessage(messageHandler.prepareMessage(messageDetails));
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        logger.debug("Setting value of SMSCacheServiceImpl.coreManager");
        logger.debug(coreManager);
        this.coreManager = coreManager;
    }

    /**
     * @return the ompManager
     */
    public OMPManager getOmpManager() {
        return ompManager;
    }

    /**
     * @param ompManager the ompManager to set
     */
    public void setOmpManager(OMPManager ompManager) {
        logger.debug("Setting value of SMSCacheServiceImpl.ompManager");
        logger.debug(ompManager);
        this.ompManager = ompManager;
    }

}
