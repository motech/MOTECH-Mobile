package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.dao.DBSession;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormDefinitionType;
import org.motechproject.mobile.core.model.IncomingMessageSession;
import org.motechproject.mobile.core.model.IncomingMessageSessionImpl;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.core.service.MotechContextImpl;
import org.motechproject.mobile.model.dao.imp.IncomingMessageDAO;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormDAO;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormDefinitionDAO;
import org.motechproject.mobile.model.dao.imp.IncomingMessageSessionDAO;
import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessage;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormDefinitionImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import org.motechproject.mobile.core.model.IncomingMessageFormParameterImpl;
import org.motechproject.mobile.core.model.IncomingMessageImpl;
import org.motechproject.mobile.core.model.IncomingMessageResponse;
import org.motechproject.mobile.core.model.IncomingMessageResponseImpl;
import org.motechproject.mobile.model.dao.imp.IncomingMessageResponseDAO;
import java.util.HashMap;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * Test for FormCommandAction class
 *
 *  Date : Dec 6, 2009
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
//@TransactionConfiguration
//@Transactional
public class FormCommandActionTest {

    CoreManager mockCore;
    DBSession mockSession;
    Transaction mockTrans;
    FormCommandAction instance;
    FormProcessor mockProcessor;
    IncomingMessageDAO mockMsgDao;
    IncomingMessageParser mockParser;
    IncomingMessageFormDAO mockFormDao;
    IncomingMessageSessionDAO mockSessDao;
    IncomingMessageResponseDAO mockRespDao;
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
        mockProcessor = createMock(FormProcessor.class);

        instance = new FormCommandAction();
        instance.setParser(mockParser);
        instance.setCoreManager(mockCore);
        instance.setFormProcessor(mockProcessor);
    }

    /**
     * Test of execute method, of class FormCommandAction.
     */
   @Test
    public void testExecute() {
        System.out.println("execute");
        
        String requesterPhone = "000000000000";
        IncomingMessage message = new IncomingMessageImpl();
        MotechContext context = new MotechContextImpl();
        String expResult = "An unexpected error occurred! Please try again.";
        
        IncomingMessageFormImpl msgForm = new IncomingMessageFormImpl();
        msgForm.setMessageFormStatus(IncMessageFormStatus.VALID);
        
        IncomingMessageFormDefinitionImpl formDefn = new IncomingMessageFormDefinitionImpl();
        formDefn.setType(IncMessageFormDefinitionType.ENCOUNTER);

        mockFormDao = createMock(IncomingMessageFormDAO.class);
        mockSessDao = createMock(IncomingMessageSessionDAO.class);
        mockRespDao = createMock(IncomingMessageResponseDAO.class);
        mockValidator = createMock(IncomingMessageFormValidator.class);
        mockFormDefDao = createMock(IncomingMessageFormDefinitionDAO.class);
        instance.setFormValidator(mockValidator);

        //Initialize Session
        expect(
                mockCore.createIncomingMessageSession()
                ).andReturn(new IncomingMessageSessionImpl());
        expect(
                mockParser.getFormCode((String) anyObject())
                ).andReturn("GENERAL");
        expect(
                mockCore.createIncomingMessageSessionDAO()
                ).andReturn(mockSessDao);
//        expect(
//                mockSessDao.getDBSession()
//                ).andReturn(mockSession);
//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockSessDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();


        //Generate form
        expect(
                mockCore.createIncomingMessageFormDefinitionDAO()
                ).andReturn(mockFormDefDao);
        expect(
                mockFormDefDao.getByCode((String) anyObject())
                ).andReturn(formDefn);
        expect(
                mockCore.createIncomingMessageForm()
                ).andReturn(msgForm);
        expect(
                mockParser.getParams((String)anyObject())
                ).andReturn(new HashMap<String,IncomingMessageFormParameter>());
        expect(
                mockCore.createIncomingMessageFormDAO()
                ).andReturn(mockFormDao);
//        expect(
//                mockFormDao.getDBSession()
//                ).andReturn(mockSession);
//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockFormDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();

        //Validate form
        expect(
                mockValidator.validate((IncomingMessageForm)anyObject(), (String)anyObject())
                ).andReturn(IncMessageFormStatus.VALID);

        //Prepare response
        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());
        expect(
                mockCore.createIncomingMessageResponseDAO()
                ).andReturn(mockRespDao);

