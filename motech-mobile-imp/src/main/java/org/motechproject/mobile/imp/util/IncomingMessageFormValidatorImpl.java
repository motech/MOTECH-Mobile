/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import org.motechproject.mobile.core.model.IncomingMessageFormParameterDefinition;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

/**
 * Validate an IncominMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 6, 2009
 */
public class IncomingMessageFormValidatorImpl implements IncomingMessageFormValidator {

    private CoreManager coreManager;
    private Map<String, ValidatorGroup> paramValidators;
    private static Logger logger = Logger.getLogger(IncomingMessageFormValidatorImpl.class);

    /**
     * 
     * @see IncomingMessageFormValidator.validate
     */
    public synchronized IncMessageFormStatus validate(IncomingMessageForm form, String requesterPhone) {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        try {
            for (IncomingMessageFormParameterDefinition paramDef : form.getIncomingMsgFormDefinition().getIncomingMsgParamDefinitions()) {

                paramDef.getParamType();

                if (form.getIncomingMsgFormParameters().containsKey(paramDef.getName().toLowerCase())) {
                    form.getIncomingMsgFormParameters().get(paramDef.getName().toLowerCase()).setIncomingMsgFormParamDefinition(paramDef);
                    form.setLastModified(new Date());

                    ValidatorGroup group = paramValidators.get(paramDef.getParamType().toUpperCase());
                    if(group.getParent() != null && !group.getParent().isEmpty()){
                        ValidatorGroup parent = paramValidators.get(group.getParent());
                        for(Entry<String, IncomingMessageFormParameterValidator> validator : group.getValidators().entrySet()){
                            parent.getValidators().put(validator.getKey(), validator.getValue());
                        }
                        group = parent;
                    }
                    
                    for (Entry<String, IncomingMessageFormParameterValidator> entry : group.getValidators().entrySet()) {
                        if (!entry.getValue().validate(form.getIncomingMsgFormParameters().get(paramDef.getName().toLowerCase()))) {
                            form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                            form.setLastModified(new Date());
                            break;
                        }
                    }
                } else {
                    if (paramDef.isRequired()) {
                        IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
                        param.setIncomingMsgFormParamDefinition(paramDef);
                        param.setName(paramDef.getName());
                        param.setDateCreated(new Date());
                        param.setIncomingMsgForm(form);
                        param.setValue(null);

                        param.setErrCode(0);
                        param.setErrText("missing");
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                        form.setMessageFormStatus(IncMessageFormStatus.INVALID);

                        form.setLastModified(new Date());
                        form.getIncomingMsgFormParameters().put(paramDef.getName().toLowerCase(), param);
                    }
                }
            }
        } catch (Exception ex) {
            logger.fatal("Error validating form", ex);
            form.setMessageFormStatus(IncMessageFormStatus.ERROR);
        }

        return form.getMessageFormStatus();
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    /**
     * @param paramValidators the paramValidators to set
     */
    public void setParamValidators(Map<String, ValidatorGroup> paramValidators) {
        this.paramValidators = paramValidators;
    }
}