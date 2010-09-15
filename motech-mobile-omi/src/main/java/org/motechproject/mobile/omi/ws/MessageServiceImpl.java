package org.motechproject.mobile.omi.ws;

import org.motechproject.mobile.omi.manager.OMIManager;
import org.motechproject.mobile.omi.service.OMIService;

import java.util.Date;
import javax.jws.WebParam;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.motechproject.ws.Care;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.MessageStatus;
import org.motechproject.ws.NameValuePair;
import org.motechproject.ws.Patient;
import org.motechproject.ws.PatientMessage;
import org.motechproject.ws.mobile.MessageService;

/**
 * An implementation of the MessageService interface.
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created 30-07-09
 */
@WebService(targetNamespace = "http://server.ws.motechproject.org/")
public class MessageServiceImpl implements MessageService {

    private OMIManager omiManager;
    private static Logger logger = Logger.getLogger(MessageServiceImpl.class);

    /**
    *
    * @see MessageService.sendPatientMessages
    */
   @WebMethod
    public void sendPatientMessages(@WebParam(name="messages") PatientMessage[] messages) {
        logger.debug("Called MessageService.sendPatientMessages with number of messages: " + 
            (messages != null ? messages.length : "null"));
       
        logger.info("Processing request...");
        if( messages != null) {
            OMIService omiService = omiManager.createOMIService();
            
            for( PatientMessage message:messages) {
                omiService.savePatientMessageRequest(message.getMessageId(), message.getPersonalInfo(), 
                        message.getPatientNumber(), message.getPatientNumberType(), message.getLangCode(), 
                        message.getMediaType(), message.getNotificationType(), message.getStartDate(), 
                        message.getEndDate(), message.getRecipientId());
            }
        }
    }
    
    /**
     *
     * @see MessageService.sendPatientMessage
     */
    @WebMethod
    public MessageStatus sendPatientMessage(@WebParam(name = "messageId") String messageId, 
    										@WebParam(name = "personalInfo") NameValuePair[] personalInfo, 
    										@WebParam(name = "patientNumber") String patientNumber, 
    										@WebParam(name = "patientNumberType") ContactNumberType patientNumberType, 
    										@WebParam(name = "langCode") String langCode, 
    										@WebParam(name = "mediaType") MediaType messageType, 
    										@WebParam(name = "notificationType") Long notificationType, 
    										@WebParam(name = "startDate") Date startDate, 
    										@WebParam(name = "endDate") Date endDate, 
    										@WebParam(name = "recipientId") String recipientId) {
        logger.debug("Called MessageService.sendPatientMessage with parameters:\n\rmessageId - " + messageId + 
        			 "\n\rclinic - " + patientNumber + 
        			 "\n\rpatientNumbrType - " + patientNumberType + 
        			 "\n\rmessageType - " + messageType + 
        			 "\n\rstartDate - " + startDate + 
        			 "\n\rendDate - " + endDate +
        			 "\n\rrecipientId - " + (recipientId != null ? recipientId : "null"));
        logger.info("Processing request...");
        return omiManager.createOMIService().savePatientMessageRequest(messageId, personalInfo, patientNumber, patientNumberType, langCode, messageType, notificationType, startDate, endDate, recipientId);
    }

    /**
     *
     * @see MessageService.sendCHPSMessage
     */
    @WebMethod
    public MessageStatus sendCHPSMessage(@WebParam(name = "messageId") String messageId,
                                         @WebParam(name = "personalInfo") NameValuePair[] personalInfo,
                                         @WebParam(name = "workerNumber") String workerNumber,
                                         @WebParam(name = "patientList") Patient[] patientList,
                                         @WebParam(name = "langCode") String langCode,
                                         @WebParam(name = "mediaType") MediaType messageType,
                                         @WebParam(name = "notificationType") Long notificationType,
                                         @WebParam(name = "startDate") Date startDate,
                                         @WebParam(name = "endDate") Date endDate) {
        logger.debug("Called MessageService.sendCHPSMessage with parameters:\n\rmessageId - " + messageId + "\n\rworkerNumber - " + workerNumber + "\n\rstartDate - " + startDate + "\n\rendDate - " + endDate);
        logger.info("Processing request...");
        return this.omiManager.createOMIService().saveCHPSMessageRequest(messageId, personalInfo, workerNumber, patientList, langCode, messageType, notificationType, startDate, endDate);
    }

    /**
     *
     * @see MessageService.sendDefaulterMessage
     */
    @WebMethod
    public MessageStatus sendDefaulterMessage(@WebParam(name = "messageId") String messageId,
                                              @WebParam(name = "workerNumber") String workerNumber,
                                              @WebParam(name = "cares") Care[] cares,
                                              @WebParam(name = "messageType") MediaType mediaType,
                                              @WebParam(name = "startDate") Date startDate,
                                              @WebParam(name = "endDate") Date endDate) {
        logger.info("Processing request...");
        return this.omiManager.createOMIService().sendDefaulterMessage(messageId, workerNumber, cares, mediaType, startDate, endDate);
    }

    /**
     *
     * @see MessageService.sendDeliveriesMessage
     */
    @WebMethod
    public MessageStatus sendDeliveriesMessage(@WebParam(name = "messageId") String messageId,
                                               @WebParam(name = "workerNumber") String workerNumber,
                                               @WebParam(name = "patients") Patient[] patients,
                                               @WebParam(name = "deliveryStatus") String deliveryStatus,
                                               @WebParam(name = "mediaType") MediaType mediaType,
                                               @WebParam(name = "startDate") Date startDate,
                                               @WebParam(name = "endDate") Date endDate) {
        logger.info("Processing request...");
        return this.omiManager.createOMIService().sendDeliveriesMessage(messageId, workerNumber, patients, deliveryStatus, mediaType, startDate, endDate);
    }

    /**
     *
     * @see MessageService.sendUpcomingCaresMessage
     */
    @WebMethod
    public MessageStatus sendUpcomingCaresMessage(@WebParam(name = "messageId") String messageId,
                                                  @WebParam(name = "workerNumber") String workerNumber,
                                                  @WebParam(name = "patient") Patient patient,
                                                  @WebParam(name = "mediaType") MediaType mediaType,
                                                  @WebParam(name = "startDate") Date startDate,
                                                  @WebParam(name = "endDate") Date endDate) {
        logger.info("Processing request...");
        return this.omiManager.createOMIService().sendUpcomingCaresMessage(messageId, workerNumber, patient, mediaType, startDate, endDate);
    }

    /**
     *
     * @see MessageService.sendMessage
     */
    @WebMethod
    public MessageStatus sendMessage(@WebParam(name = "content") String content,
                                     @WebParam(name = "recipient") String recipient) {
        logger.info("Processing request...");
        return this.omiManager.createOMIService().sendMessage(content, recipient);
    }

    /**
     * @return the omiManager
     */
    @WebMethod(exclude = true)
    public OMIManager getOmiManager() {
        return omiManager;
    }

    /**
     * @param omiManager the omiManager to set
     */
    @WebMethod(exclude = true)
    public void setOmiManager(OMIManager omiManager) {
        logger.debug("Setting MessageServiceImpl.omiManager:");
        logger.debug(omiManager);
        this.omiManager = omiManager;
    }
}
