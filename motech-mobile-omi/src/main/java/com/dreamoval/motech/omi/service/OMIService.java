package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omp.manager.OMPManager;
import com.dreamoval.motech.core.manager.CoreManager;
import java.util.Date;
import java.util.List;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Jul 31, 2009
 */
public interface OMIService {

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
    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType);

    /**
     * Sends a message to a CHPS Worker
     * 
     * @param messageId Numeric key of the message to send
     * @param workerName Name of CHPS worker recieving message
     * @param workerNumber Worker mobile contact number
     * @param patientList A List of patients requiring service from CHPS worker
     * @return The id of the message sent
     */
    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<PatientImpl> patientList);
    
    /**
     * @return the storeManager
     */
    public MessageStoreManager getStoreManager();

    /**
     * @param storeManager the storeManager to set
     */
    public void setStoreManager(MessageStoreManager storeManager);

    /**
     * @return the ompManager
     */
    public OMPManager getOmpManager();

    /**
     * @param ompManager the ompManager to set
     */
    public void setOmpManager(OMPManager ompManager);

    /**
     * @return the ompManager
     */
    public CoreManager getCoreManager();

    /**
     * @param ompManager the ompManager to set
     */
    public void setCoreManager(CoreManager coreManager);
}
