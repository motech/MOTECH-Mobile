/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */

package com.dreamoval.motech.omp.manager;

import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DummyGatewayManagerImplTest {

    GatewayMessageHandler mockHandler;

    public DummyGatewayManagerImplTest() {
    }

    @Before
    public void setUp(){
        mockHandler = createMock(GatewayMessageHandler.class);
    }
    
    /**
     * Test of sendMessage method, of class DummyGatewayManagerImpl.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        MessageDetails messageDetails = null;
        GatewayManager instance = new DummyGatewayManagerImpl();
        instance.setMessageHandler(mockHandler);

        expect(
                mockHandler.parseMessageResponse(messageDetails, "sent")
                ).andReturn(new HashSet<ResponseDetails>());
        replay(mockHandler);
        
        Set<ResponseDetails> result = instance.sendMessage(messageDetails);
        assertNotNull(result);
        verify(mockHandler);
    }

    /**
     * Test of getMessageStatus method, of class DummyGatewayManagerImpl.
     */
    @Test
    public void testGetMessageStatus() {
        System.out.println("getMessageStatus");
        String gatewayMessageId = "";
        String expResult = "delivered";
        GatewayManager instance = new DummyGatewayManagerImpl();
        instance.setMessageHandler(mockHandler);

        expect(
                mockHandler.parseMessageStatus((String) anyObject())
                ).andReturn("delivered");
        replay(mockHandler);

        String result = instance.getMessageStatus(gatewayMessageId);
        assertEquals(expResult, result);
        verify(mockHandler);
    }

}