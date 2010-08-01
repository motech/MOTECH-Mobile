/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public boolean validate(IncomingMessageFormParameter param) {
        if (!param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.NEW)) {
            return param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.VALID);
        }

        String paramRegex = getParamTypeMap().get(param.getIncomingMsgFormParamDefinition().getParamType());

        if (param.getIncomingMsgFormParamDefinition().getParamType().toUpperCase().equals("DATE")) {
            try {
                new SimpleDateFormat(paramRegex).parse(param.getValue());
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
            } catch (ParseException ex) {
                param.setErrCode(1);
                param.setErrText("wrong format");
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
            }
        } else if (!Pattern.matches(paramRegex, param.getValue().trim())) {
            param.setErrCode(1);
            param.setErrText("wrong format");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else if (param.getValue().trim().length() > param.getIncomingMsgFormParamDefinition().getLength()) {
            param.setErrCode(2);
            param.setErrText("too long");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else {
            if(param.getIncomingMsgFormParamDefinition().getParamType().toUpperCase().equals("BOOLEAN"))
                param.setValue(param.getValue().toLowerCase().equals("y") ? "true" : "false");
            else if(param.getIncomingMsgFormParamDefinition().getParamType().toUpperCase().equals("GENDER"))
                param.setValue(param.getValue().toLowerCase().equals("m") ? "MALE" : "FEMALE");

            param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
        }

        param.setLastModified(new Date());
        return param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.VALID);
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
