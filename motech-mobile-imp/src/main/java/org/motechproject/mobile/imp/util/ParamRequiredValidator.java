/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.model.IncomingMessageFormParameter;

/**
 *
 * @author user
 */
public class ParamRequiredValidator implements IncomingMessageFormParameterValidator {

    public boolean validate(IncomingMessageFormParameter param) {
        if(param.getIncomingMsgFormParamDefinition().isRequired()){
            if(param.getValue() == null || param.getValue().isEmpty())
            {
                param.setErrCode(0);
                param.setErrText("missing");
                return false;
            }
        }
        return true;
    }

}
