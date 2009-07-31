/*
 * ResponseDetails class is a POJO to hold and manipulate ResponseDetails to be persited in the database
 * It contains properties like recipient's number and message delivery status
 */

package com.dreamoval.motech.core.model;

import java.util.List;

/**
 * Date:
 * @author Joseph
 * Email : joseph@dreamoval.com
 */
public interface ResponseDetails extends MotechEntity {

    /**
     * @return the gatewayMessageId
     */
    String getGatewayMessageId();

    /**
     * @return the gid
     */
    String getGid();

    /**
     * @return the messageId
     */
    MessageDetailsImpl getMessageId();

    /**
     * @return the messageStatus
     */
    String getMessageStatus();

    /**
     * @return the recipientNumber
     */
    String getRecipientNumber();

    /**
     * @return the responseId
     */
    Long getResponseId();

    /**
     * @return the transitions
     */
    List getTransitions();

    /**
     * @param gatewayMessageId the gatewayMessageId to set
     */
    void setGatewayMessageId(String gatewayMessageId);

    /**
     * @param gid the gid to set
     */
    void setGid(String gid);

    /**
     * @param messageId the messageId to set
     */
    void setMessageId(MessageDetailsImpl messageId);

    /**
     * @param messageStatus the messageStatus to set
     */
    void setMessageStatus(String messageStatus);

    /**
     * @param recipientNumber the recipientNumber to set
     */
    void setRecipientNumber(String recipientNumber);

    /**
     * @param responseId the responseId to set
     */
    void setResponseId(Long responseId);

    /**
     * @param transitions the transitions to set
     */
    void setTransitions(List transitions);

}
