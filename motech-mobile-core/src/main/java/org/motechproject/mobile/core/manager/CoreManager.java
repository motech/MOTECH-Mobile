package org.motechproject.mobile.core.manager;

import org.motechproject.mobile.core.dao.*;
import org.motechproject.mobile.core.model.*;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.model.dao.imp.*;

import java.io.Serializable;

/**
 * This interface is responsible for the creation of all objects
 * in the core module.
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * @author Joseph Djomeda (joseph@dreamoval.com)
 * Date Created: Aug 3, 2009
 */
public interface CoreManager extends Serializable {

    /**
     * Creates a new instance of GatewayRequest
     * @return The newly created GatewayRequest
     */
    public GatewayRequest createGatewayRequest(MotechContext motechContext);

    /**
     * Creates a new instance of GatewayRequestDetails
     * @return The newly created GatewayRequestDetails
     */
    public GatewayRequestDetails createGatewayRequestDetails(MotechContext motechContext);

    /**
     * Creates a new instance of GatewayResponse
     * @return The newly created GatewayResponse
     */
    public GatewayResponse createGatewayResponse(MotechContext motechContext);

    /**
     *  Creates a new instance of Transition
     * @param motechContext takes a instance of MotechContext
     * @return the newly created Transition
     */
    public Transition createTransition(MotechContext motechContext);

    /**
     *  Creates a new instance of MessageRequest
     * @param motechContext takes a instance of MotechContext
     * @return the newly created MessageRequest
     */
    public MessageRequest createMessageRequest(MotechContext motechContext);

    /**
     *  Creates a new instance of Language
     * @param motechContext takes a instance of MotechContext
     * @return the newly created Language
     */
    public Language createLanguage(MotechContext motechContext);

    /**
     *  Creates a new instance of MessageTemplate
     * @param motechContext takes a instance of MotechContext
     * @return the newly created MessageTemplate
     */
    public MessageTemplate createMessageTemplate(MotechContext motechContext);

    /**
     *  Creates a new instance of NotificationType
     * @param motechContext takes a instance of MotechContext
     * @return the newly created NotificationType
     */
    public NotificationType createNotificationType(MotechContext motechContext);

    /**
     *  Creates a new instance of IncomingMessageSession
     * @return the newly created IncomingMessageSession
     */
    public IncomingMessageSession createIncomingMessageSession();

    /**
     *  Creates a new instance of IncomingMessage
     * @return the newly created IncomingMessage
     */
    public IncomingMessage createIncomingMessage();

    /**
     *  Creates a new instance of IncomingMessageResponse
     * @return the newly created IncomingMessageResponse
     */
    public IncomingMessageResponse createIncomingMessageResponse();

    /**
     *  Creates a new instance of IncomingMessageFormDefinition
     * @return the newly created IncomingMessageFormDefinition
     */
    public IncomingMessageFormDefinition createIncomingMessageFormDefinition();

    /**
     *  Creates a new instance of IncomingMessageFormParameterDefinition
     * @return the newly created IncomingMessageFormParameterDefinition
     */
    public IncomingMessageFormParameterDefinition createIncomingMessageFormParameterDefinition();

    /**
     *  Creates a new instance of IncomingMessageForm
     * @return the newly created IncomingMessageForm
     */
    public IncomingMessageForm createIncomingMessageForm();

    /**
     *  Creates a new instance of IncomingMessageFormParameter
     * @return the newly created IncomingMessageFormParameter
     */
    public IncomingMessageFormParameter createIncomingMessageFormParameter();

    /**
     * Creates a new instance of GatewayRequestDAO
     * @param motechContext takes a instance of MotechContext
     * @return The newly created instance of GatewayRequestDAO
     */
    public GatewayRequestDAO createGatewayRequestDAO(MotechContext motechContext);

    /**
     * Creates a new instance of GatewayRequestDetailsDAO
     * @param motechContext takes a instance of MotechContext
     * @return The newly created instance of GatewayRequestDetailsDAO
     */
    public GatewayRequestDetailsDAO createGatewayRequestDetailsDAO(MotechContext motechContext);

    /**
     * Creates a new instance of GatewayResponseDAO
     * @param motechContext takes a instance of MotechContext
     * @return The newly created GatewayResponseDAO
     */
    public GatewayResponseDAO createGatewayResponseDAO(MotechContext motechContext);

    /**
     * Creates a new instance of TransitionDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created TransitionDAO
     */
    public TransitionDAO createTransitionDAO(MotechContext motechContext);

    /**
     * Creates a new instance of MessageRequestDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created MessageRequestDAO
     */
    public MessageRequestDAO createMessageRequestDAO(MotechContext motechContext);

    /**
     * Creates a new instance of LanguageDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created LanguageDAO
     */
    public LanguageDAO createLanguageDAO(MotechContext motechContext);

    /**
     * Creates a new instance of MessateTemplateDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created MessageTemplateDAO
     */
    public MessageTemplateDAO createMessageTemplateDAO(MotechContext motechContext);

    /**
     * Creates a new instance of NotificationTypeDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created NotificationTypeDAO
     */
    public NotificationTypeDAO createNotificationTypeDAO(MotechContext motechContext);

    /**
     * Creates a new instance of IncomingMessageSessionDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created IncomingMessageSessionDAO
     */
    public IncomingMessageSessionDAO createIncomingMessageSessionDAO(MotechContext motechContext);

    /**
     * Creates a new instance of IncomingMessageDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created IncomingMessageDAO
     */
    public IncomingMessageDAO createIncomingMessageDAO(MotechContext motechContext);

    /**
     * Creates a new instance of IncomingMessageResponseDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created IncomingMessageResponseDAO
     */
    public IncomingMessageResponseDAO createIncomingMessageResponseDAO(MotechContext motechContext);

    /**
     * Creates a new instance of IncomingMessageFormDefintionDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created IncomingMessageFormDefinitionDAO
     */
    public IncomingMessageFormDefinitionDAO createIncomingMessageFormDefinitionDAO(MotechContext motechContext);

    /**
     * Creates a new instance of IncomingMessageFormParameterDefintionDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created IncomingMessageFormParameterDefinitionDAO
     */
    public IncomingMessageFormParameterDefinitionDAO createIncomingMessageFormParameterDefinitionDAO(MotechContext motechContext);

    /**
     * Creates a new instance of IncomingMessageFormDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created IncomingMessageFormDAO
     */
    public IncomingMessageFormDAO createIncomingMessageFormDAO(MotechContext motechContext);

    /**
     * Creates a new instance of IncomingMessageFormParameterDAO
     * @param motechContext takes a instance of MotechContext
     * @return the newly created IncomingMessageFormParameterDAO
     */
    public IncomingMessageFormParameterDAO createIncomingMessageFormParameterDAO(MotechContext motechContext);
    
    /**
     * @return the sessionContainer
     */
    SessionContainer getSessionContainer();

    /**
     * @param sessionContainer the sessionContainer to set
     */
    void setSessionContainer(SessionContainer sessionContainer);

    /**
     * Creates a new instance of MotechContext
     * @return The newly created instance of MotechContext
     */
    MotechContext createMotechContext();
}