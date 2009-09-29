package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omp.manager.OMPManager;
import com.dreamoval.motech.omp.service.MessagingService;
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
    public String savePatientMessageRequest(Long messageId, String patientName, String patientNumber, ContactNumberType patientNumberType, String langCode, String messageType, String notificationType, Date startDate, Date endDate){
        logger.info("Constructing MessageRequest object");
        MessageRequest messageRequest = coreManager.createMessageRequest(coreManager.createMotechContext());
        messageRequest.setId(messageId);
        messageRequest.setDate_from(startDate);
        messageRequest.setDate_to(endDate);
        messageRequest.setRecipient_name(patientName);
        messageRequest.setRecipient_number(patientNumber);
        messageRequest.setNotification_type(notificationType);
        messageRequest.setMessage_type(messageType);
        messageRequest.setLanguage(langCode);
        messageRequest.setStatus("QUEUED");

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);
        logger.info("Saving MessageRequest");
        coreManager.createMessageRequestDAO(coreManager.createMotechContext()).save(messageRequest);
        return messageRequest.getStatus();
    }

    /**
     *
     * @see OMIService.sendCHPSMessage
     */
    public String saveCHPSMessageRequest(Long messageId, String workerName, String workerNumber, List<PatientImpl> patientList, Date startDate, Date endDate){
        logger.info("Constructing MessageDetails object");
        MessageRequest messageRequest = coreManager.createMessageRequest(coreManager.createMotechContext());
        messageRequest.setId(messageId);
        messageRequest.setDate_from(startDate);
        messageRequest.setDate_to(endDate);
        messageRequest.setRecipient_name(workerName);
        messageRequest.setRecipient_number(workerNumber);
        //messageRequest.setNotification_type(notificationType);
        //messageRequest.setMessage_type(messageType);
        //messageRequest.setLanguage(language);
        messageRequest.setStatus("QUEUED");

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);
        
        logger.info("Saving MessageRequest");
        coreManager.createMessageRequestDAO(coreManager.createMotechContext()).save(messageRequest);
        return messageRequest.getStatus();
    }
    
    /**
     * @see OMIService.processMessageRequests
     */
    public void processMessageRequests(){
        logger.info("Fetching stored MessageRequest objects");
        MessageRequestDAO msgDao = coreManager.createMessageRequestDAO(coreManager.createMotechContext());
        MessageRequest sample = coreManager.createMessageRequest(coreManager.createMotechContext());
        sample.setStatus("QUEUED");
        List<MessageRequest> messages = msgDao.findByExample(sample);
        
        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();
        
        logger.info("Preparing messages:");
        for(MessageRequest message : messages){            
            GatewayRequest gwReq = storeManager.constructMessage(message);
            
            logger.info("Scheduling GatewayRequest");
            msgSvc.scheduleMessage(gwReq);
            
            logger.info("Updating MessageRequest");
            message.setStatus("SCHEDULED");
            logger.debug(message);
            msgDao.save(message);
        }
    }
    
    /**
     * @see OMIService.processMessageRetries
     */
    public void processMessageRetries(){
        logger.info("Fetching stored MessageRequest objects");
        MessageRequestDAO msgDao = coreManager.createMessageRequestDAO(coreManager.createMotechContext());
        MessageRequest sample = coreManager.createMessageRequest(coreManager.createMotechContext());
        sample.setStatus("RETRY");
        List<MessageRequest> messages = msgDao.findByExample(sample);
        
        logger.info("Initializing GatewayRequestDAO");
        GatewayRequestDAO gwDao = coreManager.createGatewayRequestDAO(coreManager.createMotechContext());
        
        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();
        
        for(MessageRequest message : messages){
            GatewayRequest gwReq = (GatewayRequest) gwDao.getById(message.getId());
            msgSvc.scheduleMessage(gwReq);
        }
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
