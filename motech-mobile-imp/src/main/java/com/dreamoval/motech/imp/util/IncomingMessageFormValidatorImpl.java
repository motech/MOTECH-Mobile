/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.model.imp.IncMessageFormParameterStatus;
import com.dreamoval.motech.model.imp.IncMessageFormStatus;
import com.dreamoval.motech.model.imp.IncomingMessageForm;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterDefinition;
import java.util.Date;

/**
 * Validate an IncominMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 6, 2009
 */
public class IncomingMessageFormValidatorImpl implements IncomingMessageFormValidator {

    private CoreManager coreManager;
    private IncomingMessageFormParameterValidator imParamValidator;

    /**
     * 
     * @see IncomingMessageFormValidator.validate
     */
    public synchronized boolean validate(IncomingMessageForm form) {
        boolean isFormValid = true;
        form.setMessageFormStatus(IncMessageFormStatus.VALID);

        for (IncomingMessageFormParameterDefinition paramDef : form.getIncomingMsgFormDefinition().getIncomingMsgParamDefinitions()) {
            for (IncomingMessageFormParameter inParam : form.getIncomingMsgFormParameters()) {
                if (inParam.getName().equals(paramDef.getName())) {
                    inParam.setIncomingMsgFormParamDefinition(paramDef);
                    form.setMessageFormStatus(IncMessageFormStatus.VALID);
                    form.setLastModified(new Date());
                    break;
                } else if (paramDef.isRequired()) {
                    form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                    form.setLastModified(new Date());
                }
            }
            if (form.getMessageFormStatus().equals(IncMessageFormStatus.INVALID)) {
                isFormValid = false;

                IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                param.setIncomingMsgFormParamDefinition(paramDef);
                param.setName(paramDef.getName());
                param.setDateCreated(new Date());
                param.setIncomingMsgForm(form);
                param.setErrText("missing");
                param.setErrCode(0);

                form.getIncomingMsgFormParameters().add(param);
            }
        }
        form.setMessageFormStatus(isFormValid ? IncMessageFormStatus.VALID : IncMessageFormStatus.INVALID);

        for (IncomingMessageFormParameter inParam : form.getIncomingMsgFormParameters()) {
            if (!imParamValidator.validate(inParam)) {
                form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                form.setLastModified(new Date());
            }
        }

        //TODO call server-side validation

        return form.getMessageFormStatus().equals(IncMessageFormStatus.VALID);
    }

    /**
     * @return the imParamValidator
     */
    public IncomingMessageFormParameterValidator getImParamValidator() {
        return imParamValidator;
    }

    /**
     * @param imParamValidator the imParamValidator to set
     */
    public void setImParamValidator(IncomingMessageFormParameterValidator imParamValidator) {
        this.imParamValidator = imParamValidator;
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
}
