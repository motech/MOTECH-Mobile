package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import java.util.List;
import java.util.Map;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public class StatusHandlerImpl implements StatusHandler {

    private Map<MStatus, List<StatusAction>> actionRegister;
    
    public void handleStatus(GatewayResponse response){
        List<StatusAction> actions = actionRegister.get(response.getMessageStatus());
        for(StatusAction action : actions){
            action.doAction(response);
        }
    }
    
    public boolean registerStatusAction(MStatus status, StatusAction action){
        return actionRegister.get(status).add(action);
    }

    public Map<MStatus, List<StatusAction>> getActionRegister() {
        return actionRegister;
    }

    public void setActionRegister(Map<MStatus, List<StatusAction>> registry) {
        this.actionRegister = registry;
    }
}
