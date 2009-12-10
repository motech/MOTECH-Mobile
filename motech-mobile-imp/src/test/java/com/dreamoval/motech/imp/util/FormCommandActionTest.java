/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.dao.DBSession;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncomingMessageSession;
import com.dreamoval.motech.core.model.IncomingMessageSessionImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import com.dreamoval.motech.model.dao.imp.IncomingMessageDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDefinitionDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageSessionDAO;
import com.dreamoval.motech.model.imp.IncMessageFormParameterStatus;
import com.dreamoval.motech.model.imp.IncMessageFormStatus;
import com.dreamoval.motech.model.imp.IncomingMessage;
import com.dreamoval.motech.model.imp.IncomingMessageForm;
import com.dreamoval.motech.model.imp.IncomingMessageFormDefinitionImpl;
import com.dreamoval.motech.model.imp.IncomingMessageFormImpl;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameterImpl;
import com.dreamoval.motech.model.imp.IncomingMessageImpl;
import com.dreamoval.motech.model.imp.IncomingMessageResponse;
import com.dreamoval.motech.model.imp.IncomingMessageResponseImpl;
import java.util.ArrayList;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 *
 * @author user
 */
public class FormCommandActionTest {

    CoreManager mockCore;
    DBSession mockSession;
    Transaction mockTrans;
    FormCommandAction instance;
    IncomingMessageDAO mockMsgDao;
    IncomingMessageParser mockParser;
    IncomingMessageFormDAO mockFormDao;
    IncomingMessageSessionDAO mockSessDao;
    IncomingMessageFormValidator mockValidator;
    IncomingMessageFormDefinitionDAO mockFormDefDao;

    public FormCommandActionTest() {
    }

    @Before
    public void setUp() {
        mockCore = createMock(CoreManager.class);
        mockParser = createMock(IncomingMessageParser.class);
        mockSession = createMock(DBSession.class);
        mockTrans = createMock(Transaction.class);

        instance = new FormCommandAction();
        instance.setParser(mockParser);
        instance.setCoreManager(mockCore);
    }

