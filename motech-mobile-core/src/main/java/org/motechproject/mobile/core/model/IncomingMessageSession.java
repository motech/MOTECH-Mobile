package org.motechproject.mobile.core.model;

import java.util.Date;
import java.util.List;

/**
 * IncomingMessageSession interface is a POJO to hold IncomingMessageSession information for data storage and manipulation
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageSession extends MotechEntity {

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
     * @param incomingMessages the incomingMessages to set
     */
    public void setIncomingMessages(List<IncomingMessage> incomingMessages);

    /**
     * @param messageSessionStatus the messageSessionStatus to set
     */
    public void setMessageSessionStatus(IncMessageSessionStatus messageSessionStatus);

    /**
     * Helper Method to add IncomingMessage to IncomingMessageSession
     * @param msg the IncomingMessage to add
     */
    public void addIncomingMessage(IncomingMessage msg);

    /**
     * Helper method to remove IncomingMessage from IncomingMessageSession
     * @param msg the IncomingMessage to add
     */
    public void removeIncomingMessage(IncomingMessage msg);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();
}
