package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public interface StatusHandler{

    /**
     * Performs set of necessary actions according to status of response
     * @param response object whose status is to be handled
     */
    public void handleStatus(GatewayResponse response);

    /**
     * Adds an action to be performed when the provided status is found
     * @param status for which action should be performed
     * @param action to perform when status is found
     * @return
     */
    public boolean registerStatusAction(MStatus status, StatusAction action);
}