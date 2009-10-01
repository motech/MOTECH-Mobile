package com.dreamoval.motech.omi.ws;

import com.dreamoval.motech.omi.manager.OMIManager;
import com.dreamoval.motech.omi.service.ContactNumberType;
import com.dreamoval.motech.omi.service.PatientImpl;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * A webservice interface providing functionality for sending messages
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created 30-07-09
 */
@WebService()
public interface MessageService extends Serializable{

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
    public String sendPatientMessage(@WebParam(name="messageId") Long messageId, @WebParam(name="patientName") String patientName, @WebParam(name="patientNumber") String patientNumber, @WebParam(name="patientNumberType") ContactNumberType patientNumberType, @WebParam(name="langCode") String langCode, @WebParam(name="messageType") String messageType, @WebParam(name="notificationType") Long notificationType, @WebParam(name="startDate")Date startDate, @WebParam(name="endDate")Date endDate);

    /**
     * Sends a message to a CHPS Worker
     *
     * @param messageId Numeric key of the message to send
     * @param workerName Name of CHPS worker recieving message
     * @param workerNumber Worker mobile contact number
     * @param patientList A List of patients requiring service from CHPS worker
     * @return The id of the message sent
     */
    public String sendCHPSMessage(@WebParam(name="messageId") Long messageId, @WebParam(name="workerName") String workerName, @WebParam(name="workerNumber") String workerNumber, @WebParam(name="patientList") List<PatientImpl> patientList, @WebParam(name="startDate")Date startDate, @WebParam(name="endDate")Date endDate);
    
    /**
     * @return the omiManager
     */
    public OMIManager getOmiManager();

    /**
     * @param omiManager the omiManager to set
     */
    public void setOmiManager(OMIManager omiManager);
}
