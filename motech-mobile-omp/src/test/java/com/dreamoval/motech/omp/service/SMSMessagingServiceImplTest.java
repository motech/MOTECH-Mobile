package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.manager.CoreManager;
import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import com.dreamoval.motech.omp.manager.GatewayManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Unit test for the SMSMessagingServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/omp-config.xml"})
public class SMSMessagingServiceImplTest {

    SMSMessagingServiceImpl instance;
    
    MotechContext mCtx;
    CoreManager mockCore;
    CacheService mockCache;
    GatewayManager mockGateway;    
    GatewayRequestDetails mockGatewayRequestDetails;

    public SMSMessagingServiceImplTest() {
    }

    @Before
    public void setUp(){
        mockCache = createMock(CacheService.class);
        mockGateway = createMock(GatewayManager.class);
        mockGatewayRequestDetails = createMock(GatewayRequestDetails.class);
        mockGatewayRequestDetails.setId(6L);
        mockCore = createMock(CoreManager.class);
        
        instance = new SMSMessagingServiceImpl();
        instance.setCache(mockCache);
        instance.setGatewayManager(mockGateway);
        instance.setCoreManager(mockCore);
        
        mCtx = new MotechContextImpl();
    }

    /**
     * Test of sendTextMessage method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testScheduleMessage() {
        System.out.println("scheduleMessage");

        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(mockGatewayRequestDetails);
        
        mockCache.saveMessage((GatewayRequestDetails) anyObject(), (MotechContext) anyObject());
        expectLastCall();

        replay(mockCache);

        instance.scheduleMessage(messageDetails, mCtx);
        verify(mockCache);
    }

    /**
     * Test of sendTextMessage method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testSendScheduledMessages() {
        System.out.println("sendScheduledMessages");

        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(mockGatewayRequestDetails);
        
        List<GatewayRequest> messages = new ArrayList<GatewayRequest>();
        List<GatewayRequest> troubledMessages = new ArrayList<GatewayRequest>();
        messages.add(messageDetails);

        expect(
                mockCore.createMotechContext()
                ).andReturn(mCtx); 
        expect(
                mockCache.getMessagesByStatusAndSchedule((MStatus) anyObject(), (Date) anyObject(), (MotechContext) anyObject())
                ).andReturn(messages);
        expect(
                mockCache.getMessagesByStatusAndSchedule((MStatus) anyObject(), (Date)anyObject(), (MotechContext) anyObject())
                ).andReturn(troubledMessages);
        expect(
                mockGateway.sendMessage((GatewayRequest) anyObject(), (MotechContext) anyObject())
                ).andReturn(null);
        
        mockCache.saveMessage((GatewayRequestDetails) anyObject(), (MotechContext) anyObject());
        expectLastCall();

        replay(mockCore, mockCache, mockGateway);

        instance.sendScheduledMessages();
        verify(mockCore, mockCache, mockGateway);
    }

    /**
     * Test of sendTextMessage method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");

        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(mockGatewayRequestDetails);

        expect(
                mockGateway.sendMessage((GatewayRequest) anyObject(), (MotechContext) anyObject())
                ).andReturn(null);
        
        mockCache.saveMessage((GatewayRequestDetails) anyObject(), (MotechContext) anyObject());
        expectLastCall();

        replay(mockGateway, mockCache);

        Map<Boolean, Set<GatewayResponse>> expResult = new HashMap<Boolean, Set<GatewayResponse>>();
        expResult.put(new Boolean(true), new HashSet<GatewayResponse>());
        Map<Boolean, Set<GatewayResponse>> result = instance.sendMessage(messageDetails, mCtx);
        assertEquals(expResult.containsKey(new Boolean(true)), result.containsKey(new Boolean(true)));
        verify(mockGateway, mockCache);
    }

    /**
     * Test of sendTextMessage method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testUpdateMessageStatuses() {
        System.out.println("updateMessageStatuses");

        GatewayResponse response = new GatewayResponseImpl();
        response.setGatewayMessageId("werfet54y56g645v4e");
        response.setMessageStatus(MStatus.PENDING);
        response.setRecipientNumber("000000000000");
        response.setResponseText("Some gateway response message");
                
        List<GatewayResponse> responses = new ArrayList<GatewayResponse>();
        responses.add(response);
        
        expect(
                mockCore.createMotechContext()
                ).andReturn(mCtx);
        expect(
                mockCore.createGatewayResponse((MotechContext) anyObject())
                ).andReturn(new GatewayResponseImpl());
        expect(
                mockCache.getResponses((GatewayResponse) anyObject(), (MotechContext) anyObject())
                ).andReturn(responses);
        expect(
                mockGateway.getMessageStatus((GatewayResponse) anyObject())
                ).andReturn("Some gateway response details");
        expect(
                mockGateway.mapMessageStatus((GatewayResponse) anyObject())
                ).andReturn(MStatus.DELIVERED);
        
        mockCache.saveResponse((GatewayResponse) anyObject(), (MotechContext) anyObject());
        expectLastCall();

        replay(mockCore, mockCache, mockGateway);

        instance.updateMessageStatuses();
        verify(mockCore, mockCache, mockGateway);
    }

    /**
     * Test of getMessageStatus method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        String expResult = "delivered";

        expect(
                mockGateway.getMessageStatus((GatewayResponse) anyObject())
                ).andReturn("delivered");
        replay(mockGateway);

        String result = instance.getMessageStatus(new GatewayResponseImpl());
        assertEquals(expResult, result);
        verify(mockGateway);
    }

}