package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * MessageDetails class is a POJO to hold MessageDetails information for data storage and manipulation
 * It has properties for example the text message itself or the numberofpages of that sms text in case it's
 * sms send
 *
 * Date: Jul 24, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface GatewayRequest extends MotechEntity {

    /**
     * @return the dateSent
     */
    Date getDateSent();

    /**
     * @return the dateFrom
     */
    Date getDateFrom();

    /**
     * @return the message
     */
    String getMessage();

    /**
     * @return the gatewayRequestDetails Object
     */
    GatewayRequestDetails getGatewayRequestDetails();

    /**
     * @return the RequestId
     */
    String getRequestId();

    /**
     * @return the  dateTo
     */
    Date getDateTo();

    /**
     * @return the recipientsNumber
     */
    String getRecipientsNumber();

    /**
     * @return the responseDetails
     */
    Set<GatewayResponse> getResponseDetails();

    /**
     * @return
     */
    int getTryNumber();

    /**
     * @return
     */
    MStatus getMessageStatus();

    /**
     *
     * @param tryNumber
     */
    void setTryNumber(int tryNumber);

    /**
     * @param dateSent the dateSent to set
     */
    void setDateSent(Date dateSent);

    /**
     * @param dateFrom the dateFrom to set
     */
    void setDateFrom(Date dateFrom);

    /**
     * @param messageText the message to set
     */
    void setMessage(String message);

    /**
     * @param messageType the requestId to set
     */
    void setGatewayRequestDetails(GatewayRequestDetails gatewayRequestDetails);

    /**
     * @param numberOfPages the dateTo to set
     */
    void setDateTo(Date dateTo);

    /**
     * @param recipientsNumbers the recipientsNumbers to set
     */
    void setRecipientsNumber(String recipientsNumber);

    /**
     *
     * @param requestId the requestId to set
     */
    void setRequestId(String requestId);

    /**
     * @param responseDetails the responseDetails to set
     */
    void setResponseDetails(Set<GatewayResponse> responseDetails);

    /**
     *
     * @param status
     */
    void setMessageStatus(MStatus status);

    /**
     *
     */
    void addResponse(GatewayResponse response);

    /**
     *
     */
    void removeResponse(GatewayResponse response);

    /**
     *
     */
    void addResponse(List<GatewayResponse> responses);

    /**
     *
     */
    void removeResponse(List<GatewayResponse> responses);
}
