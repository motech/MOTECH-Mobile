package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;
import org.motech.ws.client.LogType;
import  org.motech.ws.client.RegistrarWebService;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sept 30, 2009
 */
public class LogStatusActionImpl implements StatusAction {
   private RegistrarWebService regWs;
   
   public void doAction(GatewayResponse response){
       String summary = "Status of message with id "
               + response.getMessageId().getRequestId()
               + " is: " 
               + response.getMessageStatus().toString() 
               + ". Response from gateway was: "
               + response.getResponseText();
       
        getRegWs().log(LogType.SUCCESS, summary);
   }

    public RegistrarWebService getRegWs() {
        return regWs;
    }

    public void setRegWs(RegistrarWebService regWs) {
        this.regWs = regWs;
    }
}
