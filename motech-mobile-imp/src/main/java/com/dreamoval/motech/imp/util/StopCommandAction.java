/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageSessionDAO;
import com.dreamoval.motech.core.model.IncMessageResponseStatus;
import com.dreamoval.motech.core.model.IncMessageSessionStatus;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageResponse;
import com.dreamoval.motech.core.model.IncomingMessageSession;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Cancels processing of an IncomingMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public class StopCommandAction implements CommandAction{
    private CoreManager coreManager;

    /**
     * 
     * @see CommandAction.execute
     */
    public synchronized IncomingMessageResponse execute(IncomingMessage message, String requesterPhone, MotechContext context) {
        ///TODO fetch current open session for requester
        IncomingMessageSession imSession = coreManager.createIncomingMessageSession();
        imSession.setDateEnded(new Date());
        imSession.setLastActivity(new Date());
        imSession.setMessageSessionStatus(IncMessageSessionStatus.STOPPED);

        IncomingMessageResponse response = coreManager.createIncomingMessageResponse();
        response.setContent("Form processing stopped successfully");
        response.setDateCreated(new Date());
        response.setIncomingMessage(message);
        response.setMessageResponseStatus(IncMessageResponseStatus.SAVED);
        
        message.setIncomingMessageResponse(response);
        imSession.getIncomingMessages().add(message);

        IncomingMessageSessionDAO sessionDao = coreManager.createIncomingMessageSessionDAO(context);
        Transaction tx = ((Session)sessionDao.getDBSession().getSession()).beginTransaction();
        sessionDao.save(imSession);
        tx.commit();

        return response;
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

}
