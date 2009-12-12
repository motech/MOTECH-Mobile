/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncMessageFormParameterStatus;
import com.dreamoval.motech.core.model.IncMessageFormStatus;
import com.dreamoval.motech.core.model.IncomingMessageForm;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterDefinition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.DeliveryTime;
import org.motechproject.ws.Gender;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.server.RegistrarService;

/**
 * Validate an IncominMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 6, 2009
 */
public class IncomingMessageFormValidatorImpl implements IncomingMessageFormValidator {

    private String dateFormat;
    private RegistrarService regWS;
    private CoreManager coreManager;
    private IncomingMessageFormParameterValidator imParamValidator;

    /**
     * 
     * @see IncomingMessageFormValidator.validate
     */
    public synchronized boolean validate(IncomingMessageForm form, String requesterPhone) {
        boolean isFormValid = true;
        form.setMessageFormStatus(IncMessageFormStatus.VALID);

        ///TODO fetch param map from form
        Map<String, IncomingMessageFormParameter> paramMap = new HashMap<String, IncomingMessageFormParameter>();

        for (IncomingMessageFormParameterDefinition paramDef : form.getIncomingMsgFormDefinition().getIncomingMsgParamDefinitions()) {
            for (IncomingMessageFormParameter inParam : form.getIncomingMsgFormParameters()) {
                if (inParam.getName().equals(paramDef.getName())) {
                    inParam.setIncomingMsgFormParamDefinition(paramDef);
                    form.setMessageFormStatus(IncMessageFormStatus.VALID);
                    form.setLastModified(new Date());
                    break;
                } else if (paramDef.isRequired()) {
                    form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                    form.setLastModified(new Date());
                }
            }
            if (form.getMessageFormStatus().equals(IncMessageFormStatus.INVALID)) {
                isFormValid = false;

                IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                param.setIncomingMsgFormParamDefinition(paramDef);
                param.setName(paramDef.getName());
                param.setDateCreated(new Date());
                param.setIncomingMsgForm(form);
                param.setErrText("missing");
                param.setErrCode(0);

                form.getIncomingMsgFormParameters().add(param);
            }
        }
        form.setMessageFormStatus(isFormValid ? IncMessageFormStatus.VALID : IncMessageFormStatus.INVALID);

        for (IncomingMessageFormParameter inParam : form.getIncomingMsgFormParameters()) {
            if (!imParamValidator.validate(inParam)) {
                form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                form.setLastModified(new Date());
            }
            paramMap.put(inParam.getName().toLowerCase(), inParam);
        }

        serverValidate(form, paramMap, requesterPhone);
        //TODO call server-side validation

        return form.getMessageFormStatus().equals(IncMessageFormStatus.SERVER_VALID);
    }

    public boolean serverValidate(IncomingMessageForm form, Map<String, IncomingMessageFormParameter> paramMap, String requesterPhone) {
        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
        if (!form.getMessageFormStatus().equals(IncMessageFormStatus.VALID)) {
            return false;
        }

        String code = form.getIncomingMsgFormDefinition().getFormCode();

        ///TODO lookup params in map and set service arguments
        if (code.equals("GENREAL")) {
            //regWS.recordGeneralVisit(reqPhone, reqPhone, Gender.MALE, Integer.MIN_VALUE, Integer.MIN_VALUE, expResult, null);
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
        }
        else if (code.equals("MATERNAL")) {
            try {
                regWS.recordMaternalVisit(requesterPhone, dFormat.parse(paramMap.get("date").getValue()), paramMap.get("serial_id").getValue(), Boolean.valueOf(paramMap.get("tetanus").getValue()), Boolean.valueOf(paramMap.get("ipt").getValue()), Boolean.valueOf(paramMap.get("itn").getValue()), Integer.valueOf(paramMap.get("visit_number").getValue()), Boolean.valueOf(paramMap.get("on_arv").getValue()), Boolean.valueOf(paramMap.get("pre_pmtct").getValue()), Boolean.valueOf(paramMap.get("test_pmtct").getValue()), Boolean.valueOf(paramMap.get("post_pmtct").getValue()), Double.valueOf(paramMap.get("haemo_36_wks").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                ///TODO parser excepton for error information
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            }
        }
        else if (code.equals("PATIENT")) {
            try {
                regWS.registerPatient(requesterPhone, paramMap.get("serial_id").getValue(), paramMap.get("name").getValue(), paramMap.get("community").getValue(), paramMap.get("location").getValue(), dFormat.parse(paramMap.get("dob").getValue()), Gender.valueOf(paramMap.get("gender").getValue()), Integer.valueOf(paramMap.get("nhis").getValue()), paramMap.get("mobile").getValue(), ContactNumberType.valueOf(paramMap.get("contact_type").getValue()), paramMap.get("lang").getValue(), MediaType.valueOf(paramMap.get("medium").getValue()), DeliveryTime.valueOf(paramMap.get("deliv_time").getValue()), null);
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                ///TODO parser excepton for error information
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            }
        }
        else if (code.equals("PREGNANCY")) {
            try {
                regWS.registerPregnancy(requesterPhone, dFormat.parse(paramMap.get("date").getValue()), paramMap.get("serial_id").getValue(), dFormat.parse(paramMap.get("due_date").getValue()), Integer.valueOf(paramMap.get("parity").getValue()), Double.valueOf(paramMap.get("haemoglobin").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                ///TODO parser excepton for error information
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            }
        }

        return form.getMessageFormStatus().equals(IncMessageFormStatus.SERVER_VALID);
    }

    /**
     * @param imParamValidator the imParamValidator to set
     */
    public void setImParamValidator(IncomingMessageFormParameterValidator imParamValidator) {
        this.imParamValidator = imParamValidator;
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    /**
     * @param regWS the regWS to set
     */
    public void setRegWS(RegistrarService regWS) {
        this.regWS = regWS;
    }

    /**
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
