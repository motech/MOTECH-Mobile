package com.dreamoval.motech.model.imp;

import java.util.Date;

/*
 * IncomingMessageImpl is the implementation of the IncomingMessage interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessage operations
 *
 * Date: Dec 02, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageImpl implements IncomingMessage {

    private String content;
    private Date dateCreated;
    private Date lastModified;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
