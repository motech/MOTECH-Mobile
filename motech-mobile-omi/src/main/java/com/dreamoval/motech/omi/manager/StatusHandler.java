package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import java.util.Map;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public interface StatusHandler{

    public void handleStatus(GatewayResponse response);
    
    public boolean registerStatusAction(MStatus status, StatusAction action);

    public Map<MStatus, StatusAction> getActionRegister();

    public void setActionRegister(Map<MStatus, StatusAction> registry); 

    public Map<String, MStatus> getStatusDictionary();

    public void setStatusDictionary(Map<String, MStatus> statusDictionary);
}