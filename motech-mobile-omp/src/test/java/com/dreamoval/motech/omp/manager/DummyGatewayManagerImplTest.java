/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class DummyGatewayManagerImplTest {

    public DummyGatewayManagerImplTest() {
    }

    /**
     * Test of sendMessage method, of class DummyGatewayManagerImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        MessageDetails messageDetails = null;
        GatewayManager instance = new DummyGatewayManagerImpl();
        String expResult = "sent";
        String result = instance.sendMessage(messageDetails);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageStatus method, of class DummyGatewayManagerImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        String gatewayMessageId = "";
        DummyGatewayManagerImpl instance = new DummyGatewayManagerImpl();
        String expResult = "delivered";
        String result = instance.getMessageStatus(gatewayMessageId);
        assertEquals(expResult, result);
    }

}