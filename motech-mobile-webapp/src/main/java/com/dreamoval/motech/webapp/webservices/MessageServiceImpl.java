/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.webapp.webservices;

import com.dreamoval.motech.omi.manager.OMIManager;
import com.dreamoval.motech.omi.service.ContactNumberType;
import com.dreamoval.motech.omi.service.MessageType;
import com.dreamoval.motech.omi.service.PatientImpl;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.apache.log4j.Logger;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created 30-07-09
 */
@WebService(endpointInterface = "com.dreamoval.motech.webapp.webservices.MessageService", serviceName = "messagingService")
public class MessageServiceImpl implements MessageService {
    private OMIManager omiManager;
    private static Logger logger = Logger.getLogger(MessageServiceImpl.class);

    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType) {
        logger.debug("Called MessageService.sendPatientMessage with parameters:\n\rmessageId - "+ messageId + "\n\rclinic - " + clinic + "\n\rserviceDate - " + serviceDate + "\n\rpatientNumber - " + patientNumber + "\n\rpatientNumbrType - " + patientNumberType + "\n\rmessageType - " + messageType);
        logger.info("Calling OMIService.sendPtientMessage");
        return this.omiManager.createOMIService().sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
    }

    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<PatientImpl> patientList) {
        logger.debug("Called MessageService.sendCHPSMessage with parameters:\n\rmessageId - "+ messageId + "\n\rworkerName - " + workerName + "\n\rworkerNumber - " + workerNumber + "\n\rpatientList - " + patientList);
        logger.info("Calling OMIService.sendCHPSMessage");
        return this.omiManager.createOMIService().sendCHPSMessage(messageId, workerName, workerNumber, patientList);
    }

    /**
     * @return the omiManager
     */
    public OMIManager getOmiManager() {
        return omiManager;
    }

    /**
     * @param omiManager the omiManager to set
     */
    public void setOmiManager(OMIManager omiManager) {
        logger.debug("Setting MessageServiceImpl.omiManager:");
        logger.debug(omiManager);
        this.omiManager = omiManager;
    }

}
