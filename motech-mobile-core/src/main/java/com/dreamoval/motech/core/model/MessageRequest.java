package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.Set;

/**
 *  MessageRequest interface is a POJO to hold MessageRequest information for data storage and manipulation
 *  Date : Sep 25, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageRequest extends MotechEntity {

    public String getRecipientName();

    public Language getLanguage();

    public Date getSchedule();

    public MessageType getMessageType();

    public String getP13nData();

    public NotificationType getNotificationType();

    public Date getDateCreated();

    public Date getDateProcessed();

    public String getRecipientNumber();

    public Date getDateFrom();

    public Date getDateTo();

    public MStatus getStatus();

    public int getTryNumber();

    public String getRequestId();

    public Set getPersInfos();

    public Date getLastModified();

    public void setRecipientName(String recipientName);

    public void setLanguage(Language language);

    public void setSchedule(Date schedule);

    public void setMessageType(MessageType messageType);

    public void setP13nData(String p13nData);

    public void setNotificationType(NotificationType notificationType);

    public void setDateCreated(Date dateCreated);

    public void setDateProcessed(Date dateProcessed);

    public void setRecipientNumber(String recipientNumber);

    public void setDateFrom(Date dateFrom);

    public void setDateTo(Date dateTo);

    public void setTryNumber(int maxTryNumber);

    public void setStatus(MStatus status);

    public void setRequestId(String requestId);

    public void setPersInfos(Set persInfos);

    public void setLastModified(Date lastModified);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();
}
