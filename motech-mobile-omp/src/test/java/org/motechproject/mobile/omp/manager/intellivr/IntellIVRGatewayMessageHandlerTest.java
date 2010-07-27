package org.motechproject.mobile.omp.manager.intellivr;


import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageRequestImpl;
import org.motechproject.mobile.core.service.MotechContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:META-INF/test-omp-config.xml"})
public class IntellIVRGatewayMessageHandlerTest {

	@Resource
	IntellIVRGatewayMessageHandler intellIVRMessageHandler;
	CoreManager coreManager;
	
	Map<String, MStatus> statusCodes = new HashMap<String, MStatus>();
	
	@Before
	public void setUp() throws Exception {
		
		statusCodes.put("0000", MStatus.FAILED);
		statusCodes.put("0001", MStatus.FAILED);
		statusCodes.put("0002", MStatus.FAILED);
		statusCodes.put("0003", MStatus.FAILED);
		statusCodes.put("0004", MStatus.FAILED);
		statusCodes.put("0005", MStatus.FAILED);
		statusCodes.put("0006", MStatus.FAILED);
		statusCodes.put("0007", MStatus.FAILED);
		statusCodes.put("0008", MStatus.FAILED);
		statusCodes.put("0009", MStatus.FAILED);
		statusCodes.put("0010", MStatus.FAILED);
		statusCodes.put("0011", MStatus.FAILED);
		statusCodes.put("ERROR", MStatus.FAILED);
		statusCodes.put("OK", MStatus.PENDING);
		statusCodes.put("COMPLETED", MStatus.DELIVERED);
		statusCodes.put("REJECTED", MStatus.PENDING);
		statusCodes.put("BUSY", MStatus.PENDING);
		statusCodes.put("CONGESTION", MStatus.PENDING);
		statusCodes.put("NOANSWER", MStatus.PENDING);
		statusCodes.put("INTERNALERROR", MStatus.PENDING);
		
	}
	
	@Test
	public void testLookupStatus() {
		
		for ( String code : statusCodes.keySet())
			assertEquals(statusCodes.get(code), intellIVRMessageHandler.lookupStatus(code));

	}

	@Test
	public void testLookupResponse() {
		
		for ( String code : statusCodes.keySet())
			assertEquals(statusCodes.get(code), intellIVRMessageHandler.lookupResponse(code));
		
	}

	@Test
	public void testParseMessageStatus() {
		
		for ( String code : statusCodes.keySet()) 
			assertEquals(statusCodes.get(code), intellIVRMessageHandler.parseMessageStatus(code));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testParseMessageResponse() {
				
		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId("31000000001");
		mr1.setRecipientId("123456789");
		mr1.setRequestId("mr1");
	
		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId("31000000002");
		r1.setMessageRequest(mr1);
		r1.setRequestId(mr1.getRequestId());
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber("15555555555");
		
		for ( String code : statusCodes.keySet()) {

			GatewayResponse gwResponse = new GatewayResponseImpl();
			gwResponse.setId("31000000003");

			MotechContext context = createMock(MotechContext.class);
			
			coreManager = createMock(CoreManager.class);
			intellIVRMessageHandler.setCoreManager(coreManager);
			
			expect(coreManager.createGatewayResponse()).andReturn(gwResponse);			
			replay(coreManager);
			
			Set<GatewayResponse> responses = intellIVRMessageHandler.parseMessageResponse(r1, code);
			
			for ( GatewayResponse response : responses ) {
				assertTrue(response.getDateCreated()!= null);
				assertEquals(r1, response.getGatewayRequest());
				assertEquals(mr1.getId().toString(), response.getGatewayMessageId());
				assertEquals(statusCodes.get(code), response.getMessageStatus());
				assertEquals("15555555555", response.getRecipientNumber());
				assertEquals("mr1", response.getRequestId());
				assertEquals(code, response.getResponseText());
			}
			
			verify(coreManager);
			
		}
		
		
	}

	
}
