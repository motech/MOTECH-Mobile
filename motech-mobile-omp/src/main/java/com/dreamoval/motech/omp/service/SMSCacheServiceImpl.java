/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.MessageDetailsManager;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 15-JUL-2009
 */
public class SMSCacheServiceImpl implements SMSCacheService {

    private MessageDetailsManager messageManager;

    /**
     *
     * @see SMSCacheService.saveMessage
     */
    public void saveMessage(MessageDetails messageDetails) {
        messageManager.saveMessage(messageDetails);
    }

    /**
     *
     * @see SMSCacheService.saveMessage
     */
    public void saveMessage(String messageDetails) {
        messageManager.saveMessage(messageDetails);
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
     * @return the messageManager
     */
    public MessageDetailsManager getMessageManager() {
        return messageManager;
    }

    /**
     * @param messageManager the messageManager to set
     */
    public void setMessageManager(MessageDetailsManager messageManager) {
        this.messageManager = messageManager;
    }

}
