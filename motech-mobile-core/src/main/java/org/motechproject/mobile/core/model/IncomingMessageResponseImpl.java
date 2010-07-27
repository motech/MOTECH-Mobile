package org.motechproject.mobile.core.model;

import java.util.Date;

/*
 * IncomingMessageResponseImpl is the implementation of the IncomingMessageResponseImpl interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageResponseImpl operations
 *
 * Date: Dec 02, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageResponseImpl implements IncomingMessageResponse {

    private String id;
    private IncomingMessage incomingMessage;
    private String content;
    private Date dateCreated;
    private Date lastModified;
    private IncMessageResponseStatus messageResponseStatus;


private int version=-1;
    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * @return the incomingMessage
     */
    public IncomingMessage getIncomingMessage() {
        return incomingMessage;
    }

    /**
     * @param incomingMessage the incomingMessage to set
     */
    public void setIncomingMessage(IncomingMessage incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

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
     * @return the messageResponseStatus
     */
    public IncMessageResponseStatus getMessageResponseStatus() {
        return messageResponseStatus;
    }

    /**
     * @param messageResponseStatus the messageResponseStatus to set
     */
    public void setMessageResponseStatus(IncMessageResponseStatus messageResponseStatus) {
        this.messageResponseStatus = messageResponseStatus;
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");

        if (this != null) {
            sb.append((this.getId() != null) ? "key=Id value=" + this.getId().toString() : "Id is null ");
            sb.append(newLine);
            sb.append((this.content != null) ? "key=name value=" + this.content : "content is null  ");
            sb.append(newLine);

            sb.append((this.incomingMessage != null) ? "key=IncomingMessage.Id value=" + this.incomingMessage.getId() : "incomingMessage.Id is null ");
            sb.append(newLine);
            sb.append((this.dateCreated != null) ? "key=dateCreated value=" + this.dateCreated.toString() : "dateCreated is null ");
            sb.append(newLine);
            sb.append((this.lastModified != null) ? "key=lastModified value=" + this.lastModified.toString() : "lastModified is null ");
            sb.append(newLine);
            sb.append((this.messageResponseStatus != null) ? "key=messageResponseStatus value=" + this.messageResponseStatus.toString() : "messageResponseStatus is null ");
            sb.append(newLine);

            return sb.toString();

        } else {
            return "Object is null";
        }


    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
