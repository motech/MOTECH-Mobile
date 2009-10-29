package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequest;
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
}
