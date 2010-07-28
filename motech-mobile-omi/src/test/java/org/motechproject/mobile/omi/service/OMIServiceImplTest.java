package org.motechproject.mobile.omi.service;

import org.springframework.transaction.annotation.Transactional;
import org.motechproject.mobile.core.dao.DBSession;
import org.motechproject.mobile.core.dao.GatewayRequestDAO;
import org.motechproject.mobile.core.dao.GatewayRequestDetailsDAO;
import org.motechproject.mobile.core.dao.GatewayResponseDAO;
import org.motechproject.mobile.core.dao.LanguageDAO;
import org.motechproject.mobile.core.dao.MessageRequestDAO;
import org.motechproject.mobile.core.dao.NotificationTypeDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayRequestDetailsImpl;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.LanguageImpl;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageRequestImpl;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationTypeImpl;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.core.service.MotechContextImpl;
import org.motechproject.mobile.omi.manager.MessageStoreManager;
import org.motechproject.mobile.omi.manager.StatusHandler;
import org.motechproject.mobile.omp.manager.OMPManager;
import org.motechproject.mobile.omp.service.MessagingService;
import java.util.ArrayList;
import static org.easymock.EasyMock.*;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.MessageStatus;
import org.motechproject.ws.NameValuePair;
import org.motechproject.ws.Patient;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;

