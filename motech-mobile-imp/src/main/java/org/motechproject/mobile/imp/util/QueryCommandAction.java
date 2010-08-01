/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormDAO;
import org.motechproject.mobile.model.dao.imp.IncomingMessageSessionDAO;
import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncMessageResponseStatus;
import org.motechproject.mobile.core.model.IncMessageStatus;
import org.motechproject.mobile.core.model.IncomingMessage;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import org.motechproject.mobile.core.model.IncomingMessageResponse;
import org.motechproject.mobile.core.model.IncomingMessageSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.motechproject.mobile.core.model.IncMessageSessionStatus;
import org.motechproject.mobile.core.model.IncomingMessageFormDefinition;
import org.motechproject.mobile.model.dao.imp.IncomingMessageResponseDAO;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Resumes processing of an IncomingMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
@TransactionConfiguration
@Transactional
public class QueryCommandAction implements CommandAction {

    private String senderFieldName;
    private CoreManager coreManager;
    private FormProcessorImpl formProcessor;
    private IncomingMessageParser parser;
    private IncomingMessageFormValidator formValidator;
    private static Logger logger = Logger.getLogger(QueryCommandAction.class);

    /**
     *
     * @see CommandAction.execute
     */
    public IncomingMessageResponse execute(IncomingMessage message, String requesterPhone) {
        IncomingMessageResponse response;
        String formattedResponse = "";

        logger.info("Initializing session");
        IncomingMessageSession imSession = initializeSession(message, requesterPhone);

        logger.info("Generating form");
        IncomingMessageForm form = initializeForm(message, imSession.getFormCode());
        if (form == null) {
            response = coreManager.createIncomingMessageResponse();
            response.setContent("Errors: Unknown Form!\n\n'Type' parameter missing or invalid.");
            response.setIncomingMessage(message);
            response.setDateCreated(new Date());
            response.setMessageResponseStatus(IncMessageResponseStatus.SENT);
        } else {
            logger.info("Validating form");
            IncMessageFormStatus result = formValidator.validate(form, requesterPhone);

            if (result == IncMessageFormStatus.VALID) {
                formattedResponse = formProcessor.processForm(form);
            }

            message.setIncomingMessageForm(form);

            response = prepareResponse(message, formattedResponse);
            response.setMessageResponseStatus(IncMessageResponseStatus.SENT);

            if (message.getIncomingMessageForm().getIncomingMsgFormParameters().containsKey(senderFieldName)) {
                imSession.setRequesterPhone(message.getIncomingMessageForm().getIncomingMsgFormParameters().get(senderFieldName).getValue());
            }
        }
        logger.info("Saving request");
        message.setIncomingMessageResponse(response);
        message.setMessageStatus(IncMessageStatus.PROCESSED);
        message.setLastModified(new Date());

        imSession.setDateEnded(new Date());
        imSession.setMessageSessionStatus(IncMessageSessionStatus.ENDED);

        IncomingMessageSessionDAO sessionDao = coreManager.createIncomingMessageSessionDAO();

        try {
            sessionDao.save(imSession);
        } catch (Exception ex) {
            logger.error("Error finalizing incoming message session", ex);
        }
        return response;
    }

    /**
     * Initializes a request session conversation
     * @param message Incoming message
     * @param requesterPhone phone number of requester
     * @param context the context of the request
     * @return the initialized session
     */
    public IncomingMessageSession initializeSession(IncomingMessage message, String requesterPhone) {
        String formCode = parser.getFormCode(message.getContent());

        IncomingMessageSession imSession = coreManager.createIncomingMessageSession();
        imSession.setFormCode(formCode);
        imSession.setRequesterPhone(requesterPhone);
        imSession.setMessageSessionStatus(IncMessageSessionStatus.STARTED);
        imSession.setDateStarted(new Date());
        imSession.setLastActivity(new Date());
        imSession.addIncomingMessage(message);

        IncomingMessageSessionDAO sessionDao = coreManager.createIncomingMessageSessionDAO();

        try {

            sessionDao.save(imSession);

        } catch (Exception ex) {
            logger.error("Error initializing incoming message session", ex);

        }

        return imSession;
    }

