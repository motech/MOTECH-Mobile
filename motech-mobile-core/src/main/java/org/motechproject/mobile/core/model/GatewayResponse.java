package org.motechproject.mobile.core.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * ResponseDetails interface is a POJO to hold and manipulate ResponseDetails to be persited in the database
 * It contains properties like recipient's number and message delivery status
 * 
 * Date: Jul 24, 2009
 * @author Joseph (joseph@dreamoval.com)
 */
public interface GatewayResponse {

    /**
     * 
     * @param id id to set
     */
    public void setId(Long id);


    /**
     * 
     * @return the id
     */
    public Long getId();
    /**
     * @return the gatewayMessageId
     */
    public String getGatewayMessageId();

    /**
     * @return the messageId
     */
    public GatewayRequest getGatewayRequest();

    /**
     * @return the messageStatus
     */
    public MStatus getMessageStatus();

    /**
     * @return the recipientNumber
     */
    public String getRecipientNumber();

    /**
     * @return the transitions
//     */
//    public Set<Transition> getTransitions();

    /**
     * @return the responseText
     */
    public String getResponseText();

    /**
     * @return the requestId
     */
    public String getRequestId();

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @param gatewayMessageId the gatewayMessageId to set
     */
    public void setGatewayMessageId(String gatewayMessageId);

    /**
     * @param messageId the messageId to set
     */
    public void setGatewayRequest(GatewayRequest messageId);

    /**
     * @param messageStatus the messageStatus to set
     */
    public void setMessageStatus(MStatus messageStatus);

    /**
     * @param recipientNumber the recipientNumber to set
     */
    public void setRecipientNumber(String recipientNumber);

//    /**
//     * @param transitions the transitions to set
//     */
//    public void setTransitions(Set<Transition> transitions);

    /**
     * @param recipientNumber the recipientNumber to set
     */
    public void setResponseText(String responseText);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);
//
//    public void addTransition(Transition transition);
//
//    public void addTransition(List<Transition> transitions);
//
//    public void removeTransition(Transition transition);
//
//    public void removeTransition(List<Transition> transitions);

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId);

    /**
     * @param  date the date to set
     */
    public void setDateCreated(Date date);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();

      /**
     * @return the version
     */
    int getVersion();

    /**
     * @param version the version to set
     */
    void setVersion(int version);
}
