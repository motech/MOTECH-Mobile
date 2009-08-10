/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omp.manager.OMPManager;
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
public class OMIServiceImpl implements OMIService {
    private MessageStoreManager storeManager;
    private OMPManager ompManager;
    private CoreManager coreManager;

    /**
     *
     * @see OMIService.sendPatientMessage
     */
    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType){
        MessageDetails messageDetails = coreManager.createMessageDetails(coreManager.createMotechContext());
        messageDetails.setId(messageId);
        messageDetails.setMessageType(messageType.toString());
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers(patientNumber);
        messageDetails.setMessageText(storeManager.getMessage("patient"));

        return ompManager.createSMSService().sendTextMessage(messageDetails);
    }

    /**
     *
     * @see OMIService.sendCHPSMessage
     */
    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<PatientImpl> patientList){
        MessageDetails messageDetails = coreManager.createMessageDetails(coreManager.createMotechContext());
        messageDetails.setId(messageId);
        messageDetails.setMessageType(MessageType.TEXT.toString());
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers(workerNumber);
        messageDetails.setMessageText(storeManager.getMessage("worker"));
        
        return ompManager.createSMSService().sendTextMessage(messageDetails);
    }

    /**
     * @return the storeManager
     */
    public MessageStoreManager getStoreManager() {
        return storeManager;
    }

    /**
     * @param storeManager the storeManager to set
     */
    public void setStoreManager(MessageStoreManager storeManager) {
        this.storeManager = storeManager;
    }

    /**
     * @return the ompManager
     */
    public OMPManager getOmpManager() {
        return ompManager;
    }

    /**
     * @param ompManager the ompManager to set
     */
    public void setOmpManager(OMPManager ompManager) {
        this.ompManager = ompManager;
    }

    /**
     * @return the ompManager
     */
    public CoreManager geCoreManager() {
        return coreManager;
    }

    /**
     * @param ompManager the ompManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }
}
