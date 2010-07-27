/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import java.util.List;
import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;

/**
 *
 * @author user
 */
public class CompositeValidator{
    private List<String> fields;
    private int requiredMatches;
    private IncomingMessageFormParameterValidator validator;

    public boolean validate(IncomingMessageForm form)
    {
        boolean valid = false;
        int matchCount = 0;
        int errorCode = 0;
        String errorMsg = "";

        for(String field : fields)
        {
            if(form.getIncomingMsgFormParameters().containsKey(field)){
                IncomingMessageFormParameter param = form.getIncomingMsgFormParameters().get(field.toLowerCase());
                if(validator.validate(param))
                    matchCount++;
                else{
                    errorCode = param.getErrCode();
                    errorMsg = param.getErrText();
                }
            }
        }

        if(matchCount >= requiredMatches)
            valid = true;
        else
        {
            for(String field : fields)
            {
                if(form.getIncomingMsgFormParameters().containsKey(field)){
                    IncomingMessageFormParameter param = form.getIncomingMsgFormParameters().get(field);
                    param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                    param.setErrCode(errorCode);
                    param.setErrText(errorMsg+"(at least "+requiredMatches+" valid values required)");
                }
            }
        }
        return valid;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    /**
     * @param requiredMatches the requiredMatches to set
     */
    public void setRequiredMatches(int requiredMatches) {
        this.requiredMatches = requiredMatches;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(IncomingMessageFormParameterValidator validator) {
        this.validator = validator;
    }
}
