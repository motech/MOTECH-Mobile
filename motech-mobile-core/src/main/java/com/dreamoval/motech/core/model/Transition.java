

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 * Transition class is a POJO to hold log infomation about various state of a message sent.
 * State meaning its status for example whether still pending ,delivered or failed.
 *
 * Date : Aug 4, 2009
 * @author Joseph (joseph@dreamoval.com)
 */
//TODO eventualy refactor Transition class to logs
public interface Transition extends MotechEntity {
    /**
     * @return the responseId
     */
    GatewayResponse getGatewayResponse();

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
    void setGatewayResponse(GatewayResponse responseId);

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
