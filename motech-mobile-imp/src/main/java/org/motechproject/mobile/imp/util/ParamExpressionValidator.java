/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class ParamExpressionValidator implements IncomingMessageFormParameterValidator {

    private String expression;
    private String dateFormat;
    private String defaultDateFormat;
    private DateFormatRegexMap dateFormateExpMap;

    private static Logger logger = Logger.getLogger(ParamExpressionValidator.class);

    public synchronized boolean validate(IncomingMessageFormParameter param) {
        String paramType = param.getIncomingMsgFormParamDefinition().getParamType().toUpperCase();

        if(paramType.indexOf("TIME") >= 0){
            try{
                SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
                dFormat.setLenient(true);
                Date val = dFormat.parse(param.getValue());

                dFormat.applyPattern(defaultDateFormat);
                param.setValue(dFormat.format(val));
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
            }catch (ParseException ex) {
                logger.error("Invalid datetime format - " + param.getValue(), ex);

                param.setErrCode(1);
                param.setErrText("wrong format");
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
            }
        }else if (!Pattern.matches(expression, param.getValue().trim())) {
            param.setErrCode(1);
            param.setErrText("wrong format");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        } else {
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
            if (paramType.indexOf("DATE") >= 0) {
                try {
                    String dateInputFormat = "";
                    for (String regex : dateFormateExpMap.getDateFormatRegexMap().keySet()){
                        boolean match = Pattern.matches(regex, param.getValue());
                        if (match){
                            dateInputFormat = dateFormateExpMap.getDateFormatRegexMap().get(regex);
                            break;
                        }
                    }

                    SimpleDateFormat dFormat = new SimpleDateFormat(dateInputFormat);
                    dFormat.setLenient(true);
                    Date val = dFormat.parse(param.getValue());

                    if (paramType.equalsIgnoreCase("DATE") && val.after(new Date())) {
                        param.setErrCode(1);
                        param.setErrText("invalid date");
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                    }
                    else if (paramType.equalsIgnoreCase("DELIVERYDATE")) {
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.WEEK_OF_YEAR, 40);
                        Date endDate = cal.getTime();

                        if(val.before(new Date()) || val.after(endDate)){
                            param.setErrCode(1);
                            param.setErrText("invalid date");
                            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                        }
                    }
                    
                    dFormat.applyPattern(defaultDateFormat);
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

    /**
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * @return the dateFormateExpMap
     */
    public DateFormatRegexMap getDateFormateExpMap() {
        return dateFormateExpMap;
    }

    /**
     * @param dateFormateExpMap the dateFormateExpMap to set
     */
    public void setDateFormateExpMap(DateFormatRegexMap dateFormateExpMap) {
        this.dateFormateExpMap = dateFormateExpMap;
    }

    /**
     * @return the defaultDateFormat
     */
    public String getDefaultDateFormat() {
        return defaultDateFormat;
    }

    /**
     * @param defaultDateFormat the defaultDateFormat to set
     */
    public void setDefaultDateFormat(String defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
    }
}
