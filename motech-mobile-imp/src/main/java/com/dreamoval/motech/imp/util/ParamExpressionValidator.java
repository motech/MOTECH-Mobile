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
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class ParamExpressionValidator implements IncomingMessageFormParameterValidator {

    private String expression;
    private static Logger logger = Logger.getLogger(ParamExpressionValidator.class);

    public boolean validate(IncomingMessageFormParameter param) {
//        if (param.getIncomingMsgFormParamDefinition().getParamType().toUpperCase().equals("DATE")) {
//            try {
//                SimpleDateFormat dFormat = new SimpleDateFormat(expression);
//                dFormat.setLenient(false);
//                dFormat.parse(param.getValue());
//                param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
//            } catch (ParseException ex) {
//                param.setErrCode(1);
//                param.setErrText("wrong format");
//                param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
//            }
//        } else
        if (!Pattern.matches(expression, param.getValue().trim())) {
            param.setErrCode(1);
            param.setErrText("wrong format");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else {
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
            if(param.getIncomingMsgFormParamDefinition().getParamType().toUpperCase().equals("NUMERIC") && param.getValue().equalsIgnoreCase("NA"))
                param.setValue("");
            else if (param.getIncomingMsgFormParamDefinition().getParamType().toUpperCase().equals("DATE") && param.getValue().matches("\\d+")){
                try {
                    SimpleDateFormat dFormat = new SimpleDateFormat("ddmmyy");
                    Date val = dFormat.parse(param.getValue());
                    dFormat.applyPattern("dd/mm/yyyy");
                    param.setValue(dFormat.format(val));
                } catch (ParseException ex) {
                    logger.error("Invalid date format - " + param.getValue(), ex);
                    
                    param.setErrCode(1);
                    param.setErrText("wrong format");
                    param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                }
            }
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
