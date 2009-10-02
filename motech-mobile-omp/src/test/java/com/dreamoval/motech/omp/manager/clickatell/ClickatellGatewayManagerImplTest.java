package com.dreamoval.motech.omp.manager.clickatell;

import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
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
public class ClickatellGatewayManagerImplTest {

    ClickatellGatewayManagerImpl instance;
    GatewayMessageHandler mockHandler;

    public ClickatellGatewayManagerImplTest() {
    }

    @Before
    public void setUp(){
        mockHandler = createMock(GatewayMessageHandler.class);
        instance = new ClickatellGatewayManagerImpl();
        instance.setMessageHandler(mockHandler);
        instance.setApiId("someid");
        instance.setUser("someuser");
        instance.setPassword("somepassword");
        instance.setSender("somesender");
        instance.setDeliveryAcknowledge("1");
        instance.setCallback("somecallback");
    }

    /**
     * Test of sendMessage method, of class ClickatellGatewayManagerImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");

        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setRequestId(4L);

        expect(
                mockHandler.parseMessageResponse((GatewayRequest) anyObject(), (String) anyObject())
                ).andReturn(new HashSet<GatewayResponse>());
        replay(mockHandler);

        Set<GatewayResponse> result = instance.sendMessage(messageDetails);
        assertNotNull(result);
        verify(mockHandler);
    }

    /**
     * Test of getMessageStatus method, of class ClickatellGatewayManagerImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        GatewayResponseImpl response = new GatewayResponseImpl();
        response.setGatewayMessageId("testId");

        //expect(
        //        mockHandler.parseMessageStatus((String) anyObject())
        //        ).andReturn(MStatus.DELIVERED);
        //replay(mockHandler);

        String result = instance.getMessageStatus(response);
        assertNotNull(result);
        //verify(mockHandler);
    }

    /**
     * Test of mapMessageStatus method, of class ClickatellGatewayManagerImpl.
     */
    @Test
    public void testMapMessageStatus() {
        System.out.println("mapMessageStatus");

        GatewayResponseImpl response = new GatewayResponseImpl();
        response.setResponseText("Some gateway response message");
        
        expect(
                mockHandler.parseMessageStatus((String) anyObject())
                ).andReturn(MStatus.DELIVERED);
        replay(mockHandler);

        MStatus result = instance.mapMessageStatus(response);
        assertEquals(result, MStatus.DELIVERED);
        verify(mockHandler);
    }
}