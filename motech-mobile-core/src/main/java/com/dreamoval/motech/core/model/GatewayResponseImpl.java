
package com.dreamoval.motech.core.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date :Jul 24, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class GatewayResponseImpl extends MotechEntityImpl implements GatewayResponse{

    private static final long serialVersionUID = 1L;
    
    private GatewayRequest gatewayRequest;
    private String gatewayMessageId;
    private String recipientNumber;
    private MStatus messageStatus;
    private String responseText;
    private Set<Transition> transitions = new HashSet<Transition>();
    

    public GatewayResponseImpl(){}


    public GatewayResponseImpl(String gatewayMessageId, String recipientNumber, MStatus messageStatus) {
        this.gatewayMessageId = gatewayMessageId;
        this.recipientNumber = recipientNumber;
        this.messageStatus = messageStatus;
    }
    
    /**
     * @return the messageId
     */
    public GatewayRequest getGatewayRequest() {
        return gatewayRequest;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setGatewayRequest(GatewayRequest gatewayRequest) {
        this.gatewayRequest = gatewayRequest;
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
    public MStatus getMessageStatus() {
        return messageStatus;
    }

    /**
     * @param messageStatus the messageStatus to set
     */
    public void setMessageStatus(MStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    /**
     * @return the transitions
     */
    public Set<Transition> getTransitions() {
        return transitions;
    }

    /**
     * @param transitions the transitions to set
     */
    public void setTransitions(Set<Transition> transitions) {
        this.transitions = transitions;
    }

    public void addTransition(Transition transition) {
        transition.setGatewayResponse(this);
        this.transitions.add(transition);

    }
    public void addTransition(List<Transition> transitions) {
        for(Transition t : transitions) {
            t.setGatewayResponse(this);
            this.transitions.add(t);
        }
    }

    public void removeTransition(List<Transition> transitions) {
        for(Transition t : transitions) {
            if(this.transitions.contains(t))
                this.transitions.remove(t);
        }
    }
    public void removeTransition(Transition transition) {
        if(this.transitions.contains(transition))
            this.transitions.remove(transition);
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }




}
