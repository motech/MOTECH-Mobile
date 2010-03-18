package org.motechproject.mobile.core.dao;

import org.motechproject.mobile.core.model.GatewayRequest;

/*
 * GatewayRequestDao is an interface that defines only methods and attributes that are specific to GatewayRequest entity
 * 
 *  Date: Jul 29, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
import org.motechproject.mobile.core.model.MStatus;
import java.util.Date;
import java.util.List;

public interface GatewayRequestDAO<T extends GatewayRequest> extends GenericDAO<T> {

    /**
     * Method to select all GatewayRequest objects based on the passed status
     * @param status status to search for
     * @return List of GatewayRequest objects
     */
    public List<GatewayRequest> getByStatus(MStatus status);

    /**
     * Method to select all GatewayRequest based on the status the the date passed
     * @param status the status to search for
     * @param date the date to search for
     * @return List of GatewayRequest objects
     */
    public List<GatewayRequest> getByStatusAndSchedule(MStatus status, Date date);
}
