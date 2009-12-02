package com.dreamoval.motech.model.imp;

import java.util.Date;

/*
 * IncomingMessageFormDefinitionImpl is the implementation of the IncomingMessageFormDefinitionImpl interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageFormDefinitionImpl operations
 *
 * Date: Dec 02, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageFormDefinitionImpl implements IncomingMessageFormDefinition {

    private String form_code;
    private Date dateCreated;
    private Date lastModified;

    /**
     * @return the form_code
     */
    public String getForm_code() {
        return form_code;
    }

    /**
     * @param form_code the form_code to set
     */
    public void setForm_code(String form_code) {
        this.form_code = form_code;
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
