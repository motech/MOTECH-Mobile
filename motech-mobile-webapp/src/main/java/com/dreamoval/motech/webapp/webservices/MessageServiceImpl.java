/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.webapp.webservices;

import com.dreamoval.motech.omi.manager.OMIManager;
import com.dreamoval.motech.omi.service.ContactNumberType;
import com.dreamoval.motech.omi.service.MessageType;
import com.dreamoval.motech.omi.service.Patient;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created 30-07-09
 */
@WebService(endpointInterface = "com.dreamoval.motech.webapp.webservices.MessageService", serviceName = "messagingService")
public class MessageServiceImpl implements MessageService, ApplicationContextAware {
    ApplicationContext context;
    private OMIManager omiManager;

//    public MessageServiceImpl(){
//        this.omiManager = (OMIManager)context.getBean("omiManager");
//    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType) {
        return this.getOmiManager().createOMIService().sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
    }

    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<Patient> patientList) {
        return this.getOmiManager().createOMIService().sendCHPSMessage(messageId, workerName, workerNumber, patientList);
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
