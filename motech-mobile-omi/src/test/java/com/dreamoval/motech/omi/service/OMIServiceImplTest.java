package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.dao.DBSession;
import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.LanguageDAO;
import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.dao.NotificationTypeDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestDetailsImpl;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.LanguageImpl;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationTypeImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omp.manager.OMPManager;
import com.dreamoval.motech.omp.service.MessagingService;
import java.util.ArrayList;
import static org.easymock.EasyMock.*;

import java.util.Date;
import java.util.List;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.Patient;
import static org.junit.Assert.*;

/**
 * Unit test for the OMIServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
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
        
        instance = new OMIServiceImpl();
        instance.setCoreManager(mockCore);
        instance.setOmpManager(mockOMP);
        instance.setStoreManager(mockStore);
    }

    /**
     * Test of sendPatientMessage method, of class OMIServiceImpl.
     */
    @Test
    public void testSavePatientMessageRequest() {
        System.out.println("savePatientMessageRequest");
        
        List<Language> langList = new ArrayList<Language>();
        langList.add(new LanguageImpl());
        
        String expResult = "QUEUED";

        mockRequestDao = createMock(MessageRequestDAO.class);
        
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createMessageRequest((MotechContext)anyObject())
                ).andReturn(new MessageRequestImpl());
        expect(
                mockCore.createNotificationTypeDAO((MotechContext) anyObject())
                ).andReturn(mockNoteDao);
        expect(
                mockNoteDao.getById(anyLong())
                ).andReturn(new NotificationTypeImpl());
        expect(
                mockCore.createLanguageDAO((MotechContext) anyObject())
                ).andReturn(mockLangDao);
        expect(
                mockLangDao.getByCode((String)anyObject())
                ).andReturn(new LanguageImpl());
        expect(
                mockCore.createMessageRequestDAO((MotechContext) anyObject())
                ).andReturn(mockRequestDao);
        expect(
                mockRequestDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);
        
        mockTrans.begin();
        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(new MessageRequestImpl());
        
        mockTrans.commit();
        expectLastCall();

        replay(mockCore, mockLangDao, mockRequestDao, mockSession, mockTrans);
        
        String result = instance.savePatientMessageRequest(0L, "Test Patient", "000000000000", ContactNumberType.PERSONAL, "language", MediaType.TEXT, 1L, new Date(), new Date());
        assertEquals(expResult, result);
        verify(mockCore, mockLangDao, mockRequestDao, mockSession, mockTrans);
    }

    /**
     * Test of sendCHPSMessage method, of class OMIServiceImpl.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = "000000000000";
        Patient[] patientList = null;
        List<Language> langList = new ArrayList<Language>();
        langList.add(new LanguageImpl());
        
        String expResult = "QUEUED";
        Date testDate = new Date();

        mockRequestDao = createMock(MessageRequestDAO.class);
        
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createMessageRequest((MotechContext)anyObject())
                ).andReturn(new MessageRequestImpl());
        expect(
                mockCore.createNotificationTypeDAO((MotechContext) anyObject())
                ).andReturn(mockNoteDao);
        expect(
                mockNoteDao.getById(anyLong())
                ).andReturn(new NotificationTypeImpl());
        expect(
                mockCore.createLanguageDAO((MotechContext) anyObject())
                ).andReturn(mockLangDao);
        expect(
                mockLangDao.getByCode((String)anyObject())
                ).andReturn(new LanguageImpl());
        expect(
                mockCore.createMessageRequestDAO((MotechContext) anyObject())
                ).andReturn(mockRequestDao);
        expect(
                mockRequestDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);
        
        mockTrans.begin();
        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(new MessageRequestImpl());
        
        mockTrans.commit();
        expectLastCall();

        replay(mockCore, mockLangDao, mockRequestDao, mockSession, mockTrans);

        String result = instance.saveCHPSMessageRequest(messageId, workerName, workerNumber, patientList, "lang", MediaType.TEXT, 1L, testDate, testDate);
        assertEquals(expResult, result);
        verify(mockCore, mockLangDao, mockRequestDao, mockSession, mockTrans);
    }

    /**
     * Test of processMessageRequests method
     */
    @Test
    public void testProcessMessageRequests(){
        List<MessageRequest> messageList = new ArrayList<MessageRequest>();
        
        MessageRequest msgReq1 = new MessageRequestImpl();
        msgReq1.setDateFrom(new Date());
        msgReq1.setDateTo(new Date());
        msgReq1.setId(49L);
        msgReq1.setMaxTryNumber(3);
        msgReq1.setMessageType(MessageType.TEXT);
        msgReq1.setRecipientName("Tester");
        msgReq1.setRecipientNumber("000000000000");
        msgReq1.setStatus(MStatus.QUEUED);
        messageList.add(msgReq1);
        
        
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createMessageRequestDAO((MotechContext) anyObject())
                ).andReturn(mockRequestDao);
        expect(
                mockCore.createMessageRequest((MotechContext) anyObject())
                ).andReturn(new MessageRequestImpl());
        expect(
                mockRequestDao.findByExample((MessageRequest)anyObject())
                ).andReturn(messageList);
        expect(
                mockOMP.createMessagingService()
                ).andReturn(mockMessagingService);
        expect(
                mockStore.constructMessage((MessageRequest) anyObject())
                ).andReturn(new GatewayRequestDetailsImpl());

        mockMessagingService.scheduleMessage((GatewayRequestDetails) anyObject());
        expectLastCall();

        expect(
                mockRequestDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);
        
        mockTrans.begin();
        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(msgReq1);
        
        mockTrans.commit();
        expectLastCall();
        
        replay(mockCore, mockRequestDao, mockOMP, mockMessagingService, mockSession, mockTrans);
        instance.processMessageRequests();
        verify(mockCore, mockRequestDao, mockOMP, mockMessagingService, mockSession, mockTrans);
    }
    
    /**
     * Test processMessageRetries method
     */
    @Test
    public void testProcessMessageRetries(){
        List<MessageRequest> messageList = new ArrayList<MessageRequest>();
        
        MessageRequest msgReq1 = new MessageRequestImpl();
        msgReq1.setDateFrom(new Date());
        msgReq1.setDateTo(new Date());
        msgReq1.setId(39L);
        msgReq1.setMaxTryNumber(3);
        msgReq1.setMessageType(MessageType.TEXT);
        msgReq1.setRecipientName("Tester");
        msgReq1.setRecipientNumber("000000000000");
        msgReq1.setStatus(MStatus.QUEUED);
        messageList.add(msgReq1);
        
        
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createMessageRequestDAO((MotechContext) anyObject())
                ).andReturn(mockRequestDao);
        expect(
                mockCore.createMessageRequest((MotechContext) anyObject())
                ).andReturn(new MessageRequestImpl());
        expect(
                mockRequestDao.findByExample((MessageRequest)anyObject())
                ).andReturn(messageList);
        expect(
                mockCore.createGatewayRequestDAO((MotechContext) anyObject())
                ).andReturn(mockGatewayDao);
        expect(
                mockOMP.createMessagingService()
                ).andReturn(mockMessagingService);
        expect(
                mockGatewayDao.getById(anyLong())
                ).andReturn((new GatewayRequestImpl()));
        
        mockMessagingService.scheduleMessage((GatewayRequest) anyObject());
        expectLastCall();
        
        expect(
                mockRequestDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);
        
        mockTrans.begin();
        expectLastCall();
        
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(msgReq1);
        
        mockTrans.commit();
        expectLastCall();
        
        replay(mockCore, mockRequestDao, mockOMP, mockGatewayDao, mockMessagingService, mockSession, mockTrans);
        instance.processMessageRetries();
        verify(mockCore, mockRequestDao, mockOMP, mockGatewayDao, mockMessagingService, mockSession, mockTrans);
    }
}