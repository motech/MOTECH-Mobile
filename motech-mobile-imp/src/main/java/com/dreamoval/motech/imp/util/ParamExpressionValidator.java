/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.model.IncMessageFormParameterStatus;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class ParamExpressionValidator implements IncomingMessageFormParameterValidator {

    private String expression;

    public boolean validate(IncomingMessageFormParameter param) {
        if (param.getIncomingMsgFormParamDefinition().getParamType().toUpperCase().equals("DATE")) {
            try {
                SimpleDateFormat dFormat = new SimpleDateFormat(expression);
                dFormat.setLenient(false);
                dFormat.parse(param.getValue());
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
            } catch (ParseException ex) {
                param.setErrCode(1);
                param.setErrText("wrong format");
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
            }
        } else if (!Pattern.matches(expression, param.getValue().trim())) {
            param.setErrCode(1);
            param.setErrText("wrong format");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else {
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
        }

        param.setLastModified(new Date());
        return param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.VALID);
    }

    /**
     * @param expression the expression to set
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }
}
