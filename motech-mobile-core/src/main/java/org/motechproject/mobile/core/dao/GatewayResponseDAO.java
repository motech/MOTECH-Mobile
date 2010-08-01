package org.motechproject.mobile.core.dao;

import java.util.List;

import org.motechproject.mobile.core.model.GatewayResponse;

/**
 * GatewayResponseDao is an interface that defines only methods and attributes that are specific to GatewayResponse entity
 * Date: Jul 29, 2009
 * 
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface GatewayResponseDAO<T extends GatewayResponse> extends GenericDAO<T> {

    /**
     * Method to select the most recent GatewayResponse based on the passed requestId
     * @param requestId the id of the message associated with the required GatewayResponse objects
     * @return a single GatewayResponse object
     */
    public GatewayResponse getMostRecentResponseByMessageId(Long messageId);

    /**
     * Method to select GatewayResponse based on its parent GatewayRequest tryNumber
     * @param maxTries the max for the MessageRequest tryNumber
     * @return a list of GatewayResponse object
     */
    public List getByPendingMessageAndMaxTries(int maxTries);
}
