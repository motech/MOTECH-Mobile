package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public Map<String, IncomingMessageFormParameter> getIncomingMsgFormParameters();

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
    public void setIncomingMsgFormParameters(Map<String, IncomingMessageFormParameter> incomingMsgFormParameters);
    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();

    /**
     * Helper method to add IncomingMessageFormParameter to IncomingMessageForm
     * @param key key of the map
     * @param param the IncomingMessageFormParameter to add
     */
    void addIncomingMsgFormParam(String key, IncomingMessageFormParameter param);

    /**
     * Helper method to remover IncomingMessageFormParameter to IncomingMessageForm
     * @param key key of the map
     * @param param the IncomingMessageFormParameter to remove
     */
    void removeIncomingMsgFormParm(String key);
}