    /**
     * Test of execute method, of class FormCommandAction.
     */
   @Test
    public void testExecute() {
        System.out.println("execute");
        IncomingMessage message = new IncomingMessageImpl();
        String requesterPhone = "000000000000";
        MotechContext context = new MotechContextImpl();
        String expResult = "Errors:";
        IncomingMessageFormImpl msgForm = new IncomingMessageFormImpl();
        msgForm.setMessageFormStatus(IncMessageFormStatus.VALID);

        mockFormDao = createMock(IncomingMessageFormDAO.class);
        mockSessDao = createMock(IncomingMessageSessionDAO.class);
        mockValidator = createMock(IncomingMessageFormValidator.class);
        mockFormDefDao = createMock(IncomingMessageFormDefinitionDAO.class);
        instance.setFormValidator(mockValidator);

        expect(
                mockCore.createMotechContext()
                ).andReturn(context);

        //Initialize Session
        expect(
                mockCore.createIncomingMessageSession()
                ).andReturn(new IncomingMessageSessionImpl());
        expect(
                mockParser.getFormCode((String) anyObject())
                ).andReturn("GENERAL");
        expect(
                mockCore.createIncomingMessageSessionDAO((MotechContext)anyObject())
                ).andReturn(mockSessDao);
        expect(
                mockSessDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);

        mockTrans.begin();
        expectLastCall();

        expect(
                mockSessDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

        mockTrans.commit();
        expectLastCall();


        //Generate form
        expect(
                mockCore.createIncomingMessageFormDefinitionDAO((MotechContext) anyObject())
                ).andReturn(mockFormDefDao);
        expect(
                mockFormDefDao.getByCode((String) anyObject())
                ).andReturn(new IncomingMessageFormDefinitionImpl());
        expect(
                mockCore.createIncomingMessageForm()
                ).andReturn(msgForm);
        expect(
                mockParser.getParams((String)anyObject())
                ).andReturn(new ArrayList<IncomingMessageFormParameter>());
        expect(
                mockCore.createIncomingMessageFormDAO((MotechContext)anyObject())
                ).andReturn(mockFormDao);
        expect(
                mockFormDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);

        mockTrans.begin();
        expectLastCall();

        expect(
                mockFormDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

        mockTrans.commit();
        expectLastCall();

        //Validate form
        expect(
                mockValidator.validate((IncomingMessageForm)anyObject())
                ).andReturn(Boolean.TRUE);

        //Prepare response
        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());

        
        //Save request
        expect(
                mockCore.createIncomingMessageSessionDAO((MotechContext)anyObject())
                ).andReturn(mockSessDao);
        expect(
                mockSessDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);

        mockTrans.begin();
        expectLastCall();

        expect(
                mockSessDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

        mockTrans.commit();
        expectLastCall();
        

        replay(mockCore,mockParser,mockSessDao,mockSession,mockTrans,mockFormDefDao,mockFormDao, mockValidator);
        IncomingMessageResponse result = instance.execute(message, requesterPhone);
        verify(mockCore,mockParser,mockSessDao,mockSession,mockTrans,mockFormDefDao,mockFormDao, mockValidator);

        assertNotNull(result);
        assertEquals(expResult, result.getContent());
    }

    /**
     * Test of initializeSession method, of class FormCommandAction.
     */
    @Test
    public void testInitializeSession() {
        System.out.println("initializeSession");
        IncomingMessage message = new IncomingMessageImpl();
        String requesterPhone = "000000000000";
        MotechContext context = new MotechContextImpl();

        mockSessDao = createMock(IncomingMessageSessionDAO.class);

        expect(
                mockCore.createIncomingMessageSession()
                ).andReturn(new IncomingMessageSessionImpl());
        expect(
                mockParser.getFormCode((String) anyObject())
                ).andReturn("GENERAL");
        expect(
                mockCore.createIncomingMessageSessionDAO((MotechContext)anyObject())
                ).andReturn(mockSessDao);
        expect(
                mockSessDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);

        mockTrans.begin();
        expectLastCall();

        expect(
                mockSessDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

        mockTrans.commit();
        expectLastCall();

        replay(mockCore,mockParser,mockSessDao,mockSession,mockTrans);
        IncomingMessageSession result = instance.initializeSession(message, requesterPhone, context);
        verify(mockCore,mockParser,mockSessDao,mockSession,mockTrans);

        assertNotNull(result);
    }

    /**
     * Test of initializeForm method, of class FormCommandAction.
     */
    @Test
    public void testInitializeForm() {
        System.out.println("initializeForm");
        IncomingMessage message = new IncomingMessageImpl();
        message.setContent("test content");
        String formCode = "GENERAL";
        MotechContext context = null;

        mockFormDefDao = createMock(IncomingMessageFormDefinitionDAO.class);
        mockFormDao = createMock(IncomingMessageFormDAO.class);

        expect(
                mockCore.createIncomingMessageFormDefinitionDAO((MotechContext) anyObject())
                ).andReturn(mockFormDefDao);
        expect(
                mockFormDefDao.getByCode((String) anyObject())
                ).andReturn(new IncomingMessageFormDefinitionImpl());
        expect(
                mockCore.createIncomingMessageForm()
                ).andReturn(new IncomingMessageFormImpl());
        expect(
                mockParser.getParams((String)anyObject())
                ).andReturn(new ArrayList<IncomingMessageFormParameter>());
        expect(
                mockCore.createIncomingMessageFormDAO((MotechContext)anyObject())
                ).andReturn(mockFormDao);
        expect(
                mockFormDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);

        mockTrans.begin();
        expectLastCall();

        expect(
                mockFormDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

        mockTrans.commit();
        expectLastCall();

        replay(mockCore,mockFormDefDao,mockParser,mockFormDao,mockSession,mockTrans);
        IncomingMessageForm result = instance.initializeForm(message, formCode, context);
        verify(mockCore,mockFormDefDao,mockParser,mockFormDao,mockSession,mockTrans);

        assertNotNull(result);
    }

    /**
     * Test of prepareResponse method, of class FormCommandAction.
     */
    @Test
    public void testPrepareResponse() {
        System.out.println("prepareResponse");
        IncomingMessage message = new IncomingMessageImpl();

        //Test for empty form
        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());

        replay(mockCore);
        IncomingMessageResponse result = instance.prepareResponse(message);
        verify(mockCore);

        String expResult ="Invalid request";
        assertNotNull(result);
        assertEquals(result.getContent(), expResult);

        //Test for valid form
        message.setIncomingMessageForm(new IncomingMessageFormImpl());
        message.getIncomingMessageForm().setMessageFormStatus(IncMessageFormStatus.VALID);
        reset(mockCore);

        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());

        replay(mockCore);
        result = instance.prepareResponse(message);
        verify(mockCore);

        expResult = "Saved";
        assertNotNull(result);
        assertEquals(result.getContent(), expResult);

        //Test for valid form
        IncomingMessageFormParameter param1 = new IncomingMessageFormParameterImpl();
        param1.setName("name");
        param1.setErrText("name=wrong format");
        param1.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);

        IncomingMessageFormParameter param2 = new IncomingMessageFormParameterImpl();
        param2.setName("age");
        param2.setErrText("age=too long");
        param2.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);

        message.getIncomingMessageForm().setMessageFormStatus(IncMessageFormStatus.INVALID);
        message.getIncomingMessageForm().setIncomingMsgFormParameters(new ArrayList<IncomingMessageFormParameter>());
        message.getIncomingMessageForm().getIncomingMsgFormParameters().add(param1);
        message.getIncomingMessageForm().getIncomingMsgFormParameters().add(param2);

        reset(mockCore);

        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());

        replay(mockCore);
        result = instance.prepareResponse(message);
        verify(mockCore);

        expResult = "Errors:\nname=wrong format\nage=too long";
        assertNotNull(result);
        assertEquals(result.getContent(), expResult);
    }
}