//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockRespDao.save((IncomingMessageResponse) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();
        
        //Save request
        expect(
                mockCore.createIncomingMessageSessionDAO()
                ).andReturn(mockSessDao);

//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockSessDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();
        

//        replay(mockCore,mockParser,mockSessDao,mockSession,mockTrans,mockFormDefDao,mockFormDao, mockValidator, mockRespDao);
        replay(mockCore,mockParser,mockSessDao,mockSession,mockFormDefDao,mockFormDao, mockValidator, mockRespDao);
        IncomingMessageResponse result = instance.execute(message, requesterPhone);
//        verify(mockCore,mockParser,mockSessDao,mockSession,mockTrans,mockFormDefDao,mockFormDao, mockValidator, mockRespDao);
        verify(mockCore,mockParser,mockSessDao,mockSession,mockFormDefDao,mockFormDao, mockValidator, mockRespDao);

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
                mockCore.createIncomingMessageSessionDAO()
                ).andReturn(mockSessDao);
    
//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockSessDao.save((IncomingMessageSession) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();

//        replay(mockCore,mockParser,mockSessDao,mockSession,mockTrans);
        replay(mockCore,mockParser,mockSessDao,mockSession);
        IncomingMessageSession result = instance.initializeSession(message, requesterPhone);
//        verify(mockCore,mockParser,mockSessDao,mockSession,mockTrans);
        verify(mockCore,mockParser,mockSessDao,mockSession);

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
//        MotechContext context = new MotechContextImpl();

        mockFormDefDao = createMock(IncomingMessageFormDefinitionDAO.class);
        mockFormDao = createMock(IncomingMessageFormDAO.class);

        expect(
                mockCore.createIncomingMessageFormDefinitionDAO()
                ).andReturn(mockFormDefDao);
        expect(
                mockFormDefDao.getByCode((String) anyObject())
                ).andReturn(new IncomingMessageFormDefinitionImpl());
        expect(
                mockCore.createIncomingMessageForm()
                ).andReturn(new IncomingMessageFormImpl());
        expect(
                mockParser.getParams((String)anyObject())
                ).andReturn(new HashMap<String,IncomingMessageFormParameter>());
        expect(
                mockCore.createIncomingMessageFormDAO()
                ).andReturn(mockFormDao);
//
//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockFormDao.save((IncomingMessageForm) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();

//        replay(mockCore,mockFormDefDao,mockParser,mockFormDao,mockSession,mockTrans);
        replay(mockCore,mockFormDefDao,mockParser,mockFormDao,mockSession);
        IncomingMessageForm result = instance.initializeForm(message, formCode);
//        verify(mockCore,mockFormDefDao,mockParser,mockFormDao,mockSession,mockTrans);
        verify(mockCore,mockFormDefDao,mockParser,mockFormDao,mockSession);

        assertNotNull(result);
    }

    /**
     * Test of prepareResponse method, of class FormCommandAction.
     */
    @Test
    public void testPrepareResponse() {
        System.out.println("prepareResponse");
        IncomingMessage message = new IncomingMessageImpl();
        MotechContext context = new MotechContextImpl();

        mockRespDao = createMock(IncomingMessageResponseDAO.class);

        //Test for empty form
        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());

        replay(mockCore);
        IncomingMessageResponse result = instance.prepareResponse(message, null);
        verify(mockCore);

        String expResult ="Invalid request";
        assertNotNull(result);
        assertEquals(result.getContent(), expResult);

        //Test for valid form
        message.setIncomingMessageForm(new IncomingMessageFormImpl());
        message.getIncomingMessageForm().setMessageFormStatus(IncMessageFormStatus.SERVER_VALID);
        reset(mockCore);

        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());
        expect(
                mockCore.createIncomingMessageResponseDAO()
                ).andReturn(mockRespDao);
      
