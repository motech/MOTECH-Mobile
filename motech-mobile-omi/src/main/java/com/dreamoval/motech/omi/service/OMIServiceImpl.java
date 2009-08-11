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
import org.apache.log4j.Logger;

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
    private static Logger logger = Logger.getLogger(OMIServiceImpl.class);

    /**
     *
     * @see OMIService.sendPatientMessage
     */
    public Long sendPatientMessage(Long messageId, String clinic, Date serviceDate, String patientNumber, ContactNumberType patientNumberType, MessageType messageType){
        logger.info("Creating MessageDetails object");
        MessageDetails messageDetails = coreManager.createMessageDetails(coreManager.createMotechContext());
        messageDetails.setMessageType(messageType.toString());
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers(patientNumber);
        messageDetails.setMessageText(storeManager.getMessage("patient"));

        logger.debug(messageDetails);
        logger.info("Calling MessageService.sendTextMessage");
        return ompManager.createMessagingService().sendTextMessage(messageDetails);
    }

    /**
     *
     * @see OMIService.sendCHPSMessage
     */
    public Long sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<PatientImpl> patientList){
        logger.info("Creating MessageDetails object");
        MessageDetails messageDetails = coreManager.createMessageDetails(coreManager.createMotechContext());
        messageDetails.setId(messageId);
        messageDetails.setMessageType(MessageType.TEXT.toString());
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers(workerNumber);
        messageDetails.setMessageText(storeManager.getMessage("worker"));

        logger.debug(messageDetails);
        logger.info("Calling MessageService.sendTextMessage");
        return ompManager.createMessagingService().sendTextMessage(messageDetails);
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
        logger.debug("Setting OMIServiceImpl.storeManager");
        logger.debug(storeManager);
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
        logger.debug("Setting OMIServiceImpl.ompmanager");
        logger.debug(ompManager);
        this.ompManager = ompManager;
    }

    /**
     * @return the ompManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param ompManager the ompManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        logger.debug("Setting OMIServiceImpl.coreManager");
        logger.debug(coreManager);
        this.coreManager = coreManager;
    }
}
