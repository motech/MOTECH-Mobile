

package com.dreamoval.motech.core.manager;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.GatewayRequestDetailsDAO;
import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import com.dreamoval.motech.core.dao.LanguageDAO;
import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.dao.MessageTemplateDAO;
import com.dreamoval.motech.core.dao.NotificationTypeDAO;
import com.dreamoval.motech.core.dao.SessionContainer;
import com.dreamoval.motech.core.dao.TransitionDAO;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.NotificationType;
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
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createGatewayRequest(com.dreamoval.motech.core.service.MotechContext)  }
     */
    public GatewayRequest createGatewayRequest(MotechContext motechContext) {
        logger.info("Creating MessageDetails instance");
        GatewayRequest result = (GatewayRequest) getInstance("messageDetails", GatewayRequest.class);

        logger.info("Setting generated Id to the MessageDetails instance created");
        result.setId(MotechIDGenerator.generateID());

        return result;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createGatewayRequestDetails(com.dreamoval.motech.core.service.MotechContext)  }
     */
    public GatewayRequestDetails createGatewayRequestDetails(MotechContext motechContext) {
        logger.info("Creating GatewayRequestDetails instance");
        GatewayRequestDetails result = (GatewayRequestDetails) getInstance("gatewayRequestDetails", GatewayRequestDetails.class);
       
        return result;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createLanguage() }
     */
    public Language createLanguage(MotechContext motechContext) {
        logger.info("Creating Language instance");
        Language result = (Language) getInstance("language", Language.class);
        return result;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createResponseDetails() }
     */
    public GatewayResponse createGatewayResponse(MotechContext motechContext) {
        logger.info("Creating ResonseDetails instance");
        GatewayResponse result = (GatewayResponse) getInstance("responseDetails", GatewayResponse.class);

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

    public MessageRequest createMessageRequest(MotechContext motechContext) {
        logger.info("Creating MessageRequest instance");
        MessageRequest result = (MessageRequest) getInstance("messageRequest", MessageRequest.class);

        return result;
    }

    public MessageTemplate createMessageTemplate(MotechContext motechContext) {
        logger.info("Creating MessageTemplate instance");
        MessageTemplate result = (MessageTemplate) getInstance("messageTemplate", MessageTemplate.class);

        return result;
    }

    public NotificationType createNotificationType(MotechContext motechContext) {
        logger.info("Creating NotificationType instance");
        NotificationType result = (NotificationType) getInstance("notificationType", NotificationType.class);

        return result;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createMessageDetailsDAO() }
     */
    public GatewayRequestDAO createGatewayRequestDAO(MotechContext motechContext) {
        logger.info("Creating MessageDetailsDAO instance");
        GatewayRequestDAO mdDAO = (GatewayRequestDAO) getInstance("messageDetailsDAO", GatewayRequestDAO.class);

        mdDAO.setDBSession(motechContext.getDBSession());

        return mdDAO;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createGatewayRequestDetailsDAO(com.dreamoval.motech.core.service.MotechContext)}
     */
    public GatewayRequestDetailsDAO createGatewayRequestDetailsDAO(MotechContext motechContext) {
        logger.info("Creating GatewayRequestDetailsDAO instance");
        GatewayRequestDetailsDAO mdDAO = (GatewayRequestDetailsDAO) getInstance("gatewayRequestDetailsDAO", GatewayRequestDetailsDAO.class);

        mdDAO.setDBSession(motechContext.getDBSession());

        return mdDAO;
    }

    /**
     * @see {@link com.dreamoval.motech.core.manager.CoreManager#createResponseDetailsDAO() }
     */
    public GatewayResponseDAO createGatewayResponseDAO(MotechContext motechContext) {
        logger.info("Creating ResponseDetailsDAO instance");
        GatewayResponseDAO rdDAO = (GatewayResponseDAO) getInstance("responseDetailsDAO", GatewayResponseDAO.class);
        
        rdDAO.setDBSession(motechContext.getDBSession());

        return rdDAO;
    }

    /**
     *
     @see {@link com.dreamoval.motech.core.manager.CoreManager#createMessageRequestDAO() }
     */
    public MessageRequestDAO createMessageRequestDAO(MotechContext motechContext) {
        logger.info("Creating ResponseDetailsDAO instance");
        MessageRequestDAO mrDAO =(MessageRequestDAO) getInstance("messageRequestDAO", MessageRequestDAO.class);
        mrDAO.setDBSession(motechContext.getDBSession());
        return mrDAO;
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

    /**
     *
     * @param motechContext
     * @return
     */
    public LanguageDAO createLanguageDAO(MotechContext motechContext) {
        logger.info("Creating LanguageDAO instance");
        LanguageDAO lDAO =(LanguageDAO)getInstance("languageDAO", LanguageDAO.class);
        lDAO.setDBSession(motechContext.getDBSession());
        return lDAO;
    }


    /**
     *
     * @param motechContext
     * @return
     */
    public MessageTemplateDAO createMessageTemplateDAO(MotechContext motechContext) {
        logger.info("Creating messageTemplateDAO instance");
        MessageTemplateDAO mDAO =(MessageTemplateDAO)getInstance("messageTemplateDAO", MessageTemplateDAO.class);
        mDAO.setDBSession(motechContext.getDBSession());
        return mDAO;
    }

    /**
     *
     * @param motechContext
     * @return
     */
    public NotificationTypeDAO createNotificationTypeDAO(MotechContext motechContext) {
        logger.info("Creating notiticationTypeDAO instance");
        NotificationTypeDAO nDAO =(NotificationTypeDAO)getInstance("notificationTypeDAO", NotificationTypeDAO.class);
        nDAO.setDBSession(motechContext.getDBSession());
        return nDAO;
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
