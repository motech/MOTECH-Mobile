/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;

/**
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 6, 2009
 */
public interface IncomingMessageFormParameterValidator {

    /**
     * Validates an IncomingMessageFormParameter
     * 
     * @param param the IncomingMessageFormParameter object to validate
     * @return the validated IncomingMessageFormParameter
     */
    IncomingMessageFormParameter validate(IncomingMessageFormParameter param);

}