/**
 * Unit test for the OMIServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@TransactionConfiguration
@Transactional
public class OMIServiceImplTest {

    OMPManager mockOMP;
    CoreManager mockCore;
    DBSession mockSession;
    Transaction mockTrans;
    OMIServiceImpl instance;
    MessageStoreManager mockStore;
    MessageRequestDAO mockRequestDao;
    LanguageDAO mockLangDao;
    NotificationTypeDAO mockNoteDao;
    MessagingService mockMessagingService;
    GatewayRequestDAO mockGatewayDao;
    GatewayResponseDAO mockResponseDao;
    GatewayRequestDetailsDAO mockGwDetDao;
    StatusHandler mockHandler;

    public OMIServiceImplTest() {
    }

    @Before
    public void setUp(){
        mockCore = createMock(CoreManager.class);
        mockOMP = createMock(OMPManager.class);
        mockMessagingService = createMock(MessagingService.class);
        mockStore = createMock(MessageStoreManager.class);
        mockGatewayDao = createMock(GatewayRequestDAO.class);
        mockRequestDao = createMock(MessageRequestDAO.class);
        mockNoteDao = createMock(NotificationTypeDAO.class);
        mockLangDao = createMock(LanguageDAO.class);
        mockSession = createMock(DBSession.class);
        mockTrans = createMock(Transaction.class);
        mockGwDetDao = createMock(GatewayRequestDetailsDAO.class);
        mockHandler = createMock(StatusHandler.class);
        mockResponseDao = createMock(GatewayResponseDAO.class);
        
        instance = new OMIServiceImpl();
        instance.setCoreManager(mockCore);
        instance.setOmpManager(mockOMP);
        instance.setStoreManager(mockStore);
        instance.setStatHandler(mockHandler);
        instance.setMaxTries(3);
        instance.setDefaultLang("en");
    }

    /**
     * Test of sendPatientMessage method, of class OMIServiceImpl.
     */
    @Test
    public void testSavePatientMessageRequest() {
        System.out.println("savePatientMessageRequest");
        
        System.out.println("sendCHPSMessage");
        String messageId = "tsid17";
        String patientNumber = "000000000000";
        List<Language> langList = new ArrayList<Language>();
        langList.add(new LanguageImpl());
        NameValuePair[] personalInfo = new NameValuePair[0];
        String recipientId = "123456789";
        
        MessageStatus expResult = MessageStatus.QUEUED;

        mockRequestDao = createMock(MessageRequestDAO.class);
        
        
        expect(
                mockCore.createMessageRequest()
                ).andReturn(new MessageRequestImpl());
        expect(
                mockCore.createNotificationTypeDAO()
                ).andReturn(mockNoteDao);
        expect(
                mockNoteDao.getById(anyLong())
                ).andReturn(new NotificationTypeImpl());
        expect(
                mockCore.createLanguageDAO()
                ).andReturn(mockLangDao);
        expect(
                mockLangDao.getByCode((String)anyObject())
                ).andReturn(new LanguageImpl());
        expect(
                mockCore.createMessageRequestDAO()
                ).andReturn(mockRequestDao);
        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(new MessageRequestImpl());
        
        expectLastCall();

//        replay(mockCore, mockLangDao, mockRequestDao, mockSession, mockTrans);
        replay(mockCore, mockLangDao, mockRequestDao);
        
        MessageStatus result = instance.savePatientMessageRequest(messageId, personalInfo, patientNumber, ContactNumberType.PERSONAL, "language", MediaType.TEXT, 1L, new Date(), new Date(), "123456789");
        assertEquals(expResult, result);
//        verify(mockCore, mockLangDao, mockRequestDao, mockSession, mockTrans);
        verify(mockCore, mockLangDao, mockRequestDao);
    }

    /**
     * Test of sendCHPSMessage method, of class OMIServiceImpl.
     */
    @Test
    public void testSaveCHPSMessageRequest() {
        System.out.println("saveCHPSMessage");
        String messageId = "tsid17";
        String workerNumber = "000000000000";
        Patient[] patientList = null;
        List<Language> langList = new ArrayList<Language>();
        langList.add(new LanguageImpl());
        NameValuePair[] personalInfo = new NameValuePair[0];
        
        MessageStatus expResult = MessageStatus.QUEUED;
        Date testDate = new Date();

        mockRequestDao = createMock(MessageRequestDAO.class);
        

        expect(
                mockCore.createMessageRequest()
                ).andReturn(new MessageRequestImpl());
        expect(
                mockCore.createNotificationTypeDAO()
                ).andReturn(mockNoteDao);
        expect(
                mockNoteDao.getById(anyLong())
                ).andReturn(new NotificationTypeImpl());
        expect(
                mockCore.createLanguageDAO()
                ).andReturn(mockLangDao);
        expect(
                mockLangDao.getByCode((String)anyObject())
                ).andReturn(new LanguageImpl());
        expect(
                mockCore.createMessageRequestDAO()
                ).andReturn(mockRequestDao);

        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(new MessageRequestImpl());
        

        expectLastCall();

//        replay(mockCore, mockLangDao, mockRequestDao, mockSession, mockTrans);
        replay(mockCore, mockLangDao, mockRequestDao);

        MessageStatus result = instance.saveCHPSMessageRequest(messageId, personalInfo, workerNumber, patientList, "lang", MediaType.TEXT, 1L, testDate, testDate);
        assertEquals(expResult, result);
        verify(mockCore, mockLangDao, mockRequestDao);
    }
    
    @Test
    public void testSendMessage(){
        System.out.println("sendMessage");
        
        MessageRequest msgReq1 = new MessageRequestImpl();
        msgReq1.setDateFrom(new Date());
        msgReq1.setDateTo(new Date());
        msgReq1.setId(19000000001l);
        msgReq1.setTryNumber(1);
        msgReq1.setMessageType(MessageType.TEXT);
        msgReq1.setRecipientName("Tester");
        msgReq1.setRecipientNumber("000000000000");
        msgReq1.setStatus(MStatus.QUEUED);
        
        GatewayRequestImpl gwReq = new GatewayRequestImpl();
        gwReq.setGatewayRequestDetails(new GatewayRequestDetailsImpl());
        HashMap<Boolean, Set<GatewayResponse>> respMap = new HashMap<Boolean, Set<GatewayResponse>>();
        respMap.put(new Boolean(true), new HashSet<GatewayResponse>());
        
        expect(
                mockCore.createLanguageDAO()
                ).andReturn(mockLangDao);
        expect(
                mockLangDao.getByCode((String) anyObject())
                ).andReturn(new LanguageImpl());
        expect(
                mockCore.createMessageRequestDAO()
                ).andReturn(mockRequestDao);

        expectLastCall();

        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(new MessageRequestImpl());

        expectLastCall();
        
        expect(
                mockStore.constructMessage((MessageRequest) anyObject(), (Language) anyObject())
                ).andReturn(gwReq);
        expect(
                mockOMP.createMessagingService()
                ).andReturn(mockMessagingService);
        expect(
                mockMessagingService.sendMessage((GatewayRequest) anyObject() )
                ).andReturn(respMap);
        

        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(new MessageRequestImpl());
        

        expectLastCall();
        
        replay(mockStore, mockOMP, mockMessagingService, mockCore, mockRequestDao);
        instance.sendMessage(msgReq1);
        verify(mockStore, mockOMP, mockMessagingService, mockCore, mockRequestDao);
    }

    /**
     * Test of processMessageRequests method
     */
    @Test
    public void testProcessMessageRequests(){
        System.out.println("processMessageRequests");
        List<MessageRequest> messageList = new ArrayList<MessageRequest>();
        
        MessageRequest msgReq1 = new MessageRequestImpl();
        msgReq1.setDateFrom(new Date());
        msgReq1.setDateTo(new Date());
        msgReq1.setId(19000000002l);
        msgReq1.setTryNumber(1);
        msgReq1.setMessageType(MessageType.TEXT);
        msgReq1.setRecipientName("Tester");
        msgReq1.setRecipientNumber("000000000000");
        msgReq1.setStatus(MStatus.QUEUED);
        messageList.add(msgReq1);
        
        GatewayRequest gwReq = new GatewayRequestImpl();
        gwReq.setGatewayRequestDetails(new GatewayRequestDetailsImpl());
        
     
        expect(
                mockCore.createMessageRequestDAO()
                ).andReturn(mockRequestDao);
        expect(
                mockRequestDao.getMsgByStatus((MStatus) anyObject())
                ).andReturn(messageList);
        expect(
                mockOMP.createMessagingService()
                ).andReturn(mockMessagingService);
        expect(
                mockCore.createLanguageDAO()
                ).andReturn(mockLangDao);
        expect(
                mockLangDao.getByCode((String) anyObject())
                ).andReturn(new LanguageImpl());
        expect(
                mockStore.constructMessage((MessageRequest) anyObject(),  (Language) anyObject())
                ).andReturn(gwReq);

        mockMessagingService.scheduleMessage((GatewayRequest) anyObject());
        expectLastCall();

        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(msgReq1);
        

        expectLastCall();
        
        replay(mockCore, mockRequestDao, mockOMP, mockMessagingService, mockStore);
        instance.processMessageRequests();
        verify(mockCore, mockRequestDao, mockOMP, mockMessagingService,  mockStore);
    }
    
    /**
     * Test processMessageRetries method
     */
    @Test
    public void testProcessMessageRetries(){
        System.out.println("processMessageRetries");
        List<MessageRequest> messageList = new ArrayList<MessageRequest>();
        
        MessageRequest msgReq1 = new MessageRequestImpl();
        msgReq1.setDateFrom(new Date());
        msgReq1.setDateTo(new Date());
        msgReq1.setId(19000000003l);
        msgReq1.setTryNumber(1);
        msgReq1.setMessageType(MessageType.TEXT);
        msgReq1.setRecipientName("Tester");
        msgReq1.setRecipientNumber("000000000000");
        msgReq1.setStatus(MStatus.QUEUED);
        messageList.add(msgReq1);
        
        GatewayRequestDetails details = new GatewayRequestDetailsImpl();
        details.setId(19000000004l);
        details.setMessage("Some message");
        details.setMessageType(MessageType.TEXT);
        details.setNumberOfPages(1);
        details.setGatewayRequests(new HashSet());
        
        msgReq1.setGatewayRequestDetails(details);
        
      
        expect(
                mockCore.createMessageRequestDAO()
                ).andReturn(mockRequestDao);
        expect(
                mockRequestDao.getMsgRequestByStatusAndTryNumber((MStatus) anyObject(), anyInt())
                ).andReturn(messageList);
        expect(
                mockCore.createGatewayRequestDetailsDAO()
                ).andReturn(mockGwDetDao);
        expect(
                mockOMP.createMessagingService()
                ).andReturn(mockMessagingService);
        expect(
                mockGwDetDao.getById(anyLong())
                ).andReturn(details);
        expect(
                mockCore.createGatewayRequest()
                ).andReturn(new GatewayRequestImpl());
        
        mockMessagingService.scheduleMessage((GatewayRequestDetails) anyObject());
        expectLastCall();

        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(msgReq1);
        
        expectLastCall();
        
        replay(mockCore, mockRequestDao, mockOMP, mockGatewayDao, mockMessagingService, mockGwDetDao);
        instance.processMessageRetries();
        verify(mockCore, mockRequestDao, mockOMP, mockGatewayDao, mockMessagingService, mockGwDetDao);
    }
    
    @Test
    public void testGetMessageResponses(){
        System.out.println("getMessageResponses");
        
        List<MessageRequest> msgList = new ArrayList<MessageRequest>();
        
        MessageRequestImpl request = new MessageRequestImpl();
        request.setId(19000000005l);
        request.setStatus(MStatus.PENDING);
        
        msgList.add(request);

        GatewayRequest gwReq = new GatewayRequestImpl();
        gwReq.setMessageRequest(request);
        gwReq.setId(19000000006l);

        GatewayResponseImpl response = new GatewayResponseImpl();
        response.setMessageStatus(MStatus.DELIVERED);
        response.setGatewayRequest(gwReq);
        

        expect(
                mockCore.createMessageRequestDAO()
                ).andReturn(mockRequestDao);
        expect(
                mockRequestDao.getMsgRequestByStatusAndTryNumber((MStatus) anyObject(), anyInt())
                ).andReturn(msgList);
        expect(
                mockCore.createGatewayResponseDAO()
                ).andReturn(mockResponseDao);
        expect(
                mockResponseDao.getByMessageIdAndTryNumber(anyLong(), anyInt())
                ).andReturn(response);
  
        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(request);
        

        expectLastCall();
        
        mockHandler.handleStatus((GatewayResponse) anyObject());
        expectLastCall();
        
        replay(mockCore, mockResponseDao, mockRequestDao, mockHandler);
        instance.getMessageResponses();
        verify(mockCore, mockResponseDao, mockRequestDao, mockHandler);
    }
}