package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.GatewayRequest;

/*
 * GatewayRequestDao is an interface that defines only methods and attributes that are specific to GatewayRequest entity
 * 
 *  Date: Jul 29, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
import com.dreamoval.motech.core.model.MStatus;
import java.util.Date;
import java.util.List;
public interface GatewayRequestDAO<T extends GatewayRequest> extends GenericDAO<T> {
    public List<GatewayRequest> getByStatus(MStatus status);
    
    public List<GatewayRequest> getByStatusAndSchedule(MStatus status, Date date);
}
