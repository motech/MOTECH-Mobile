/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import java.util.Date;

/**
 *
 * @author user
 */
public class ParamRangeValidator implements IncomingMessageFormParameterValidator {

    private Float minValue = null;
    private Float maxValue = null;

    public synchronized boolean validate(IncomingMessageFormParameter param) {
        Float value;

        if(param.getValue().isEmpty())
            return true;
        
        try {
            value = Float.parseFloat(param.getValue());
        } catch (NumberFormatException ex) {
            param.setErrCode(1);
            param.setErrText("wrong format");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
            return false;
        }

        if (minValue != null && value < minValue){
            param.setErrCode(3);
            param.setErrText("too small");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else if (maxValue != null && value > maxValue){
            param.setErrCode(3);
            param.setErrText("too large");
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
