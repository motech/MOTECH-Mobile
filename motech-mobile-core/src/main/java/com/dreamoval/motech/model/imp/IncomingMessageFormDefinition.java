/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.model.imp;

import java.util.Date;

/**
 *
 * @author jojo
 */
public interface IncomingMessageFormDefinition {

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @return the form_code
     */
    String getForm_code();

    /**
     * @return the lastModified
     */
    Date getLastModified();

    /**
     * @param dateCreated the dateCreated to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param form_code the form_code to set
     */
    void setForm_code(String form_code);

    /**
     * @param lastModified the lastModified to set
     */
    void setLastModified(Date lastModified);

}
