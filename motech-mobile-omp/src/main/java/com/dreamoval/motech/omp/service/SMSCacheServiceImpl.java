/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.model.MessageDetails;

/**
 *
 * @author Yoofi
 */
public class SMSCacheServiceImpl implements SMSCacheService {

    private MessageDetailsDAO messageDetailsDao;

    public boolean saveMessage(MessageDetails messageDetails) {
        this.messageDetailsDao.StoreMessage(messageDetails);
        return true;
    }

    public boolean updateMessage(MessageDetails messageDetails) {
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

}
