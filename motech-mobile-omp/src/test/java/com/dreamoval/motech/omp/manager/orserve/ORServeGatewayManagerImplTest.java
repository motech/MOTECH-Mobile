/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
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
public class ORServeGatewayManagerImplTest {

    @Autowired
    ORServeGatewayManagerImpl gatewayManager;

    public ORServeGatewayManagerImplTest() {
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
     * Test of sendMessage method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        
        MessageDetails messageDetails = new MessageDetailsImpl();
        messageDetails.setGlobalStatus("pending");
        messageDetails.setMessageText("a message for testing");
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers("000000000000");
        messageDetails.setMessageType("TEXT");

        ORServeGatewayManagerImpl instance = gatewayManager;
        String result = instance.sendMessage(messageDetails);
        assertNotNull(result);
    }

    /**
     * Test of getMessageStatus method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        String gatewayMessageId = "testid";
        ORServeGatewayManagerImpl instance = gatewayManager;
        String result = instance.getMessageStatus(gatewayMessageId);
        assertNotNull(result);
    }

    /**
     * Test of getProductCode method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testGetProductCode() {
        System.out.println("getProductCode");
        ORServeGatewayManagerImpl instance = gatewayManager;
        String expResult = "m0t3ch-d3v";
        String result = instance.getProductCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProductCode method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testSetProductCode() {
        System.out.println("setProductCode");
        String productCode = "";
        ORServeGatewayManagerImpl instance = gatewayManager;
        instance.setProductCode(productCode);
        assertEquals(productCode, instance.getProductCode());
    }

    /**
     * Test of getSenderId method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testGetSenderId() {
        System.out.println("getSenderId");
        ORServeGatewayManagerImpl instance = gatewayManager;
        String expResult = "myTXTbuddy";
        String result = instance.getSenderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSenderId method, of class ORServeGatewayManagerImpl.
     */
    @Test
    public void testSetSenderId() {
        System.out.println("setSenderId");
        String senderId = "";
        ORServeGatewayManagerImpl instance = gatewayManager;
        instance.setSenderId(senderId);
        assertEquals(senderId, instance.getSenderId());
    }

}