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
import org.motechproject.ws.BirthOutcome;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.DeliveredBy;
import org.motechproject.ws.Gender;
import org.motechproject.ws.HIVStatus;
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
            } else {
                IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
                param.setMessageFormParamStatus(IncMessageFormParameterStatus.VALID);
                param.setIncomingMsgFormParamDefinition(paramDef);
                param.setName(paramDef.getName());
                param.setDateCreated(new Date());
                param.setIncomingMsgForm(form);
                param.setValue(null);

                if (paramDef.isRequired()) {
                    param.setErrCode(0);
                    param.setErrText("missing");
                    param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);
                    form.setMessageFormStatus(IncMessageFormStatus.INVALID);
                }
                
                form.setLastModified(new Date());
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
                Integer diag2 = form.getIncomingMsgFormParameters().containsKey("secondarydiagnosis") ? Integer.valueOf(form.getIncomingMsgFormParameters().get("secondaryDiagnosis").getValue()) : null;

                regWS.recordGeneralVisit(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("serialno").getValue(), Gender.valueOf(form.getIncomingMsgFormParameters().get("sex").getValue()), dFormat.parse(form.getIncomingMsgFormParameters().get("dob").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("insured").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("newcase").getValue()), Integer.valueOf(form.getIncomingMsgFormParameters().get("diagnosis").getValue()), diag2, Boolean.valueOf(form.getIncomingMsgFormParameters().get("referral").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        } else if (code.equalsIgnoreCase("PregnancyStop")) {
            try {
                regWS.stopPregnancyProgram(form.getIncomingMsgFormParameters().get("chpsid").getValue(), form.getIncomingMsgFormParameters().get("patientregnum").getValue());
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        } else if (code.equalsIgnoreCase("RegisterChildU5")) {
            try {
                regWS.registerChild(form.getIncomingMsgFormParameters().get("chpsid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("regdate").getValue()), form.getIncomingMsgFormParameters().get("motherregnum").getValue(), form.getIncomingMsgFormParameters().get("childregnum").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("dob").getValue()), Gender.valueOf(form.getIncomingMsgFormParameters().get("childgender").getValue()), form.getIncomingMsgFormParameters().get("childfirstname").getValue(), form.getIncomingMsgFormParameters().get("nhis").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("nhisexp").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        } else if (code.equalsIgnoreCase("EditPatient")) {
            try {
                String priPhone = form.getIncomingMsgFormParameters().containsKey("primaryphone") ? form.getIncomingMsgFormParameters().get("primaryphone").getValue() : null;
                ContactNumberType priNumType = form.getIncomingMsgFormParameters().containsKey("primaryphonetype") ? ContactNumberType.valueOf(form.getIncomingMsgFormParameters().get("primaryphonetype").getValue()) : null;
                String secPhone = form.getIncomingMsgFormParameters().containsKey("secondaryphone") ? form.getIncomingMsgFormParameters().get("secondaryphone").getValue() : null;
                ContactNumberType secNumType = form.getIncomingMsgFormParameters().containsKey("secondaryphonetype") ? ContactNumberType.valueOf(form.getIncomingMsgFormParameters().get("secondaryphonetype").getValue()) : null;
                String nhis = form.getIncomingMsgFormParameters().containsKey("nhis") ? form.getIncomingMsgFormParameters().get("nhis").getValue() : null;
                Date nhisExp = form.getIncomingMsgFormParameters().containsKey("nhisexp") ? dFormat.parse(form.getIncomingMsgFormParameters().get("nhisexp").getValue()) : null;
                
                regWS.editPatient(form.getIncomingMsgFormParameters().get("chpsid").getValue(), form.getIncomingMsgFormParameters().get("patientregnum").getValue(), priPhone, priNumType, secPhone, secNumType, nhis, nhisExp);
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("ANC")) {
            try {
                regWS.recordMotherANCVisit(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("motechid").getValue(), Integer.parseInt(form.getIncomingMsgFormParameters().get("visitno").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("ttdose").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("iptdose").getValue()), Boolean.valueOf(form.getIncomingMsgFormParameters().get("itnuse").getValue()), HIVStatus.valueOf(form.getIncomingMsgFormParameters().get("hivstatus").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("Abortion")) {
            try {
                regWS.recordPregnancyTermination(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("motechid").getValue(), Integer.parseInt(form.getIncomingMsgFormParameters().get("abortiontype").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("complications").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("Delivery")) {
            try {
                Integer cause = form.getIncomingMsgFormParameters().containsKey("cause") ? Integer.parseInt(form.getIncomingMsgFormParameters().get("cause").getValue()) : null;
                String c1Name = form.getIncomingMsgFormParameters().containsKey("c1name") ? form.getIncomingMsgFormParameters().get("c1name").getValue() : null;
                BirthOutcome c2BO = form.getIncomingMsgFormParameters().containsKey("c2birthoutcome") ? BirthOutcome.valueOf(form.getIncomingMsgFormParameters().get("c2birthoutcome").getValue()) : null;
                String c2MotechId = form.getIncomingMsgFormParameters().containsKey("c2motechid") ? form.getIncomingMsgFormParameters().get("c2motechid").getValue() : null;
                Gender c2Sex = form.getIncomingMsgFormParameters().containsKey("c2sex") ? Gender.valueOf(form.getIncomingMsgFormParameters().get("c2sex").getValue()) : null;
                String c2Name = form.getIncomingMsgFormParameters().containsKey("c2name") ? form.getIncomingMsgFormParameters().get("c2name").getValue() : null;
                Boolean c2OPV = form.getIncomingMsgFormParameters().containsKey("c2opv") ? Boolean.valueOf(form.getIncomingMsgFormParameters().get("c2opv").getValue()) : null;
                Boolean c2BCG = form.getIncomingMsgFormParameters().containsKey("c2bcg") ? Boolean.valueOf(form.getIncomingMsgFormParameters().get("c2bcg").getValue()) : null;
                
                regWS.recordPregnancyDelivery(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("dod").getValue()), form.getIncomingMsgFormParameters().get("motechid").getValue(), Integer.parseInt(form.getIncomingMsgFormParameters().get("mod").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("ood").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("location").getValue()), DeliveredBy.valueOf(form.getIncomingMsgFormParameters().get("deliveredBy").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("maternaldeath").getValue()), cause, BirthOutcome.valueOf(form.getIncomingMsgFormParameters().get("c1birthoutcome").getValue()), form.getIncomingMsgFormParameters().get("c1motechid").getValue(), Gender.valueOf(form.getIncomingMsgFormParameters().get("c1sex").getValue()), c1Name, Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("c1opv").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("c1bcg").getValue()), c2BO, c2MotechId, c2Sex, c2Name, c2OPV, c2BCG);
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("PPC")) {
            try {
                regWS.recordMotherPPCVisit(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("motechid").getValue(), Integer.parseInt(form.getIncomingMsgFormParameters().get("visitno").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("vita").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("ttdose").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("Death")) {
            try {
                Integer cause = form.getIncomingMsgFormParameters().containsKey("cause") ? Integer.valueOf(form.getIncomingMsgFormParameters().get("cause").getValue()) : null;

                regWS.recordDeath(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("motechid").getValue(), cause);
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("Child")) {
            try {
                regWS.recordChildPNCVisit(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("motechid").getValue(), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("bcg").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("opvdose").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("pentadose").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("yellowfever").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("csm").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("ipti").getValue()), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("vita").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("ChildOPD")) {
            Integer secondDiag = (form.getIncomingMsgFormParameters().get("secondaryDiagnosis") != null) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("serialnumber").getValue()) : null;
            try {
                regWS.recordChildVisit(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("serialnumber").getValue(), form.getIncomingMsgFormParameters().get("motechid").getValue(), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("casestatus").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("diagnosis").getValue()), secondDiag, Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("referral").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("MotherOPD")) {
            Integer secondDiag = (form.getIncomingMsgFormParameters().get("secondaryDiagnosis") != null) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("serialnumber").getValue()) : null;
            try {
                regWS.recordChildVisit(form.getIncomingMsgFormParameters().get("facilityid").getValue(), dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()), form.getIncomingMsgFormParameters().get("serialnumber").getValue(), form.getIncomingMsgFormParameters().get("motechid").getValue(), Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("casestatus").getValue()), Integer.parseInt(form.getIncomingMsgFormParameters().get("diagnosis").getValue()), secondDiag, Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("referral").getValue()));
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }
        return form.getMessageFormStatus().equals(IncMessageFormStatus.SERVER_VALID);
    }

    public void parseValidationErrors(IncomingMessageForm form, ValidationException ex){
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
