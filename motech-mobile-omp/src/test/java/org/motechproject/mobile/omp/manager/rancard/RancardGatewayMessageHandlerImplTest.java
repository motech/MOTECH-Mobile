package org.motechproject.mobile.omp.manager.rancard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Iterator;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * User: Imran, ThoughtWorks, Date: 4 May, 2011, Time: 11:45:13 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/test-omp-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class RancardGatewayMessageHandlerImplTest {

    @Autowired
    private RancardGatewayMessageHandlerImpl messageHandler;

    @Autowired
    private GatewayRequestImpl gatewayRequest;
    private final String recipientNumber = "0549959580";

    @Test
    public void shouldSetMessageStatusToSentWhenResponseMessageHasOk() {
        Set<GatewayResponse> responses = messageHandler.parseMessageResponse(gatewayRequest, "Status: Sent\nOK: " + recipientNumber);
        assertNotNull(responses);
        assertEquals(1, responses.size());
        Iterator<GatewayResponse> iterator = responses.iterator();
        GatewayResponse sentResponse = iterator.next();
        assertEquals(MStatus.SENT, sentResponse.getMessageStatus());
        assertEquals(recipientNumber, sentResponse.getRecipientNumber());


    }

    @Test
    public void shouldSetMessageStatusToFailedWhenResponseMessageHasErrorWithCode() {
        Set<GatewayResponse> responses = messageHandler.parseMessageResponse(gatewayRequest, "Status: Retry Sending\nERROR: 111");
        assertNotNull(responses);
        assertEquals(1, responses.size());
        Iterator<GatewayResponse> iterator = responses.iterator();
        GatewayResponse errorResponse = iterator.next();
        assertEquals(MStatus.FAILED, errorResponse.getMessageStatus());
        assertEquals(recipientNumber, errorResponse.getRecipientNumber());

    }

    @Test
      public void shouldSetMessageStatusToRetryWhenResponseMessageHasErrorWithUnknownCode() {
          Set<GatewayResponse> responses = messageHandler.parseMessageResponse(gatewayRequest, "Status: Retry Sending\nERROR: Unknown");
          assertNotNull(responses);
          assertEquals(1, responses.size());
          Iterator<GatewayResponse> iterator = responses.iterator();
          GatewayResponse errorResponse = iterator.next();
          assertEquals(MStatus.RETRY, errorResponse.getMessageStatus());
          assertEquals(recipientNumber, errorResponse.getRecipientNumber());

      }

     @Test
      public void shouldSetMessageStatusToCannotConnectWhenResponseMessageHasErrorWithCode901() {
          Set<GatewayResponse> responses = messageHandler.parseMessageResponse(gatewayRequest, "Status: Retry Sending\nERROR: 901");
          assertNotNull(responses);
          assertEquals(1, responses.size());
          Iterator<GatewayResponse> iterator = responses.iterator();
          GatewayResponse errorResponse = iterator.next();
          assertEquals(MStatus.CANNOT_CONNECT, errorResponse.getMessageStatus());
          assertEquals(recipientNumber, errorResponse.getRecipientNumber());

      }


}
