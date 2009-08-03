/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.manager;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.dao.SessionContainer;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.service.MotechContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Aug 3, 2009
 */
public class CoreManagerImpl implements CoreManager, ApplicationContextAware {

    ApplicationContext applicationContext;
    private SessionContainer sessionContainer;

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createMessageDetails() }
     */
    public MessageDetails createMessageDetails(MotechContext motechContext) {
        return (MessageDetails) getInstance("messageDetails", MessageDetails.class);
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createResponseDetails() }
     */
    public ResponseDetails createResponseDetails(MotechContext motechContext) {
        return (ResponseDetails) getInstance("responseDetails", ResponseDetails.class);
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createMessageDetailsDAO() }
     */
    public MessageDetailsDAO createMessageDetailsDAO(MotechContext motechContext) {
        MessageDetailsDAO mdDAO = (MessageDetailsDAO) getInstance("messageDetailsDAO", MessageDetailsDAO.class);

        mdDAO.setDBSession(motechContext.getDBSession());

        return mdDAO;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createResponseDetailsDAO() }
     */
    public ResponseDetailsDAO createResponseDetailsDAO(MotechContext motechContext) {
        ResponseDetailsDAO rdDAO = (ResponseDetailsDAO) getInstance("responseDetailsDAO", ResponseDetailsDAO.class);
        
        rdDAO.setDBSession(motechContext.getDBSession());

        return rdDAO;
    }
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#getSessionContainer() }
     */
    public SessionContainer getSessionContainer() {
        return sessionContainer;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#setSessionContainer(com.dreamoval.motech.core.dao.SessionContainer) 
     */
    public void setSessionContainer(SessionContainer sessionContainer) {
        this.sessionContainer = sessionContainer;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createMotechContext() }
     */
    public MotechContext createMotechContext() {
       MotechContext<Session, Transaction> mc = (MotechContext) getInstance("motechContext", MotechContext.class);
       mc.getDBSession().setSession(sessionContainer.requestSession());

       return mc;
    }

    private Object getInstance(String beanName, Class<?> reqType){
        return applicationContext.getBean(beanName, reqType);
    }
}
