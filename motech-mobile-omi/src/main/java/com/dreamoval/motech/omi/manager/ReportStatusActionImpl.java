package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import org.motech.ws.client.RegistrarWebService;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public class ReportStatusActionImpl implements StatusAction{
   private RegistrarWebService regWs;
   
   public void doAction(GatewayResponse response){
       Long rId = response.getMessageId().getRequestId();
       
       if(response.getMessageStatus() == MStatus.DELIVERED)
           getRegWs().setMessageStatus(rId.toString(), true);
       else if(response.getMessageStatus() == MStatus.FAILED)
           getRegWs().setMessageStatus(rId.toString(), false);
   }

    public RegistrarWebService getRegWs() {
        return regWs;
    }

    public void setRegWs(RegistrarWebService regWs) {
        this.regWs = regWs;
    }
}
