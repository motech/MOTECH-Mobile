package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omi.manager.StatusHandler;
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
    private StatusHandler statHandler;

    private static Logger logger = Logger.getLogger(OMIServiceImpl.class);

    /**
     *
     * @see OMIService.sendPatientMessage
     */
    public String savePatientMessageRequest(Long messageId, String patientName, String patientNumber, ContactNumberType patientNumberType, String langCode, MessageType messageType, Long notificationType, Date startDate, Date endDate){
        logger.info("Constructing MessageRequest object");
        
        MotechContext mc = coreManager.createMotechContext();
        MessageRequest messageRequest = coreManager.createMessageRequest(mc);
        NotificationType notifTypeObject = coreManager.createNotificationType(mc);
        Language langObject = coreManager.createLanguage(mc);
        langObject.setCode(langCode);
        
        notifTypeObject.setId(notificationType);
        messageRequest.setId(messageId);
        messageRequest.setDateFrom(startDate);
        messageRequest.setDateTo(endDate);
        messageRequest.setRecipientName(patientName);
        messageRequest.setRecipientNumber(patientNumber);
        messageRequest.setNotificationType(notifTypeObject);
        messageRequest.setMessageType(messageType);
        messageRequest.setLanguage(langObject);
        messageRequest.setStatus(MStatus.QUEUED);

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);
        logger.info("Saving MessageRequest");
        coreManager.createMessageRequestDAO(mc).save(messageRequest);
        return messageRequest.getStatus().toString();
    }

    /**
     *
     * @see OMIService.sendCHPSMessage
     */
    public String saveCHPSMessageRequest(Long messageId, String workerName, String workerNumber, List<PatientImpl> patientList, String langCode, MessageType messageType, Long notificationType, Date startDate, Date endDate){
        logger.info("Constructing MessageDetails object");
        
        
        MotechContext mc = coreManager.createMotechContext();
        MessageRequest messageRequest = coreManager.createMessageRequest(mc);
        NotificationType notifTypeObject = coreManager.createNotificationType(mc);
        Language langObject = coreManager.createLanguage(mc);
        
        langObject.setCode(langCode);
        messageRequest.setId(messageId);
        messageRequest.setDateFrom(startDate);
        messageRequest.setDateTo(endDate);
        messageRequest.setRecipientName(workerName);
        messageRequest.setRecipientNumber(workerNumber);
        messageRequest.setNotificationType(notifTypeObject);
        messageRequest.setMessageType(messageType);
        messageRequest.setLanguage(langObject);
        messageRequest.setStatus(MStatus.QUEUED);

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);
        
        logger.info("Saving MessageRequest");
        coreManager.createMessageRequestDAO(mc).save(messageRequest);
        return messageRequest.getStatus().toString();
    }
    
    /**
     * @see OMIService.processMessageRequests
     */
    public void processMessageRequests(){
        logger.info("Fetching stored MessageRequest objects");
        MotechContext mc = coreManager.createMotechContext();
        
        MessageRequestDAO msgDao = coreManager.createMessageRequestDAO(mc);
        MessageRequest sample = coreManager.createMessageRequest(mc);
        sample.setStatus(MStatus.QUEUED);
        List<MessageRequest> messages = msgDao.findByExample(sample);
        
        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();
        
        logger.info("Preparing messages:");
        for(MessageRequest message : messages){            
            GatewayRequest gwReq = storeManager.constructMessage(message);
            
            logger.info("Scheduling GatewayRequest");
            msgSvc.scheduleMessage(gwReq);
            
            logger.info("Updating MessageRequest");
            message.setStatus(MStatus.SCHEDULED);
            logger.debug(message);
            msgDao.save(message);
        }
        logger.info("Messages processed successfully");
    }
    
    /**
     * @see OMIService.processMessageRetries
     */
    public void processMessageRetries(){
        logger.info("Fetching stored MessageRequest objects");
        MotechContext mc = coreManager.createMotechContext();
        
        MessageRequestDAO msgDao = coreManager.createMessageRequestDAO(mc);
        MessageRequest sample = coreManager.createMessageRequest(mc);
        sample.setStatus(MStatus.RETRY);
        List<MessageRequest> messages = msgDao.findByExample(sample);
        
        logger.info("Initializing GatewayRequestDAO");
        GatewayRequestDAO gwDao = coreManager.createGatewayRequestDAO(mc);
        
        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();
        
        for(MessageRequest message : messages){
            GatewayRequest gwReq = (GatewayRequest) gwDao.getById(message.getId());
            msgSvc.scheduleMessage(gwReq);
        }
    }
    
    /**
     * @see OMIService.getMessageResponses
     */
    public void getMessageResponses(){
        MotechContext mc = coreManager.createMotechContext();
        
        logger.info("Building search criteria");
        MessageRequest sample = coreManager.createMessageRequest(mc);
        sample.setStatus(MStatus.PENDING);
        logger.debug(sample);
        
        logger.info("Initializing MessageRequestDAO and fetching matching message requests");
        List<MessageRequest> messages = coreManager.createMessageRequestDAO(mc).findByExample(sample);
        
        logger.info("Initializing GatewayRequestDAO");
        GatewayRequestDAO gatewayDao = coreManager.createGatewayRequestDAO(mc);
        
        for(MessageRequest message : messages){
            //GatewayRequest request = (GatewayRequest) gatewayDao.getById(message.getId());
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

    public StatusHandler getStatHandler() {
        return statHandler;
    }

    public void setStatHandler(StatusHandler statHandler) {
        logger.debug("Setting OMIServiceImpl.statHandler");
        logger.debug(coreManager);
        this.statHandler = statHandler;
    }
}
