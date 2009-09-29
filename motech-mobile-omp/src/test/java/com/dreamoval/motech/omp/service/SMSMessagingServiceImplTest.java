package com.dreamoval.motech.omp.service;

import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.omp.manager.GatewayManager;
import java.util.Date;
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

        replay(mockGateway);

        Long expResult = messageDetails.getId();
        Long result = instance.sendMessage(messageDetails);
        assertEquals(expResult, result);
        verify(mockGateway);
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