/*
 * Transition class is a POJO to hold log infomation about various state of a message sent.
 * State meaning its status for example whether still pending ,delivered or failed.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 * Date : 
 * @author Joseph
 * Email: joseph@dreamoval.com
 */
//TODO eventualy refactor Transition class to logs
public interface Transition {
    /**
     * @return the responseId
     */
    ResponseDetails getResponseId();

    /**
     * @return the transactionDate
     */
    Date getTransactionDate();

    /**
     * @return the transactionDescription
     */
    String getTransactionDescription();

    /**
     * @return the transactionType
     */
    String getTransactionType();

    /**
     * @param responseId the responseId to set
     */
    void setResponseId(ResponseDetails responseId);

    /**
     * @param transactionDate the transactionDate to set
     */
    void setTransactionDate(Date transactionDate);

    /**
     * @param transactionDescription the transactionDescription to set
     */
    void setTransactionDescription(String transactionDescription);

    /**
     * @param transactionType the transactionType to set
     */
    void setTransactionType(String transactionType);
}
