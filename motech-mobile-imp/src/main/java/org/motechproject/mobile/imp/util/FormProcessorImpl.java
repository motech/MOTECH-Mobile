/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2010-11 The Trustees of Columbia University in the City of
 * New York and Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, Columbia University, or
 * their respective contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA, COLUMBIA UNIVERSITY
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION
 * USA, COLUMBIA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.motechproject.mobile.imp.util;

import org.apache.log4j.Logger;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.omi.manager.OMIManager;
import org.motechproject.mobile.omi.manager.SMSMessageFormatter;
import org.motechproject.ws.server.RegistrarService;
import org.motechproject.ws.server.ValidationException;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author user
 */
public class FormProcessorImpl implements FormProcessor {

    private String defaultDateFormat;
    private RegistrarService regWS;
    private OMIManager omiManager;
    private CoreManager coreManager;
    private Map<Integer, String> serverErrors;
    private Map<String, MethodSignature> serviceMethods;
    private static Logger logger = Logger.getLogger(FormProcessorImpl.class);

    /**
     * Submits a form to the server for processing
     *
     * @param form The form to submit
     * @return
     */
    public String processForm(IncomingMessageForm form) {
        Object result = null;
        MethodSignature methodSignature;

        if(form.getMessageFormStatus() != IncMessageFormStatus.VALID)
            return "Invalid form";

        SimpleDateFormat dFormat = new SimpleDateFormat(defaultDateFormat);
        dFormat.setLenient(true);

        if (getServiceMethods().containsKey(form.getIncomingMsgFormDefinition().getFormCode().toUpperCase())) {
            methodSignature = getServiceMethods().get(form.getIncomingMsgFormDefinition().getFormCode().toUpperCase());
        } else {
            //Server can not process this type of form. Return current form status
            return form.getMessageFormStatus().toString();
        }

        /** 
         * Using reflection to determine and execute the appropriate serve call for the form.
         * Get the name of the method and initialize arrays to hold the types and values of the arguments
         */
        String methodName = methodSignature.getMethodName();
        Class[] paramTypes = new Class[methodSignature.getMethodParams().size()];
        Object[] paramObjs = new Object[methodSignature.getMethodParams().size()];

        int idx = 0;

        try {
            for (Entry<String, Class> e : methodSignature.getMethodParams().entrySet()) {
                /** Entry key is name of argument, value is class type */
                logger.debug("Param: "+e.getKey()+" Class:"+e.getValue());
                paramTypes[idx] = e.getValue();

                if (form.getIncomingMsgFormParameters().containsKey(e.getKey().toLowerCase())) {//Form field was passed
                    if(form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue().isEmpty()){//Field empty, pass null
                        paramObjs[idx] = null;
                    } else if (e.getValue().equals(Date.class)) {//Argument is a date, parse date string
                        paramObjs[idx] = dFormat.parse(form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue());
                    } else if (e.getValue().isEnum()) {//Argument is an enumeration, convert to appropriate Enum type
                        paramObjs[idx] = Enum.valueOf(e.getValue(), form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue());
                    } else if (e.getValue().equals(String.class)) {
                        paramObjs[idx] = form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue();
                    } else if (e.getValue().isArray()) {//Argument is an array. Process further
                        String[] a = form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue().split(" ");
                        //Get array element type
                        Class baseType = e.getValue().getComponentType();
                        Object arrayObj = Array.newInstance(baseType, a.length);
                        for (int i = 0; i < a.length; i++) {
                            /**
                             * Assumes the base type of any array passed will contain a constructor
                             * which accepts a single string parameter (expecting primitives or custom types)
                             */
                            Constructor constr = baseType.getConstructor(String.class);
                            Object val = constr.newInstance(a[i]);
                            Array.set(arrayObj, i, val);
                        }

                        paramObjs[idx] = arrayObj;
                    } else {
                        /**
                         * For types other than String, Array, Enum and Date, A constructor
                         * which accepts a single string parameter is expected. This is default
                         * for primitive types. Custom types MUST be defined with a matching constructor.
                         */
                        Constructor constr = e.getValue().getConstructor(String.class);
                        paramObjs[idx] = constr.newInstance(form.getIncomingMsgFormParameters().get(e.getKey().toLowerCase()).getValue());
                    }
                } else {//Form field was not supplied. Pass null for argument value.
                    paramObjs[idx] = null;
                }
                idx++;
            }
            //Initialize method
            Method method = regWS.getClass().getDeclaredMethod(methodName, paramTypes);
            result = method.invoke(regWS, paramObjs);
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
        } catch (NoSuchMethodException ex) {//Method does not exist
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            logger.fatal("Could not find web service method " + methodName, ex);
        } catch (SecurityException ex) {//Method cannot be accessed (insufficient permissions)
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            logger.fatal("Could not access method " + methodName + " due to SecurityException", ex);
        } catch (IllegalAccessException ex) {//Method cannot be accessed (private or protected)
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            logger.fatal("Could not invoke method " + methodName + " due to IllegalAccessException", ex);
        } catch (InvocationTargetException ex) {//Method threw exception
        	form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            if (ex.getCause().getClass().equals(ValidationException.class)) {
                parseValidationErrors(form, (ValidationException) ex.getCause());
            } else {
                logger.fatal("Could not invoke method " + methodName + " due to InvocationTargetException", ex);
            }
        } catch (Exception ex) {//Other exception
            logger.error("Form could not be processed on server", ex);
            form.setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
            return "An error occurred on the server";
        }

        if (methodSignature.getCallback() == null) {//Method result requires no further processing. return
            return (result == null) ? null : String.valueOf(result);
        }
        //Process method result
        return executeCallback(methodSignature.getCallback(), result);
    }

