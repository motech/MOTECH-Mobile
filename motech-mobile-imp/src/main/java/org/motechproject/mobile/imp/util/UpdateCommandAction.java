/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageSessionStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.model.dao.imp.IncomingMessageDAO;
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
    public synchronized IncomingMessageResponse execute(IncomingMessage message, String requesterPhone, MotechContext context) {
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
        form.setIncomingMsgFormParameters(new HashMap<String,IncomingMessageFormParameter>());

        for (Entry<String,IncomingMessageFormParameter> entry : oldForm.getIncomingMsgFormParameters().entrySet()) {
            if (entry.getValue().getMessageFormParamStatus().equals(IncMessageFormParameterStatus.VALID)) {
                form.getIncomingMsgFormParameters().put(entry.getKey(), entry.getValue());
            }
        }

        form.getIncomingMsgFormParameters().putAll(parser.getParams(message.getContent()));

        tx = ((Session) formDao.getDBSession().getSession()).beginTransaction();
        sessionDao.save(form);
        tx.commit();

        IncomingMessageResponse response = coreManager.createIncomingMessageResponse();
        response.setDateCreated(new Date());
        response.setIncomingMessage(message);

        
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