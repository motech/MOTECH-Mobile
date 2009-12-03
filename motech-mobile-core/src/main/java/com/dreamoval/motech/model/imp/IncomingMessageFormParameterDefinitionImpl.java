package com.dreamoval.motech.model.imp;

import java.util.Date;

/*
 * IncomingMessageFormParameterDefinitionImpl is the implementation of the IncomingMessageFormParameterDefinition interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageFormParameterDefinition operations
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageFormParameterDefinitionImpl implements IncomingMessageFormParameterDefinition {

    private IncomingMessageFormDefinition IncomingMsgFormDefinition;
    private String name;
    private boolean required;
    private String paramType;
    private int length;
    private Date dateCreated;
    private Date lastModified;

    /**
     * @return the IncomingMsgFormDefinition
     */
    public IncomingMessageFormDefinition getIncomingMsgFormDefinition() {
        return IncomingMsgFormDefinition;
    }

    /**
     * @param IncomingMsgFormDefinition the IncomingMsgFormDefinition to set
     */
    public void setIncomingMsgFormDefinition(IncomingMessageFormDefinition IncomingMsgFormDefinition) {
        this.IncomingMsgFormDefinition = IncomingMsgFormDefinition;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return the paramType
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * @param paramType the paramType to set
     */
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
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
