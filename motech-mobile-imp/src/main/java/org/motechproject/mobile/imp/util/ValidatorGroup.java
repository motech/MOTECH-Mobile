/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import java.util.Map;

/**
 *
 * @author user
 */
public class ValidatorGroup {
    private String parent = null;
    private Map<String, IncomingMessageFormParameterValidator> validators;

    /**
     * @return the parent
     */
    public String getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * @return the validators
     */
    public Map<String, IncomingMessageFormParameterValidator> getValidators() {
        return validators;
    }

    /**
     * @param validators the validators to set
     */
    public void setValidators(Map<String, IncomingMessageFormParameterValidator> validators) {
        this.validators = validators;
    }
}
