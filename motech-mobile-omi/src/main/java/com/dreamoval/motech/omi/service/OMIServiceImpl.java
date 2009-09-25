package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omp.manager.OMPManager;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * An implementation of the OMIService
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 *
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
        GatewayRequest messageDetails = coreManager.createMessageDetails(coreManager.createMotechContext());
//     TODO: to yoofi  conflict here
        messageDetails.setRequestId(1L);
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber(patientNumber);
        messageDetails.setMessage(storeManager.getMessage("patient"));

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
        GatewayRequest messageDetails = coreManager.createMessageDetails(coreManager.createMotechContext());
        messageDetails.setId(messageId);
        //TODO to yoofi confilct here
        messageDetails.setRequestId(2L);
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber(workerNumber);
        messageDetails.setMessage(storeManager.getMessage("worker"));

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
