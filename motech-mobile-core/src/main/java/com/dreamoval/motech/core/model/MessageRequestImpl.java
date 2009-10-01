/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *  Date : Sep 25, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class MessageRequestImpl extends MotechEntityImpl implements MessageRequest{

    private String language;
    private Date schedule;
    private MessageType messageType;
    private String p13nData;
    private Long notificationType;
    private Date dateCreated;
    private Date dateProcessed;
    private String recipientName;
    private String recipientNumber;
    private Date dateFrom;
    private Date dateTo;
    private MStatus status;
    private int maxTryNumber;
    

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the schedule
     */
    public Date getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    /**
     * @return the message_type
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * @param message_type the message_type to set
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the p13nData
     */
    public String getP13nData() {
        return p13nData;
    }

    /**
     * @param p13nData the p13nData to set
     */
    public void setP13nData(String p13nData) {
        this.p13nData = p13nData;
    }

    /**
     * @return the notificationType
     */
    public Long getNotificationType() {
        return notificationType;
    }

    /**
     * @param notificationType the notificationType to set
     */
    public void setNotificationType(Long notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the dateProcessed
     */
    public Date getDateProcessed() {
        return dateProcessed;
    }

    /**
     * @param dateProcessed the dateProcessed to set
     */
    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    /**
     * @return the recipientName
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * @param recipientName the recipientName to set
     */
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
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
     * @return the dateTo
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return the status
     */
    public MStatus getStatus() {
        return status;
    }


    /**
     * @return the maxTryNumber
     */
    public int getMaxTryNumber() {
        return maxTryNumber;
    }

    /**
     * @param maxTryNumber the maxTryNumber to set
     */
    public void setMaxTryNumber(int maxTryNumber) {
        this.maxTryNumber = maxTryNumber;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(MStatus status) {
        this.status = status;
    }




}
