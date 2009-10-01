package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;
import java.util.Map;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public class StatusHandlerImpl implements StatusHandler {

    private Map<MessageStatus, StatusAction> actionRegister;
    private Map<String, MessageStatus> statusDictionary;
    
    public void handleStatus(GatewayResponse response){
        //MessageResponse msgResponse = coreManager.
        actionRegister.get(response.getMessageStatus()).DoAction(response);
    }
    
    public boolean registerStatusAction(MessageStatus status, StatusAction action){
        if(actionRegister.put(status, action).equals(action))
            return true;
        else
            return false;
    }

    public Map<MessageStatus, StatusAction> getActionRegister() {
        return actionRegister;
    }

    public void setActionRegister(Map<MessageStatus, StatusAction> registry) {
        this.actionRegister = registry;
    }

    public Map<String, MessageStatus> getStatusDictionary() {
        return statusDictionary;
    }

    public void setStatusDictionary(Map<String, MessageStatus> statusDictionary) {
        this.statusDictionary = statusDictionary;
    }
}
