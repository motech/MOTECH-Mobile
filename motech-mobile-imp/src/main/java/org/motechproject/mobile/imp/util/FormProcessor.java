/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import java.util.Map;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.omi.manager.OMIManager;
import org.motechproject.ws.server.RegistrarService;
import org.motechproject.ws.server.ValidationException;

/**
 *
 * @author user
 */
public interface FormProcessor {
    String processForm(IncomingMessageForm form);

    void parseValidationErrors(IncomingMessageForm form, ValidationException ex);

    /**
     * @param coreManager the coreManager to set
     */
    void setCoreManager(CoreManager coreManager);

    /**
     * @param dateFormat the dateFormat to set
     */
    void setDateFormat(String dateFormat);

    /**
     * @param omiManager the omiManager to set
     */
    void setOmiManager(OMIManager omiManager);

    /**
     * @param regWS the regWS to set
     */
    void setRegWS(RegistrarService regWS);

    /**
     * @param serverErrors the serverErrors to set
     */
    void setServerErrors(Map<Integer, String> serverErrors);

    /**
     * @param serviceMethods the serviceMethods to set
     */
    void setServiceMethods(Map<String, MethodSignature> serviceMethods);

}
