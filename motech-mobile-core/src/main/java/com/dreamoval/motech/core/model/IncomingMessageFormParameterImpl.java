package com.dreamoval.motech.core.model;

import java.util.Date;

/*
 * IncomingMessageFormParameterImpl is the implementation of the IncomingMessageFormParameter interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageFormParameter operations
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageFormParameterImpl extends MotechEntityImpl implements IncomingMessageFormParameter {

    private IncomingMessageForm incomingMsgForm;
    private IncomingMessageFormParameterDefinition incomingMsgFormParamDefinition;
    private String name;
    private String value;
    private int errCode;
    private String errText;
    private IncMessageFormParameterStatus messageFormParamStatus;
    private Date dateCreated;
    private Date lastModified;

    /**
     * @return the incomingMsgForm
     */
    public IncomingMessageForm getIncomingMsgForm() {
        return incomingMsgForm;
    }

    /**
     * @param incomingMsgForm the incomingMsgForm to set
     */
    public void setIncomingMsgForm(IncomingMessageForm incomingMsgForm) {
        this.incomingMsgForm = incomingMsgForm;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the errCode
     */
    public int getErrCode() {
        return errCode;
    }

    /**
     * @param errCode the errCode to set
     */
    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    /**
     * @return the errText
     */
    public String getErrText() {
        return errText;
    }

    /**
     * @param errText the errText to set
     */
    public void setErrText(String errText) {
        this.errText = errText;
    }

    /**
     * @return the messageFormParamStatus
     */
    public IncMessageFormParameterStatus getMessageFormParamStatus() {
        return messageFormParamStatus;
    }

    /**
     * @param messageFormParamStatus the messageFormParamStatus to set
     */
    public void setMessageFormParamStatus(IncMessageFormParameterStatus messageFormParamStatus) {
        this.messageFormParamStatus = messageFormParamStatus;
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
     * @return the incomingMsgFormParamDefinition
     */
    public IncomingMessageFormParameterDefinition getIncomingMsgFormParamDefinition() {
        return incomingMsgFormParamDefinition;
    }

    /**
     * @param incomingMsgFormParamDefinition the incomingMsgFormParamDefinition to set
     */
    public void setIncomingMsgFormParamDefinition(IncomingMessageFormParameterDefinition incomingMsgFormParamDefinition) {
        this.incomingMsgFormParamDefinition = incomingMsgFormParamDefinition;
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");

        if (this != null) {
            sb.append((this.getId() != null) ? "key=Id value=" + this.getId().toString() : "Id is null ");
            sb.append(newLine);
            sb.append((this.name != null) ? "key=name value=" + this.name : "message is null  ");
            sb.append(newLine);
            sb.append((this.value != null) ? "key=value value=" + this.value : "value is null ");
            sb.append(newLine);
            sb.append((this.errCode != -1) ? "key=errCode value=" + Integer.toString(this.errCode) : "errCode is null ");
            sb.append(newLine);
            sb.append((this.errText != null) ? "key=errText value=" + this.errText : "errText is null ");
            sb.append(newLine);
            sb.append((this.incomingMsgForm != null) ? "key=IncomingMessageForm.Id value=" + this.incomingMsgForm.getId() : "IncomingMessageForm.Id is null ");
            sb.append(newLine);
            sb.append((this.incomingMsgFormParamDefinition != null) ? "key=IncomingMsgFormDefinition.Id value=" + this.incomingMsgFormParamDefinition.getId() : "incomingMessageFormDefinition.Id is null ");
            sb.append(newLine);
            sb.append((this.dateCreated != null) ? "key=dateCreated value=" + this.dateCreated.toString() : "dateCreated is null ");
            sb.append(newLine);
            sb.append((this.lastModified != null) ? "key=lastModified value=" + this.lastModified.toString() : "lastModified is null ");
            sb.append(newLine);
            sb.append((this.messageFormParamStatus != null) ? "key=messageFormParamStatus value=" + this.messageFormParamStatus.toString() : "messageFormParamStatus is null ");
            sb.append(newLine);

            return sb.toString();

        } else {
            return "Object is null";
        }


    }
}
