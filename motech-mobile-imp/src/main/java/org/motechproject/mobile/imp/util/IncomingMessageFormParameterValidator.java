/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.model.IncomingMessageFormParameter;

/**
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 6, 2009
 */
public interface IncomingMessageFormParameterValidator {

    /**
     * Validates an IncomingMessageFormParameter
     * 
     * @param param the IncomingMessageFormParameter object to validate
     * @return the boolean indicating whether the parameter is valid or not
     */
    boolean validate(IncomingMessageFormParameter param);
}
