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
     * @return the incomingMsgParamDefinition
     */
    Set<IncomingMessageFormParameterDefinition> getIncomingMsgParamDefinition( );

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

    /**
     * @param incomingMsgParamDefinition the incomingMsgParamDefinition to set
     */
    void setIncomingMsgParamDefinition(Set<IncomingMessageFormParameterDefinition> incomingMsgParamDefinition);
}
