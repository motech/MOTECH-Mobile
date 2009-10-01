/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *  MessageRequest class is a POJO to hold MessageRequest information for data storage and manipulation
 *  Date : Sep 25, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageRequest extends MotechEntity {

    String getRecipientName();
    String getLanguage();
    Date getSchedule();
    MessageType getMessageType();
    String getP13nData();
    Long getNotificationType();
    Date getDateCreated();
    Date getDateProcessed();
    String getRecipientNumber();
    Date getDateFrom();
    Date getDateTo();
    MStatus getStatus();
    int getMaxTryNumber();

    void setRecipientName(String recipientName);
    void setLanguage(String language);
    void setSchedule(Date schedule);
    void setMessageType(MessageType messageType);
    void setP13nData(String p13nData);
    void setNotificationType(Long notificationType);
    void setDateCreated(Date dateCreated);
    void setDateProcessed(Date dateProcessed);
    void setRecipientNumber(String recipientNumber);
    void setDateFrom(Date dateFrom);
    void setDateTo(Date dateTo);
    void setMaxTryNumber(int maxTryNumber);
    void setStatus(MStatus status);
    
}
