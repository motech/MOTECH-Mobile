/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * Unit test for the DummyGatewayMessageHandlerImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Dec 16, 2009
 */
public class DummyGatewayManagerImplTest {
    DummyGatewayManagerImpl instance;
    GatewayMessageHandler mockHandler;
    GatewayRequestDetails mockGatewayRequestDetails ;

    public DummyGatewayManagerImplTest() {
    }

    @Before
    public void setUp(){
        mockHandler = createMock(GatewayMessageHandler.class);
        mockGatewayRequestDetails = createMock(GatewayRequestDetails.class);
        mockGatewayRequestDetails.setId(89L);
        instance = new DummyGatewayManagerImpl();
        instance.setMessageHandler(mockHandler);
    }

    /**
     * Test of sendMessage method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");

        MotechContext context = new MotechContextImpl();
        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        //messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(mockGatewayRequestDetails);

        expect(
                mockHandler.parseMessageResponse((GatewayRequest) anyObject(), (String) anyObject(), (MotechContext) anyObject())
                ).andReturn(new HashSet<GatewayResponse>());
        replay(mockHandler);

        Set<GatewayResponse> result = instance.sendMessage(messageDetails, context);
        assertNotNull(result);
        verify(mockHandler);
    }

    /**
     * Test of getMessageStatus method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        GatewayResponseImpl response = new GatewayResponseImpl();
        response.setGatewayMessageId("testId");

        String result = instance.getMessageStatus(response);
        assertNotNull(result);
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