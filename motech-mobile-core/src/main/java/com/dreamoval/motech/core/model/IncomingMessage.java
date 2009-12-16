/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessage  extends MotechEntity{

    /**
     * @return the content
     */
    String getContent();

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @return the incomingMessageForm
     */
    IncomingMessageForm getIncomingMessageForm();

    /**
     * @return the incomingMessageResponse
     */
    IncomingMessageResponse getIncomingMessageResponse();

    /**
     * @return the incomingMsgSession
     */
    IncomingMessageSession getIncomingMsgSession();

    /**
     * @return the lastModified
     */
    Date getLastModified();

    /**
     * @return the messageStatus
     */
    IncMessageStatus getMessageStatus();

    /**
     * @param content the content to set
     */
    void setContent(String content);

    /**
     * @param dateCreated the dateCreated to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param incomingMessageForm the incomingMessageForm to set
     */
    void setIncomingMessageForm(IncomingMessageForm incomingMessageForm);

    /**
     * @param incomingMessageResponse the incomingMessageResponse to set
     */
    void setIncomingMessageResponse(IncomingMessageResponse incomingMessageResponse);

    /**
     * @param incomingMsgSession the incomingMsgSession to set
     */
    void setIncomingMsgSession(IncomingMessageSession incomingMsgSession);

    /**
     * @param lastModified the lastModified to set
     */
    void setLastModified(Date lastModified);

    /**
     * @param messageStatus the messageStatus to set
     */
    void setMessageStatus(IncMessageStatus messageStatus);
    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();

}
