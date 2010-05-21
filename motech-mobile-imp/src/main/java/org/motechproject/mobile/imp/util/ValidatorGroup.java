/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import java.util.LinkedHashMap;

/**
 *
 * @author user
 */
public class ValidatorGroup {
    private String parent = null;
    private LinkedHashMap<String, IncomingMessageFormParameterValidator> validators;

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
    public LinkedHashMap<String, IncomingMessageFormParameterValidator> getValidators() {
        return validators;
    }

    /**
     * @param validators the validators to set
     */
    public void setValidators(LinkedHashMap<String, IncomingMessageFormParameterValidator> validators) {
        this.validators = validators;
    }
}
