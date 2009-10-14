package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.Set;

/**
 *  MessageRequest class is a POJO to hold MessageRequest information for data storage and manipulation
 *  Date : Sep 25, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageRequest extends MotechEntity {

    String getRecipientName();

    Language getLanguage();

    Date getSchedule();

    MessageType getMessageType();

    String getP13nData();

    NotificationType getNotificationType();

    Date getDateCreated();

    Date getDateProcessed();

    String getRecipientNumber();

    Date getDateFrom();

    Date getDateTo();

    MStatus getStatus();

    int getMaxTryNumber();

    String getRequestId();

    Set getPersInfos();

    void setRecipientName(String recipientName);

    void setLanguage(Language language);

    void setSchedule(Date schedule);

    void setMessageType(MessageType messageType);

    void setP13nData(String p13nData);

    void setNotificationType(NotificationType notificationType);

    void setDateCreated(Date dateCreated);

    void setDateProcessed(Date dateProcessed);

    void setRecipientNumber(String recipientNumber);

    void setDateFrom(Date dateFrom);

    void setDateTo(Date dateTo);

    void setMaxTryNumber(int maxTryNumber);

    void setStatus(MStatus status);

    void setRequestId(String requestId);

    void setPersInfos(Set persInfos);
}
