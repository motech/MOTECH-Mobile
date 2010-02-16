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
        dFormat.setLenient(true);

        if (!form.getMessageFormStatus().equals(IncMessageFormStatus.VALID)) {
            return false;
        }

        String code = form.getIncomingMsgFormDefinition().getFormCode();

        if (code.equalsIgnoreCase("GeneralOPD")) {
            try {
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date date = form.getIncomingMsgFormParameters().containsKey("date") ? dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue()) : null;
                String serialNo = form.getIncomingMsgFormParameters().get("serialno").getValue();
                Gender gender = Gender.valueOf(form.getIncomingMsgFormParameters().get("sex").getValue());
                Date birthDate = dFormat.parse(form.getIncomingMsgFormParameters().get("dob").getValue());
                Boolean insured = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("insured").getValue());
                Boolean newCase = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("newcase").getValue());
                Integer diag = Integer.valueOf(form.getIncomingMsgFormParameters().get("diagnosis").getValue());
                Integer diag2 = form.getIncomingMsgFormParameters().containsKey("secondarydiagnosis") ? Integer.valueOf(form.getIncomingMsgFormParameters().get("secondarydiagnosis").getValue()) : null;
                Boolean referral = Boolean.valueOf(form.getIncomingMsgFormParameters().get("referral").getValue());

                regWS.recordGeneralVisit(facilityId, date, serialNo, gender, birthDate, insured, newCase, diag, diag2, referral);
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
                String chpsId = form.getIncomingMsgFormParameters().get("chpsid").getValue();
                String patientReg = form.getIncomingMsgFormParameters().get("patientregnum").getValue();

                regWS.stopPregnancyProgram(chpsId, patientReg);
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        } else if (code.equalsIgnoreCase("RegisterChildU5")) {
            try {
                String chpsId = form.getIncomingMsgFormParameters().get("chpsid").getValue();
                Date regDate = dFormat.parse(form.getIncomingMsgFormParameters().get("regdate").getValue());
                String motherReg = form.getIncomingMsgFormParameters().get("motherregnum").getValue();
                String childReg = form.getIncomingMsgFormParameters().get("childregnum").getValue();
                Date birthDate = dFormat.parse(form.getIncomingMsgFormParameters().get("dob").getValue());
                Gender gender = Gender.valueOf(form.getIncomingMsgFormParameters().get("childgender").getValue());
                String childFName = form.getIncomingMsgFormParameters().get("childfirstname").getValue();
                String nhis = form.getIncomingMsgFormParameters().get("nhis").getValue();
                Date nhisExp = dFormat.parse(form.getIncomingMsgFormParameters().get("nhisexp").getValue());

                regWS.registerChild(chpsId, regDate, motherReg, childReg, birthDate, gender, childFName, nhis, nhisExp);
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
                String chpsId = form.getIncomingMsgFormParameters().get("chpsid").getValue();
                String patientReg = form.getIncomingMsgFormParameters().get("patientregnum").getValue();
                String priPhone = form.getIncomingMsgFormParameters().containsKey("primaryphone") ? form.getIncomingMsgFormParameters().get("primaryphone").getValue() : null;
                ContactNumberType priNumType = form.getIncomingMsgFormParameters().containsKey("primaryphonetype") ? ContactNumberType.valueOf(form.getIncomingMsgFormParameters().get("primaryphonetype").getValue()) : null;
                String secPhone = form.getIncomingMsgFormParameters().containsKey("secondaryphone") ? form.getIncomingMsgFormParameters().get("secondaryphone").getValue() : null;
                ContactNumberType secNumType = form.getIncomingMsgFormParameters().containsKey("secondaryphonetype") ? ContactNumberType.valueOf(form.getIncomingMsgFormParameters().get("secondaryphonetype").getValue()) : null;
                String nhis = form.getIncomingMsgFormParameters().containsKey("nhis") ? form.getIncomingMsgFormParameters().get("nhis").getValue() : null;
                Date nhisExp = form.getIncomingMsgFormParameters().containsKey("nhisexp") ? dFormat.parse(form.getIncomingMsgFormParameters().get("nhisexp").getValue()) : null;
                
                regWS.editPatient(chpsId, patientReg, priPhone, priNumType, secPhone, secNumType, nhis, nhisExp);
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
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date date = dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue());
                String motechId = form.getIncomingMsgFormParameters().get("motechid").getValue();
                Integer visitNo = Integer.parseInt(form.getIncomingMsgFormParameters().get("visitno").getValue());
                Integer ttDose = (!form.getIncomingMsgFormParameters().get("ttdose").getValue().isEmpty()) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("ttdose").getValue()) : null;
                Integer iptDose = (!form.getIncomingMsgFormParameters().get("iptdose").getValue().isEmpty()) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("iptdose").getValue()) : null;
                Boolean itn = Boolean.valueOf(form.getIncomingMsgFormParameters().get("itn").getValue());
                HIVStatus hivRes = HIVStatus.valueOf(form.getIncomingMsgFormParameters().get("hivresult").getValue());

                regWS.recordMotherANCVisit(facilityId, date, motechId, visitNo, ttDose, iptDose, itn, hivRes);
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
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date date = dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue());
                String motechId = form.getIncomingMsgFormParameters().get("motechid").getValue();
                Integer abortionType = Integer.parseInt(form.getIncomingMsgFormParameters().get("abortiontype").getValue());
                Integer complications = Integer.parseInt(form.getIncomingMsgFormParameters().get("complications").getValue());

                regWS.recordPregnancyTermination(facilityId, date, motechId, abortionType, complications);
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
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date delivDate = dFormat.parse(form.getIncomingMsgFormParameters().get("dod").getValue());
                String motechId = form.getIncomingMsgFormParameters().get("motechid").getValue();
                Integer mod = Integer.parseInt(form.getIncomingMsgFormParameters().get("mod").getValue());
                Integer ood = Integer.parseInt(form.getIncomingMsgFormParameters().get("ood").getValue());
                Integer location = Integer.parseInt(form.getIncomingMsgFormParameters().get("location").getValue());
                DeliveredBy deliverer = DeliveredBy.valueOf(form.getIncomingMsgFormParameters().get("deliveredBy").getValue());
                Boolean maternalDeath = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("maternaldeath").getValue());
                Integer cause = form.getIncomingMsgFormParameters().containsKey("cause") ? Integer.parseInt(form.getIncomingMsgFormParameters().get("cause").getValue()) : null;
                BirthOutcome c1BirthOut = BirthOutcome.valueOf(form.getIncomingMsgFormParameters().get("c1birthoutcome").getValue());
                String c1Id = form.getIncomingMsgFormParameters().get("c1motechid").getValue();
                Gender c1sex = Gender.valueOf(form.getIncomingMsgFormParameters().get("c1sex").getValue());
                Boolean c1opv = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("c1opv").getValue());
                String c1Name = form.getIncomingMsgFormParameters().containsKey("c1name") ? form.getIncomingMsgFormParameters().get("c1name").getValue() : null;
                Boolean c1bcg = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("c1bcg").getValue());
                BirthOutcome c2BO = form.getIncomingMsgFormParameters().containsKey("c2birthoutcome") ? BirthOutcome.valueOf(form.getIncomingMsgFormParameters().get("c2birthoutcome").getValue()) : null;
                String c2MotechId = form.getIncomingMsgFormParameters().containsKey("c2motechid") ? form.getIncomingMsgFormParameters().get("c2motechid").getValue() : null;
                Gender c2Sex = form.getIncomingMsgFormParameters().containsKey("c2sex") ? Gender.valueOf(form.getIncomingMsgFormParameters().get("c2sex").getValue()) : null;
                String c2Name = form.getIncomingMsgFormParameters().containsKey("c2name") ? form.getIncomingMsgFormParameters().get("c2name").getValue() : null;
                Boolean c2OPV = form.getIncomingMsgFormParameters().containsKey("c2opv") ? Boolean.valueOf(form.getIncomingMsgFormParameters().get("c2opv").getValue()) : null;
                Boolean c2BCG = form.getIncomingMsgFormParameters().containsKey("c2bcg") ? Boolean.valueOf(form.getIncomingMsgFormParameters().get("c2bcg").getValue()) : null;
                
                regWS.recordPregnancyDelivery(facilityId, delivDate, motechId, mod, ood, location, deliverer, maternalDeath, cause, c1BirthOut, c1Id, c1sex, c1Name, c1opv, c1bcg, c2BO, c2MotechId, c2Sex, c2Name, c2OPV, c2BCG);
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
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date date = dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue());
                String motechId = form.getIncomingMsgFormParameters().get("motechid").getValue();
                Integer visitNo = Integer.parseInt(form.getIncomingMsgFormParameters().get("visitno").getValue());
                Boolean vitA = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("vita").getValue());
                Integer ttDose = (!form.getIncomingMsgFormParameters().get("ttdose").getValue().isEmpty()) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("ttdose").getValue()) : null;

                regWS.recordMotherPPCVisit(facilityId, date, motechId, visitNo, vitA, ttDose);
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
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date date = dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue());
                String motechId = form.getIncomingMsgFormParameters().get("motechid").getValue();
                Integer cause = form.getIncomingMsgFormParameters().containsKey("cause") ? Integer.valueOf(form.getIncomingMsgFormParameters().get("cause").getValue()) : null;

                regWS.recordDeath(facilityId, date, motechId, cause);
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
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date date = dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue());
                String motechId = form.getIncomingMsgFormParameters().get("motechid").getValue();
                Boolean bcg = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("bcg").getValue());
                Integer opv = (!form.getIncomingMsgFormParameters().get("opvdose").getValue().isEmpty()) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("opvdose").getValue()) : null;
                Integer penta = (!form.getIncomingMsgFormParameters().get("pentadose").getValue().isEmpty()) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("pentadose").getValue()) : null;
                Boolean yellowFever = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("yellowfever").getValue());
                Boolean csm = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("csm").getValue());
                Boolean ipti = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("ipti").getValue());
                Boolean vitA = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("vita").getValue());
                
                regWS.recordChildPNCVisit(facilityId, date, motechId, bcg, opv, penta, yellowFever, csm, ipti, vitA);
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("ChildOPD")) {
            try {
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date date = dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue());
                String serialNo = form.getIncomingMsgFormParameters().get("serialno").getValue();
                String motechId = form.getIncomingMsgFormParameters().get("motechid").getValue();
                Boolean caseStatus = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("casestatus").getValue());
                Integer diag = Integer.parseInt(form.getIncomingMsgFormParameters().get("diagnosis").getValue());
                Integer secondDiag = (form.getIncomingMsgFormParameters().get("secondarydiagnosis") != null) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("secondarydiagnosis").getValue()) : null;
                Boolean referral = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("referral").getValue());
            
                regWS.recordChildVisit(facilityId, date, serialNo, motechId, caseStatus, diag, secondDiag, referral);
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
            } catch (ParseException ex) {
                form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            } catch (ValidationException ex) {
                parseValidationErrors(form, ex);
            } catch (Exception ex) {
                logger.error("Server validation of form failed", ex);
            }
        }else if (code.equalsIgnoreCase("MotherOPD")) {
            try {
                String facilityId = form.getIncomingMsgFormParameters().get("facilityid").getValue();
                Date date = dFormat.parse(form.getIncomingMsgFormParameters().get("date").getValue());
                String serialNo = form.getIncomingMsgFormParameters().get("serialno").getValue();
                String motechId = form.getIncomingMsgFormParameters().get("motechid").getValue();
                Boolean caseStatus = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("casestatus").getValue());
                Integer diag = Integer.parseInt(form.getIncomingMsgFormParameters().get("diagnosis").getValue());
                Boolean referral = Boolean.parseBoolean(form.getIncomingMsgFormParameters().get("referral").getValue());
                Integer secondDiag = (form.getIncomingMsgFormParameters().get("secondarydiagnosis") != null) ? Integer.parseInt(form.getIncomingMsgFormParameters().get("secondarydiagnosis").getValue()) : null;
                
                regWS.recordChildVisit(facilityId, date, serialNo, motechId, caseStatus, diag, secondDiag, referral);
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
