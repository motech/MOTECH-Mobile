package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * ResponseDetails class is a POJO to hold and manipulate ResponseDetails to be persited in the database
 * It contains properties like recipient's number and message delivery status
 * 
 * Date: Jul 24, 2009
 * @author Joseph (joseph@dreamoval.com)
 */
public interface GatewayResponse extends MotechEntity {

    /**
     * @return the gatewayMessageId
     */
    String getGatewayMessageId();

    /**
     * @return the messageId
     */
    GatewayRequest getGatewayRequest();

    /**
     * @return the messageStatus
     */
    MStatus getMessageStatus();

    /**
     * @return the recipientNumber
     */
    String getRecipientNumber();

    /**
     * @return the transitions
     */
    Set<Transition> getTransitions();

    /**
     * @return the responseText
     */
    String getResponseText();

    /**
     * @return the requestId
     */
    String getRequestId();

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @param gatewayMessageId the gatewayMessageId to set
     */
    void setGatewayMessageId(String gatewayMessageId);

    /**
     * @param messageId the messageId to set
     */
    void setGatewayRequest(GatewayRequest messageId);

    /**
     * @param messageStatus the messageStatus to set
     */
    void setMessageStatus(MStatus messageStatus);

    /**
     * @param recipientNumber the recipientNumber to set
     */
    void setRecipientNumber(String recipientNumber);

    /**
     * @param transitions the transitions to set
     */
    void setTransitions(Set<Transition> transitions);

    /**
     * @param recipientNumber the recipientNumber to set
     */
    void setResponseText(String responseText);

    /**

     */
    void addTransition(Transition transition);

    void addTransition(List<Transition> transitions);

    void removeTransition(Transition transition);

    void removeTransition(List<Transition> transitions);

    /**
     * @param requestId the requestId to set
     */
    void setRequestId(String requestId);

    /**
     * @param  date the date to set
     */
    void setDateCreated(Date date);
}
