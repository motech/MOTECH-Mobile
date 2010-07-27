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
import org.motechproject.ws.Care;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.Patient;
import org.motechproject.ws.MessageStatus;
import org.motechproject.ws.NameValuePair;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of the OMIService
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 *
 */
@TransactionConfiguration
@Transactional
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

        if(patientNumberType == ContactNumberType.PUBLIC && messageType == MediaType.TEXT)
            return MessageStatus.REJECTED;


        MessageRequest messageRequest = coreManager.createMessageRequest();

        NotificationTypeDAO noteTypeDao = coreManager.createNotificationTypeDAO();
        NotificationType noteType = (NotificationType) noteTypeDao.getById(notificationType);

        Language langObject = coreManager.createLanguageDAO().getByCode(langCode);

        if (personalInfo != null) {
            HashSet<NameValuePair> details = new HashSet<NameValuePair>();
            for (NameValuePair detail : personalInfo) {
                details.add(detail);
            }
            messageRequest.setPersInfos(details);
        }

        messageRequest.setTryNumber(1);
        messageRequest.setRequestId(messageId);
        //VOICE messages need to have a start date to accommodate replaying DELIVERED messages
        messageRequest.setDateFrom(startDate == null && messageType == MediaType.VOICE ? new Date() : startDate);
        messageRequest.setDateTo(endDate);
        messageRequest.setRecipientNumber(patientNumber);
        messageRequest.setPhoneNumberType(patientNumberType.toString());
        messageRequest.setRecipientId(recipientId);
        messageRequest.setNotificationType(noteType);
        messageRequest.setMessageType(MessageType.valueOf(messageType.toString()));
        messageRequest.setLanguage(langObject);
        messageRequest.setStatus(MStatus.QUEUED);
        messageRequest.setDateCreated(new Date());

        logger.info("MessageRequest object successfully constructed");
        logger.debug(messageRequest);

        if (messageRequest.getDateFrom() == null && messageRequest.getDateTo() == null) {
            return sendMessage(messageRequest);
        }

        logger.info("Saving MessageRequest...");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO();

        msgReqDao.save(messageRequest);
        return MessageStatus.valueOf(messageRequest.getStatus().toString());
    }

    /**
     *logger
     * @see OMIService.sendCHPSMessage
     */
    public synchronized MessageStatus saveCHPSMessageRequest(String messageId, NameValuePair[] personalInfo, String workerNumber, Patient[] patientList, String langCode, MediaType messageType, Long notificationType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object...");


   
        MessageRequest messageRequest = coreManager.createMessageRequest();

        NotificationTypeDAO noteTypeDao = coreManager.createNotificationTypeDAO();
        NotificationType noteType = (NotificationType) noteTypeDao.getById(notificationType);

        Language langObject = coreManager.createLanguageDAO().getByCode(langCode);

        HashSet<NameValuePair> details = new HashSet<NameValuePair>();
        if (personalInfo != null) {
            for (NameValuePair detail : personalInfo) {
                details.add(detail);
            }
        }
        if (patientList != null) {
            for (Patient p : patientList) {
                if (p.getPreferredName() != null) {
                    details.add(new NameValuePair("PreferredName", p.getPreferredName()));
                }
                if (p.getLastName() != null) {
                    details.add(new NameValuePair("LastName", p.getLastName()));
                }
                if (p.getCommunity() != null) {
                    details.add(new NameValuePair("Community", p.getCommunity()));
                }
                if (p.getFirstName() != null) {
                    details.add(new NameValuePair("FirstName", p.getFirstName()));
                }
                if (p.getMotechId() != null) {
                    details.add(new NameValuePair("MotechId", p.getMotechId()));
                }
                if (p.getPhoneNumber() != null) {
                    details.add(new NameValuePair("PhoneNumber", p.getPhoneNumber()));
                }
            }
        }
        messageRequest.setPersInfos(details);

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
            return sendMessage(messageRequest);
        }

        logger.info("Saving MessageRequest...");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO();

        msgReqDao.save(messageRequest);

        return MessageStatus.valueOf(messageRequest.getStatus().toString());
    }

    public synchronized MessageStatus sendMessage(MessageRequest message) {
        Language defaultLanguage = coreManager.createLanguageDAO().getByCode(defaultLang);

        if (message.getLanguage() == null) {
            message.setLanguage(defaultLanguage);
        }

        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO();
    
        message.setStatus(MStatus.QUEUED);
        msgReqDao.save(message);
  
        logger.info("Constructing GatewayRequest...");
        GatewayRequest gwReq = storeManager.constructMessage(message, defaultLanguage);

        logger.info("Initializing OMP MessagingService...");
        MessagingService msgSvc = ompManager.createMessagingService();

        logger.info("Sending GatewayRequest...");

   
        Map<Boolean, Set<GatewayResponse>> responses = msgSvc.sendMessage(gwReq);;

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

        msgReqDao.save(message);

        logger.info("Messages sent successfully");
        return MessageStatus.valueOf(message.getStatus().toString());
    }

    public synchronized MessageStatus sendMessage(MessageRequest message, String content) {
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO();
 
        message.setStatus(MStatus.QUEUED);
        msgReqDao.save(message);
 

        //TODO Check length of message and break if necessary
        logger.info("Constructing GatewayRequest...");
        GatewayRequest gwReq = storeManager.constructMessage(message, null);
        gwReq.setMessage(content);
        gwReq.getGatewayRequestDetails().setMessage(content);

        logger.info("Initializing OMP MessagingService...");
        MessagingService msgSvc = ompManager.createMessagingService();

        logger.info("Sending GatewayRequest...");
        Map<Boolean, Set<GatewayResponse>> responses = msgSvc.sendMessage(gwReq);

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

        msgReqDao.save(message);
        logger.info("Messages sent successfully");
        return MessageStatus.valueOf(message.getStatus().toString());
    }

    public synchronized MessageStatus sendMessage(String content, String recipient) {
        logger.info("Constructing MessageDetails object...");

        MessageRequest messageRequest = coreManager.createMessageRequest();
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

        MessageStatus status = sendMessage(messageRequest, content);
        return status;
    }



    public synchronized MessageStatus scheduleMessage(String content, String recipient) {
        logger.info("Constructing MessageDetails object...");

        MessageRequest messageRequest = coreManager.createMessageRequest();
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

        MessageStatus status = scheduleMessage(messageRequest, content );
        return status;
    }

    public synchronized MessageStatus scheduleMessage(MessageRequest message, String content) {
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO();
   
        msgReqDao.save(message);


        //TODO Check length of message and break if necessary
        logger.info("Constructing GatewayRequest...");
        GatewayRequest gwReq = storeManager.constructMessage(message, null);
        gwReq.setMessage(content);
        gwReq.getGatewayRequestDetails().setMessage(content);

        logger.info("Initializing OMP MessagingService...");
        MessagingService msgSvc = ompManager.createMessagingService();

        logger.info("Scheduling GatewayRequest...");

//        if (context.getDBSession() != null) {
//            ((Session) context.getDBSession().getSession()).evict(gwReq.getGatewayRequestDetails());
//            ((Session) context.getDBSession().getSession()).evict(message);
//            ((Session) context.getDBSession().getSession()).evict(gwReq);
//        }

        msgSvc.scheduleMessage(gwReq);

        logger.info("Updating MessageRequest...");
        message.setDateProcessed(new Date());
        message.setStatus(MStatus.PENDING);
        logger.debug(message);

//        if (context.getDBSession() != null) {
//            ((Session) context.getDBSession().getSession()).evict(gwReq.getGatewayRequestDetails());
//            ((Session) context.getDBSession().getSession()).evict(message);
//            ((Session) context.getDBSession().getSession()).evict(gwReq);
//        }


        msgReqDao.save(message);
        logger.info("Messages sent successfully");
        return MessageStatus.valueOf(message.getStatus().toString());
    }

    /**
     * @see OMIService.sendDefaulterMessage
     */
    public synchronized MessageStatus sendDefaulterMessage(String messageId, String workerNumber, Care[] cares, MediaType messageType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object...");


        MessageFormatter formatter = omiManager.createMessageFormatter();
        MessageRequest messageRequest = coreManager.createMessageRequest();

        String content = formatter.formatDefaulterMessage(cares);

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

        MessageStatus status = scheduleMessage(messageRequest, content );
        return status;
    }

    /**
     * @see OMIService.sendDeliveriesMessage
     */
    public synchronized MessageStatus sendDeliveriesMessage(String messageId, String workerNumber, Patient[] patients, String deliveryStatus, MediaType messageType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object...");


        MessageFormatter formatter = omiManager.createMessageFormatter();
        MessageRequest messageRequest = coreManager.createMessageRequest();

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

        MessageStatus status = scheduleMessage(messageRequest, content);
        return status;
    }

    /**
     * @see OMIService.sendUpcomingCaresMessage
     */
    public synchronized MessageStatus sendUpcomingCaresMessage(String messageId, String workerNumber, Patient patient, MediaType messageType, Date startDate, Date endDate) {
        logger.info("Constructing MessageDetails object...");


        MessageFormatter formatter = omiManager.createMessageFormatter();
        MessageRequest messageRequest = coreManager.createMessageRequest();

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

        MessageStatus status = scheduleMessage(messageRequest, content);
        return status;
    }

    /**
     * @see OMIService.processMessageRequests
     */
    public synchronized void processMessageRequests() {
              MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO();

        logger.info("Fetching queued messages...");
        List<MessageRequest> messages = msgReqDao.getMsgByStatus(MStatus.QUEUED);

        int numMsgs = (messages == null) ? 0 : messages.size();
        logger.info("MessageRequest fetched: " + numMsgs);
        logger.debug(messages);

        logger.info("Initializing OMP MessagingService...");
        MessagingService msgSvc = ompManager.createMessagingService();

        Language defaultLanguage = coreManager.createLanguageDAO().getByCode(defaultLang);

        logger.info("Building GatewayRequests...");
        for (MessageRequest message : messages) {
            GatewayRequest gwReq = storeManager.constructMessage(message,defaultLanguage);
            message.setGatewayRequestDetails(gwReq.getGatewayRequestDetails());

            if (message.getLanguage() == null) {
                message.setLanguage(defaultLanguage);
            }

        
            msgSvc.scheduleMessage(gwReq);

            message.setDateProcessed(new Date());
            message.setStatus(MStatus.PENDING);
            logger.debug(message); 
            msgReqDao.save(message);

        }
       
        logger.info("Messages processed successfully");
    }

    /**
     * @see OMIService.processMessageRetries
     */
    public synchronized void processMessageRetries() {
       
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO();

        logger.info("Fetching messages marked for retry...");
        List<MessageRequest> messages = msgReqDao.getMsgRequestByStatusAndTryNumber(MStatus.RETRY, maxTries);

        logger.info("MessageRequest objects fetched successfully");
        logger.debug(messages);

        logger.info("Initializing GatewayRequestDAO...");
        GatewayRequestDetailsDAO gwReqDao = coreManager.createGatewayRequestDetailsDAO();

        logger.info("Initializing OMP MessagingService");
        MessagingService msgSvc = ompManager.createMessagingService();

        logger.info("Processing messages...");
        for (MessageRequest message : messages) {
            if (message.getTryNumber() >= maxTries) {
                message.setStatus(MStatus.FAILED);
            } else {
            
                message.setTryNumber(message.getTryNumber() + 1);

                if(message.getGatewayRequestDetails() == null)
                    continue;

                GatewayRequestDetails gwReqDet = (GatewayRequestDetails) gwReqDao.getById(message.getGatewayRequestDetails().getId());

                GatewayRequest gwReq = coreManager.createGatewayRequest();
                gwReq.setDateFrom(message.getDateFrom());
                gwReq.setDateTo(message.getDateTo());
                gwReq.setMessageRequest(message);
                gwReq.setRecipientsNumber(storeManager.formatPhoneNumber(message.getRecipientNumber(), message.getMessageType()));
                gwReq.setRequestId(message.getRequestId());
                gwReq.setTryNumber(message.getTryNumber());
                gwReq.setMessage(gwReqDet.getMessage());
                gwReq.setMessageStatus(MStatus.SCHEDULED);

                gwReqDet.getGatewayRequests().add(gwReq);
                msgSvc.scheduleMessage(gwReqDet);

                message.setStatus(MStatus.PENDING);
            }

  
            msgReqDao.save(message);

        }


        logger.info("Messages processed successfully");
    }

    /**
     * @see OMIService.getMessageResponses
     */
    public synchronized void getMessageResponses() {
     

        logger.info("Initializing MessageRequestDAO...");
        logger.info("fetching updated message responses...");
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO();

        List<MessageRequest> messages = msgReqDao.getMsgRequestByStatusAndTryNumber(MStatus.PENDING, maxTries);

        if (messages != null) {
            logger.info("MessageRequest objects fetched successfully");
            logger.debug(messages);

            logger.info("Initializing GatewayResponseDAO...");
            GatewayResponseDAO gwRespDao = coreManager.createGatewayResponseDAO();

            logger.info("Processing GatewayResponses...");
            for (MessageRequest message : messages) {
                GatewayResponse response = gwRespDao.getByMessageIdAndTryNumber(message.getId(), message.getTryNumber());

                if (response != null) {
                    if (response.getMessageStatus() == MStatus.RETRY && message.getTryNumber() >= maxTries) {
                        response.setMessageStatus(MStatus.FAILED);
                    }

                    statHandler.handleStatus(response);

                    message.setStatus(response.getMessageStatus());          
                
                    msgReqDao.save(message);
            
                }
            }
            logger.info("GatewayResponses processed successfully...");
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
