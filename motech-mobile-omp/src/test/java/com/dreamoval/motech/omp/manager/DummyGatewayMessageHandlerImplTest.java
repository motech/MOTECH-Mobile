/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * Unit test for the DummyGatewayMessageHandlerImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Dec 16, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/omp-config.xml"})
public class DummyGatewayMessageHandlerImplTest {
    CoreManager mockCoreManager;

    @Autowired
    DummyGatewayMessageHandlerImpl dummyHandler;

    public DummyGatewayMessageHandlerImplTest() {
    }

    @Before
    public void setUp(){
        mockCoreManager = createMock(CoreManager.class);

        dummyHandler.setCoreManager(mockCoreManager);
    }

    /**
     * Test of parseMessageResponse method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageResponse() {
        System.out.println("parseMessageResponse");
        GatewayRequest message = null;
        String gatewayResponse = "";
        MotechContext context = new MotechContextImpl();
        GatewayRequest expResult = null;
        Set<GatewayResponse> result = dummyHandler.parseMessageResponse(message, gatewayResponse, context);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseMessageStatus method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageStatus() {
        System.out.println("parseMessageStatus");
        String messageStatus = "004";
        MStatus expResult = MStatus.PENDING;
        MStatus result = dummyHandler.parseMessageStatus(messageStatus);
        assertEquals(expResult, result);
    }

    /**
     * Test of lookupStatus method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testLookupStatus() {
        System.out.println("lookupStatus");
        String messageStatus = "004";
        MStatus expResult = MStatus.DELIVERED;
        MStatus result = dummyHandler.lookupStatus(messageStatus);
        assertEquals(expResult, result);
    }

    /**
     * Test of lookupResponse method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testLookupResponse() {
        System.out.println("lookupResponse");
        String messageStatus = "ID: somestatus";
        MStatus expResult = MStatus.SCHEDULED;
        MStatus result = dummyHandler.lookupResponse(messageStatus);
        assertEquals(expResult, result);
    }

}