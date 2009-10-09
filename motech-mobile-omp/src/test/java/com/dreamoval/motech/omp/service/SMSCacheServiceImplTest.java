package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.dao.DBSession;
import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the SMSCacheServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class SMSCacheServiceImplTest {

    SMSCacheServiceImpl instance;

    DBSession mockSession;
    Transaction mockTrans;
    CoreManager mockCore;
    GatewayRequestDAO mockMessageDAO;
    GatewayResponseDAO mockResponseDAO;
    GatewayRequestDetails mockGatewayRequestDetails;

    public SMSCacheServiceImplTest() {
    }

    @Before
    public void setUp(){
        mockCore = createMock(CoreManager.class);
        mockTrans = createMock(Transaction.class);
        mockSession = createMock(DBSession.class);
        mockGatewayRequestDetails = createMock(GatewayRequestDetails.class);
        
        mockGatewayRequestDetails.setId(2L);
        instance = new SMSCacheServiceImpl();
        instance.setCoreManager(mockCore);
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
                mockCore.createGatewayRequestDAO((MotechContext) anyObject())
                ).andReturn(mockMessageDAO);
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockMessageDAO.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);
        
        mockTrans.begin();
        expectLastCall();
        
        expect(
                mockMessageDAO.save((GatewayRequest) anyObject())
                ).andReturn(messageDetails);
        
        mockTrans.commit();
        expectLastCall();
        
        replay(mockCore, mockMessageDAO, mockSession, mockTrans);
        instance.saveMessage(messageDetails);
        verify(mockCore, mockMessageDAO, mockSession, mockTrans);
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
        response.setId(5L);
        
        mockResponseDAO = createMock(GatewayResponseDAO.class);
        
        expect(
                mockCore.createGatewayResponseDAO((MotechContext) anyObject())
                ).andReturn(mockResponseDAO);
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockResponseDAO.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);
        
        mockTrans.begin();
        expectLastCall();
        
        expect(
                mockResponseDAO.save((GatewayRequest) anyObject())
                ).andReturn(response);
        
        mockTrans.commit();
        expectLastCall();
        
        replay(mockCore, mockResponseDAO, mockSession, mockTrans);
        instance.saveResponse(response);
        verify(mockCore, mockResponseDAO, mockSession, mockTrans);
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
                mockCore.createGatewayRequestDAO((MotechContext) anyObject())
                ).andReturn(mockMessageDAO);
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
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
                mockCore.createGatewayResponseDAO((MotechContext) anyObject())
                ).andReturn(mockResponseDAO);
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockResponseDAO.findByExample((GatewayResponse) anyObject())
                ).andReturn(new ArrayList<GatewayResponse>());
        
        replay(mockCore, mockResponseDAO);

        List<GatewayResponse> result = instance.getResponses(response);
        assertNotNull(result);
        
        verify(mockCore, mockResponseDAO);
    }

}