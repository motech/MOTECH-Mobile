/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omi.wrapper.ContactNumberType;
import com.dreamoval.motech.omi.wrapper.MessageType;
import com.dreamoval.motech.omi.wrapper.Patient;
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
public class MessageServiceImpl implements MessageService {
    private MessageStoreManager storeManager;
    private OMPManager ompManager;

    /**
     *
     * @see MessageService.sendPatientMessage
     */
    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType){
        MessageDetails messageDetails = new MessageDetailsImpl();
        messageDetails.setMessageId(messageId);
        messageDetails.setMessageType(messageType.toString());
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers(patientNumber);
        messageDetails.setMessageText(storeManager.getMessage("patient"));

        return getOmpManager().sendTextMessage(messageDetails);
    }

    /**
     *
     * @see MessageService.sendCHPSMessage
     */
    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<Patient> patientList){
        MessageDetails messageDetails = new MessageDetailsImpl();
        messageDetails.setMessageId(messageId);
        messageDetails.setMessageType(MessageType.TEXT.toString());
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers(workerNumber);
        messageDetails.setMessageText(storeManager.getMessage("worker"));
        
        return getOmpManager().sendTextMessage(messageDetails);
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
}
