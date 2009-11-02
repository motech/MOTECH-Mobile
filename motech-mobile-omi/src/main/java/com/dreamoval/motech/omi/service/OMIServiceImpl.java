package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.dao.GatewayRequestDetailsDAO;
import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.dao.NotificationTypeDAO;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omi.manager.StatusHandler;
import com.dreamoval.motech.omp.manager.OMPManager;
import com.dreamoval.motech.omp.service.MessagingService;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.Patient;
import org.motechproject.ws.MessageStatus;
import org.motechproject.ws.NameValuePair;

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
    private String defaultLang;
    private int maxTries;

    public OMIServiceImpl() {
        Properties settings = new Properties();

        try {
            settings.load(getClass().getResourceAsStream("/omi.settings.properties"));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        defaultLang = settings.getProperty("defaultLang", "en");
        maxTries = Integer.valueOf(settings.getProperty("maxTries", "3"));
    }

    /**String
     *
     * @see OMIService.sendPatientMessage
     */
    public MessageStatus savePatientMessageRequest(String messageId, NameValuePair[] personalInfo, String patientNumber, ContactNumberType patientNumberType, String langCode, MediaType messageType, Long notificationType, Date startDate, Date endDate) {
        logger.info("Constructing MessageRequest object");

        MotechContext mc = coreManager.createMotechContext();
        MessageRequest messageRequest = coreManager.createMessageRequest(mc);

        NotificationTypeDAO noteTypeDao = coreManager.createNotificationTypeDAO(mc);
        NotificationType noteType = (NotificationType) noteTypeDao.getById(notificationType);

        Language langObject = coreManager.createLanguageDAO(mc).getByCode(langCode);

        if (personalInfo != null) {
            HashSet<NameValuePair> details = new HashSet<NameValuePair>();
            for (NameValuePair detail : personalInfo) {
                details.add(detail);
            }
            messageRequest.setPersInfos(details);
        }

        messageRequest.setTryNumber(1);
        messageRequest.setRequestId(messageId);
        messageRequest.setDateFrom(startDate);
        messageRequest.setDateTo(endDate);
        messageRequest.setRecipientNumber(patientNumber);
        messageRequest.setNotificationType(noteType);
        messageRequest.setMessageType(MessageType.valueOf(messageType.toString()));
        messageRequest.setLanguage(langObject);
        messageRequest.setStatus(MStatus.QUEUED);
        messageRequest.setDateCreated(new Date());

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);

        if (messageRequest.getDateFrom() == null && messageRequest.getDateTo() == null) {
            return sendMessage(messageRequest, mc);
        }

        logger.info("Saving MessageRequest");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        msgReqDao.save(messageRequest);
        tx.commit();

        mc.cleanUp();

        return MessageStatus.valueOf(messageRequest.getStatus().toString());
    }

    /**
     *logger
     * @see OMIService.sendCHPSMessage
     */
    public MessageStatus saveCHPSMessageRequest(String messageId, NameValuePair[] personalInfo, String workerNumber, Patient[] patientList, String langCode, MediaType messageType, Long notificationType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object");


        MotechContext mc = coreManager.createMotechContext();
        MessageRequest messageRequest = coreManager.createMessageRequest(mc);

        NotificationTypeDAO noteTypeDao = coreManager.createNotificationTypeDAO(mc);
        NotificationType noteType = (NotificationType) noteTypeDao.getById(notificationType);

        Language langObject = coreManager.createLanguageDAO(mc).getByCode(langCode);

        if (personalInfo != null) {
            HashSet<NameValuePair> details = new HashSet<NameValuePair>();
            for (NameValuePair detail : personalInfo) {
                details.add(detail);
            }
            messageRequest.setPersInfos(details);
        }

        messageRequest.setTryNumber(1);
        messageRequest.setRequestId(messageId);
        messageRequest.setDateFrom(startDate);
        messageRequest.setDateTo(endDate);
        messageRequest.setRecipientNumber(workerNumber);
        messageRequest.setNotificationType(noteType);
        messageRequest.setMessageType(MessageType.valueOf(messageType.toString()));
        messageRequest.setLanguage(langObject);
        messageRequest.setStatus(MStatus.QUEUED);
        messageRequest.setDateCreated(new Date());

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);

        if (messageRequest.getDateFrom() == null && messageRequest.getDateTo() == null) {
            return sendMessage(messageRequest, mc);
        }

        logger.info("Saving MessageRequest");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        msgReqDao.save(messageRequest);
        tx.commit();

        mc.cleanUp();

        return MessageStatus.valueOf(messageRequest.getStatus().toString());
    }

    public MessageStatus sendMessage(MessageRequest message, MotechContext context) {
        Language defaultLanguage = coreManager.createLanguageDAO(context).getByCode(defaultLang);

        if (message.getLanguage() == null) {
            message.setLanguage(defaultLanguage);
        }

        logger.info("Constructing GatewayRequest");
        GatewayRequestDetails gwReqDet = storeManager.constructMessage(message, context, defaultLanguage);

        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();

        logger.info("Sending GatewayRequest");

        if (context.getDBSession() != null) {
            ((Session) context.getDBSession().getSession()).evict(gwReqDet);
        }

        msgSvc.sendMessage(gwReqDet, context);

        logger.info("Updating MessageRequest");
        message.setDateProcessed(new Date());
        message.setStatus(MStatus.PENDING);
        logger.debug(message);

        if (context.getDBSession() != null) {
            ((Session) context.getDBSession().getSession()).evict(gwReqDet);
        }

        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(context);

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        msgReqDao.save(message);
        tx.commit();

        context.cleanUp();

        logger.info("Messages sent successfully");
        return MessageStatus.valueOf(message.getStatus().toString());
    }

    /**
     * @see OMIService.processMessageRequests
     */
    public void processMessageRequests() {
        logger.info("Building search criteria");
        MotechContext mc = coreManager.createMotechContext();

        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);
        List<MessageRequest> messages = msgReqDao.getMsgRequestByStatusAndSchedule(MStatus.QUEUED, new Date());

        logger.info("MessageRequest objects fetched successfully");
        logger.debug(messages);

        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();

        Language defaultLanguage = coreManager.createLanguageDAO(mc).getByCode(defaultLang);

        logger.info("Preparing messages:");
        for (MessageRequest message : messages) {
            GatewayRequestDetails gwReqDet = storeManager.constructMessage(message, mc, defaultLanguage);

            if (message.getLanguage() == null) {
                message.setLanguage(defaultLanguage);
            }

            logger.info("Scheduling GatewayRequest");
            if (mc.getDBSession() != null) {
                ((Session) mc.getDBSession().getSession()).evict(message);
                ((Session) mc.getDBSession().getSession()).evict(gwReqDet);
            }
            msgSvc.scheduleMessage(gwReqDet, mc);

            logger.info("Updating MessageRequest");
            message.setDateProcessed(new Date());
            message.setStatus(MStatus.PENDING);
            logger.debug(message);

            if (mc.getDBSession() != null) {
                ((Session) mc.getDBSession().getSession()).evict(gwReqDet);
                ((Session) mc.getDBSession().getSession()).evict(message);
            }

            Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
            tx.begin();
            msgReqDao.save(message);
            tx.commit();
        }
        mc.cleanUp();

        logger.info("Messages processed successfully");
    }

    /**
     * @see OMIService.processMessageRetries
     */
    public void processMessageRetries() {
        logger.info("Building search criteria");
        MotechContext mc = coreManager.createMotechContext();

        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);
        MessageRequest sample = coreManager.createMessageRequest(mc);
        sample.setStatus(MStatus.RETRY);
        logger.debug(sample);

        logger.info("Fetching stored MessageRequest objects");
        List<MessageRequest> messages = msgReqDao.findByExample(sample);

        logger.info("MessageRequest objects fetched successfully");
        logger.debug(messages);

        logger.info("Initializing GatewayRequestDAO");
        GatewayRequestDetailsDAO gwReqDao = coreManager.createGatewayRequestDetailsDAO(mc);

        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();

        for (MessageRequest message : messages) {
            if (message.getTryNumber() >= maxTries) {
                message.setStatus(MStatus.FAILED);
            } else {
                message.setTryNumber(message.getTryNumber() + 1);

                logger.info("Constructing GatewayRequest object");
                GatewayRequestDetails gwReqDet = (GatewayRequestDetails) gwReqDao.getById(message.getId());

                GatewayRequest gwReq = coreManager.createGatewayRequest(mc);
                gwReq.setDateFrom(message.getDateFrom());
                gwReq.setDateTo(message.getDateTo());
                gwReq.setRecipientsNumber(message.getRecipientNumber());
                gwReq.setRequestId(message.getRequestId());
                gwReq.setTryNumber(message.getTryNumber());
                gwReq.setMessage(gwReqDet.getMessage());
                gwReq.setMessageStatus(MStatus.SCHEDULED);

                gwReqDet.getGatewayRequests().add(gwReq);
                msgSvc.scheduleMessage(gwReqDet, mc);

                message.setStatus(MStatus.SCHEDULED);
            }

            Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
            tx.begin();
            msgReqDao.save(message);
            tx.commit();
        }
        mc.cleanUp();

        logger.info("Messages processed successfully");
    }

    /**
     * @see OMIService.getMessageResponses
     */
    public void getMessageResponses() {
        MotechContext mc = coreManager.createMotechContext();

       logger.info("Initializing MessageRequestDAO and fetching matching message requests");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);
      
        List<MessageRequest> messages = msgReqDao.getMsgRequestByStatusAndTryNumber(MStatus.PENDING, maxTries);

        if (messages != null) {
            logger.info("MessageRequest objects fetched successfully");
            logger.debug(messages);

            logger.info("Initializing GatewayResponseDAO");
            GatewayResponseDAO gwRespDao = coreManager.createGatewayResponseDAO(mc);

            for (MessageRequest message : messages) {
                GatewayResponse response = gwRespDao.getMostRecentResponseByRequestId(message.getRequestId());

                if (response != null) {
                    statHandler.handleStatus(response);

                    message.setStatus(response.getMessageStatus());

                    if (mc.getDBSession() != null) {
                        ((Session) mc.getDBSession().getSession()).evict(message);
                    }

                    Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
                    tx.begin();
                    msgReqDao.save(message);
                    tx.commit();
                }
            }
        }
        mc.cleanUp();
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

    public String getDefaultLang() {
        return defaultLang;
    }

    public void setDefaultLang(String defaultLang) {
        this.defaultLang = defaultLang;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public void setMaxTries(int maxRetries) {
        this.maxTries = maxRetries;
    }
}
