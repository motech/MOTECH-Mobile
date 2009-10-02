package com.dreamoval.motech.omp.service;

import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.omp.manager.GatewayManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    CacheService mockCache;
    GatewayManager mockGateway;

    public SMSMessagingServiceImplTest() {
    }

    @Before
    public void setUp(){
        mockCache = createMock(CacheService.class);
        mockGateway = createMock(GatewayManager.class);

        instance = new SMSMessagingServiceImpl();
        instance.setCache(mockCache);
        instance.setGatewayManager(mockGateway);
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
        messageDetails.setRequestId(6L);
        
        mockCache.saveMessage((GatewayRequest) anyObject());
        expectLastCall();

        replay(mockCache);

        instance.scheduleMessage(messageDetails);
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
        messageDetails.setRequestId(6L);
        
        List<GatewayRequest> messages = new ArrayList<GatewayRequest>();
        messages.add(messageDetails);

        expect(
                mockCache.getMessages((GatewayRequest) anyObject())
                ).andReturn(messages);        
        expect(
                mockGateway.sendMessage((GatewayRequest) anyObject())
                ).andReturn(null);
        
        mockCache.saveMessage((GatewayRequest) anyObject());
        expectLastCall();

        replay(mockCache, mockGateway);

        instance.sendScheduledMessages();
        verify(mockCache, mockGateway);
    }

    /**
     * Test of sendTextMessage method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");

        GatewayRequest messageDetails = new GatewayRequestImpl();
//        TODO to yoofi conflict here
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setRequestId(6L);

        expect(
                mockGateway.sendMessage((GatewayRequest) anyObject())
                ).andReturn(null);
        
        mockCache.saveMessage((GatewayRequest) anyObject());
        expectLastCall();

        replay(mockGateway, mockCache);

        Long expResult = messageDetails.getId();
        Long result = instance.sendMessage(messageDetails);
        assertEquals(expResult, result);
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
                mockCache.getResponses((GatewayResponse) anyObject())
                ).andReturn(responses);
        expect(
                mockGateway.getMessageStatus((GatewayResponse) anyObject())
                ).andReturn("Some gateway response details");
        expect(
                mockGateway.mapMessageStatus((GatewayResponse) anyObject())
                ).andReturn(MStatus.DELIVERED);
        
        mockCache.saveResponse((GatewayResponse) anyObject());
        expectLastCall();

        replay(mockCache, mockGateway);

        instance.updateMessageStatuses();
        verify(mockCache, mockGateway);
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