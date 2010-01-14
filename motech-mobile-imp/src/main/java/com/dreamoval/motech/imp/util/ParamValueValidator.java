/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.model.IncMessageFormParameterStatus;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author user
 */
public class ParamValueValidator implements IncomingMessageFormParameterValidator {

    private String values;
    private Map<String, String> conversions;
    private boolean caseSensitive;

    public boolean validate(IncomingMessageFormParameter param) {
        String value = caseSensitive ? param.getValue().trim() : param.getValue().trim().toUpperCase();
        param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);

        if (!values.contains(value)) {
            param.setErrCode(3);
            param.setErrText("out of range");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else if (conversions != null && conversions.containsKey(value)) {
            param.setValue(conversions.get(value));
        }
        param.setLastModified(new Date());
        return param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.VALID);
    }

    /**
     * @param values the values to set
     */
    public void setValues(String values) {
        this.values = values;
    }

    /**
     * @param conversions the conversions to set
     */
    public void setConversions(Map<String, String> conversions) {
        this.conversions = conversions;
    }

    /**
     * @param caseSensitive the caseSensitive to set
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }
}
