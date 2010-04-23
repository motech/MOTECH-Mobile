package org.motechproject.mobile.omi.service;

import org.motechproject.mobile.core.dao.GatewayRequestDetailsDAO;
import org.motechproject.mobile.core.dao.GatewayResponseDAO;
import org.motechproject.mobile.core.dao.MessageRequestDAO;
import org.motechproject.mobile.core.dao.NotificationTypeDAO;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationType;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omi.manager.MessageFormatter;
import org.motechproject.mobile.omi.manager.MessageStoreManager;
import org.motechproject.mobile.omi.manager.OMIManager;
import org.motechproject.mobile.omi.manager.StatusHandler;
import org.motechproject.mobile.omp.manager.OMPManager;
import org.motechproject.mobile.omp.service.MessagingService;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.motechproject.ws.Care;
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
    private OMIManager omiManager;
    private OMPManager ompManager;
    private CoreManager coreManager;
    private StatusHandler statHandler;
    private static Logger logger = Logger.getLogger(OMIServiceImpl.class);
    private String defaultLang;
    private int maxTries;

    public OMIServiceImpl() {
    }

    /**String
     *
     * @see OMIService.sendPatientMessage
     */
    public synchronized MessageStatus savePatientMessageRequest(String messageId, 
    															NameValuePair[] personalInfo, 
    															String patientNumber, 
    															ContactNumberType patientNumberType, 
    															String langCode, 
    															MediaType messageType, 
    															Long notificationType, 
    															Date startDate, 
    															Date endDate,
    															String recipientId) {
        logger.info("Constructing MessageRequest object...");

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
        messageRequest.setRecipientId(recipientId);
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

        logger.info("Saving MessageRequest...");
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
    public synchronized MessageStatus saveCHPSMessageRequest(String messageId, NameValuePair[] personalInfo, String workerNumber, Patient[] patientList, String langCode, MediaType messageType, Long notificationType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object...");


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

        logger.info("Saving MessageRequest...");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        msgReqDao.save(messageRequest);
        tx.commit();

        mc.cleanUp();

        return MessageStatus.valueOf(messageRequest.getStatus().toString());
    }

    public synchronized MessageStatus sendMessage(MessageRequest message, MotechContext context) {
        Language defaultLanguage = coreManager.createLanguageDAO(context).getByCode(defaultLang);

        if (message.getLanguage() == null) {
            message.setLanguage(defaultLanguage);
        }

        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(context);

        if (context.getDBSession() != null) {
            ((Session) context.getDBSession().getSession()).evict(message);
        }

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        message.setStatus(MStatus.QUEUED);
        msgReqDao.save(message);
        tx.commit();

        logger.info("Constructing GatewayRequest...");
        GatewayRequest gwReq = storeManager.constructMessage(message, context, defaultLanguage);

        logger.info("Initializing OMP MessagingService...");
        MessagingService msgSvc = ompManager.createMessagingService();

        logger.info("Sending GatewayRequest...");

        if (context.getDBSession() != null) {
            ((Session) context.getDBSession().getSession()).evict(gwReq.getGatewayRequestDetails());
            ((Session) context.getDBSession().getSession()).evict(message);
            ((Session) context.getDBSession().getSession()).evict(gwReq);
        }

        Map<Boolean, Set<GatewayResponse>> responses = msgSvc.sendMessage(gwReq, context);

        Boolean falseBool = new Boolean(false);
        if (responses.containsKey(falseBool)) {
            Set<GatewayResponse> resps = responses.get(falseBool);
            for (GatewayResponse gp : resps) {
                statHandler.handleStatus(gp);
            }
        }

        logger.info("Updating MessageRequest...");
        message.setDateProcessed(new Date());
        message.setStatus(MStatus.PENDING);
        logger.debug(message);

        if (context.getDBSession() != null) {
            ((Session) context.getDBSession().getSession()).evict(gwReq.getGatewayRequestDetails());
            ((Session) context.getDBSession().getSession()).evict(message);
            ((Session) context.getDBSession().getSession()).evict(gwReq);
        }

        tx.begin();
        msgReqDao.save(message);
        tx.commit();

        context.cleanUp();

        logger.info("Messages sent successfully");
        return MessageStatus.valueOf(message.getStatus().toString());
    }

    public synchronized MessageStatus sendMessage(MessageRequest message, String content, MotechContext context) {
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(context);

        if (context.getDBSession() != null) {
            ((Session) context.getDBSession().getSession()).evict(message);
        }

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        message.setStatus(MStatus.QUEUED);
        msgReqDao.save(message);
        tx.commit();

        //TODO Check length of message and break if necessary
        logger.info("Constructing GatewayRequest...");
        GatewayRequest gwReq = storeManager.constructMessage(message, context, null);
        gwReq.setMessage(content);
        gwReq.getGatewayRequestDetails().setMessage(content);

        logger.info("Initializing OMP MessagingService...");
        MessagingService msgSvc = ompManager.createMessagingService();

        logger.info("Sending GatewayRequest...");

        if (context.getDBSession() != null) {
            ((Session) context.getDBSession().getSession()).evict(gwReq.getGatewayRequestDetails());
            ((Session) context.getDBSession().getSession()).evict(message);
            ((Session) context.getDBSession().getSession()).evict(gwReq);
        }

        Map<Boolean, Set<GatewayResponse>> responses = msgSvc.sendMessage(gwReq, context);

        Boolean falseBool = new Boolean(false);
        if (responses.containsKey(falseBool)) {
            Set<GatewayResponse> resps = responses.get(falseBool);
            for (GatewayResponse gp : resps) {
                statHandler.handleStatus(gp);
            }
        }

        logger.info("Updating MessageRequest...");
        message.setDateProcessed(new Date());
        message.setStatus(MStatus.PENDING);
        logger.debug(message);

        if (context.getDBSession() != null) {
            ((Session) context.getDBSession().getSession()).evict(gwReq.getGatewayRequestDetails());
            ((Session) context.getDBSession().getSession()).evict(message);
            ((Session) context.getDBSession().getSession()).evict(gwReq);
        }

        tx.begin();
        msgReqDao.save(message);
        tx.commit();

        context.cleanUp();

        logger.info("Messages sent successfully");
        return MessageStatus.valueOf(message.getStatus().toString());
    }

    public synchronized MessageStatus sendMessage(String content, String recipient) {
        logger.info("Constructing MessageDetails object...");

        MotechContext mc = coreManager.createMotechContext();

        MessageRequest messageRequest = coreManager.createMessageRequest(mc);
        messageRequest.setTryNumber(1);
        messageRequest.setRequestId("");
        messageRequest.setDateFrom(null);
        messageRequest.setDateTo(null);
        messageRequest.setRecipientNumber(recipient);
        messageRequest.setNotificationType(null);
        messageRequest.setMessageType(MessageType.TEXT);
        messageRequest.setLanguage(null);
        messageRequest.setStatus(MStatus.QUEUED);
        messageRequest.setDateCreated(new Date());

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);

        return sendMessage(messageRequest, content, mc);
    }

    /**
     * @see OMIService.sendDefaulterMessage
     */
    public synchronized MessageStatus sendDefaulterMessage(String messageId, String workerNumber, Care[] cares, MediaType messageType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object...");

        MotechContext mc = coreManager.createMotechContext();
        MessageFormatter formatter = omiManager.createMessageFormatter();
        MessageRequest messageRequest = coreManager.createMessageRequest(mc);

        String content = "";
        for (Care c : cares) {
            content += formatter.formatDefaulterMessage(c) + "\n\n";
        }

        messageRequest.setTryNumber(1);
        messageRequest.setRequestId(messageId);
        messageRequest.setDateFrom(startDate);
        messageRequest.setDateTo(endDate);
        messageRequest.setRecipientNumber(workerNumber);
        messageRequest.setMessageType(MessageType.valueOf(messageType.toString()));
        messageRequest.setStatus(MStatus.QUEUED);
        messageRequest.setDateCreated(new Date());

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);

        if (messageRequest.getDateFrom() == null && messageRequest.getDateTo() == null) {
            return sendMessage(messageRequest, content, mc);
        }

        logger.info("Saving MessageRequest...");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        msgReqDao.save(messageRequest);
        tx.commit();

        mc.cleanUp();

        return MessageStatus.valueOf(messageRequest.getStatus().toString());
    }

    /**
     * @see OMIService.sendDeliveriesMessage
     */
    public synchronized MessageStatus sendDeliveriesMessage(String messageId, String workerNumber, Patient[] patients, String deliveryStatus, MediaType messageType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object...");

        MotechContext mc = coreManager.createMotechContext();
        MessageFormatter formatter = omiManager.createMessageFormatter();
        MessageRequest messageRequest = coreManager.createMessageRequest(mc);

        String content = formatter.formatDeliveriesMessage(deliveryStatus, patients);

        messageRequest.setTryNumber(1);
        messageRequest.setRequestId(messageId);
        messageRequest.setDateFrom(startDate);
        messageRequest.setDateTo(endDate);
        messageRequest.setRecipientNumber(workerNumber);
        messageRequest.setMessageType(MessageType.valueOf(messageType.toString()));
        messageRequest.setStatus(MStatus.QUEUED);
        messageRequest.setDateCreated(new Date());

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);

        if (messageRequest.getDateFrom() == null && messageRequest.getDateTo() == null) {
            return sendMessage(messageRequest, content, mc);
        }

        logger.info("Saving MessageRequest...");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        msgReqDao.save(messageRequest);
        tx.commit();

        mc.cleanUp();

        return MessageStatus.valueOf(messageRequest.getStatus().toString());
    }

    /**
     * @see OMIService.sendUpcomingCaresMessage
     */
    public synchronized MessageStatus sendUpcomingCaresMessage(String messageId, String workerNumber, Patient patient, MediaType messageType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object...");

        MotechContext mc = coreManager.createMotechContext();
        MessageFormatter formatter = omiManager.createMessageFormatter();
        MessageRequest messageRequest = coreManager.createMessageRequest(mc);

        String content = formatter.formatUpcomingCaresMessage(patient);

        messageRequest.setTryNumber(1);
        messageRequest.setRequestId(messageId);
        messageRequest.setDateFrom(startDate);
        messageRequest.setDateTo(endDate);
        messageRequest.setRecipientNumber(workerNumber);
        messageRequest.setMessageType(MessageType.valueOf(messageType.toString()));
        messageRequest.setStatus(MStatus.QUEUED);
        messageRequest.setDateCreated(new Date());

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);

        if (messageRequest.getDateFrom() == null && messageRequest.getDateTo() == null) {
            return sendMessage(messageRequest, content, mc);
        }

        logger.info("Saving MessageRequest...");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        Transaction tx = (Transaction) msgReqDao.getDBSession().getTransaction();
        tx.begin();
        msgReqDao.save(messageRequest);
        tx.commit();

        mc.cleanUp();

        return MessageStatus.valueOf(messageRequest.getStatus().toString());
    }

    /**
     * @see OMIService.processMessageRequests
     */
    public synchronized void processMessageRequests() {
        MotechContext mc = coreManager.createMotechContext();

        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        logger.info("Fetching queued messages...");
        List<MessageRequest> messages = msgReqDao.getMsgByStatus(MStatus.QUEUED);

        int numMsgs = (messages == null) ? 0 : messages.size();
        logger.info("MessageRequest fetched: " + numMsgs);
        logger.debug(messages);

        logger.info("Initializing OMP MessagingService...");
        MessagingService msgSvc = ompManager.createMessagingService();

        Language defaultLanguage = coreManager.createLanguageDAO(mc).getByCode(defaultLang);

        logger.info("Building GatewayRequests...");
        for (MessageRequest message : messages) {
            GatewayRequest gwReq = storeManager.constructMessage(message, mc, defaultLanguage);

            if (message.getLanguage() == null) {
                message.setLanguage(defaultLanguage);
            }

            if (mc.getDBSession() != null) {
                ((Session) mc.getDBSession().getSession()).evict(message);
                ((Session) mc.getDBSession().getSession()).evict(gwReq);
            }
            msgSvc.scheduleMessage(gwReq, mc);

            message.setDateProcessed(new Date());
            message.setStatus(MStatus.PENDING);
            logger.debug(message);

            if (mc.getDBSession() != null) {
                ((Session) mc.getDBSession().getSession()).evict(gwReq.getGatewayRequestDetails());
                ((Session) mc.getDBSession().getSession()).evict(gwReq);
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
    public synchronized void processMessageRetries() {
        MotechContext mc = coreManager.createMotechContext();
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        logger.info("Fetching messages marked for retry...");
        List<MessageRequest> messages = msgReqDao.getMsgRequestByStatusAndTryNumber(MStatus.RETRY, maxTries);

        logger.info("MessageRequest objects fetched successfully");
        logger.debug(messages);

        logger.info("Initializing GatewayRequestDAO...");
        GatewayRequestDetailsDAO gwReqDao = coreManager.createGatewayRequestDetailsDAO(mc);

        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();

        logger.info("Processing messages...");
        for (MessageRequest message : messages) {
            if (message.getTryNumber() >= maxTries) {
                message.setStatus(MStatus.FAILED);
            } else {
                if (mc.getDBSession() != null) {
                    ((Session) mc.getDBSession().getSession()).evict(message);
                }

                message.setTryNumber(message.getTryNumber() + 1);

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

                if (mc.getDBSession() != null) {
                    ((Session) mc.getDBSession().getSession()).evict(gwReqDet);
                }

                message.setStatus(MStatus.PENDING);
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
    public synchronized void getMessageResponses() {
        MotechContext mc = coreManager.createMotechContext();

        logger.info("Initializing MessageRequestDAO...");
        logger.info("fetching updated message responses...");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(mc);

        List<MessageRequest> messages = msgReqDao.getMsgRequestByStatusAndTryNumber(MStatus.PENDING, maxTries);

        if (messages != null) {
            logger.info("MessageRequest objects fetched successfully");
            logger.debug(messages);

            logger.info("Initializing GatewayResponseDAO...");
            GatewayResponseDAO gwRespDao = coreManager.createGatewayResponseDAO(mc);

            logger.info("Processing GatewayResponses...");
            for (MessageRequest message : messages) {
                GatewayResponse response = gwRespDao.getByRequestIdAndTryNumber(message.getRequestId(), message.getTryNumber());

                if (response != null) {
                    if (response.getMessageStatus() == MStatus.RETRY && message.getTryNumber() >= maxTries) {
                        response.setMessageStatus(MStatus.FAILED);
                    }

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
            logger.info("GatewayResponses processed successfully...");
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

    /**
     * @param omiManager the omiManager to set
     */
    public void setOmiManager(OMIManager omiManager) {
        this.omiManager = omiManager;
    }
}
