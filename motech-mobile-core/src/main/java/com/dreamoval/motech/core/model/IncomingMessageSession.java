package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jojo
 */
public interface IncomingMessageSession extends MotechEntity{

    /**
     * @return the dateStarted
     */
    public Date getDateStarted();

    /**
     * @return the dateEnded
     */
    public Date getDateEnded();

    /**
     * @return the formCode
     */
    public String getFormCode();

    /**
     * @return the lastActivity
     */
    public Date getLastActivity();

    /**
     * @return the requesterPhone
     */
    public String getRequesterPhone();

    /**
     * @return the incomingMessages
     */
    public List<IncomingMessage> getIncomingMessages();

    /**
     * @return the messageSessionStatus
     */
    public IncMessageSessionStatus getMessageSessionStatus();

    /**
     * @param dateStarted the dateCreated to set
     */
    public void setDateStarted(Date dateStarted);

    /**
     * @param dateEnded the dateEnded to set
     */
    public void setDateEnded(Date dateEnded);

    /**
     * @param formCode the formCode to set
     */
    public void setFormCode(String formCode);

    /**
     * @param lastActivity the lastActivity to set
     */
    public void setLastActivity(Date lastActivity);

    /**
     * @param requesterPhone the requesterPhone to set
     */
    public void setRequesterPhone(String requesterPhone);

     /**
     * @param messageSessionStatus the messageSessionStatus to set
     */
    public void setMessageSessionStatus(IncMessageSessionStatus messageSessionStatus);
}
