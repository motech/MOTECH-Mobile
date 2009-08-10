/*
 * MessageDetails class is a POJO to hold MessageDetails information for data storage and manipulation
 * It has properties for example the text message itself or the numberofpages of that sms text in case it's
 * sms send
 */

package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.Set;

/**
 * Date: Jul 24, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface MessageDetails extends MotechEntity {

    /**
     * @return the dateSent
     */
    Date getDateSent();
    
    /**
     * @return the globalStatus
     */
    String getGlobalStatus();

    /**
     * @return the messageText
     */
    String getMessageText();

    /**
     * @return the messageType
     */
    String getMessageType();

    /**
     * @return the numberOfPages
     */
    int getNumberOfPages();

    /**
     * @return the recipientsNumbers
     */
    String getRecipientsNumbers();

    /**
     * @return the responseDetails
     */
    Set<ResponseDetails> getResponseDetails();

    /**
     * @param dateSent the dateSent to set
     */
    void setDateSent(Date dateSent);

    /**
     * @param globalStatus the globalStatus to set
     */
    void setGlobalStatus(String globalStatus);

    /**
     * @param messageText the messageText to set
     */
    void setMessageText(String messageText);

    /**
     * @param messageType the messageType to set
     */
    void setMessageType(String messageType);

    /**
     * @param numberOfPages the numberOfPages to set
     */
    void setNumberOfPages(int numberOfPages);

    /**
     * @param recipientsNumbers the recipientsNumbers to set
     */
    void setRecipientsNumbers(String recipientsNumbers);

    /**
     * @param responseDetails the responseDetails to set
     */
    void setResponseDetails(Set<ResponseDetails> responseDetails);

    /**
     *
     */
    void addResponse(ResponseDetails response);
    /**
     *
     */
    void removeResponse(ResponseDetails response);
}
