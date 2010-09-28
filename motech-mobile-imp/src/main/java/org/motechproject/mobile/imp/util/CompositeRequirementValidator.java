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
 * Validate an IncominMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
public class CompositeRequirementValidator {

    private List<String> fields;
    private int requiredMatches;

    public boolean validate(IncomingMessageForm form, CoreManager coreManager) {
        boolean valid = false;
        int matchCount = 0;
        String fieldName = "";

        for (String field : fields) {
            if (fieldName.isEmpty()) {
                fieldName = field;
            }

            if (form.getIncomingMsgFormParameters().containsKey(field.toLowerCase())) {
                matchCount++;
            }
        }

        if (matchCount >= requiredMatches) {
            valid = true;
        } else {
            String error = "At least " + requiredMatches + " of the fields below required:";

            IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
            param.setName(fieldName.toLowerCase());
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);

            for (String field : fields) {
                error += "\n" + field;
            }
            param.setErrCode(1);
            param.setErrText(error);

            form.getIncomingMsgFormParameters().put(fieldName.toLowerCase(), param);
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
