/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.webapp.webservices;

import com.dreamoval.motech.omi.manager.OMIManager;
import com.dreamoval.motech.omi.wrapper.ContactNumberType;
import com.dreamoval.motech.omi.wrapper.MessageType;
import com.dreamoval.motech.omi.wrapper.Patient;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Yoofi
 */
@WebService(endpointInterface = "com.dreamoval.motech.webapp.webservices.MessageService", serviceName = "messagingService")
public class MessageServiceImpl implements MessageService {
    OMIManager omiManager;

//    public MessageDetails getMessageDetails(String msgId) {
//        return new MessageDetails();
//    }
    
    /**
     * Sends a message to a CHPS patient
     *
     * @param messageId Numeric key of the message to send
     * @param clinic Location of patient's default CHPS compound
     * @param serviceDate Date of current service delivery
     * @param patientNumber Patient mobile contact number
     * @param patientNumberType Type of contact number. Possible values include PERSONAL, SHARED
     * @param messageType Preferred message type. Possible values include TEXT, VOICE
     * @return The id of the message sent
     */
    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType){
        return this.omiManager.sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
    }

    /**
     * Sends a message to a CHPS Worker
     * 
     * @param messageId Numeric key of the message to send
     * @param workerName Name of CHPS worker recieving message
     * @param workerNumber Worker mobile contact number
     * @param patientList A List of patients requiring service from CHPS worker
     * @return The id of the message sent
     */
    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<Patient> patientList){
        return this.omiManager.sendCHPSMessage(messageId, workerName, workerNumber, patientList);
    }

}
