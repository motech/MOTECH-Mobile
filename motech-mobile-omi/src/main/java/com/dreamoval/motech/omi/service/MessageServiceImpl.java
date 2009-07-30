/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omp.service.SMSService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-Apr-2009
 *
 * <p>Handles interactions with OMP for message delivery related functions</p>
 */
public class MessageServiceImpl {
    MessageStoreManager storeManager;
    SMSService smsService;

    /**
     *
     * @see MessageService.sendPatientMessage
     */
    public long sendPatientMessage(long messageId, long patientId, String patientSerial, String clinic, String serviceType, Date serviceDate, String patientNumber, String patientNumberType, String messageType, String language, Date messageDate){
        MessageDetails messageDetails = new MessageDetails();
        return smsService.sendTextMessage(messageDetails).longValue();
    }

    /**
     *
     * @see MessageService.sendCHPSMessage
     */
    public long sendCHPSMessage(long messageId, long workerId, long workerName, String workerNumber, List<Patient> patientList, Date messageDate){
        MessageDetails messageDetails = new MessageDetails();
        return smsService.sendTextMessage(messageDetails).longValue();
    }
}
