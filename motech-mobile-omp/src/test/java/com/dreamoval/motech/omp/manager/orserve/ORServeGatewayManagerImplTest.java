package com.dreamoval.motech.omp.manager.orserve;

import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tst for th ORServeGatewayManagerImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class ORServeGatewayManagerImplTest {

    ORServeGatewayManagerImpl instance;
    GatewayMessageHandler mockHandler;

    public ORServeGatewayManagerImplTest() {
    }

    @Before
    public void setUp(){
        mockHandler = createMock(GatewayMessageHandler.class);
        instance = new ORServeGatewayManagerImpl();
        instance.setProductCode("TestCode");
        instance.setSenderId("TestSender");
        instance.setMessageHandler(mockHandler);
    }

    /**
     * Test of sendMessage method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        
        GatewayRequest messageDetails = new GatewayRequestImpl();
//TODO to yoofi conflict here
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setRequestId(5L);

        expect(
                mockHandler.parseMessageResponse((GatewayRequest) anyObject(), (String) anyObject())
                ).andReturn(new HashSet<GatewayResponse>());
        replay(mockHandler);
        
        Set<GatewayResponse> result = instance.sendMessage(messageDetails);
        assertNotNull(result);
        verify(mockHandler);
    }

    /**
     * Test of getMessageStatus method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        String gatewayMessageId = "testid";

        expect(
                mockHandler.parseMessageStatus((String) anyObject())
                ).andReturn("delivered");
        replay(mockHandler);

        String result = instance.getMessageStatus(gatewayMessageId);
        assertNotNull(result);
        verify(mockHandler);
    }

}