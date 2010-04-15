package org.motechproject.mobile.omp.manager.intellivr;


import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import org.motechproject.mobile.omp.manager.utils.MessageStatusStore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:META-INF/test-omp-config.xml"})
public class IntellIVRBeanTest {

	@Resource
	IntellIVRBean intellivrBean;
	
	Map<String, MStatus> statusCodes = new HashMap<String, MStatus>();
	
	@Before
	public void setUp() throws Exception {
		
		statusCodes.put("0000", MStatus.CANCELLED);
		statusCodes.put("0001", MStatus.CANCELLED);
		statusCodes.put("0002", MStatus.CANCELLED);
		statusCodes.put("0003", MStatus.CANCELLED);
		statusCodes.put("0004", MStatus.CANCELLED);
		statusCodes.put("0005", MStatus.CANCELLED);
		statusCodes.put("0006", MStatus.CANCELLED);
		statusCodes.put("0007", MStatus.CANCELLED);
		statusCodes.put("0008", MStatus.CANCELLED);
		statusCodes.put("0009", MStatus.CANCELLED);
		statusCodes.put("0010", MStatus.CANCELLED);
		statusCodes.put("0011", MStatus.CANCELLED);
		statusCodes.put("OK", MStatus.PENDING);
		statusCodes.put("COMPLETED", MStatus.DELIVERED);
		statusCodes.put("REJECTED", MStatus.FAILED);
		statusCodes.put("BUSY", MStatus.FAILED);
		statusCodes.put("CONGESTION", MStatus.FAILED);
		statusCodes.put("NOANSWER", MStatus.FAILED);
		statusCodes.put("INTERNALERROR", MStatus.FAILED);
		
	}
	
	@Test
	public void testHandleGetIVRConfigTestData() {
		
		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid("123456789");
		
		ResponseType expected = new ResponseType();
		expected.setStatus(StatusType.OK);
		expected.setLanguage(intellivrBean.getDefaultLanguage());
		expected.setPrivate("123456789");
		expected.setReportUrl(intellivrBean.getReportURL());
		expected.setTree(intellivrBean.getDefaultTree());
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType audio = new AudioType();
		audio.setSrc(intellivrBean.getDefaultReminder());
		vxml.getPrompt().getAudioOrBreak().add(audio);
		expected.setVxml(vxml);
		
		ResponseType response = intellivrBean.handleRequest(request);
		
		assertEquals(expected, response);
		
	}
	
	@Test
	public void testGetIVRConfigInvalidID() {
		
		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid("invalid_id");
		
		ResponseType expected = new ResponseType();
		expected.setStatus(StatusType.ERROR);
		expected.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
		
		ResponseType response = intellivrBean.handleRequest(request);
		
		assertEquals(expected.getStatus(), response.getStatus());
		assertEquals(expected.getErrorCode(), response.getErrorCode());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSendMessage() {
		
		String testPhone = "+0115555555555";
		String testRequestID = "test_request_id";
		
		GatewayRequest request = new GatewayRequestImpl();
		request.setRecipientsNumber(testPhone);
		request.setRequestId(testRequestID);

		RequestType r = new RequestType();
		r.setApiId(intellivrBean.getApiID());
		r.setCallee(testPhone);
		r.setMethod(intellivrBean.getMethod());
		r.setLanguage(intellivrBean.getDefaultLanguage());
		r.setPrivate(testRequestID);
		r.setReportUrl(intellivrBean.getReportURL());
		r.setTree(intellivrBean.getDefaultTree());
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType audio = new AudioType();
		audio.setSrc(intellivrBean.getDefaultReminder());
		vxml.getPrompt().getAudioOrBreak().add(audio);
		r.setVxml(vxml);
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);
		
		IntellIVRServer ivrServer = createMock(IntellIVRServer.class);
		intellivrBean.setIvrServer(ivrServer);
		
		GatewayMessageHandler messageHandler = createMock(GatewayMessageHandler.class);
		intellivrBean.setMessageHandler(messageHandler);

		MessageStatusStore statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);
		
		MotechContext context = createMock(MotechContext.class);
		
		expect(ivrServer.requestCall(r)).andReturn(expectedResponse);
		replay(ivrServer);

		Set<GatewayResponse> responseSet = new HashSet<GatewayResponse>();
		GatewayResponse gwResponse = new GatewayResponseImpl();
		gwResponse.setGatewayMessageId(testRequestID);
		gwResponse.setResponseText(StatusType.OK.value());
		responseSet.add(gwResponse);
		
		expect(messageHandler.parseMessageResponse(request, StatusType.OK.value(), context))
			.andReturn(responseSet);
		replay(messageHandler);
		
		statusStore.updateStatus(testRequestID, StatusType.OK.value());
		replay(statusStore);

		intellivrBean.sendMessage(request, context);
		
		verify(ivrServer);
		verify(messageHandler);
		verify(statusStore);
				
	}
	
	@Test
	public void testHandleReport() {
		
		ReportType report = new ReportType();
		report.setCallee("15555555555");
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setDuration(0);
		report.setINTELLIVREntryCount(0);
		report.setPrivate("PRIVATE");
		report.intellivrEntry = new ArrayList<IvrEntryType>();

		for ( ReportStatusType type : ReportStatusType.values()) {
			
			report.setStatus(type);
			
			MessageStatusStore statusStore = createMock(MessageStatusStore.class);
			intellivrBean.setStatusStore(statusStore);
			statusStore.updateStatus(report.getPrivate(), report.getStatus().value());
			
			replay(statusStore);

			ResponseType response = intellivrBean.handleReport(report);
			assertEquals(StatusType.OK, response.getStatus());
			
			verify(statusStore);
			
		}

	}
	
	@Test
	public void testGetMessageStatus() {
		
		for ( String code: statusCodes.keySet() ) {

			MessageStatusStore statusStore = createMock(MessageStatusStore.class);
			statusStore.updateStatus("testid", code);
			intellivrBean.setStatusStore(statusStore);
			
			reset(statusStore);
			
			GatewayResponse response = new GatewayResponseImpl();
			response.setGatewayMessageId("testid");
			
			expect(statusStore.getStatus("testid")).andReturn(code);
			
			replay(statusStore);
			
			String actual = intellivrBean.getMessageStatus(response);
			
			assertEquals(code, actual);
			
			verify(statusStore);
			
		}
			
		
	}

	@Test
	public void testMapMessageStatus() {
		
		for ( String code: statusCodes.keySet() ) {

			GatewayMessageHandler messageHandler = createMock(GatewayMessageHandler.class);
			
			intellivrBean.setMessageHandler(messageHandler);
			
			GatewayResponse response = new GatewayResponseImpl();
			response.setResponseText(code);
			
			expect(messageHandler.lookupStatus(code)).andReturn(statusCodes.get(code));
			
			replay(messageHandler);
			
			MStatus actual = intellivrBean.mapMessageStatus(response);
			
			assertEquals(statusCodes.get(code), actual);
			
			verify(messageHandler);
			
		}
			
		
	}

	
}
