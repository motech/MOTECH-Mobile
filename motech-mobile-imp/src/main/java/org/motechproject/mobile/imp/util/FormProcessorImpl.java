package org.motechproject.mobile.imp.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import org.motechproject.mobile.omi.manager.MessageFormatter;
import org.motechproject.mobile.omi.manager.OMIManager;
import org.motechproject.ws.server.RegistrarService;
import org.motechproject.ws.server.ValidationError;
import org.motechproject.ws.server.ValidationException;

/**
 *
 * @author user
 */
public class FormProcessorImpl implements FormProcessor {
    private String dateFormat;
    private RegistrarService regWS;
    private OMIManager omiManager;
    private CoreManager coreManager;
    private Map<Integer, String> serverErrors;
    private Map<String, MethodSignature> serviceMethods;

    private static Logger logger = Logger.getLogger(FormProcessorImpl.class);

    public String processForm(IncomingMessageForm form) {
        Object result = null;
        MethodSignature mSig;
        String formattedResult = "";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
        dFormat.setLenient(true);

        if (serviceMethods.containsKey(form.getIncomingMsgFormDefinition().getFormCode().toUpperCase())) {
            mSig = serviceMethods.get(form.getIncomingMsgFormDefinition().getFormCode().toUpperCase());
        } else {
            return form.getMessageFormStatus().toString();
        }

        String methodName = mSig.getMethodName();
        Class[] paramTypes = new Class[mSig.getMethodParams().size()];
        Object[] paramObjs = new Object[mSig.getMethodParams().size()];

        int idx = 0;

        try {
            for (Entry<String, Class> e : mSig.getMethodParams().entrySet()) {
                paramTypes[idx] = e.getValue();
                if (form.getIncomingMsgFormParameters().containsKey(e.getKey().toLowerCase())) {
                    if (e.getValue().equals(Date.class)) {
                        paramObjs[idx] = dFormat.parse(form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue());
                    } else if (e.getValue().isEnum()) {
                        paramObjs[idx] = Enum.valueOf(e.getValue(), form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue());
                    } else if (e.getValue().equals(String.class)) {
                        paramObjs[idx] = form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue();
                    } else if(e.getValue().isArray()){
                        paramObjs[idx] = form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue().split(",");
                    }else {
                        Constructor constr = e.getValue().getConstructor(String.class);
                        paramObjs[idx] = constr.newInstance(form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue());
                    }
                } else {
                    paramObjs[idx] = null;
                }
                idx++;
            }
            Method method = regWS.getClass().getDeclaredMethod(methodName, paramTypes);
            result = method.invoke(regWS, paramObjs);
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
        } catch (NoSuchMethodException ex) {
            logger.fatal("Could not find web service method " + methodName, ex);
        } catch (SecurityException ex) {
            logger.fatal("Could not access method " + methodName + " due to SecurityException", ex);
        } catch (IllegalAccessException ex) {
            logger.fatal("Could not invoke method " + methodName + " due to IllegalAccessException", ex);
        } catch (InvocationTargetException ex) {
            if (ex.getCause().getClass().equals(ValidationException.class)) {
                parseValidationErrors(form, (ValidationException) ex.getCause());
            } else {
                logger.fatal("Could not invoke method " + methodName + " due to InvocationTargetException", ex);
            }
        } catch (Exception ex) {
            logger.error("Form could not be processed on server", ex);
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            return "An error occurred on the server";
        }

        if (mSig.getCallback() == null) {
            return (result == null) ? null : String.valueOf(result);
        }
        return executeCallback(mSig, result);
    }

    private String executeCallback(MethodSignature mSig, Object param){
        MessageFormatter formatter = omiManager.createMessageFormatter();
        String formattedResponse = "";

        try {
            Method callback = formatter.getClass().getMethod(mSig.getMethodName(), (Class[]) mSig.getMethodParams().values().toArray());
            formattedResponse = (String) callback.invoke(formatter, param);
        } catch (NoSuchMethodException ex) {
            logger.fatal("Could not find formatter method " + mSig.getMethodName(), ex);
            return "Error: could not process registrar service response";
        } catch (SecurityException ex) {
            logger.fatal("Could not access formatter method " + mSig.getMethodName() + " due to SecurityException", ex);
            return "Error: could not process registrar service response";
        } catch (IllegalAccessException ex) {
            logger.fatal("Could not invoke formatter method " + mSig.getMethodName() + " due to IllegalAccessException", ex);
            return "Error: could not process registrar service response";
        } catch (InvocationTargetException ex) {
            logger.fatal("Could not invoke formatter method " + mSig.getMethodName() + " due to InvocationTargetException", ex);
            return "Error: could not process registrar service response";
        } catch (Exception ex) {
            logger.error("Could not format webservice response", ex);
            return "Error: could not process registrar service response";
        }
        return formattedResponse;
    }

    public void parseValidationErrors(IncomingMessageForm form, ValidationException ex) {
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
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * @param regWS the regWS to set
     */
    public void setRegWS(RegistrarService regWS) {
        this.regWS = regWS;
    }

    /**
     * @param omiManager the omiManager to set
     */
    public void setOmiManager(OMIManager omiManager) {
        this.omiManager = omiManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    /**
     * @param serverErrors the serverErrors to set
     */
    public void setServerErrors(Map<Integer, String> serverErrors) {
        this.serverErrors = serverErrors;
    }

    /**
     * @param serviceMethods the serviceMethods to set
     */
    public void setServiceMethods(Map<String, MethodSignature> serviceMethods) {
        this.serviceMethods = serviceMethods;
    }
}