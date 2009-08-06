/*
 * ResponseDetails class is a POJO to hold and manipulate ResponseDetails to be persited in the database
 * It contains properties like recipient's number and message delivery status
 */

package com.dreamoval.motech.core.model;

import java.util.List;

/**
 * Date: Jul 24, 2009
 * @author Joseph (joseph@dreamoval.com)
 */
public interface ResponseDetails extends MotechEntity {

    /**
     * @return the gatewayMessageId
     */
    String getGatewayMessageId();

    /**
     * @return the messageId
     */
    MessageDetails getMessageId();

    /**
     * @return the messageStatus
     */
    String getMessageStatus();

    /**
     * @return the recipientNumber
     */
    String getRecipientNumber();

    /**
     * @return the transitions
     */
    List getTransitions();

    /**
     * @param gatewayMessageId the gatewayMessageId to set
     */
    void setGatewayMessageId(String gatewayMessageId);

    /**
     * @param messageId the messageId to set
     */
    void setMessageId(MessageDetails messageId);

    /**
     * @param messageStatus the messageStatus to set
     */
    void setMessageStatus(String messageStatus);

    /**
     * @param recipientNumber the recipientNumber to set
     */
    void setRecipientNumber(String recipientNumber);

    /**
     * @param transitions the transitions to set
     */
    void setTransitions(List transitions);

}
