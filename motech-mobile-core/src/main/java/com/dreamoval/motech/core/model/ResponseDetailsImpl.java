/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.List;
import java.util.Set;

/**
 * Date :Jul 24, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class ResponseDetailsImpl extends MotechEntityImpl implements ResponseDetails{

    private static final long serialVersionUID = 1L;
    
    private MessageDetailsImpl messageId;
    private String gatewayMessageId;
    private String recipientNumber;
    private String messageStatus;
    private List transitions;

    public ResponseDetailsImpl(){}


    public ResponseDetailsImpl(String gatewayMessageId, String recipientNumber, String messageStatus) {
        this.gatewayMessageId = gatewayMessageId;
        this.recipientNumber = recipientNumber;
        this.messageStatus = messageStatus;
    }
    
    /**
     * @return the messageId
     */
    public MessageDetailsImpl getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(MessageDetailsImpl messageId) {
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
    public List getTransitions() {
        return transitions;
    }

    /**
     * @param transitions the transitions to set
     */
    public void setTransitions(List transitions) {
        this.transitions = transitions;
    }
}
