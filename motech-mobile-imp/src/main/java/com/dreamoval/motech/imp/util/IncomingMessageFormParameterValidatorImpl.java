/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.model.imp.IncMessageFormParameterStatus;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Validates a form parameter
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 6, 2009
 */
public class IncomingMessageFormParameterValidatorImpl implements IncomingMessageFormParameterValidator {
    private Map<String, String> paramTypeMap;

    /**
     * @see IncomingMessageFormParameterValidator.validate
     */
    public IncomingMessageFormParameter validate(IncomingMessageFormParameter param){
        String paramRegex = getParamTypeMap().get(param.getIncomingMsgFormParamDefinition().getParamType());

        System.out.println("Type: " + param.getIncomingMsgFormParamDefinition().getParamType());
        System.out.println("Regex: " + paramRegex);
        System.out.println("Value: " + param.getValue());
        System.out.println("---------------------------------------------------------");

        if(!param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.NEW)){
            return param;
        }

        if(!Pattern.matches(paramRegex, param.getValue().trim().toLowerCase())){
            param.setErrCode(0);
            param.setErrText("wrong format");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        }
        else if(param.getValue().trim().length() > param.getIncomingMsgFormParamDefinition().getLength()){
            param.setErrCode(1);
            param.setErrText("too long");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        }
        else{
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
        }

        param.setLastModified(new Date());
        return param;
    }

    /**
     * @return the paramTypeMap
     */
    public Map<String, String> getParamTypeMap() {
        return paramTypeMap;
    }

    /**
     * @param paramTypeMap the paramTypeMap to set
     */
    public void setParamTypeMap(Map<String, String> paramTypeMap) {
        this.paramTypeMap = paramTypeMap;
    }
}
