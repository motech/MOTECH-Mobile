
package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class GatewayRequestImpl extends MotechEntityImpl implements GatewayRequest{
    
    private Long requestId;
    private Date dateTo;
    private String message;
    private String recipientsNumber;
    private Date dateFrom;
    private Set<GatewayResponse> responseDetails = new HashSet<GatewayResponse>();
    private Date dateSent;

    public GatewayRequestImpl(){}

    public GatewayRequestImpl(Long requestId, Date dateTo, String messageText, String recipientsNumber, Date dateFrom, Date dateSent) {
        this.requestId =  requestId;
        this.dateTo = dateTo;
        this.message = messageText;
        this.dateFrom = dateFrom;
        this.dateSent = dateSent;
        this.recipientsNumber = recipientsNumber;
    }    

    /**
     * @return the messageType
     */
    
    public Long getRequestId() {
        return requestId;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the numberOfPages
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return the messageText
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param messageText the messageText to set
     */
    public void setMessage(String messageText) {
        this.message = messageText;
    }

    /**
     * @return the dateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the responseDetails
     */
    public Set<GatewayResponse> getResponseDetails() {
        return responseDetails;
    }

    /**
     * @param responseDetails the responseDetails to set
     */
    public void setResponseDetails(Set<GatewayResponse> responseDetails) {
        this.responseDetails = responseDetails;
    }

    /**
     * @return the dateSent
     */
    public Date getDateSent() {
        return dateSent;
    }

    /**
     * @param dateSent the dateSent to set
     */
    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    /**
     * @return the recipientsNumbers
     */
    public String getRecipientsNumber() {
        return recipientsNumber;
    }

    /**
     * @param recipientsNumbers the recipientsNumbers to set
     */
    public void setRecipientsNumber(String recipientsNumbers) {
        this.recipientsNumber = recipientsNumbers;
    }

    public void addResponse(GatewayResponse response) {
        response.setMessageId(this);
        this.responseDetails.add(response);
    }

    public void removeResponse(GatewayResponse response) {
        if(this.responseDetails.contains(response)) {
            this.responseDetails.remove(response);
        }

    }

    public void addResponse(List<GatewayResponse> responses) {

        for(GatewayResponse r : responses) {
            r.setMessageId(this);
            this.responseDetails.add(r);
        }

    }

      public void removeResponse(List<GatewayResponse> responses) {
          for(GatewayResponse r : responses) {
              if(this.responseDetails.contains(r))
                  this.responseDetails.remove(r);
          }
    }

    
}