//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockRespDao.save((IncomingMessageResponse) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();

//        replay(mockCore,mockRespDao,mockSession,mockTrans);
        replay(mockCore,mockRespDao,mockSession);
        result = instance.prepareResponse(message, null);
//        verify(mockCore,mockRespDao,mockSession,mockTrans);
        verify(mockCore,mockRespDao,mockSession);

        expResult = "Data saved successfully";
        assertNotNull(result);
        assertEquals(result.getContent(), expResult);

        //Test for locally invalid form
        IncomingMessageFormParameter param1 = new IncomingMessageFormParameterImpl();
        param1.setName("name");
        param1.setErrText("wrong format");
        param1.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);

        IncomingMessageFormParameter param2 = new IncomingMessageFormParameterImpl();
        param2.setName("age");
        param2.setErrText("too long");
        param2.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);

        message.getIncomingMessageForm().setMessageFormStatus(IncMessageFormStatus.INVALID);
        message.getIncomingMessageForm().setIncomingMsgFormParameters(new HashMap<String,IncomingMessageFormParameter>());
        message.getIncomingMessageForm().getIncomingMsgFormParameters().put(param1.getName(),param1);
        message.getIncomingMessageForm().getIncomingMsgFormParameters().put(param2.getName(),param2);

//        reset(mockCore,mockRespDao,mockSession,mockTrans);
        reset(mockCore,mockRespDao,mockSession);

        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());
        expect(
                mockCore.createIncomingMessageResponseDAO()
                ).andReturn(mockRespDao);
    
//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockRespDao.save((IncomingMessageResponse) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();

//        replay(mockCore,mockRespDao,mockSession,mockTrans);
        replay(mockCore,mockRespDao,mockSession);
        result = instance.prepareResponse(message, null);
//        verify(mockCore,mockRespDao,mockSession,mockTrans);
        verify(mockCore,mockRespDao,mockSession);

        expResult = "Errors:\nage=too long\nname=wrong format";
        assertNotNull(result);
        assertEquals(result.getContent(), expResult);

        //Test for server invalid form
        param2.setName("age");
        param2.setErrText("server error");
        param2.setMessageFormParamStatus(IncMessageFormParameterStatus.SERVER_INVALID);

        message.getIncomingMessageForm().setMessageFormStatus(IncMessageFormStatus.SERVER_INVALID);
        message.getIncomingMessageForm().setIncomingMsgFormParameters(new HashMap<String,IncomingMessageFormParameter>());
        message.getIncomingMessageForm().getIncomingMsgFormParameters().put(param2.getName(),param2);

//        reset(mockCore,mockRespDao,mockSession,mockTrans);
        reset(mockCore,mockRespDao,mockSession);

        expect(
                mockCore.createIncomingMessageResponse()
                ).andReturn(new IncomingMessageResponseImpl());
        expect(
                mockCore.createIncomingMessageResponseDAO()
                ).andReturn(mockRespDao);
    
//        expect(
//                mockSession.getTransaction()
//                ).andReturn(mockTrans);

//        mockTrans.begin();
        expectLastCall();

        expect(
                mockRespDao.save((IncomingMessageResponse) anyObject())
                ).andReturn(null);

//        mockTrans.commit();
        expectLastCall();

//        replay(mockCore,mockRespDao,mockSession,mockTrans);
        replay(mockCore,mockRespDao,mockSession);
        result = instance.prepareResponse(message, null);
//        verify(mockCore,mockRespDao,mockSession,mockTrans);
        verify(mockCore,mockRespDao,mockSession);

        expResult = "Errors:\nage=server error";
        assertNotNull(result);
        assertEquals(result.getContent(), expResult);
    }
}