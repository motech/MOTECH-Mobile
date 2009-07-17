/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.DataAccess.Domain;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author Jojo
 */
public class MessageDetails {

    public MessageDetails(){}


    private Long messageId;

    private String messageType;
    private int numberOfPages;
    private String messageText;
    private String globalStatus;
    private Set responseDetails;

    public MessageDetails(String messageType, int numberOfPages, String messageText, String globalStatus, Date dateSent) {
        this.messageType = messageType;
        this.numberOfPages = numberOfPages;
        this.messageText = messageText;
        this.globalStatus = globalStatus;
        this.dateSent = dateSent;
    }
    private Date dateSent;

    /**
     * @return the messageType
     */
    
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the numberOfPages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * @return the messageText
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * @param messageText the messageText to set
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * @return the globalStatus
     */
    public String getGlobalStatus() {
        return globalStatus;
    }

    /**
     * @param globalStatus the globalStatus to set
     */
    public void setGlobalStatus(String globalStatus) {
        this.globalStatus = globalStatus;
    }

    /**
     * @return the responseDetails
     */
    public Set getResponseDetails() {
        return responseDetails;
    }

    /**
     * @param responseDetails the responseDetails to set
     */
    public void setResponseDetails(Set responseDetails) {
        this.responseDetails = responseDetails;
    }

    /**
     * @return the messageId
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

}
