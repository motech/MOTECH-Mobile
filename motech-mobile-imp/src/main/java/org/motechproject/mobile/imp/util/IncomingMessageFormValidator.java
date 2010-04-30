/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;

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
    IncMessageFormStatus validate(IncomingMessageForm form, String requesterPhone);

}
