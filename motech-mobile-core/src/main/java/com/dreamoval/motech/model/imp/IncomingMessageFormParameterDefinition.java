package com.dreamoval.motech.model.imp;

import java.util.Date;


public interface IncomingMessageFormParameterDefinition {

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @return the IncomingMsgFormDefinition
     */
    IncomingMessageFormDefinition getIncomingMsgFormDefinition();

    /**
     * @return the lastModified
     */
    Date getLastModified();

    /**
     * @return the length
     */
    int getLength();

    /**
     * @return the name
     */
    String getName();

    /**
     * @return the paramType
     */
    String getParamType();

    /**
     * @return the required
     */
    boolean isRequired();

    /**
     * @param dateCreated the dateCreated to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param IncomingMsgFormDefinition the IncomingMsgFormDefinition to set
     */
    void setIncomingMsgFormDefinition(IncomingMessageFormDefinition IncomingMsgFormDefinition);

    /**
     * @param lastModified the lastModified to set
     */
    void setLastModified(Date lastModified);

    /**
     * @param length the length to set
     */
    void setLength(int length);

    /**
     * @param name the name to set
     */
    void setName(String name);

    /**
     * @param paramType the paramType to set
     */
    void setParamType(String paramType);

    /**
     * @param required the required to set
     */
    void setRequired(boolean required);
}
