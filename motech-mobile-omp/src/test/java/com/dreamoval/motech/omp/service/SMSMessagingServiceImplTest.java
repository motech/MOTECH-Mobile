package com.dreamoval.motech.omp.service;

import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import com.dreamoval.motech.omp.manager.GatewayManager;
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
    public void testSendTextMessage_MessageDetails() {
        System.out.println("sendTextMessage");

        MessageDetails messageDetails = new MessageDetailsImpl();
        messageDetails.setGlobalStatus("pending");
        messageDetails.setMessageText("a message for testing");
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers("000000000000");
        messageDetails.setMessageType("TEXT");

        mockCache.saveMessage((MessageDetails) anyObject());
        expectLastCall();

        expect(
                mockGateway.sendMessage((MessageDetails) anyObject())
                ).andReturn(null);

        mockCache.saveMessage((MessageDetails) anyObject());
        expectLastCall();

        replay(mockCache, mockGateway);

        Long expResult = messageDetails.getId();
        Long result = instance.sendTextMessage(messageDetails);
        assertEquals(expResult, result);
        verify(mockCache, mockGateway);
    }

    /**
     * Test of getMessageStatus method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        String gatewayMessageId = "";
        String expResult = "delivered";

        expect(
                mockGateway.getMessageStatus((String) anyObject())
                ).andReturn("delivered");
        replay(mockGateway);

        String result = instance.getMessageStatus(gatewayMessageId);
        assertEquals(expResult, result);
        verify(mockGateway);
    }

}