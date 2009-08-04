/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import com.dreamoval.motech.omp.manager.OMPManager;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 15-JUL-2009
 */
public class SMSCacheServiceImpl implements SMSCacheService {

    private CoreManager coreManager;
    private MotechContext motechContext;
    private OMPManager ompManager;

    /**
     *
     * @see SMSCacheService.saveMessage
     */
    public void saveMessage(MessageDetails messageDetails) {
        MessageDetailsDAO messageDAO = coreManager.createMessageDetailsDAO(getMotechContext());
        messageDAO.save(messageDetails);
    }

    /**
     *
     * @see SMSCacheService.saveMessage
     */
    public void saveMessage(String messageDetails) {
        GatewayMessageHandler messageHandler = getOmpManager().createGatewayMessageHandler();
        saveMessage(messageHandler.prepareMessage(messageDetails));
    }

    /**
     *
     * @see SMSCacheService.updateMessage
     */
    public boolean updateMessage(MessageDetails messageDetails) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @see SMSCacheService.updateMessage
     */
    public boolean updateMessage(String messageDetails) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        this.coreManager = coreManager;
    }

    /**
     * @return the motechContext
     */
    public MotechContext getMotechContext() {
        return motechContext;
    }

    /**
     * @param motechContext the motechContext to set
     */
    public void setMotechContext(MotechContext motechContext) {
        this.motechContext = motechContext;
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
        this.ompManager = ompManager;
    }

}
