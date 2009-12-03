package com.dreamoval.motech.model.imp;

import com.dreamoval.motech.core.model.MotechEntityImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * IncomingMessageSessionImpl is the implementation of the IncomingMessageSession interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageSession operations
 *
 * Date: Dec 02, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageSessionImpl extends MotechEntityImpl implements IncomingMessageSession {

  private String requesterPhone;
  private Date dateStarted;
  private Date dateEnded;
  private Date lastActivity;
  private String formCode;
  private List<IncomingMessage> incomingMessages = new ArrayList<IncomingMessage>();
  private IncMessageSessionStatus messageSessionStatus;

    /**
     * @return the requesterPhone
     */
    public String getRequesterPhone() {
        return requesterPhone;
    }

    /**
     * @param requesterPhone the requesterPhone to set
     */
    public void setRequesterPhone(String requesterPhone) {
        this.requesterPhone = requesterPhone;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateStarted() {
        return dateStarted;
    }

    /**
     * @param dateStarted the dateCreated to set
     */
    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    /**
     * @return the dateEnded
     */
    public Date getDateEnded() {
        return dateEnded;
    }

    /**
     * @param dateEnded the dateEnded to set
     */
    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    /**
     * @return the lastActivity
     */
    public Date getLastActivity() {
        return lastActivity;
    }

    /**
     * @param lastActivity the lastActivity to set
     */
    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    /**
     * @return the formCode
     */
    public String getFormCode() {
        return formCode;
    }

    /**
     * @param formCode the formCode to set
     */
    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    /**
     * @return the incomingMessages
     */
    public List<IncomingMessage> getIncomingMessages() {
        return incomingMessages;
    }

    /**
     * @param incomingMessages the incomingMessages to set
     */
    public void setIncomingMessages(List<IncomingMessage> incomingMessages) {
        this.incomingMessages = incomingMessages;
    }

    /**
     * @return the messageSessionStatus
     */
    public IncMessageSessionStatus getMessageSessionStatus() {
        return messageSessionStatus;
    }

    /**
     * @param messageSessionStatus the messageSessionStatus to set
     */
    public void setMessageSessionStatus(IncMessageSessionStatus messageSessionStatus) {
        this.messageSessionStatus = messageSessionStatus;
    }

    
  
}
