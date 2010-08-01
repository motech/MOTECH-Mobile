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
public class ParamSizeValidator implements IncomingMessageFormParameterValidator{
    public boolean validate(IncomingMessageFormParameter param) {
        param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
        int paramLength = param.getValue().trim().length();
        int maxLength = param.getIncomingMsgFormParamDefinition().getLength();
        
        if (paramLength > maxLength) {
            param.setErrCode(2);
            param.setErrText("too long");
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
        }

        param.setLastModified(new Date());
        return param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.VALID);
    }
}
