/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.model.dao.imp;

import java.util.Date;

/**
 *
 * @author jojo
 */
public interface IncomingMessageSession {

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @return the dateEnded
     */
    Date getDateEnded();

    /**
     * @return the formCode
     */
    String getFormCode();

    /**
     * @return the lastActivity
     */
    Date getLastActivity();

    /**
     * @return the requesterPhone
     */
    String getRequesterPhone();

    /**
     * @param dateCreated the dateCreated to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param dateEnded the dateEnded to set
     */
    void setDateEnded(Date dateEnded);

    /**
     * @param formCode the formCode to set
     */
    void setFormCode(String formCode);

    /**
     * @param lastActivity the lastActivity to set
     */
    void setLastActivity(Date lastActivity);

    /**
     * @param requesterPhone the requesterPhone to set
     */
    void setRequesterPhone(String requesterPhone);

}
