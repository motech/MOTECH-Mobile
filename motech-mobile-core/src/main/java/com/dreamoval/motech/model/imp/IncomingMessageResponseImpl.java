package com.dreamoval.motech.model.imp;

import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.MotechEntityImpl;
import java.util.Date;

/*
 * IncomingMessageResponseImpl is the implementation of the IncomingMessageResponseImpl interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageResponseImpl operations
 *
 * Date: Dec 02, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageResponseImpl extends MotechEntityImpl implements IncomingMessageResponse {

    private IncomingMessage incomingMessage;
    private String content;
    private Date dateCreated;
    private Date lastModified;
    private IncMessageResponseStatus messageResponseStatus;

    /**
     * @return the incomingMessage
     */
    public IncomingMessage getIncomingMessage() {
        return incomingMessage;
    }

    /**
     * @param incomingMessage the incomingMessage to set
     */
    public void setIncomingMessage(IncomingMessage incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

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
     * @return the messageResponseStatus
     */
    public IncMessageResponseStatus getMessageResponseStatus() {
        return messageResponseStatus;
    }

    /**
     * @param messageResponseStatus the messageResponseStatus to set
     */
    public void setMessageResponseStatus(IncMessageResponseStatus messageResponseStatus) {
        this.messageResponseStatus = messageResponseStatus;
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
}
