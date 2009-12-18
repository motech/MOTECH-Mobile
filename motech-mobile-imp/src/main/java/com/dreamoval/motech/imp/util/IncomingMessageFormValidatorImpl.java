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
import java.util.List;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.DeliveryTime;
import org.motechproject.ws.Gender;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.server.RegistrarService;
import org.motechproject.ws.server.ValidationError;
import org.motechproject.ws.server.ValidationException;

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

        for (IncomingMessageFormParameterDefinition paramDef : form.getIncomingMsgFormDefinition().getIncomingMsgParamDefinitions()) {
            if (form.getIncomingMsgFormParameters().containsKey(paramDef.getName().toLowerCase())) {
                form.getIncomingMsgFormParameters().get(paramDef.getName()).setIncomingMsgFormParamDefinition(paramDef);
                form.setLastModified(new Date());

                if (!imParamValidator.validate(form.getIncomingMsgFormParameters().get(paramDef.getName()))) {
                    form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                    form.setLastModified(new Date());
                }
            } else if (paramDef.isRequired()) {
                form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                form.setLastModified(new Date());

                IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                param.setIncomingMsgFormParamDefinition(paramDef);
                param.setName(paramDef.getName());
                param.setDateCreated(new Date());
                param.setIncomingMsgForm(form);
                param.setErrText("missing");
                param.setErrCode(0);

                form.getIncomingMsgFormParameters().put(paramDef.getName(), param);
            }
        }

        serverValidate(form, requesterPhone);
        //TODO call server-side validation

        return form.getMessageFormStatus().equals(IncMessageFormStatus.SERVER_VALID);
    }

    public boolean serverValidate(IncomingMessageForm form, String requesterPhone) {
        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
        if (!form.getMessageFormStatus().equals(IncMessageFormStatus.VALID)) {
            return false;
        }

        String code = form.getIncomingMsgFormDefinition().getFormCode();

        ///TODO lookup params in map and set service arguments
        if (code.equals("GENERAL")) {
            try {
                regWS.recordGeneralVisit(Integer.valueOf(form.getIncomingMsgFormParameters().get("FacilityId").getValue()), dFormat.parse(form.getIncomingMsgFormParameters().get("Date").getValue()), form.getIncomingMsgFormParameters().get("SerialNo").getValue(), Gender.valueOf(form.getIncomingMsgFormParameters().get("Sex").getValue()), dFormat.parse(form.getIncomingMsgFormParameters().get("DoB").getValue()), Integer.valueOf(form.getIncomingMsgFormParameters().get("Diagnosis").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("Referral").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                List<ValidationError> errors = ex.getFaultInfo().getErrors();

                for (ValidationError error : errors) {
                    IncomingMessageFormParameter param = form.getIncomingMsgFormParameters().get(error.getField().toLowerCase());
                    param.setErrCode(error.getCode());
                    param.setErrText("server error");
                    param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);

                }
            }
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);

        } else if (code.equals("MATERNAL")) {
            try {
                regWS.recordMaternalVisit(requesterPhone, dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("serial_id").getValue(), Boolean.valueOf(form.getIncomingMsgFormParameters().get("tetanus").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("ipt").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("itn").getValue()), Integer.valueOf(form.getIncomingMsgFormParameters().get("visit_number").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("on_arv").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("pre_pmtct").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("test_pmtct").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("post_pmtct").getValue()), Double.valueOf(form.getIncomingMsgFormParameters().get("haemo_36_wks").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                ///TODO parse excepton for error information
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            }
        } else if (code.equals("PATIENT")) {
            try {
                regWS.registerPatient(requesterPhone, form.getIncomingMsgFormParameters().get("serial_id").getValue(), form.getIncomingMsgFormParameters().get("name").getValue(), form.getIncomingMsgFormParameters().get("community").getValue(), form.getIncomingMsgFormParameters().get("location").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("dob").getValue()), Gender.valueOf(form.getIncomingMsgFormParameters().get("gender").getValue()), Integer.valueOf(form.getIncomingMsgFormParameters().get("nhis").getValue()), form.getIncomingMsgFormParameters().get("mobile").getValue(), ContactNumberType.valueOf(form.getIncomingMsgFormParameters().get("contact_type").getValue()), form.getIncomingMsgFormParameters().get("lang").getValue(), MediaType.valueOf(form.getIncomingMsgFormParameters().get("medium").getValue()), DeliveryTime.valueOf(form.getIncomingMsgFormParameters().get("deliv_time").getValue()), null);
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                ///TODO parse excepton for error information
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            }
        } else if (code.equals("PREGNANCY")) {
            try {
                regWS.registerPregnancy(requesterPhone, dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("serial_id").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("due_date").getValue()), Integer.valueOf(form.getIncomingMsgFormParameters().get("parity").getValue()), Double.valueOf(form.getIncomingMsgFormParameters().get("haemoglobin").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                ///TODO parse excepton for error information
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
