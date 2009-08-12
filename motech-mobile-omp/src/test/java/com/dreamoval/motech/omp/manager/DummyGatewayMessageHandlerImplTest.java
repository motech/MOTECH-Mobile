package com.dreamoval.motech.omp.manager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
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
public class DummyGatewayMessageHandlerImplTest {

    @Autowired
    DummyGatewayMessageHandlerImpl dummyHandler;

    public DummyGatewayMessageHandlerImplTest() {
    }

    /**
     * Test of prepareMessage method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testPrepareMessage() {
        System.out.println("prepareMessage");
        String message = "";
        MessageDetails result = dummyHandler.prepareMessage(message);
        assertNotNull(result);
    }

    /**
     * Test of parseMessageResponse method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageResponse() {
        System.out.println("parseMessageResponse");
        MessageDetails message = new MessageDetailsImpl();
        String gatewayResponse = "sent";
        Set result = dummyHandler.parseMessageResponse(message, gatewayResponse);
        assertNotNull(result);
    }

    /**
     * Test of parseMessageStatus method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageStatus() {
        System.out.println("parseMessageStatus");
        String messageStatus = "";
        String expResult = "delivered";
        String result = dummyHandler.parseMessageStatus(messageStatus);
        assertEquals(expResult, result);
    }

}