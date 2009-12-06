/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.model.imp.IncMessageFormParameterStatus;
import com.dreamoval.motech.model.imp.IncMessageFormStatus;
import com.dreamoval.motech.model.imp.IncomingMessageForm;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;

/**
 * Validate an IncominMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 6, 2009
 */
public class IncomingMessageFormValidatorImpl {
    private IncomingMessageFormParameterValidator imParamValidator;

    /**
     * 
     * @see IncomingMessageFormValidator.validate
     */
    public IncomingMessageForm validate(IncomingMessageForm form){        
        ///TODOcheck for required params
        
        for(IncomingMessageFormParameter inParam : form.getIncomingMsgFormParameters()){
            IncomingMessageFormParameter validated = imParamValidator.validate(inParam);
            form.getIncomingMsgFormParameters().remove(inParam);
            form.getIncomingMsgFormParameters().add(validated);

            if(validated.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.INVALID)){
                form.setMessageFormStatus(IncMessageFormStatus.INVALID);
            }
        }

        return form;
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
}
