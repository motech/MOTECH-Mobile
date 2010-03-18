package org.motechproject.mobile.core.manager;

import org.motechproject.mobile.core.dao.*;
import org.motechproject.mobile.core.model.*;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.core.util.MotechIDGenerator;
import org.motechproject.mobile.model.dao.imp.*;
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
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createGatewayRequest(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public GatewayRequest createGatewayRequest(MotechContext motechContext) {
        logger.info("Creating GatewayRequest instance");
        GatewayRequest result = (GatewayRequest) getInstance("gatewayRequest", GatewayRequest.class);

        logger.info("Setting generated Id to the GatewayRequest instance created");
        result.setId(MotechIDGenerator.generateID());

        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createGatewayRequestDetails(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public GatewayRequestDetails createGatewayRequestDetails(MotechContext motechContext) {
        logger.info("Creating GatewayRequestDetails instance");
        GatewayRequestDetails result = (GatewayRequestDetails) getInstance("gatewayRequestDetails", GatewayRequestDetails.class);

        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createLanguage() }
     */
    public Language createLanguage(MotechContext motechContext) {
        logger.info("Creating Language instance");
        Language result = (Language) getInstance("language", Language.class);
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createResponseDetails() }
     */
    public GatewayResponse createGatewayResponse(MotechContext motechContext) {
        logger.info("Creating GatewayResponse  instance");
        GatewayResponse result = (GatewayResponse) getInstance("gatewayResponse", GatewayResponse.class);

        logger.info("Setting generated Id to the GatewayResponse instance created");
        result.setId(MotechIDGenerator.generateID());

        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createTransition(org.motechproject.mobile.core.service.MotechContext) }
     */
    public Transition createTransition(MotechContext motechContext) {
        logger.info("Creating Transition instance");
        Transition result = (Transition) getInstance("transition", Transition.class);

        logger.info("Setting generated Id to the Transition instance created");
        result.setId(MotechIDGenerator.generateID());

        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createMessageRequest(org.motechproject.mobile.core.service.MotechContext) }
     */
    public MessageRequest createMessageRequest(MotechContext motechContext) {
        logger.info("Creating MessageRequest instance");
        MessageRequest result = (MessageRequest) getInstance("messageRequest", MessageRequest.class);

        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createMessageTemplate(org.motechproject.mobile.core.service.MotechContext) }
     */
    public MessageTemplate createMessageTemplate(MotechContext motechContext) {
        logger.info("Creating MessageTemplate instance");
        MessageTemplate result = (MessageTemplate) getInstance("messageTemplate", MessageTemplate.class);

        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createNotificationType(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public NotificationType createNotificationType(MotechContext motechContext) {
        logger.info("Creating NotificationType instance");
        NotificationType result = (NotificationType) getInstance("notificationType", NotificationType.class);

        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageSession()   }
     */
    public IncomingMessageSession createIncomingMessageSession() {
        logger.info("Creating IncomingMessageSession instance");
        IncomingMessageSession result = (IncomingMessageSession) getInstance("incomingMessageSession", IncomingMessageSession.class);
        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessage()   }
     */
    public IncomingMessage createIncomingMessage() {
        logger.info("Creating IncomingMessage instance");
        IncomingMessage result = (IncomingMessage) getInstance("incomingMessage", IncomingMessage.class);
        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageResponse()   }
     */
    public IncomingMessageResponse createIncomingMessageResponse() {
        logger.info("Creating IncomingMessageResponse instance");
        IncomingMessageResponse result = (IncomingMessageResponse) getInstance("incomingMessageResponse", IncomingMessageResponse.class);
        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageFormDefinition()    }
     */
    public IncomingMessageFormDefinition createIncomingMessageFormDefinition() {
        logger.info("Creating IncomingMessageFormDefinition instance");
        IncomingMessageFormDefinition result = (IncomingMessageFormDefinition) getInstance("incomingMessageFormDefinition", IncomingMessageFormDefinition.class);
        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageFormParameterDefinition()    }
     */
    public IncomingMessageFormParameterDefinition createIncomingMessageFormParameterDefinition() {
        logger.info("Creating IncomingMessageFormParameterDefinition instance");
        IncomingMessageFormParameterDefinition result = (IncomingMessageFormParameterDefinition) getInstance("incomingMessageFormParameterDefinition", IncomingMessageFormParameterDefinition.class);
        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageForm()    }
     */
    public IncomingMessageForm createIncomingMessageForm() {
        logger.info("Creating IncomingMessageForm instance");
        IncomingMessageForm result = (IncomingMessageForm) getInstance("incomingMessageForm", IncomingMessageForm.class);
        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageFormParameter()    }
     */
    public IncomingMessageFormParameter createIncomingMessageFormParameter() {
        logger.info("Creating IncomingMessageFormParameter instance");
        IncomingMessageFormParameter result = (IncomingMessageFormParameter) getInstance("incomingMessageFormParameter", IncomingMessageFormParameter.class);
        result.setId(MotechIDGenerator.generateID());
        return result;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createGatewayRequestDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public GatewayRequestDAO createGatewayRequestDAO(MotechContext motechContext) {
        logger.info("Creating GatewayRequestDAO instance");
        GatewayRequestDAO mdDAO = (GatewayRequestDAO) getInstance("gatewayRequestDAO", GatewayRequestDAO.class);

        mdDAO.setDBSession(motechContext.getDBSession());

        return mdDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createGatewayRequestDetailsDAO(org.motechproject.mobile.core.service.MotechContext)}
     */
    public GatewayRequestDetailsDAO createGatewayRequestDetailsDAO(MotechContext motechContext) {
        logger.info("Creating GatewayRequestDetailsDAO instance");
        GatewayRequestDetailsDAO mdDAO = (GatewayRequestDetailsDAO) getInstance("gatewayRequestDetailsDAO", GatewayRequestDetailsDAO.class);

        mdDAO.setDBSession(motechContext.getDBSession());

        return mdDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createGatewayResponseDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public GatewayResponseDAO createGatewayResponseDAO(MotechContext motechContext) {
        logger.info("Creating GatewayResponseDAO instance");
        GatewayResponseDAO rdDAO = (GatewayResponseDAO) getInstance("gatewayResponseDAO", GatewayResponseDAO.class);

        rdDAO.setDBSession(motechContext.getDBSession());

        return rdDAO;
    }

    /**
     *
    @see {@link org.motechproject.mobile.core.manager.CoreManager#createMessageRequestDAO() }
     */
    public MessageRequestDAO createMessageRequestDAO(MotechContext motechContext) {
        logger.info("Creating ResponseDetailsDAO instance");
        MessageRequestDAO mrDAO = (MessageRequestDAO) getInstance("messageRequestDAO", MessageRequestDAO.class);
        mrDAO.setDBSession(motechContext.getDBSession());
        return mrDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createTransitionDAO(org.motechproject.mobile.core.service.MotechContext) }
     */
    public TransitionDAO createTransitionDAO(MotechContext motechContext) {
        logger.info("Creating TransitionDAO instance");
        TransitionDAO tDAO = (TransitionDAO) getInstance("transitionDAO", TransitionDAO.class);
        tDAO.setDBSession(motechContext.getDBSession());
        return tDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createLanguageDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public LanguageDAO createLanguageDAO(MotechContext motechContext) {
        logger.info("Creating LanguageDAO instance");
        LanguageDAO lDAO = (LanguageDAO) getInstance("languageDAO", LanguageDAO.class);
        lDAO.setDBSession(motechContext.getDBSession());
        return lDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createMessageTemplate(org.motechproject.mobile.core.service.MotechContext)}
     */
    public MessageTemplateDAO createMessageTemplateDAO(MotechContext motechContext) {
        logger.info("Creating messageTemplateDAO instance");
        MessageTemplateDAO mDAO = (MessageTemplateDAO) getInstance("messageTemplateDAO", MessageTemplateDAO.class);
        mDAO.setDBSession(motechContext.getDBSession());
        return mDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createNotificationTypeDAO(org.motechproject.mobile.core.service.MotechContext)}
     */
    public NotificationTypeDAO createNotificationTypeDAO(MotechContext motechContext) {
        logger.info("Creating notiticationTypeDAO instance");
        NotificationTypeDAO nDAO = (NotificationTypeDAO) getInstance("notificationTypeDAO", NotificationTypeDAO.class);
        nDAO.setDBSession(motechContext.getDBSession());
        return nDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public IncomingMessageSessionDAO createIncomingMessageSessionDAO(MotechContext motechContext) {
        logger.info("Creating IncomingMessageSessionDAO instance");
        IncomingMessageSessionDAO imsDAO = (IncomingMessageSessionDAO) getInstance("incomingMessageSessionDAO", IncomingMessageSessionDAO.class);
        imsDAO.setDBSession(motechContext.getDBSession());
        return imsDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public IncomingMessageDAO createIncomingMessageDAO(MotechContext motechContext) {
        logger.info("Creating IncomingMessageDAO instance");
        IncomingMessageDAO imDAO = (IncomingMessageDAO) getInstance("incomingMessageDAO", IncomingMessageDAO.class);
        imDAO.setDBSession(motechContext.getDBSession());
        return imDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageResponseDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public IncomingMessageResponseDAO createIncomingMessageResponseDAO(MotechContext motechContext) {
        logger.info("Creating IncomingMessageResponseDAO instance");
        IncomingMessageResponseDAO imDAO = (IncomingMessageResponseDAO) getInstance("incomingMessageResponseDAO", IncomingMessageResponseDAO.class);
        imDAO.setDBSession(motechContext.getDBSession());
        return imDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageFromDefinitionDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public IncomingMessageFormDefinitionDAO createIncomingMessageFormDefinitionDAO(MotechContext motechContext) {
        logger.info("Creating IncomingMessageFormDefinitionDAO instance");
        IncomingMessageFormDefinitionDAO imDAO = (IncomingMessageFormDefinitionDAO) getInstance("incomingMessageFormDefinitionDAO", IncomingMessageFormDefinitionDAO.class);
        imDAO.setDBSession(motechContext.getDBSession());
        return imDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageFormParameterDefinitionDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public IncomingMessageFormParameterDefinitionDAO createIncomingMessageFormParameterDefinitionDAO(MotechContext motechContext) {
        logger.info("Creating IncomingMessageFormParameterDefinitionDAO instance");
        IncomingMessageFormParameterDefinitionDAO imDAO = (IncomingMessageFormParameterDefinitionDAO) getInstance("incomingMessageFormParameterDefinitionDAO", IncomingMessageFormParameterDefinitionDAO.class);
        imDAO.setDBSession(motechContext.getDBSession());
        return imDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageFormDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public IncomingMessageFormDAO createIncomingMessageFormDAO(MotechContext motechContext) {
        logger.info("Creating IncomingMessageFormDAO instance");
        IncomingMessageFormDAO imDAO = (IncomingMessageFormDAO) getInstance("incomingMessageFormDAO", IncomingMessageFormDAO.class);
        imDAO.setDBSession(motechContext.getDBSession());
        return imDAO;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createIncomingMessageFormParameterDAO(org.motechproject.mobile.core.service.MotechContext)  }
     */
    public IncomingMessageFormParameterDAO createIncomingMessageFormParameterDAO(MotechContext motechContext) {
        logger.info("Creating IncomingMessageFormParameterDAO instance");
        IncomingMessageFormParameterDAO imDAO = (IncomingMessageFormParameterDAO) getInstance("incomingMessageFormParameterDAO", IncomingMessageFormParameterDAO.class);
        imDAO.setDBSession(motechContext.getDBSession());
        return imDAO;
    }

    /**
     *
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("Setting te applicationContext property");
        this.applicationContext = applicationContext;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#getSessionContainer() }
     */
    public SessionContainer getSessionContainer() {
        logger.info("Getting the sessionContainer");
        return sessionContainer;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#setSessionContainer(org.motechproject.mobile.core.dao.SessionContainer) 
     */
    public void setSessionContainer(SessionContainer sessionContainer) {
        logger.info("Setting the sessionContainer");
        this.sessionContainer = sessionContainer;
    }

    /**
     * @see {@link org.motechproject.mobile.core.manager.CoreManager#createMotechContext() }
     */
    public MotechContext createMotechContext() {
        logger.info("Calling createMotechContext method");
        MotechContext<Session, Transaction> mc = (MotechContext) getInstance("motechContext", MotechContext.class);
        mc.getDBSession().setSession(sessionContainer.requestSession());
        return mc;
    }

    private Object getInstance(String beanName, Class<?> reqType) {
        logger.debug("Calling getInstance");
        return applicationContext.getBean(beanName, reqType);
    }
}
