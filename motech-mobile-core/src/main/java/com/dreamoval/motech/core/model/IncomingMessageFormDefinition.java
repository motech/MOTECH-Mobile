package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.Set;

/**
 * IncomingMessageFormDefinition interface is a POJO to hold IncomingMessageFormDefinition information for data storage and manipulation
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageFormDefinition extends MotechEntity {

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the formCode
     */
    public String getFormCode();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @return the incomingMsgParamDefinition
     */
    public Set<IncomingMessageFormParameterDefinition> getIncomingMsgParamDefinitions();

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated);

    /**
     * @param formCode the formCode to set
     */
    public void setFormCode(String formCode);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * @param incomingMsgParamDefinition the incomingMsgParamDefinition to set
     */
    public void setIncomingMsgParamDefinitions(Set<IncomingMessageFormParameterDefinition> incomingMsgParamDefinition);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();

    /**
     * @return the incomingMessageForms
     */
    Set<IncomingMessageForm> getIncomingMessageForms();

    /**
     * @param incomingMessageForms the incomingMessageForms to set
     */
    void setIncomingMessageForms(Set<IncomingMessageForm> incomingMessageForms);

    /**
     * Helper method to add IncomingMesasgeForm to IncomingMessageFormDefinition
     * @param form the IncomingMessageForm object to add
     */
    void addIncomingMessageForm(IncomingMessageForm form);

    /**
     * Helper method to remove IncomingMesasgeForm to IncomingMessageFormDefinition
     * @param form the IncomingMessageForm object to remove
     */
    void removeIncomingMessageForm(IncomingMessageForm form);
}
