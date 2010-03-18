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
     * @param requestId the requestId corresponding to a GatewayResponse objects
     * @return a single GatewayResponse object
     */
    public GatewayResponse getMostRecentResponseByRequestId(String requestId);

    /**
     * Method to select GatewayResponse based on the requestId and its parent GatewayRequest tryNumber
     * @param requestId the requestId corresponding to a GatewayResponse objects
     * @param tryNumber tryNumber of the GatewayRequest
     * @return a single GatewayResponse object
     */
    public GatewayResponse getByRequestIdAndTryNumber(String requestId, int tryNumber);
}
