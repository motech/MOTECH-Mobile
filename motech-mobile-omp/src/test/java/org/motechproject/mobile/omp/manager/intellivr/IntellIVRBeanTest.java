package org.motechproject.mobile.omp.manager.intellivr;


import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
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
	
	@Before
	public void setUp() throws Exception {
		
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
		
		String testPhone = "00015555555555";
		String testRequestID = "test_request_id";
		String testReminderID1 = "test1";
		String testReminderID2 = "test2";
		String testReminderFile1 = "test1.wav";
		String testReminderFile2 = "test2.wav";
		String testWeek = "1";
		String testTree = "api_test";
		String testLanguage = "English";
		String testMessage = testReminderID1 + "," + testReminderID2 + ":" + testWeek + ":" + testLanguage;
		
		GatewayRequest request = new GatewayRequestImpl();
		request.setRecipientsNumber(testPhone);
		request.setRequestId(testRequestID);
		request.setMessage(testMessage);

		RequestType expectedRequest = new RequestType();
		expectedRequest.setApiId(intellivrBean.getApiID());
		expectedRequest.setCallee(testPhone);
		expectedRequest.setMethod(intellivrBean.getMethod());
		expectedRequest.setLanguage(testLanguage);
		expectedRequest.setPrivate(testRequestID);
		expectedRequest.setReportUrl(intellivrBean.getReportURL());
		expectedRequest.setTree(testTree);
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType audio1 = new AudioType();
		audio1.setSrc(testReminderFile1);
		vxml.getPrompt().getAudioOrBreak().add(audio1);
		AudioType audio2 = new AudioType();
		audio2.setSrc(testReminderFile2);
		vxml.getPrompt().getAudioOrBreak().add(audio2);
		expectedRequest.setVxml(vxml);
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);
		
		IntellIVRServer ivrServer = createMock(IntellIVRServer.class);
		intellivrBean.setIvrServer(ivrServer);
		
		GatewayMessageHandler messageHandler = createMock(GatewayMessageHandler.class);
		intellivrBean.setMessageHandler(messageHandler);

		MessageStatusStore statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);
		
		MotechContext context = createMock(MotechContext.class);
		
		expect(ivrServer.requestCall(expectedRequest)).andReturn(expectedResponse);
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

}
