package com.dreamoval.motech.model.imp;

import com.dreamoval.motech.core.model.IncomingMessageSession;
import com.dreamoval.motech.core.model.MotechEntity;
import java.util.Date;

/**
 *
 * @author jojo
 */
public interface IncomingMessage extends MotechEntity {

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

    /*
     * @return incomingMessageResponse
     */
    public IncomingMessageResponse getIncomingMessageResponse();

    /*
     * @return incomingMessageForm
     */
    public IncomingMessageForm getIncomingMessageForm();

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
     * @param messageStatus the messageStatus to set
     */
    public void setMessageStatus(IncMessageStatus messageStatus);
    
    /**
     * @param IncomingMessageResponse the incomingMessageResponse to set
     */
    public void setIncomingMessageResponse(IncomingMessageResponse incomingmessageResponse);

    /**
     * @param IncomingMessageForm the incomingMessageForm to set
     */
    public void setIncomingMessageForm(IncomingMessageForm incomingMessageForm);

}
