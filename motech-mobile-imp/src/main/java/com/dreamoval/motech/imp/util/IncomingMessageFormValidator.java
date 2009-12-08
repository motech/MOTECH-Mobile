/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.model.imp.IncomingMessageForm;

/**
 *
 * @author user
 */
public interface IncomingMessageFormValidator {

    /**
     * Validates an IncomingMessageForm
     * 
     * @param form the form to validate
     * @return the validated form
     */
    boolean validate(IncomingMessageForm form);

}
