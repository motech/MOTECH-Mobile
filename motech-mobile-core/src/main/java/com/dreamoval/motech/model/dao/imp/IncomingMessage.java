/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.model.dao.imp;

import java.util.Date;

/**
 *
 * @author jojo
 */
public interface IncomingMessage {

    /**
     * @return the content
     */
    String getContent();

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @return the lastModified
     */
    Date getLastModified();

    /**
     * @param content the content to set
     */
    void setContent(String content);

    /**
     * @param dateCreated the dateCreated to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param lastModified the lastModified to set
     */
    void setLastModified(Date lastModified);

}
