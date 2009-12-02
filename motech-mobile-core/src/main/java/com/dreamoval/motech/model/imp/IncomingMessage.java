package com.dreamoval.motech.model.imp;

import java.util.Date;

/**
 *
 * @author jojo
 */
public interface IncomingMessage {

    /**
     * @return the content
     */
    public String getContent();

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @return the incomingMessageSession
     */
    public IncomingMessageSession getIncomingMsgSession();

    /**
     * @return the messageStatus
     */
    public IncMessageStatus getMessageStatus();

    /**
     * @param content the content to set
     */
    public void setContent(String content);

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * @param incomingMsgSession the incomingMsgSession to set
     */
    public void setIncomingMsgSession(IncomingMessageSession incomingMsgSession);

    /**
     * @param messageStatus the messageStaus to set
     */
    public void setMessageStatus(IncMessageStatus messageStatus);

}
