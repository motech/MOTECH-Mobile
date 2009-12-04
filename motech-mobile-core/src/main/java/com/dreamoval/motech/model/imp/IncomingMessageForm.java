package com.dreamoval.motech.model.imp;

import com.dreamoval.motech.core.model.MotechEntity;
import java.util.Date;
import java.util.Set;

public interface IncomingMessageForm extends MotechEntity {

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the incomingMsgFormDefinition
     */
    public IncomingMessageFormDefinition getIncomingMsgFormDefinition();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @return the messageStatus
     */
    public IncMessageFormStatus getMessageFormStatus();

    /**
     * @return the incomingMsgFormParameters
     */
    public Set<IncomingMessageFormParameter> getIncomingMsgFormParameters();

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated);

    /**
     * @param incomingMsgFormDefinition the incomingMsgFormDefinition to set
     */
    public void setIncomingMsgFormDefinition(IncomingMessageFormDefinition incomingMsgFormDefinition);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * @param messageStatus the messageStatus to set
     */
    public void setMessageFormStatus(IncMessageFormStatus messageStatus);

    /**
     * @param incomingMsgFormParameters the incomingMsgFormParameters to set
     */
    public void setIncomingMsgFormParameters(Set<IncomingMessageFormParameter> incomingMsgFormParameters);
}
