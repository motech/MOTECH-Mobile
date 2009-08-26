/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.manager.CoreManager;
import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ORServeGatewayMessageHandlerImplTest {

    CoreManager mockCoreManager;
    GatewayMessageHandler instance;
    
    public ORServeGatewayMessageHandlerImplTest() {
    }

    @Before
    public void setUp(){
        mockCoreManager = createMock(CoreManager.class);
        instance = new ORServeGatewayMessageHandlerImpl();
        instance.setCoreManager(mockCoreManager);
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
        String expResult = "failed";
        String result = instance.parseMessageStatus(messageStatus);
        assertEquals(expResult, result);
    }

}