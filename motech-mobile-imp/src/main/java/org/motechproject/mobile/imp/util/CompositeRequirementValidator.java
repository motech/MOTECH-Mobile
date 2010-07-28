/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import java.util.List;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;

/**
 *
 * @author user
 */
public class CompositeRequirementValidator {

    private List<String> fields;
    private int requiredMatches;

    public boolean validate(IncomingMessageForm form, CoreManager coreManager) {
        boolean valid = false;
        int matchCount = 0;

        for (String field : fields) {
            if (form.getIncomingMsgFormParameters().containsKey(field.toLowerCase())) {
                matchCount++;
            }
        }

        if (matchCount >= requiredMatches) {
            valid = true;
        } else {
            for (String field : fields) {
                IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                param.setName(field.toLowerCase());
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                param.setErrCode(1);
                param.setErrText("missing (at least " + requiredMatches + " of these fields required)");

                form.getIncomingMsgFormParameters().put(field.toLowerCase(), param);
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
}
