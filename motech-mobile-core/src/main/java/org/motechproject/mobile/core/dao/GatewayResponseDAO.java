package org.motechproject.mobile.core.dao;

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
    public GatewayResponse getMostRecentResponseByMessageId(String messageId);

    /**
     * Method to select GatewayResponse based on the requestId and its parent GatewayRequest tryNumber
     * @param requestId the id of the message associated with the required GatewayResponse objects
     * @param tryNumber tryNumber of the GatewayRequest
     * @return a single GatewayResponse object
     */
    public GatewayResponse getByMessageIdAndTryNumber(String messageId, int tryNumber);
}
