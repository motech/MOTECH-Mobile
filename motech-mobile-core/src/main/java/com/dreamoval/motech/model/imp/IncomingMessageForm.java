package com.dreamoval.motech.model.imp;

import java.util.Date;


public interface IncomingMessageForm {

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @return the incomingMsgFormDefinition
     */
    IncomingMessageFormDefinition getIncomingMsgFormDefinition();

    /**
     * @return the lastModified
     */
    Date getLastModified();

    /**
     * @return the messageStatus
     */
    IncMessageFormStatus getMessageStatus();

    /**
     * @param dateCreated the dateCreated to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param incomingMsgFormDefinition the incomingMsgFormDefinition to set
     */
    void setIncomingMsgFormDefinition(IncomingMessageFormDefinition incomingMsgFormDefinition);

    /**
     * @param lastModified the lastModified to set
     */
    void setLastModified(Date lastModified);

    /**
     * @param messageStatus the messageStatus to set
     */
    void setMessageStatus(IncMessageFormStatus messageStatus);
}
