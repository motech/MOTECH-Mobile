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
import java.util.Map;
import org.apache.log4j.Logger;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.Gender;
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
    //private IncomingMessageFormParameterValidator imParamValidator;
    private Map<String, List<IncomingMessageFormParameterValidator>> paramValidators;
    private Map<Integer, String> serverErrors;
    private static Logger logger = Logger.getLogger(IncomingMessageFormValidatorImpl.class);

    /**
     * 
     * @see IncomingMessageFormValidator.validate
     */
    public synchronized boolean validate(IncomingMessageForm form, String requesterPhone) {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);

        for (IncomingMessageFormParameterDefinition paramDef : form.getIncomingMsgFormDefinition().getIncomingMsgParamDefinitions()) {
            if (form.getIncomingMsgFormParameters().containsKey(paramDef.getName().toLowerCase())) {
                form.getIncomingMsgFormParameters().get(paramDef.getName().toLowerCase()).setIncomingMsgFormParamDefinition(paramDef);
                form.setLastModified(new Date());

                for (IncomingMessageFormParameterValidator validator : paramValidators.get(paramDef.getParamType().toUpperCase())) {
                    if (!validator.validate(form.getIncomingMsgFormParameters().get(paramDef.getName().toLowerCase()))) {
                        form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                        form.setLastModified(new Date());
                        break;
                    }
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

                form.getIncomingMsgFormParameters().put(paramDef.getName().toLowerCase(), param);
            }
        }

        serverValidate(form, requesterPhone);

        return form.getMessageFormStatus().equals(IncMessageFormStatus.SERVER_VALID);
    }

    public boolean serverValidate(IncomingMessageForm form, String requesterPhone) {
        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
        dFormat.setLenient(false);
        
        if (!form.getMessageFormStatus().equals(IncMessageFormStatus.VALID)) {
            return false;
        }

        String code = form.getIncomingMsgFormDefinition().getFormCode();

        if (code.equalsIgnoreCase("GeneralOPD")) {
            try {
                regWS.recordGeneralVisit(Integer.valueOf(form.getIncomingMsgFormParameters().get("facilityid").getValue()), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("serialno").getValue(), Gender.valueOf(form.getIncomingMsgFormParameters().get("sex").getValue()), dFormat.parse(form.getIncomingMsgFormParameters().get("dob").getValue()), Integer.valueOf(form.getIncomingMsgFormParameters().get("diagnosis").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("referral").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                List<ValidationError> errors = ex.getFaultInfo().getErrors();

                for (ValidationError error : errors) {
                    if (form.getIncomingMsgFormParameters().containsKey(error.getField().toLowerCase())) {
                        IncomingMessageFormParameter param = form.getIncomingMsgFormParameters().get(error.getField().toLowerCase());
                        param.setErrCode(error.getCode());
                        if (serverErrors.containsKey(error.getCode())) {
                            param.setErrText(serverErrors.get(error.getCode()));
                        } else {
                            param.setErrText("server error");
                        }
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);
                    } else {
                        IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);
                        param.setName(error.getField());
                        param.setDateCreated(new Date());
                        param.setIncomingMsgForm(form);
                        param.setErrText("missing");
                        param.setErrCode(error.getCode());

                        form.getIncomingMsgFormParameters().put(error.getField(), param);
                    }
                }
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        } else if (code.equalsIgnoreCase("PregnancyStop")) {
            try {
                regWS.stopPregnancyProgram(form.getIncomingMsgFormParameters().get("chpsid").getValue(), form.getIncomingMsgFormParameters().get("patientregnum").getValue());
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ValidationException ex) {
                List<ValidationError> errors = ex.getFaultInfo().getErrors();

                for (ValidationError error : errors) {
                    if (form.getIncomingMsgFormParameters().containsKey(error.getField().toLowerCase())) {
                        IncomingMessageFormParameter param = form.getIncomingMsgFormParameters().get(error.getField().toLowerCase());
                        param.setErrCode(error.getCode());
                        if (serverErrors.containsKey(error.getCode())) {
                            param.setErrText(serverErrors.get(error.getCode()));
                        } else {
                            param.setErrText("server error");
                        }
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);
                    } else {
                        IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);
                        param.setName(error.getField());
                        param.setDateCreated(new Date());
                        param.setIncomingMsgForm(form);
                        param.setErrText("missing");
                        param.setErrCode(error.getCode());

                        form.getIncomingMsgFormParameters().put(error.getField(), param);
                    }
                }
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        } else if (code.equalsIgnoreCase("RegisterChild")) {
            try {
                regWS.registerChild(form.getIncomingMsgFormParameters().get("chpsid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("regdate").getValue()), form.getIncomingMsgFormParameters().get("motherregnum").getValue(), form.getIncomingMsgFormParameters().get("childregnum").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("dob").getValue()), Gender.valueOf(form.getIncomingMsgFormParameters().get("childgender").getValue()), form.getIncomingMsgFormParameters().get("childfirstname").getValue(), form.getIncomingMsgFormParameters().get("nhis").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("nhisexp").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                List<ValidationError> errors = ex.getFaultInfo().getErrors();

                for (ValidationError error : errors) {
                    if (form.getIncomingMsgFormParameters().containsKey(error.getField().toLowerCase())) {
                        IncomingMessageFormParameter param = form.getIncomingMsgFormParameters().get(error.getField().toLowerCase());
                        param.setErrCode(error.getCode());
                        if (serverErrors.containsKey(error.getCode())) {
                            param.setErrText(serverErrors.get(error.getCode()));
                        } else {
                            param.setErrText("server error");
                        }
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);
                    } else {
                        IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);
                        param.setName(error.getField());
                        param.setDateCreated(new Date());
                        param.setIncomingMsgForm(form);
                        param.setErrText("missing");
                        param.setErrCode(error.getCode());

                        form.getIncomingMsgFormParameters().put(error.getField(), param);
                    }
                }
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        } else if (code.equalsIgnoreCase("EditPatient")) {
            try {
                regWS.editPatient(form.getIncomingMsgFormParameters().get("chpsid").getValue(), form.getIncomingMsgFormParameters().get("patientregnum").getValue(), form.getIncomingMsgFormParameters().get("primaryphone").getValue(), ContactNumberType.valueOf(form.getIncomingMsgFormParameters().get("primaryphonetype").getValue()), form.getIncomingMsgFormParameters().get("secondaryphone").getValue(), ContactNumberType.valueOf(form.getIncomingMsgFormParameters().get("secondaryphonetype").getValue()), form.getIncomingMsgFormParameters().get("nhis").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("nhisexp").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                List<ValidationError> errors = ex.getFaultInfo().getErrors();

                for (ValidationError error : errors) {
                    if (form.getIncomingMsgFormParameters().containsKey(error.getField().toLowerCase())) {
                        IncomingMessageFormParameter param = form.getIncomingMsgFormParameters().get(error.getField().toLowerCase());
                        param.setErrCode(error.getCode());
                        if (serverErrors.containsKey(error.getCode())) {
                            param.setErrText(serverErrors.get(error.getCode()));
                        } else {
                            param.setErrText("server error");
                        }
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);
                    } else {
                        IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                        param.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);
                        param.setName(error.getField());
                        param.setDateCreated(new Date());
                        param.setIncomingMsgForm(form);
                        param.setErrText("missing");
                        param.setErrCode(error.getCode());

                        form.getIncomingMsgFormParameters().put(error.getField(), param);
                    }
                }
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }
        return form.getMessageFormStatus().equals(IncMessageFormStatus.SERVER_VALID);
    }

    /**
     * @param imParamValidator the imParamValidator to set
     */
//    public void setImParamValidator(IncomingMessageFormParameterValidator imParamValidator) {
//        this.imParamValidator = imParamValidator;
//    }
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

    /**
     * @param paramValidators the paramValidators to set
     */
    public void setParamValidators(Map<String, List<IncomingMessageFormParameterValidator>> paramValidators) {
        this.paramValidators = paramValidators;
    }

    /**
     * @param serverErrors the serverErrors to set
     */
    public void setServerErrors(Map<Integer, String> serverErrors) {
        this.serverErrors = serverErrors;
    }
}
