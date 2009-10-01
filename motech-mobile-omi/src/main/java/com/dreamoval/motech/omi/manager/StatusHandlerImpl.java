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
public class StatusHandlerImpl implements StatusHandler {

    private Map<MStatus, StatusAction> actionRegister;
    private Map<String, MStatus> statusDictionary;
    
    public void handleStatus(GatewayResponse response){
        actionRegister.get(response.getMessageStatus()).DoAction(response);
    }
    
    public boolean registerStatusAction(MStatus status, StatusAction action){
        if(actionRegister.put(status, action).equals(action))
            return true;
        else
            return false;
    }

    public Map<MStatus, StatusAction> getActionRegister() {
        return actionRegister;
    }

    public void setActionRegister(Map<MStatus, StatusAction> registry) {
        this.actionRegister = registry;
    }

    public Map<String, MStatus> getStatusDictionary() {
        return statusDictionary;
    }

    public void setStatusDictionary(Map<String, MStatus> statusDictionary) {
        this.statusDictionary = statusDictionary;
    }
}
