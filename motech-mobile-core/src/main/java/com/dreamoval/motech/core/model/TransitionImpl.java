/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *
 * @author Jojo
 */
public class TransitionImpl extends MotechEntityImpl implements Transition{
    public TransitionImpl(){}

    private Long transitionId;
    private ResponseDetails responseId;
    private String transactionType;
    private String transactionDescription;
    private Date transactionDate;

    
    /**
     * @return the responseId
     */
    public ResponseDetails getResponseId() {
        return responseId;
    }

    /**
     * @param responseId the responseId to set
     */
    public void setResponseId(ResponseDetails responseId) {
        this.responseId = responseId;
    }

    /**
     * @return the transactionType
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the transactionDescription
     */
    public String getTransactionDescription() {
        return transactionDescription;
    }

    /**
     * @param transactionDescription the transactionDescription to set
     */
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the transitionId
     */
    public Long getTransitionId() {
        return transitionId;
    }

    /**
     * @param transitionId the transitionId to set
     */
    public void setTransitionId(Long transitionId) {
        this.transitionId = transitionId;
    }

}
