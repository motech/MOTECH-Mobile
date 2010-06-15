package org.motechproject.mobile.core.dao;

import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import java.util.Date;
import java.util.List;

/**
 * MessageRequestDao is an interface that defines only methods and attributes that are specific to MessageRequest entity
 *  Date : Sep 25, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageRequestDAO<T extends MessageRequest> extends GenericDAO<T> {

    /**
     *  Method to select MessageRequest Objects based on its status and its schedule time
     * @param status the Status of the message
     * @param schedule the Date schedule
     * @return a list of MessageRequest
     */
    public List getMsgRequestByStatusAndSchedule(MStatus status, Date schedule);

    /**
     * Method to select MessageRequest Objects based on status and tryNumber
     * @param status the Status of the message
     * @param tryNumber number of retries for the message
     * @return list of MessageRequest Objects
     */
    public List getMsgRequestByStatusAndTryNumber(MStatus status, int tryNumber);

    /**
     * Method to select MessageRequest Objects based on status
     * @param status the Status of the message
     * @return list of MessageRequest Objects
     */
    public List<MessageRequest> getMsgByStatus(MStatus status);
    
    /**
     * Method to select MessageRequest object based on recipient ID and status
     * @param recipientID id of the recipient
     * @param status status of the message request
     * @return list of matching message requests
     */
    public List<MessageRequest> getMsgRequestByRecipientAndStatus(String recipientID, MStatus status);
    
    /**
     * Method to select MessageRequest object based on recipient ID and status
     * @param recipientID id of the recipient
     * @param schedule the Date schedule
     * @return list of matching message requests
     */
    public List<MessageRequest> getMsgRequestByRecipientAndSchedule(String recipientID, Date schedule);
    
}
