/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.model.IncMessageFormParameterStatus;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import java.util.Date;

/**
 *
 * @author user
 */
public class ParamRangeValidator implements IncomingMessageFormParameterValidator {

    private Float minValue;
    private Float maxValue;

    public boolean validate(IncomingMessageFormParameter param) {
        Float value;
        
        try {
            value = Float.parseFloat(param.getValue());
        } catch (NumberFormatException ex) {
            param.setErrCode(1);
            param.setErrText("wrong format");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
            return false;
        }

        if (value < minValue || value > maxValue) {
            param.setErrCode(3);
            param.setErrText("out of range");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else {
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
        }

        param.setLastModified(new Date());
        return param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.VALID);
    }

    /**
     * @param minValue the minValue to set
     */
    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    /**
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }
}
