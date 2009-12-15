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
public class IncomingMessageImpl extends MotechEntityImpl implements IncomingMessage {
    private String content;
    private Date dateCreated;
    private Date lastModified;
    private IncomingMessageSession incomingMsgSession;
    private IncomingMessageResponse incomingMessageResponse;
    private IncomingMessageForm incomingMessageForm;
    private IncMessageStatus messageStatus;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
     * @return the lastModified
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return the incomingMsgSession
     */
    public IncomingMessageSession getIncomingMsgSession() {
        return incomingMsgSession;
    }

    /**
     * @param incomingMsgSession the incomingMsgSession to set
     */
    public void setIncomingMsgSession(IncomingMessageSession incomingMsgSession) {
        this.incomingMsgSession = incomingMsgSession;
    }

    /**
     * @return the incomingMessageResponse
     */
    public IncomingMessageResponse getIncomingMessageResponse() {
        return incomingMessageResponse;
    }

    /**
     * @param incomingMessageResponse the incomingMessageResponse to set
     */
    public void setIncomingMessageResponse(IncomingMessageResponse incomingMessageResponse) {
        this.incomingMessageResponse = incomingMessageResponse;
    }

    /**
     * @return the incomingMessageForm
     */
    public IncomingMessageForm getIncomingMessageForm() {
        return incomingMessageForm;
    }

    /**
     * @param incomingMessageForm the incomingMessageForm to set
     */
    public void setIncomingMessageForm(IncomingMessageForm incomingMessageForm) {
        this.incomingMessageForm = incomingMessageForm;
    }

    /**
     * @return the messageStatus
     */
    public IncMessageStatus getMessageStatus() {
        return messageStatus;
    }

    /**
     * @param messageStatus the messageStatus to set
     */
    public void setMessageStatus(IncMessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

}
