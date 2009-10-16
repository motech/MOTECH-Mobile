package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.GatewayResponse;

/**
 * GatewayResponseDao is an interface that defines only methods and attributes that are specific to GatewayResponse entity
 * Date: Jul 29, 2009
 * 
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface GatewayResponseDAO<T extends GatewayResponse> extends GenericDAO<T> {

    public GatewayResponse getMostRecentResponseByRequestId(String requestId);
}
