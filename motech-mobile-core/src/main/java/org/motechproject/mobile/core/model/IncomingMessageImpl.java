package org.motechproject.mobile.core.model;

import java.util.Date;

/**
 * IncomingMessageImpl class is an implementation of IncomingMessage interface'
 * which is actually mapped in hibernate.It provides properties to handle incomingMessage
 * operations
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageImpl implements IncomingMessage {

    private Long id;
    private String content;
    private Date dateCreated;
    private Date lastModified;
    private IncomingMessageSession incomingMsgSession;
    private IncomingMessageResponse incomingMessageResponse;
    private IncomingMessageForm incomingMessageForm;
    private IncMessageStatus messageStatus;



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

    /**
     * @return the incomingMsgSession
     */
    public IncomingMessageSession getIncomingMsgSession() {
        return incomingMsgSession;
    }

    /**
     * @param incomingMsgSession the incomingMsgSession to set
     */
    public void setIncomingMsgSession(IncomingMessageSession incomingMsgSession) {
        this.incomingMsgSession = incomingMsgSession;
    }

    /**
     * @return the incomingMessageResponse
     */
    public IncomingMessageResponse getIncomingMessageResponse() {
        return incomingMessageResponse;
    }

    /**
     * @param incomingMessageResponse the incomingMessageResponse to set
     */
    public void setIncomingMessageResponse(IncomingMessageResponse incomingMessageResponse) {
        this.incomingMessageResponse = incomingMessageResponse;
    }

    /**
     * @return the incomingMessageForm
     */
    public IncomingMessageForm getIncomingMessageForm() {
        return incomingMessageForm;
    }

    /**
     * @param incomingMessageForm the incomingMessageForm to set
     */
    public void setIncomingMessageForm(IncomingMessageForm incomingMessageForm) {
        this.incomingMessageForm = incomingMessageForm;
    }

    /**
     * @return the messageStatus
     */
    public IncMessageStatus getMessageStatus() {
        return messageStatus;
    }

    /**
     * @param messageStatus the messageStatus to set
     */
    public void setMessageStatus(IncMessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");

        if (this != null) {
            sb.append((this.getId() != null) ? "key=Id value=" + this.getId().toString() : "Id is null ");
            sb.append(newLine);
            sb.append((this.content != null) ? "key=content value=" + this.content : "content is null  ");
            sb.append(newLine);

            sb.append((this.incomingMessageForm != null) ? "key=IncomingMessageForm.Id value=" + this.incomingMessageForm.getId() : "incomingMessageForm.Id is null ");
            sb.append(newLine);
            sb.append((this.incomingMessageResponse != null) ? "key=IncomingMessageResponse.Id value=" + this.incomingMessageResponse.getId() : "incomingMessageResponse.Id is null ");
            sb.append(newLine);
            sb.append((this.incomingMsgSession != null) ? "key=IncomingMsgSession.Id value=" + this.incomingMsgSession.getId() : "incomingMsgSession.Id is null ");
            sb.append(newLine);
            sb.append((this.dateCreated != null) ? "key=dateCreated value=" + this.dateCreated.toString() : "dateCreated is null ");
            sb.append(newLine);
            sb.append((this.lastModified != null) ? "key=lastModified value=" + this.lastModified.toString() : "lastModified is null ");
            sb.append(newLine);
            sb.append((this.messageStatus != null) ? "key=messageStatus value=" + this.messageStatus.toString() : "messageStatus is null ");
            sb.append(newLine);

            return sb.toString();

        } else {
            return "Object is null";
        }


    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
