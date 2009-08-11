/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.DummyGatewayManagerImpl;
import com.dreamoval.motech.omp.manager.DummyGatewayMessageHandlerImpl;
import com.dreamoval.motech.omp.manager.GatewayManager;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
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
public class SMSMessagingServiceImplTest {

    @Autowired
    SMSMessagingServiceImpl smsService;
    @Autowired
    CoreManager coreManager;
    @Autowired
    DummyGatewayManagerImpl dummyGateway;
    @Autowired
    DummyGatewayMessageHandlerImpl dummyHandler;

    public SMSMessagingServiceImplTest() {
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
     * Test of setGatewayManager method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testSetGatewayManager() {
        System.out.println("setGatewayManager");
        GatewayManager gatewayManager = dummyGateway;
        SMSMessagingServiceImpl instance = smsService;
        instance.setGatewayManager(gatewayManager);
    }

    /**
     * Test of getGatewayManager method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testGetGatewayManager() {
        System.out.println("getGatewayManager");
        SMSMessagingServiceImpl instance = smsService;
        GatewayManager expResult = dummyGateway;
        GatewayManager result = instance.getGatewayManager();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHandler method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testSetHandler() {
        System.out.println("setHandler");
        GatewayMessageHandler handler = dummyHandler;
        SMSMessagingServiceImpl instance = smsService;
        instance.setHandler(handler);
    }

    /**
     * Test of getHandler method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testGetHandler() {
        System.out.println("getHandler");
        SMSMessagingServiceImpl instance = smsService;
        GatewayMessageHandler expResult = dummyHandler;
        GatewayMessageHandler result = instance.getHandler();
        assertEquals(expResult, result);
    }

    /**
     * Test of sendTextMessage method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testSendTextMessage_MessageDetails() {
        System.out.println("sendTextMessage");

        MessageDetails messageDetails = coreManager.createMessageDetails(coreManager.createMotechContext());
        messageDetails.setGlobalStatus("pending");
        messageDetails.setMessageText("a message for testing");
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers("000000000000");
        messageDetails.setMessageType("TEXT");

        SMSMessagingServiceImpl instance = smsService;
        
        dummyHandler.setCoreManager(coreManager);
        instance.setGatewayManager(dummyGateway);
        instance.setHandler(dummyHandler);

        Long expResult = messageDetails.getId();
        Long result = instance.sendTextMessage(messageDetails);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendTextMessage method, of class SMSMessagingServiceImpl.
     */
//    @Test
//    public void testSendTextMessage_String() {
//        System.out.println("sendTextMessage");
//        String messageDetails = "";
//        SMSMessagingServiceImpl instance = smsService;
//        Long expResult = null;
//        Long result = instance.sendTextMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getMessageStatus method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        String gatewayMessageId = "";
        SMSMessagingServiceImpl instance = smsService;
        instance.setHandler(dummyHandler);
        String expResult = "delivered";
        String result = instance.getMessageStatus(gatewayMessageId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCache method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testGetCache() {
        System.out.println("getCache");
        SMSMessagingServiceImpl instance = smsService;
        CacheService result = instance.getCache();
        assertNotNull(result);
    }

    /**
     * Test of setCache method, of class SMSMessagingServiceImpl.
     */
    @Test
    public void testSetCache() {
        System.out.println("setCache");
        CacheService cache = null;
        SMSMessagingServiceImpl instance = smsService;
        instance.setCache(cache);
        assertEquals(cache, instance.getCache());
    }

}