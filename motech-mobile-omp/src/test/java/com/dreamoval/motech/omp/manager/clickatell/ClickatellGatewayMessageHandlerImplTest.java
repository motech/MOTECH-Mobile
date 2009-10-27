package com.dreamoval.motech.omp.manager.clickatell;

import com.dreamoval.motech.core.manager.CoreManager;
import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test fo the ORServeGaewayMessageHanlerImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class ClickatellGatewayMessageHandlerImplTest {

    CoreManager mockCoreManager;
    GatewayMessageHandler instance;
    
    public ClickatellGatewayMessageHandlerImplTest() {
    }

    @Before
    public void setUp(){
        mockCoreManager = createMock(CoreManager.class);
        instance = new ClickatellGatewayMessageHandlerImpl();
        instance.setCoreManager(mockCoreManager);
    }

    /**
     * Test of parseMessageResponse method, of class ORServeGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageResponse() {
        System.out.println("parseMessageResponse");
        GatewayRequest message = null;
        String gatewayResponse = "";
        GatewayRequest expResult = null;
        MotechContext context = new MotechContextImpl();
        Set<GatewayResponse> result = instance.parseMessageResponse(message, gatewayResponse, context);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseMessageStatus method, of class ORServeGatewayMessageHandlerImpl.
     */
    @Test
    public void testParseMessageStatus() {
        System.out.println("parseMessageStatus");
        String messageStatus = "";
        MStatus expResult = MStatus.RETRY;
        MStatus result = instance.parseMessageStatus(messageStatus);
        assertEquals(expResult, result);
    }

}