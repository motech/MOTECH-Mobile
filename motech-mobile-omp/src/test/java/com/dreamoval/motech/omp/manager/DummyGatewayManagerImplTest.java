/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import java.util.Set;
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
public class DummyGatewayManagerImplTest {

    @Autowired
    DummyGatewayManagerImpl dummyGateway;

    public DummyGatewayManagerImplTest() {
    }

    /**
     * Test of sendMessage method, of class DummyGatewayManagerImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        MessageDetails messageDetails = null;
        Set<ResponseDetails> result = dummyGateway.sendMessage(messageDetails);
        assertNotNull(result);
    }

    /**
     * Test of getMessageStatus method, of class DummyGatewayManagerImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        String gatewayMessageId = "";
        String expResult = "delivered";
        String result = dummyGateway.getMessageStatus(gatewayMessageId);
        assertEquals(expResult, result);
    }

}