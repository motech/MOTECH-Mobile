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

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created 30-07-09
 */
@WebService(endpointInterface = "com.dreamoval.motech.webapp.webservices.MessageService", serviceName = "messagingService")
public class MessageServiceImpl implements MessageService {
    private OMIManager omiManager;

    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType) {
        return this.omiManager.createOMIService().sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
    }

    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<PatientImpl> patientList) {
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
        this.omiManager = omiManager;
    }

}
