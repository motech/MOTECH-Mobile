package com.dreamoval.motech.model.imp;

import java.util.Date;
import java.util.Set;


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
     * @return the incomingMsgFormParameters
     */
    Set<IncomingMessageFormParameter> getIncomingMsgFormParameters( );

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

    /**
     * @param incomingMsgFormParameters the incomingMsgFormParameters to set
     */
    void setIncomingMsgFormParameters(Set<IncomingMessageFormParameter> incomingMsgFormParameters);
}
