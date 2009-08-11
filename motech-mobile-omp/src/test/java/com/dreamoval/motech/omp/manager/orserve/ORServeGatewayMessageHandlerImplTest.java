/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/omp-config.xml"})
public class ORServeGatewayMessageHandlerImplTest {

    @Autowired
    ORServeGatewayMessageHandlerImpl messageHandler;
    
    public ORServeGatewayMessageHandlerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of prepareMessage method, of class ORServeGatewayMessageHandlerImpl.
     */
    @Test
    public void testPrepareMessage() {
        System.out.println("prepareMessage");
        String message = "";
        MessageDetails expResult = null;
        ORServeGatewayMessageHandlerImpl instance = messageHandler;
        MessageDetails result = instance.prepareMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseMessageResponse method, of class ORServeGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageResponse() {
        System.out.println("parseMessageResponse");
        MessageDetails message = null;
        String gatewayResponse = "";
        MessageDetails expResult = null;
        ORServeGatewayMessageHandlerImpl instance = messageHandler;
        Set<ResponseDetails> result = instance.parseMessageResponse(message, gatewayResponse);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseMessageStatus method, of class ORServeGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageStatus() {
        System.out.println("parseMessageStatus");
        String messageStatus = "";
        ORServeGatewayMessageHandlerImpl instance = messageHandler;
        String expResult = "failed";
        String result = instance.parseMessageStatus(messageStatus);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCoreManager method, of class ORServeGatewayMessageHandlerImpl.
     */
    @Test
    public void testGetCoreManager() {
        System.out.println("getCoreManager");
        ORServeGatewayMessageHandlerImpl instance = messageHandler;
        CoreManager result = instance.getCoreManager();
        assertNotNull(result);
    }

    /**
     * Test of setCoreManager method, of class ORServeGatewayMessageHandlerImpl.
     */
    @Test
    public void testSetCoreManager() {
        System.out.println("setCoreManager");
        CoreManager coreManager = null;
        ORServeGatewayMessageHandlerImpl instance = messageHandler;
        instance.setCoreManager(coreManager);
        assertEquals(coreManager, instance.getCoreManager());
    }

}