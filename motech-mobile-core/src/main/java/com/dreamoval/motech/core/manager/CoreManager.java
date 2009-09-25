

package com.dreamoval.motech.core.manager;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import com.dreamoval.motech.core.dao.SessionContainer;
import com.dreamoval.motech.core.dao.TransitionDAO;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
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
     * Creates a new instance of MessageDetails
     * 
     * @return The newly created MessageDetails
     */
    public GatewayRequest createMessageDetails(MotechContext motechContext);

    /**
     * Creates a new instance of ResponseDetails
     *
     * @return The newly created ResponseDetails
     */
    public GatewayResponse createResponseDetails(MotechContext motechContext);

    /**
     *  Creates a new instance of Transition
     * @param motechContext takes a instance of MotechContext
     * @return the newly created Transition
     */
    public Transition createTransition(MotechContext motechContext);

    /**
     * Creates a new instance of MessageDetailsDAO
     *
     * @return The newly created instance of MessageDetailsDAO
     */
    public GatewayRequestDAO createMessageDetailsDAO(MotechContext motechContext);

    /**
     * Creates a new instance of ResponseDetailsDAO
     *
     * @return The newly created ResponseDetailsDAO
     */
    public GatewayResponseDAO createResponseDetailsDAO(MotechContext motechContext);

    public TransitionDAO createTransitionDAO(MotechContext motechContext);
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
