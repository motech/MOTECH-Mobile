package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import org.apache.log4j.Logger;
import org.motech.ws.client.RegistrarWebService;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public class ReportStatusActionImpl implements StatusAction{
   private RegistrarWebService regWs;
   private static Logger logger = Logger.getLogger(LogStatusActionImpl.class);
   
   public void doAction(GatewayResponse response){
       Long rId = response.getMessageId().getGatewayRequestDetails().getId();
       
       try{
           logger.info("Reporting message status to event engine");
           if(response.getMessageStatus() == MStatus.DELIVERED)
               getRegWs().setMessageStatus(rId.toString(), true);
           else if(response.getMessageStatus() == MStatus.FAILED)
               getRegWs().setMessageStatus(rId.toString(), false);
       }
       catch(Exception e){
           logger.error("Error communicating with event engine");
           logger.debug(e);
       }
   }

    public RegistrarWebService getRegWs() {
        return regWs;
    }

    public void setRegWs(RegistrarWebService regWs) {
        this.regWs = regWs;
    }
}
