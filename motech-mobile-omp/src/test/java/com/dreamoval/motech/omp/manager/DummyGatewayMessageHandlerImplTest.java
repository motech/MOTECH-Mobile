/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */

package com.dreamoval.motech.omp.manager;

import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import com.dreamoval.motech.core.model.ResponseDetailsImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DummyGatewayMessageHandlerImplTest {

    CoreManager mockCoreManager;
    GatewayMessageHandler instance;

    public DummyGatewayMessageHandlerImplTest() {
    }

    @Before
    public void setUp(){
        instance = new DummyGatewayMessageHandlerImpl();
        mockCoreManager = createMock(CoreManager.class);
        instance.setCoreManager(mockCoreManager);
    }

    /**
     * Test of parseMessageResponse method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageResponse() {
        System.out.println("parseMessageResponse");
        MessageDetails message = new MessageDetailsImpl();
        String gatewayResponse = "sent";

        expect(
                mockCoreManager.createResponseDetails((MotechContext) anyObject())
                ).andReturn(new ResponseDetailsImpl());
        expect(
                mockCoreManager.createMotechContext()
                ).andReturn(new MotechContextImpl());
        replay(mockCoreManager);

        Set result = instance.parseMessageResponse(message, gatewayResponse);
        assertNotNull(result);
        verify(mockCoreManager);
    }

    /**
     * Test of parseMessageStatus method, of class DummyGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageStatus() {
        System.out.println("parseMessageStatus");
        String messageStatus = "";
        String expResult = "delivered";
        String result = instance.parseMessageStatus(messageStatus);
        assertEquals(expResult, result);
    }

}