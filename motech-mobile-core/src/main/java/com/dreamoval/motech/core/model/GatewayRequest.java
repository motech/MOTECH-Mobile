package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * MessageDetails interface is a POJO to hold MessageDetails information for data storage and manipulation
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
    public Date getDateSent();

    /**
     * @return the dateFrom
     */
    public Date getDateFrom();

    /**
     * @return the message
     */
    public String getMessage();

    /**
     * @return the gatewayRequestDetails Object
     */
    public GatewayRequestDetails getGatewayRequestDetails();

    /**
     * @return the RequestId
     */
    public String getRequestId();

    /**
     * @return the  dateTo
     */
    public Date getDateTo();

    /**
     * @return the recipientsNumber
     */
    public String getRecipientsNumber();

    /**
     * @return the responseDetails
     */
    public Set<GatewayResponse> getResponseDetails();

    /**
     * @return the try number
     */
    public int getTryNumber();

    /**
     * @return the messageStatus
     */
    public MStatus getMessageStatus();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     *
     * @param tryNumber
     */
    public void setTryNumber(int tryNumber);

    /**
     * @param dateSent the dateSent to set
     */
    public void setDateSent(Date dateSent);

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom);

    /**
     * @param messageText the message to set
     */
    public void setMessage(String message);

    /**
     * @param messageType the requestId to set
     */
    public void setGatewayRequestDetails(GatewayRequestDetails gatewayRequestDetails);

    /**
     * @param numberOfPages the dateTo to set
     */
    public void setDateTo(Date dateTo);

    /**
     * @param recipientsNumbers the recipientsNumbers to set
     */
    public void setRecipientsNumber(String recipientsNumber);

    /**
     *
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId);

    /**
     * @param responseDetails the responseDetails to set
     */
    public void setResponseDetails(Set<GatewayResponse> responseDetails);

    /**
     * @param MStatus the status to set
     */
    public void setMessageStatus(MStatus status);

    /**
     * @param lastModified lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * Helper method to add a GatewayResponse Object to GatewayRequest
     * @param  GatewayResponse GatewayResponse Object to pass
     */
    public void addResponse(GatewayResponse response);

    /**
     * Helper method to remove the passed GatewayResponse  object from GatewayRequest
     * @param  GatewayRespone the GatewayResponse to pass
     */
    public void removeResponse(GatewayResponse response);

    /**
     * Helper method to add a list of  GatewayResponse Objects to GatewayRequest
     * @param  List the GatewayRespone List to pass
     */
    public void addResponse(List<GatewayResponse> responses);

    /**
     *Helper method to remove the passed List of GatewayResponse objects from GatewayRequest
     * @param  List the List of GatewayResponse to pass
     */
    public void removeResponse(List<GatewayResponse> responses);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();
}
