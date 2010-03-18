/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.model.dao.imp.IncomingMessageSessionDAO;
import org.motechproject.mobile.core.model.IncMessageResponseStatus;
import org.motechproject.mobile.core.model.IncMessageSessionStatus;
import org.motechproject.mobile.core.model.IncomingMessage;
import org.motechproject.mobile.core.model.IncomingMessageResponse;
import org.motechproject.mobile.core.model.IncomingMessageSession;
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
