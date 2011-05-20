package org.motechproject.mobile.omp.manager.gupshup;

import org.junit.Test;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.MStatus;

import java.util.Set;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SMSGupshupGatewayMessageHandlerTest {


    @Test
    public void shouldParseSuccessfulResponseMessage() {
        SMSGupshupGatewayMessageHandler handler = new SMSGupshupGatewayMessageHandler();

        CoreManager coreManager = createMock(CoreManager.class);

        expect(coreManager.createGatewayResponse()).andReturn(new GatewayResponseImpl());
        handler.setCoreManager(coreManager);

        replay(coreManager);

        String responseText = "success | 9886992233 | abcdv-ef-1224";
        Set<GatewayResponse> gatewayResponseSet = handler.parseMessageResponse(new GatewayRequestImpl(), responseText);

        verify(coreManager);

        assertTrue(gatewayResponseSet.size() == 1);
        GatewayResponse response = (GatewayResponse) gatewayResponseSet.toArray()[0];
        assertEquals(MStatus.DELIVERED, response.getMessageStatus());
        assertEquals("9886992233", response.getRecipientNumber());
        assertEquals(responseText, response.getResponseText());
    }

     @Test
    public void shouldParseFailedResponseMessage() {
        SMSGupshupGatewayMessageHandler handler = new SMSGupshupGatewayMessageHandler();

        CoreManager coreManager = createMock(CoreManager.class);

        expect(coreManager.createGatewayResponse()).andReturn(new GatewayResponseImpl());
        handler.setCoreManager(coreManager);

        replay(coreManager);

        String responseText = "error | 9886992233 | abcdv-ef-1224";
        Set<GatewayResponse> gatewayResponseSet = handler.parseMessageResponse(new GatewayRequestImpl(), responseText);

        verify(coreManager);

        assertTrue(gatewayResponseSet.size() == 1);
        GatewayResponse response = (GatewayResponse) gatewayResponseSet.toArray()[0];
        assertEquals(MStatus.FAILED, response.getMessageStatus());
    }
}
