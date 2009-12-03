package com.dreamoval.motech.model.imp;

import com.dreamoval.motech.core.model.MotechEntity;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author jojo
 */
public interface IncomingMessageFormDefinition extends MotechEntity {

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the formCode
     */
    public String getFormCode();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @return the incomingMsgParamDefinition
     */
    public Set<IncomingMessageFormParameterDefinition> getIncomingMsgParamDefinitions();

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated);

    /**
     * @param formCode the formCode to set
     */
    public void setFormCode(String formCode);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * @param incomingMsgParamDefinition the incomingMsgParamDefinition to set
     */
    public void setIncomingMsgParamDefinitions(Set<IncomingMessageFormParameterDefinition> incomingMsgParamDefinition);
}
