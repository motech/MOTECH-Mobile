package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import org.apache.log4j.Logger;
import org.motechproject.ws.server.RegistrarService;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public class ReportStatusActionImpl implements StatusAction{
   private RegistrarService regWs;
   private static Logger logger = Logger.getLogger(LogStatusActionImpl.class);
   
   public void doAction(GatewayResponse response){
       
       try{
           logger.info("Reporting message status to event engine");
           if(response.getMessageStatus() == MStatus.DELIVERED)
               getRegWs().setMessageStatus(response.getRequestId(), true);
           else if(response.getMessageStatus() == MStatus.FAILED)
               getRegWs().setMessageStatus(response.getRequestId(), false);
       }
       catch(Exception e){
           logger.error("Error communicating with event engine", e);
       }
   }

    public RegistrarService getRegWs() {
        return regWs;
    }

    public void setRegWs(RegistrarService regWs) {
        this.regWs = regWs;
    }
}
