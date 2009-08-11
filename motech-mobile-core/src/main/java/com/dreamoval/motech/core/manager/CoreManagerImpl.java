

package com.dreamoval.motech.core.manager;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.dao.SessionContainer;
import com.dreamoval.motech.core.dao.TransitionDAO;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.model.Transition;
import com.dreamoval.motech.core.service.MotechContext;

import com.dreamoval.motech.core.util.MotechIDGenerator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>Reponsible for the creation of all entities in the Core module</p>
 * 
 * @author Henry Sampson (henry@dreamoval.com)
 * @author Josepoh Djomeda (joseph@dreamoval.com)
 * Date Created: Aug 3, 2009
 */
public class CoreManagerImpl implements CoreManager, ApplicationContextAware {

    private static Logger logger = Logger.getLogger(CoreManagerImpl.class);
    ApplicationContext applicationContext;
    private SessionContainer sessionContainer;

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createMessageDetails() }
     */
    public MessageDetails createMessageDetails(MotechContext motechContext) {
        logger.info("Creating MessageDetails instance");
        MessageDetails result = (MessageDetails) getInstance("messageDetails", MessageDetails.class);

        logger.info("Setting generated Id to the MessageDetails instance created");
        result.setId(MotechIDGenerator.generateID());

        return result;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createResponseDetails() }
     */
    public ResponseDetails createResponseDetails(MotechContext motechContext) {
        logger.info("Creating ResonseDetails instance");
        ResponseDetails result = (ResponseDetails) getInstance("responseDetails", ResponseDetails.class);

        logger.info("Setting generated Id to the ResponseDetails instance created");
        result.setId(MotechIDGenerator.generateID());

        return result;
    }
    
    /**
     * 
     * @param motechContext
     * @return
     */
    public Transition createTransition(MotechContext motechContext) {
        logger.info("Creating Transition instance");
        Transition result = (Transition) getInstance("transition", Transition.class);

        logger.info("Setting generated Id to the Transition instance created");
        result.setId(MotechIDGenerator.generateID());

        return result;
    }
    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createMessageDetailsDAO() }
     */
    public MessageDetailsDAO createMessageDetailsDAO(MotechContext motechContext) {
        logger.info("Creating MessageDetailsDAO instance");
        MessageDetailsDAO mdDAO = (MessageDetailsDAO) getInstance("messageDetailsDAO", MessageDetailsDAO.class);

        mdDAO.setDBSession(motechContext.getDBSession());

        return mdDAO;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createResponseDetailsDAO() }
     */
    public ResponseDetailsDAO createResponseDetailsDAO(MotechContext motechContext) {
        logger.info("Creating ResponseDetailsDAO instance");
        ResponseDetailsDAO rdDAO = (ResponseDetailsDAO) getInstance("responseDetailsDAO", ResponseDetailsDAO.class);
        
        rdDAO.setDBSession(motechContext.getDBSession());

        return rdDAO;
    }
    
    /**
     *
     * @param motechContext
     * @return
     */
    public TransitionDAO createTransitionDAO(MotechContext motechContext) {
        logger.info("Creating TransitionDAO instance");
        TransitionDAO tDAO =(TransitionDAO)getInstance("transitionDAO", TransitionDAO.class);
        tDAO.setDBSession(motechContext.getDBSession());
        return tDAO;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("Setting te applicationContext property");
        this.applicationContext = applicationContext;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#getSessionContainer() }
     */
    public SessionContainer getSessionContainer() {
        logger.info("Getting the sessionContainer");
        return sessionContainer;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#setSessionContainer(com.dreamoval.motech.core.dao.SessionContainer) 
     */
    public void setSessionContainer(SessionContainer sessionContainer) {
        logger.info("Setting the sessionContainer");
        this.sessionContainer = sessionContainer;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createMotechContext() }
     */
    public MotechContext createMotechContext() {
        logger.info("Calling createMotechContext method");
       MotechContext<Session, Transaction> mc = (MotechContext) getInstance("motechContext", MotechContext.class);
       mc.getDBSession().setSession(sessionContainer.requestSession());
       return mc;
    }

    private Object getInstance(String beanName, Class<?> reqType){
        logger.debug("Calling getInstance");
        return applicationContext.getBean(beanName, reqType);
    }

   

    
}
