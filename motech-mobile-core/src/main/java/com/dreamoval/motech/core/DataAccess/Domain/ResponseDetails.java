/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dataaccess.domain;

import java.util.Set;

/**
 *
 * @author Jojo
 */
public class ResponseDetails {

    public ResponseDetails(){}

private Long responseId;
private MessageDetails messageId;
private String gatewayMessageId;

    public ResponseDetails(String gatewayMessageId, String recipientNumber, String messageStatus) {
        this.gatewayMessageId = gatewayMessageId;
        this.recipientNumber = recipientNumber;
        this.messageStatus = messageStatus;
    }
private String recipientNumber;
private String messageStatus;
private Set transitions;

    /**
     * @return the responseId
     */
    public Long getResponseId() {
        return responseId;
    }

    /**
     * @param responseId the responseId to set
     */
    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    /**
     * @return the messageId
     */
    public MessageDetails getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(MessageDetails messageId) {
        this.messageId = messageId;
    }

    /**
     * @return the gatewayMessageId
     */
    public String getGatewayMessageId() {
        return gatewayMessageId;
    }

    /**
     * @param gatewayMessageId the gatewayMessageId to set
     */
    public void setGatewayMessageId(String gatewayMessageId) {
        this.gatewayMessageId = gatewayMessageId;
    }

    /**
     * @return the recipientNumber
     */
    public String getRecipientNumber() {
        return recipientNumber;
    }

    /**
     * @param recipientNumber the recipientNumber to set
     */
    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    /**
     * @return the messageStatus
     */
    public String getMessageStatus() {
        return messageStatus;
    }

    /**
     * @param messageStatus the messageStatus to set
     */
    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    /**
     * @return the transitions
     */
    public Set getTransitions() {
        return transitions;
    }

    /**
     * @param transitions the transitions to set
     */
    public void setTransitions(Set transitions) {
        this.transitions = transitions;
    }

  
}
