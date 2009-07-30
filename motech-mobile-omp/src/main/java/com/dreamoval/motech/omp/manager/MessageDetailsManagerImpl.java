/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.model.MessageDetails;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-Apr-2009
 *
 * Handles all creation, modification and persistence functions of the MessageDetails entity
 */
public class MessageDetailsManagerImpl implements MessageDetailsManager {
    private MessageDetailsDAO messageDetailsDao;
    private GatewayMessageHandler handler;

    /**
     *
     * @see MessageDetailsManager.saveMessage
     */
    public boolean saveMessage(MessageDetails messageDetails) {
        return this.messageDetailsDao.StoreMessage(messageDetails);
    }

    /**
     *
     * @see MessageDetailsManager.saveMessage
     */
    public boolean saveMessage(String messageDetails) {
        return saveMessage((handler.prepareMessage(messageDetails)));
    }

    /**
     *
     * @see MessageDetailsManager.updateMessage
     */
    public boolean updateMessage(MessageDetails messageDetails) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @see MessageDetailsManager.updateMessage
     */
    public boolean updateMessage(String messageDetails) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the messageDetailsDao
     */
    public MessageDetailsDAO getMessageDetailsDao() {
        return messageDetailsDao;
    }

    /**
     * @param messageDetailsDao the messageDetailsDao to set
     */
    public void setMessageDetailsDao(MessageDetailsDAO messageDetailsDao) {
        this.messageDetailsDao = messageDetailsDao;
    }

    /**
     * @return the handler
     */
    public GatewayMessageHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(GatewayMessageHandler handler) {
        this.handler = handler;
    }
}
