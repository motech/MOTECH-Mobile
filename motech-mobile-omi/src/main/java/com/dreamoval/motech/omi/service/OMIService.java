package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.omi.manager.OMIManager;
import java.util.Date;
import org.motechproject.ws.Care;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.MessageStatus;
import org.motechproject.ws.NameValuePair;
import org.motechproject.ws.Patient;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Jul 31, 2009
 */
public interface OMIService {

    /**
     * Processes and stores a message to a registered patient
     *
     * @param messageId Id of the message to send
     * @param personalInfo List of name value pairs containing patient information
     * @param patientNumber Patient mobile contact number
     * @param patientNumberType Type of contact number. Possible values include PERSONAL, SHARED
     * @param langCode Code representing preferred communication language
     * @param mediaType Patient's preferred communication medium
     * @param notificationType Type of message to send to patient
     * @param startDate Date to begin message sending attempts
     * @param endDate Date to stop message sending attempts
     * @return The status of the message
     */
    public MessageStatus savePatientMessageRequest(String messageId, NameValuePair[] personalInfo, String patientNumber, ContactNumberType patientNumberType, String langCode, MediaType messageType, Long notificationType, Date startDate, Date endDate);

    /**
     * Processes and stores a message to a registered CHPS worker
     *
     * @param messageId Id of the message to send
     * @param personalInfo List of name value pairs containing patient information
     * @param workerNumber CHPS worker's mobile contact number
     * @param patients A List of patients requiring service from CHPS worker
     * @param langCode  Code representing preferred communication language
     * @param mediaType Patient's preferred communication medium
     * @param notificationType Type of message to send to patient
     * @param startDate Date to begin message sending attempts
     * @param endDate Date to stop message sending attempts
     * @return The status of the message
     */
    MessageStatus saveCHPSMessageRequest(String messageId, NameValuePair[] personalInfo, String workerNumber, Patient[] patientList, String langCode, MediaType messageType, Long notificationType, Date startDate, Date endDate);

    /**
     * Sends a templated outgoing message request
     *
     * @param message The message request to send
     * @param context The current application context
     * @return The status of the message
     */
    MessageStatus sendMessage(MessageRequest message, MotechContext context);

    /**
     * Sends a non-templated outgoing message request
     *
     * @param message The message request to send
     * @param content The content of the message
     * @param context  The current application context
     * @return
     */
    MessageStatus sendMessage(MessageRequest message, String content, MotechContext context);

    /**
     * Sends a list of care defaulters to a CHPS worker
     *
     * @param messageId Id of the message to send
     * @param workerNumber CHPS worker's mobile contact number
     * @param cares List of patient care options which have defaulters
     * @param mediaType Patient's preferred communication medium
     * @param startDate Date to begin message sending attempts
     * @param endDate Date to stop message sending attempts
     * @return The status of the message
     */
    MessageStatus sendDefaulterMessage(String messageId, String workerNumber, Care[] cares, MediaType messageType, Date startDate, Date endDate);

    /**
     * Sends a list of patients within a delivery schedule to a CHPS worker
     *
     * @param messageId Id of the message to send
     * @param workerNumber CHPS worker's mobile contact number
     * @param patients List of patients with matching delivery status
     * @param deliveryStatus Status of patient delivery. Expected values are 'Upcoming', 'Recent' and 'Overdue'
     * @param mediaType Patient's preferred communication medium
     * @param startDate Date to begin message sending attempts
     * @param endDate Date to stop message sending attempts
     * @return The status of the message
     */
    MessageStatus sendDeliveriesMessage(String messageId, String workerNumber, Patient[] patients, String deliveryStatus, MediaType messageType, Date startDate, Date endDate);

    /**
     * Sends a list of upcoming care for a particular patient to a CHPS worker
     *
     * @param messageId Id of the message to send
     * @param workerNumber CHPS worker's mobile contact number
     * @param patient patient due for care
     * @param mediaType Patient's preferred communication medium
     * @param startDate Date to begin message sending attempts
     * @param endDate Date to stop message sending attempts
     * @return The status of the message
     */
    MessageStatus sendUpcomingCaresMessage(String messageId, String workerNumber, Patient patient, MediaType messageType, Date startDate, Date endDate);

    /**
     * Sends an SMS message
     *
     * @param content the message to send
     * @param recipient the phone number to receive the message
     * @return
     */
    MessageStatus sendMessage(String content, String recipient);

    /**
     * Processes stored MessageRequests into GatewayRequests and schedules them for delivery on the OMP
     */
    void processMessageRequests();
    
    /**
     * Resends all messages that have been marked for retry
     */
    void processMessageRetries();
    
    /**
     * Queries and updates the statuses of sent messages
     */
    void getMessageResponses();
    
    
    /**
     * Gets the default language
     * @return the default language
     */
    String getDefaultLang();

    /**
     * Sets the default language
     * @param defaultLang the default language to set
     */
    void setDefaultLang(String defaultLang);

    /**
     * Gets the maximum number of message retries
     * @return the maximum retries
     */
    int getMaxTries();

    /**
     * Sets the maximum number of message retries
     * @param maxRetries the maximum number of message retries
     */
    void setMaxTries(int maxRetries);

    /**
     * @param omiManager the omiManager to set
     */
    public void setOmiManager(OMIManager omiManager);
}
