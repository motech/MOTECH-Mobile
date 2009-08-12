/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.DummyGatewayManagerImpl;
import com.dreamoval.motech.omp.manager.DummyGatewayMessageHandlerImpl;
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

}