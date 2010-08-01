/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
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
        param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        param.setValue(value);

        if (!values.isEmpty()){
            String[] valArr = values.split(",");
            for(String val : valArr){
                if(val.equals(param.getValue())){
                    param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
                    break;
                }
            }
        }
        else
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);

        if(param.getMessageFormParamStatus() == IncMessageFormParameterStatus.INVALID){
            param.setErrCode(3);
            param.setErrText("out of range");
        }
        else if (conversions != null && conversions.containsKey(value)) {
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
