package com.dreamoval.motech.model.imp;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author jojo
 */
public interface IncomingMessageFormDefinition {

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the form_code
     */
    public String getForm_code();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @return the incomingMsgParamDefinition
     */
    public Set<IncomingMessageFormParameterDefinition> getIncomingMsgParamDefinition();

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated);

    /**
     * @param form_code the form_code to set
     */
    public void setForm_code(String form_code);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * @param incomingMsgParamDefinition the incomingMsgParamDefinition to set
     */
    public void setIncomingMsgParamDefinition(Set<IncomingMessageFormParameterDefinition> incomingMsgParamDefinition);
}
