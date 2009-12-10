/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageSessionDAO;
import com.dreamoval.motech.model.imp.IncMessageFormParameterStatus;
import com.dreamoval.motech.model.imp.IncMessageFormStatus;
import com.dreamoval.motech.model.imp.IncMessageResponseStatus;
import com.dreamoval.motech.core.model.IncMessageSessionStatus;
import com.dreamoval.motech.model.imp.IncMessageStatus;
import com.dreamoval.motech.model.imp.IncomingMessage;
import com.dreamoval.motech.model.imp.IncomingMessageForm;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;
import com.dreamoval.motech.model.imp.IncomingMessageResponse;
import com.dreamoval.motech.core.model.IncomingMessageSession;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Resumes processing of an IncomingMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public class UpdateCommandAction implements CommandAction {

    private CoreManager coreManager;
    private IncomingMessageParser parser;
    private IncomingMessageFormValidator formValidator;

    /**
     *
     * @see CommandAction.execute
     */
    public synchronized IncomingMessageResponse execute(IncomingMessage message, String requesterPhone) {
        MotechContext context = coreManager.createMotechContext();

        //TODO fetch current open session by requester
        IncomingMessageSession imSession = coreManager.createIncomingMessageSession();
        if (imSession == null) {
            IncomingMessageResponse response = coreManager.createIncomingMessageResponse();
            response.setDateCreated(new Date());
            response.setIncomingMessage(message);
            response.setMessageResponseStatus(IncMessageResponseStatus.SAVED);
            response.setContent("Invalid request. Please specify a command");

            message.setIncomingMessageResponse(response);
            message.setMessageStatus(IncMessageStatus.PROCESSED);
            message.setLastModified(new Date());

            IncomingMessageDAO msgDao = coreManager.createIncomingMessageDAO(context);
            Transaction tx = ((Session) msgDao.getDBSession().getSession()).beginTransaction();
            msgDao.save(message);
            tx.commit();

            return response;
        }

        imSession.setLastActivity(new Date());
        imSession.getIncomingMessages().add(message);

        IncomingMessageSessionDAO sessionDao = coreManager.createIncomingMessageSessionDAO(context);
        Transaction tx = ((Session) sessionDao.getDBSession().getSession()).beginTransaction();
        sessionDao.save(imSession);
        tx.commit();

        IncomingMessageFormDAO formDao = coreManager.createIncomingMessageFormDAO(context);
        ///TODO fetch last form for the session
        IncomingMessageForm oldForm = coreManager.createIncomingMessageForm();

        IncomingMessageForm form = coreManager.createIncomingMessageForm();
        form.setIncomingMsgFormDefinition(oldForm.getIncomingMsgFormDefinition());
        form.setMessageFormStatus(IncMessageFormStatus.NEW);
        form.setDateCreated(new Date());
        form.setIncomingMsgFormParameters(new ArrayList<IncomingMessageFormParameter>());

        for (IncomingMessageFormParameter param : oldForm.getIncomingMsgFormParameters()) {
            if (param.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.VALID)) {
                form.getIncomingMsgFormParameters().add(param);
            }
        }

        form.getIncomingMsgFormParameters().addAll(parser.getParams(message.getContent()));

        tx = ((Session) formDao.getDBSession().getSession()).beginTransaction();
        sessionDao.save(form);
        tx.commit();

        IncomingMessageResponse response = coreManager.createIncomingMessageResponse();
        response.setDateCreated(new Date());
        response.setIncomingMessage(message);

        if (getFormValidator().validate(form)) {
            response.setMessageResponseStatus(IncMessageResponseStatus.SAVED);
            response.setContent("Data saved successfully.");

            imSession.setDateEnded(new Date());
            imSession.setMessageSessionStatus(IncMessageSessionStatus.ENDED);
        } else {
            String responseText = "Errors: ";
            for (IncomingMessageFormParameter inParam : form.getIncomingMsgFormParameters()) {
                if (inParam.getMessageFormParamStatus().equals(IncMessageFormParameterStatus.INVALID)) {
                    responseText += inParam.getErrText() + ',';
                }
            }
            response.setMessageResponseStatus(IncMessageResponseStatus.SAVED);
            response.setContent(responseText);
        }
        message.setIncomingMessageResponse(response);
        message.setIncomingMessageForm(form);

        message.setMessageStatus(IncMessageStatus.PROCESSED);
        message.setLastModified(new Date());

        tx = ((Session) sessionDao.getDBSession().getSession()).beginTransaction();
        sessionDao.save(imSession);
        tx.commit();

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
}