    /**
     * Initializes a request form
     * @param message the request message
     * @param formCode the type of form
     * @param context the context of the request
     * @return
     */
    public IncomingMessageForm initializeForm(IncomingMessage message, String formCode) {
        IncomingMessageFormDefinition formDefn = coreManager.createIncomingMessageFormDefinitionDAO().getByCode(formCode);

        if (formDefn == null) {
            return null;
        }

        IncomingMessageForm form = coreManager.createIncomingMessageForm();
        form.setIncomingMsgFormDefinition(formDefn);
        form.setMessageFormStatus(IncMessageFormStatus.NEW);
        form.setDateCreated(new Date());
        form.setIncomingMsgFormParameters(new HashMap<String, IncomingMessageFormParameter>());
        form.getIncomingMsgFormParameters().putAll(parser.getParams(message.getContent()));

        IncomingMessageFormDAO formDao = coreManager.createIncomingMessageFormDAO();

        try {

            formDao.save(form);

        } catch (Exception ex) {
            logger.error("Error initializing form", ex);

        }

        return form;
    }

    /**
     * Prepares a response to a request message
     * @param message the message to respond to
     * @return the response to the message
     */
    public IncomingMessageResponse prepareResponse(IncomingMessage message, String formattedResponse) {
        IncomingMessageForm form = message.getIncomingMessageForm();

        IncomingMessageResponse response = coreManager.createIncomingMessageResponse();
        response.setDateCreated(new Date());
        response.setIncomingMessage(message);

        if (form == null) {
            response.setContent("Error: Invalid request");
            return response;
        }

        if (form.getMessageFormStatus().equals(IncMessageFormStatus.SERVER_VALID)) {
            response.setContent(formattedResponse);
        } else {
            String responseText = "Errors:";
            for (Entry<String, IncomingMessageFormParameter> entry : form.getIncomingMsgFormParameters().entrySet()) {
                if (entry.getValue().getMessageFormParamStatus().equals(IncMessageFormParameterStatus.INVALID) || entry.getValue().getMessageFormParamStatus().equals(IncMessageFormParameterStatus.SERVER_INVALID)) {
                    responseText += '\n' + entry.getValue().getName() + "=" + entry.getValue().getErrText();
                }
            }
            for (String error : form.getErrors()) {
                responseText += '\n' + error;
            }
            if (responseText.equals("Errors:")) {
                responseText = "An unexpected error occurred! Please try again.";
            }
            response.setContent(responseText);
        }
        response.setMessageResponseStatus(IncMessageResponseStatus.SAVED);

        IncomingMessageResponseDAO responseDao = coreManager.createIncomingMessageResponseDAO();

        try {

            responseDao.save(response);

        } catch (Exception ex) {
            logger.error("Error initializing form", ex);

        }

        return response;
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
     * @return the parser
     */
    public IncomingMessageParser getParser() {
        return parser;
    }

    /**
     * @param parser the parser to set
     */
    public void setParser(IncomingMessageParser parser) {
        this.parser = parser;
    }

    /**
     * @return the formValidator
     */
    public IncomingMessageFormValidator getFormValidator() {
        return formValidator;
    }

    /**
     * @param formValidator the formValidator to set
     */
    public void setFormValidator(IncomingMessageFormValidator formValidator) {
        this.formValidator = formValidator;
    }

    /**
     * @param senderFieldName the senderFieldName to set
     */
    public void setSenderFieldName(String senderFieldName) {
        this.senderFieldName = senderFieldName;
    }

    /**
     * @param formProcessor the formProcessor to set
     */
    public void setFormProcessor(FormProcessorImpl formProcessor) {
        this.formProcessor = formProcessor;
    }
}