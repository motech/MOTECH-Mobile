package com.dreamoval.motech.model.imp;

import com.dreamoval.motech.model.dao.imp.IncomingMessageSession;
import java.util.Date;

/*
 * IncomingMessageSessionImpl is the implementation of the IncomingMessageSession interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageSession operations
 *
 * Date: Dec 02, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageSessionImpl implements IncomingMessageSession {

  private String requesterPhone;
  private Date dateCreated;
  private Date dateEnded;
  private Date lastActivity;
  private String formCode;

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
  
}