    /**
     * Executes a method for a response received from the server after processing a form
     * Assumes the method accepts a single parameter, being the response from the server call
     *
     * @param methodSignature The signature of the method
     * @param param The parameter required by the method
     * @return
     */
    private String executeCallback(MethodSignature methodSignature, Object param) {
        String formattedResponse = "No matching records found";

        if(param == null)
            return formattedResponse;

        SMSMessageFormatter formatter = omiManager.createMessageFormatter();
        
        try {
            int idx = 0;
            Class[] paramTypes = new Class[methodSignature.getMethodParams().size()];

            for (Entry<String, Class> entry : methodSignature.getMethodParams().entrySet()) {
                paramTypes[idx] = entry.getValue();
                idx++;
            }

            Method callback = formatter.getClass().getMethod(methodSignature.getMethodName(), paramTypes);
            formattedResponse = (String) callback.invoke(formatter, param);
        } catch (NoSuchMethodException ex) {
            logger.fatal("Could not find formatter method " + methodSignature.getMethodName(), ex);
            return "Error: could not process registrar service response";
        } catch (SecurityException ex) {
            logger.fatal("Could not access formatter method " + methodSignature.getMethodName() + " due to SecurityException", ex);
            return "Error: could not process registrar service response";
        } catch (IllegalAccessException ex) {
            logger.fatal("Could not invoke formatter method " + methodSignature.getMethodName() + " due to IllegalAccessException", ex);
            return "Error: could not process registrar service response";
        } catch (InvocationTargetException ex) {
            logger.fatal("Could not invoke formatter method " + methodSignature.getMethodName() + " due to InvocationTargetException", ex);
            return "Error: could not process registrar service response";
        } catch (Exception ex) {
            logger.error("Could not format webservice response", ex);
            return "Error: could not process registrar service response";
        }
        return formattedResponse;
    }

    /**
     * Parses validation errors thrown as a {@link org.motechproject.ws.server.ValidationException ValidationException}
     * by the server during processing of form
     *
     * @param form the form being processed
     * @param ex the ValidationException thrown by the server
     */
    public void parseValidationErrors(IncomingMessageForm form, ValidationException ex) {
        List<String> errors = ex.getFaultInfo().getErrors();
        form.setErrors(errors);
    }

    /**
     * @param defaultDateFormat the defaultDateFormat to set
     */
    public void setDefaultDateFormat(String defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
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
     * @return the serviceMethods
     */
    public Map<String, MethodSignature> getServiceMethods() {
        return serviceMethods;
    }

    /**
     * @param serviceMethods the serviceMethods to set
     */
    public void setServiceMethods(Map<String, MethodSignature> serviceMethods) {
        this.serviceMethods = serviceMethods;
    }
}
