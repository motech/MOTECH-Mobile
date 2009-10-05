package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the OMIServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class OMIServiceImplTest {

    OMPManager mockOMP;
    OMIServiceImpl instance;
    CoreManager mockCore;
    MessageStoreManager mockStore;
    MessageRequestDAO mockRequestDao;
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
        
        String expResult = "QUEUED";

        mockRequestDao = createMock(MessageRequestDAO.class);
        
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createMessageRequest((MotechContext)anyObject())
                ).andReturn(new MessageRequestImpl());
        expect(
                mockCore.createNotificationType((MotechContext) anyObject())
                ).andReturn(new NotificationTypeImpl());
        expect(
                mockCore.createLanguage((MotechContext) anyObject())
                ).andReturn(new LanguageImpl());
        expect(
                mockCore.createMessageRequestDAO((MotechContext) anyObject())
                ).andReturn(mockRequestDao);
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(new MessageRequestImpl());

        replay(mockCore, mockRequestDao);
        
        String result = instance.savePatientMessageRequest(0L, "Test Patient", "000000000000", ContactNumberType.PERSONAL, "language", MessageType.TEXT, 1L, new Date(), new Date());
        assertEquals(expResult, result);
        verify(mockCore, mockRequestDao);
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
        List<PatientImpl> patientList = null;
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
                mockCore.createNotificationType((MotechContext) anyObject())
                ).andReturn(new NotificationTypeImpl());
        expect(
                mockCore.createLanguage((MotechContext) anyObject())
                ).andReturn(new LanguageImpl());
        expect(
                mockCore.createMessageRequestDAO((MotechContext) anyObject())
                ).andReturn(mockRequestDao);
        expect(
                mockRequestDao.save((MessageRequest) anyObject())
                ).andReturn(new MessageRequestImpl());

        replay(mockCore, mockRequestDao);

        String result = instance.saveCHPSMessageRequest(messageId, workerName, workerNumber, patientList, "lang", MessageType.TEXT, 1L, testDate, testDate);
        assertEquals(expResult, result);
        verify(mockCore, mockRequestDao);
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
                ).andReturn(new GatewayRequestImpl());

        mockMessagingService.scheduleMessage((GatewayRequest) anyObject());
        expectLastCall();

        mockRequestDao.save((GatewayRequest) anyObject());
        expectLastCall();
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
        
        replay(mockCore, mockRequestDao, mockOMP, mockGatewayDao, mockMessagingService);
        instance.processMessageRetries();
        verify(mockCore, mockRequestDao, mockOMP, mockGatewayDao, mockMessagingService);
    }
}