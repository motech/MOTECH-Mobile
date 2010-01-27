package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 * IncomingMessageFormParameterDefinition interface is a POJO to hold IncomingMessageFormParameterDefinition information for data storage and manipulation
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageFormParameterDefinition extends MotechEntity {

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the IncomingMsgFormDefinition
     */
    public IncomingMessageFormDefinition getIncomingMsgFormDefinition();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @return the length
     */
    public int getLength();

    /**
     * @return the name
     */
    public String getName();

    /**
     * @return the paramType
     */
    public String getParamType();

    /**
     * @return the required
     */
    public boolean isRequired();

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated);

    /**
     * @param IncomingMsgFormDefinition the IncomingMsgFormDefinition to set
     */
    public void setIncomingMsgFormDefinition(IncomingMessageFormDefinition IncomingMsgFormDefinition);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * @param length the length to set
     */
    public void setLength(int length);

    /**
     * @param name the name to set
     */
    public void setName(String name);

    /**
     * @param paramType the paramType to set
     */
    public void setParamType(String paramType);

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();
}
