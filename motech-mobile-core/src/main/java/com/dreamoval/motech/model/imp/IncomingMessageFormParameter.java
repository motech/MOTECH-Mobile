package com.dreamoval.motech.model.imp;

import java.util.Date;


public interface IncomingMessageFormParameter {

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @return the errCode
     */
    int getErrCode();

    /**
     * @return the errText
     */
    String getErrText();

    /**
     * @return the incomingMsgForm
     */
    IncomingMessageForm getIncomingMsgForm();

    /**
     * @return the lastModified
     */
    Date getLastModified();

    /**
     * @return the messageFormParamStatus
     */
    IncMessageFormParameterStatus getMessageFormParamStatus();

    /**
     * @return the value
     */
    String getValue();

    /**
     * @return the value incomingMsgFormParamDefinition
     */
    IncomingMessageFormParameterDefinition getIncomingMsgFormParamDefinition();

    /**
     * @param dateCreated the dateCreated to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param errCode the errCode to set
     */
    void setErrCode(int errCode);

    /**
     * @param errText the errText to set
     */
    void setErrText(String errText);

    /**
     * @param incomingMsgForm the incomingMsgForm to set
     */
    void setIncomingMsgForm(IncomingMessageForm incomingMsgForm);

    /**
     * @param lastModified the lastModified to set
     */
    void setLastModified(Date lastModified);

    /**
     * @param messageFormParamStatus the messageFormParamStatus to set
     */
    void setMessageFormParamStatus(IncMessageFormParameterStatus messageFormParamStatus);

    /**
     * @param value the value to set
     */
    void setValue(String value);

    /**
     * @param incomingMsgFormParamDefinition the incomingMsgFormParamDefinition to set
     */
    void setIncomingMsgFormParamDefinition(IncomingMessageFormParameterDefinition incomingMsgFormParamDefinition);
}
