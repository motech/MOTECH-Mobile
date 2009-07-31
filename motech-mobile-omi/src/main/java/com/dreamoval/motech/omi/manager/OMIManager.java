/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.omi.service.MessageService;
import com.dreamoval.motech.omi.wrapper.ContactNumberType;
import com.dreamoval.motech.omi.wrapper.MessageType;
import com.dreamoval.motech.omi.wrapper.Patient;
import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Henry Sampson
 * Date Created: Jul 31, 2009
 */
public class OMIManager{
    private MessageService messageService;

    public OMIManager(){
        ApplicationContext context = new ClassPathXmlApplicationContext("omi-config.xml");
        messageService = (MessageService)context.getBean("messageService");
    }

    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType) {
        return this.getMessageService().sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
    }

    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<Patient> patientList) {
        return this.getMessageService().sendCHPSMessage(messageId, workerName, workerNumber, patientList);
    }

    /**
     * @return the messageService
     */
    public MessageService getMessageService() {
        return messageService;
    }

    /**
     * @param messageService the messageService to set
     */
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

}
