package com.dreamoval.motech.model.imp;

import java.util.Date;

/*
 * IncomingMessageFormParameterImpl is the implementation of the IncomingMessageFormParameter interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageFormParameter operations
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageFormParameterImpl implements IncomingMessageFormParameter {

    private IncomingMessageForm incomingMsgForm;
//    private IncomingMessageParameterDefinition incomingMsgParamDefinition;
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

}
