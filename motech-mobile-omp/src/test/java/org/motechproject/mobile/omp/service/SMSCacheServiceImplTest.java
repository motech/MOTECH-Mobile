package org.motechproject.mobile.omp.service;

import org.springframework.transaction.annotation.Transactional;
import org.motechproject.mobile.core.dao.DBSession;
import org.motechproject.mobile.core.dao.GatewayRequestDAO;
import org.motechproject.mobile.core.dao.GatewayResponseDAO;
import static org.easymock.EasyMock.*;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.core.service.MotechContextImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;

/**
 * Unit test for the SMSCacheServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@TransactionConfiguration
@Transactional
public class SMSCacheServiceImplTest {

    SMSCacheServiceImpl instance;

    DBSession mockSession;
    Transaction mockTrans;
    CoreManager mockCore;
    GatewayRequestDAO mockMessageDAO;
    GatewayResponseDAO mockResponseDAO;
    GatewayRequestDetails mockGatewayRequestDetails;
    MotechContext mCtx;

    public SMSCacheServiceImplTest() {
    }

    @Before
    public void setUp(){
        mockCore = createMock(CoreManager.class);
        mockTrans = createMock(Transaction.class);
        mockSession = createMock(DBSession.class);
        mockGatewayRequestDetails = createMock(GatewayRequestDetails.class);
        
        mockGatewayRequestDetails.setId(32000000000l);
        instance = new SMSCacheServiceImpl();
        instance.setCoreManager(mockCore);
        
        mCtx = new MotechContextImpl();
    }

    /**
     * Test of saveMessage method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testSaveMessage() {
        System.out.println("saveMessage");

        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(mockGatewayRequestDetails);
        messageDetails.setMessageStatus(MStatus.PENDING);
        
        mockMessageDAO = createMock(GatewayRequestDAO.class);
        
        expect(
                mockCore.createGatewayRequestDAO()
                ).andReturn(mockMessageDAO);
  
        expectLastCall();
        
        expect(
                mockMessageDAO.save((GatewayRequest) anyObject())
                ).andReturn(messageDetails);
        
        mockTrans.commit();
        expectLastCall();
        
        replay(mockCore, mockMessageDAO);
        instance.saveMessage(messageDetails);
        verify(mockCore, mockMessageDAO);
    }

    /**
     * Test of saveMessage method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testSaveResponse() {
        System.out.println("saveResponse");

        GatewayResponse response = new GatewayResponseImpl();
        response.setGatewayMessageId("werfet54y56g645v4e");
        response.setMessageStatus(MStatus.PENDING);
        response.setRecipientNumber("000000000000");
        response.setResponseText("Some gateway response message");
        response.setId(32000000001l);
        
        mockResponseDAO = createMock(GatewayResponseDAO.class);
        
        expect(
                mockCore.createGatewayResponseDAO()
                ).andReturn(mockResponseDAO);
      
        expectLastCall();
        
        expect(
                mockResponseDAO.save((GatewayRequest) anyObject())
                ).andReturn(response);
        
        mockTrans.commit();
        expectLastCall();
        
        replay(mockCore, mockResponseDAO);
        instance.saveResponse(response);
        verify(mockCore, mockResponseDAO);
    }

    /**
     * Test of saveMessage method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testGetMessages() {
        System.out.println("getMessages");

        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(mockGatewayRequestDetails);
        
        mockMessageDAO = createMock(GatewayRequestDAO.class);
        
        expect(
                mockCore.createGatewayRequestDAO()
                ).andReturn(mockMessageDAO);
        expect(
                mockMessageDAO.findByExample((GatewayRequest) anyObject())
                ).andReturn(new ArrayList<GatewayRequest>());
        replay(mockCore, mockMessageDAO);

        List<GatewayRequest> result = instance.getMessages(messageDetails);
        assertNotNull(result);
        
        verify(mockCore, mockMessageDAO);
    }

    /**
     * Test of saveMessage method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testGetResponses() {
        System.out.println("getResponses");

        GatewayResponse response = new GatewayResponseImpl();
        response.setGatewayMessageId("werfet54y56g645v4e");
        response.setMessageStatus(MStatus.PENDING);
        response.setRecipientNumber("000000000000");
        response.setResponseText("Some gateway response message");
        
        mockResponseDAO = createMock(GatewayResponseDAO.class);
        
        expect(
                mockCore.createGatewayResponseDAO()
                ).andReturn(mockResponseDAO);
        expect(
                mockResponseDAO.findByExample((GatewayResponse) anyObject())
                ).andReturn(new ArrayList<GatewayResponse>());
        
        replay(mockCore, mockResponseDAO);

        List<GatewayResponse> result = instance.getResponses(response);
        assertNotNull(result);
        
        verify(mockCore, mockResponseDAO);
    }

    /**
     * Test of getMessagesByStatus method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testGetMessagesByStatus() {
        System.out.println("getMessagesByStatus");

        mockMessageDAO = createMock(GatewayRequestDAO.class);

        expect(
                mockCore.createGatewayRequestDAO()
                ).andReturn(mockMessageDAO);
        expect(
                mockMessageDAO.getByStatus((MStatus) anyObject())
                ).andReturn(new ArrayList<GatewayRequest>());
        replay(mockCore, mockMessageDAO);

        List<GatewayRequest> result = instance.getMessagesByStatus(MStatus.CANCELLED);
        assertNotNull(result);

        verify(mockCore, mockMessageDAO);
    }

    /**
     * Test of getMessagesByStatusAndSchedule method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testGetMessagesByStatusAndSchedule() {
        System.out.println("getMessagesByStatusAndSchedule");

        mockMessageDAO = createMock(GatewayRequestDAO.class);

        expect(
                mockCore.createGatewayRequestDAO()
                ).andReturn(mockMessageDAO);
        expect(
                mockMessageDAO.getByStatusAndSchedule((MStatus) anyObject(), (Date) anyObject())
                ).andReturn(new ArrayList<GatewayRequest>());
        replay(mockCore, mockMessageDAO);

        List<GatewayRequest> result = instance.getMessagesByStatusAndSchedule(MStatus.CANCELLED, new Date());
        assertNotNull(result);

        verify(mockCore, mockMessageDAO);
    }
}