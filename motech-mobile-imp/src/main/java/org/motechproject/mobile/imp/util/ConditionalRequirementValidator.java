/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import java.util.Date;
import java.util.List;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;

/**
 * Validate an IncominMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Sep 28, 2010
 */
public class ConditionalRequirementValidator {

    public boolean validate(IncomingMessageForm form, List<SubField> subFields, CoreManager coreManager) {
        boolean valid = true;

        for(SubField sf : subFields) {
            if(!form.getIncomingMsgFormParameters().containsKey(sf.getParentField().toLowerCase()))
                continue;
            
            IncomingMessageFormParameter parent = form.getIncomingMsgFormParameters().get(sf.getParentField().toLowerCase());

            if (sf.getReplaceOn().equals("*") || parent.getValue().equalsIgnoreCase(sf.getReplaceOn())) {

                if (!form.getIncomingMsgFormParameters().containsKey(sf.getFieldName().toLowerCase())) {
                    valid = false;
                    
                    IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                    param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                    param.setName(sf.getFieldName().toLowerCase());
                    param.setErrText("missing");
                    param.setErrCode(0);

                    form.getIncomingMsgFormParameters().put(sf.getFieldName().toLowerCase(), param);
                    form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                    form.setLastModified(new Date());
                }
            }
        }
        return valid;
    }
}
