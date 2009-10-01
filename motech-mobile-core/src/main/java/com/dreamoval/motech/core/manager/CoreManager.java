

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
import java.io.Serializable;

/**
 * This interface is responsible for the creation of all objects
 * in the core module.
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * @author Joseph Djomeda (joseph@dreamoval.com)
 * Date Created: Aug 3, 2009
 */
public interface CoreManager extends Serializable{

    /**
     * Creates a new instance of GatewayRequest
     * 
     * @return The newly created GatewayRequest
     */
    public GatewayRequest createGatewayRequest(MotechContext motechContext);

    /**
     * Creates a new instance of MessageDetails
     *
     * @return The newly created MessageDetails
     */
    public GatewayRequestDetails createGatewayRequestDetails(MotechContext motechContext);

    /**
     * Creates a new instance of ResponseDetails
     *
     * @return The newly created ResponseDetails
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
     * Creates a new instance of GatewayRequestDAO
     *
     * @return The newly created instance of GatewayRequestDAO
     */
    public GatewayRequestDAO createGatewayRequestDAO(MotechContext motechContext);

    /**
     * Creates a new instance of GatewayRequestDetailsDAO
     *
     * @return The newly created instance of GatewayRequestDetailsDAO
     */
    public GatewayRequestDetailsDAO createGatewayRequestDetailsDAO(MotechContext motechContext);

    /**
     * Creates a new instance of ResponseDetailsDAO
     *
     * @return The newly created ResponseDetailsDAO
     */
    public GatewayResponseDAO createGatewayResponseDAO(MotechContext motechContext);

    /**
     *
     * @param motechContext
     * @return
     */
    public TransitionDAO createTransitionDAO(MotechContext motechContext);


    /**
     * Creates a new instance of MessageRequestDAO
     * @param motechContext motechcontext to pass
     * @return the newly created MessageRequestDAO
     */
    public MessageRequestDAO createMessageRequestDAO(MotechContext motechContext);

    /**
     * Creates a new instance of LanguageDAO
     * @param motechContext motechcontext to pass
     * @return the newly created LanguageDAO
     */

    public LanguageDAO createLanguageDAO(MotechContext motechContext);

    /**
     * Creates a new instance of MessateTemplateDAO
     * @param motechContext motechcontext to pass
     * @return the newly created MessageTemplateDAO
     */
    public MessageTemplateDAO createMessageTemplateDAO(MotechContext motechContext);

    /**
     * Creates a new instance of NotificationTypeDAO
     * @param motechContext motechcontext to pass
     * @return the newly created NotificationTypeDAO
     */
    public NotificationTypeDAO createNotificationTypeDAO(MotechContext motechContext);
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
     *
     * @return The newly created instance of MotechContext
     */
    MotechContext createMotechContext();
}
