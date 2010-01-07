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

    public boolean validate(IncomingMessageFormParameter param) {
        param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);

        if (!values.contains(param.getValue().trim())) {
            param.setErrCode(3);
            param.setErrText("out of range");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else if (conversions.containsKey(param.getValue().trim())) {
            param.setValue(conversions.get(param.getValue()));
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
}
