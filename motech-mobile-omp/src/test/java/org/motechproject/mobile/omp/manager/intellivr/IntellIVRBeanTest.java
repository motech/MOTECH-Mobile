package org.motechproject.mobile.omp.manager.intellivr;


import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.LanguageImpl;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageRequestImpl;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationType;
import org.motechproject.mobile.core.model.NotificationTypeImpl;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import org.motechproject.mobile.omp.manager.intellivr.IntellIVRBean.IVRServerTimerTask;
import org.motechproject.mobile.omp.manager.utils.MessageStatusStore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.xml.ws.tx.webservice.member.at.Notification;

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

		intellivrBean.setBundlingDelay(-1);

		MotechContext mockContext = createMock(MotechContext.class);
		
		GatewayMessageHandler mockMessageHandler = createMock(GatewayMessageHandler.class);
		intellivrBean.setMessageHandler(mockMessageHandler);
		
		MessageStatusStore mockStatusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(mockStatusStore);
		
		IntellIVRServer mockIVRServer = createMock(IntellIVRServer.class);
		intellivrBean.setIvrServer(mockIVRServer);
				
		IVRNotificationMapping m1 = new IVRNotificationMapping();
		m1.setId(1);
		m1.setIvrEntityName("tree");
		m1.setType(IVRNotificationMapping.INFORMATIONAL);
		
		IVRNotificationMapping m2 = new IVRNotificationMapping();
		m2.setId(2);
		m2.setIvrEntityName("message.wav");
		m2.setType(IVRNotificationMapping.REMINDER);
		
		IVRNotificationMapping m3 = new IVRNotificationMapping();
		m3.setId(3);
		m3.setIvrEntityName("message2.wav");
		m3.setType(IVRNotificationMapping.REMINDER);

		Map<Long, IVRNotificationMapping> mapping = new HashMap<Long, IVRNotificationMapping>();
		mapping.put(m1.getId(), m1);
		mapping.put(m2.getId(), m2);
		mapping.put(m3.getId(), m3);
		
		intellivrBean.ivrNotificationMap = mapping;

		LanguageImpl english = new LanguageImpl();
		english.setCode("en");
		english.setId(1L);
		english.setName("English");
		
		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);
		
		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId(1L);
		mr1.setLanguage(english);
		mr1.setRecipientId("123456789");
		mr1.setRequestId("mr1");
		mr1.setNotificationType(n1);
	
		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId(1000L);
		r1.setMessageRequest(mr1);
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber("15555555555");

		GatewayResponse gr1 = new GatewayResponseImpl();
		gr1.setGatewayMessageId(mr1.getRequestId());
		gr1.setGatewayRequest(r1);
		gr1.setRecipientNumber(mr1.getRecipientNumber());
		gr1.setMessageStatus(r1.getMessageStatus());
		gr1.setResponseText(StatusType.OK.value());
		
		Set<GatewayResponse> grs1 = new HashSet<GatewayResponse>();
		grs1.add(gr1);
		
		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest mr2 = new MessageRequestImpl();
		mr2.setId(2L);
		mr2.setLanguage(english);
		mr2.setRecipientId("123456789");
		mr2.setRequestId("mr2");
		mr2.setNotificationType(n2);
			
		GatewayRequest r2 = new GatewayRequestImpl();
		r2.setId(2000L);
		r2.setMessageRequest(mr2);
		r2.setMessageStatus(MStatus.PENDING);
		r2.setRecipientsNumber("15555555555");
		
		GatewayResponse gr2 = new GatewayResponseImpl();
		gr2.setGatewayMessageId(mr2.getRequestId());
		gr2.setGatewayRequest(r2);
		gr2.setRecipientNumber(mr2.getRecipientNumber());
		gr2.setMessageStatus(r2.getMessageStatus());
		gr2.setResponseText(StatusType.OK.value());
		
		Set<GatewayResponse> grs2 = new HashSet<GatewayResponse>();
		grs2.add(gr2);
		
		NotificationType n3 = new NotificationTypeImpl();
		n3.setId(3L);
		
		MessageRequest mr3 = new MessageRequestImpl();
		mr3.setId(3L);
		mr3.setLanguage(english);
		mr3.setRecipientId("123456789");
		mr3.setRequestId("mr3");
		mr3.setNotificationType(n3);
		
		GatewayRequest r3 = new GatewayRequestImpl();
		r3.setId(3000L);
		r3.setMessageRequest(mr3);
		r3.setMessageStatus(MStatus.PENDING);
		r3.setRecipientsNumber("15555555555");

		GatewayResponse gr3 = new GatewayResponseImpl();
		gr3.setGatewayMessageId(mr3.getRequestId());
		gr3.setGatewayRequest(r3);
		gr3.setRecipientNumber(mr3.getRecipientNumber());
		gr3.setMessageStatus(r3.getMessageStatus());
		gr3.setResponseText(StatusType.OK.value());
		
		Set<GatewayResponse> grs3 = new HashSet<GatewayResponse>();
		grs3.add(gr3);
		
		List<GatewayRequest> expectedGatewayRequestsList = new ArrayList<GatewayRequest>();
		expectedGatewayRequestsList.add(r1);
		expectedGatewayRequestsList.add(r2);
		expectedGatewayRequestsList.add(r3);
		
		Map<String, List<GatewayRequest>> expectedBundledRequests = new HashMap<String, List<GatewayRequest>>();
		expectedBundledRequests.put(mr1.getRecipientId(), expectedGatewayRequestsList);
		
		/*
		 * Test non-null recipient
		 */
		expect(mockMessageHandler.parseMessageResponse(r1, StatusType.OK.value(), mockContext)).andReturn(grs1);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr1.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);
		assertEquals(grs1,intellivrBean.sendMessage(r1, mockContext));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);

		expect(mockMessageHandler.parseMessageResponse(r2, StatusType.OK.value(), mockContext)).andReturn(grs2);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr2.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);
		assertEquals(grs2,intellivrBean.sendMessage(r2, mockContext));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);

		expect(mockMessageHandler.parseMessageResponse(r3, StatusType.OK.value(), mockContext)).andReturn(grs3);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr3.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);
		assertEquals(grs3,intellivrBean.sendMessage(r3, mockContext));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);
		
		assertEquals(expectedBundledRequests, intellivrBean.bundledGatewayRequests);
		
		/*
		 * Now test sendPending and see if it sends the right RequestType
		 */
		RequestType expectedRequest = intellivrBean.createIVRRequest(expectedBundledRequests.get(mr1.getRecipientId()));
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);
		
		expect(mockIVRServer.requestCall(expectedRequest)).andReturn(expectedResponse);
		replay(mockIVRServer);
		mockStatusStore.updateStatus(r1.getRequestId(), StatusType.OK.value());
		mockStatusStore.updateStatus(r2.getRequestId(), StatusType.OK.value());
		mockStatusStore.updateStatus(r3.getRequestId(), StatusType.OK.value());		
		replay(mockStatusStore);
		
		intellivrBean.sendPending(mr1.getRecipientId());
		assertFalse(intellivrBean.bundledGatewayRequests.containsKey(mr1.getRecipientId()));
		assertTrue(intellivrBean.bundledGatewayRequests.containsKey(expectedRequest.getPrivate()));
		
		verify(mockIVRServer);
		verify(mockStatusStore);
		reset(mockIVRServer);
		reset(mockStatusStore);
		
		/*
		 * Test null recipient
		 */
		mr1.setRecipientId(null);
		gr1.setResponseText(StatusType.ERROR.value());
		
		expect(mockMessageHandler.parseMessageResponse(r1, StatusType.ERROR.value(), mockContext)).andReturn(grs1);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr1.getGatewayMessageId(), StatusType.ERROR.value());
		replay(mockStatusStore);
		assertEquals(grs1,intellivrBean.sendMessage(r1, mockContext));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);
		
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

			/*MessageStatusStore statusStore = createMock(MessageStatusStore.class);
			intellivrBean.setStatusStore(statusStore);
			statusStore.updateStatus(report.getPrivate(), report.getStatus().value());
			
			replay(statusStore);*/

			ResponseType response = intellivrBean.handleReport(report);
			assertEquals(StatusType.OK, response.getStatus());
			
			//verify(statusStore);
			
		}

		
	}
	
	@Test
	public void testCreateRequestType() {
		
		IVRNotificationMapping m1 = new IVRNotificationMapping();
		m1.setId(1);
		m1.setIvrEntityName("tree");
		m1.setType(IVRNotificationMapping.INFORMATIONAL);
		
		IVRNotificationMapping m2 = new IVRNotificationMapping();
		m2.setId(2);
		m2.setIvrEntityName("message.wav");
		m2.setType(IVRNotificationMapping.REMINDER);
		
		IVRNotificationMapping m3 = new IVRNotificationMapping();
		m3.setId(3);
		m3.setIvrEntityName("message2.wav");
		m3.setType(IVRNotificationMapping.REMINDER);

		Map<Long, IVRNotificationMapping> mapping = new HashMap<Long, IVRNotificationMapping>();
		mapping.put(m1.getId(), m1);
		mapping.put(m2.getId(), m2);
		mapping.put(m3.getId(), m3);
		
		LanguageImpl english = new LanguageImpl();
		english.setCode("en");
		english.setId(1L);
		english.setName("English");
		
		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);
		
		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId(1L);
		mr1.setLanguage(english);
		mr1.setRecipientId("123456789");
		mr1.setNotificationType(n1);
	
		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId(1000L);
		r1.setMessageRequest(mr1);
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber("15555555555");

		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest mr2 = new MessageRequestImpl();
		mr2.setId(2L);
		mr2.setLanguage(english);
		mr2.setRecipientId("123456789");
		mr2.setNotificationType(n2);
			
		GatewayRequest r2 = new GatewayRequestImpl();
		r2.setId(2000L);
		r2.setMessageRequest(mr2);
		r2.setMessageStatus(MStatus.PENDING);
		r2.setRecipientsNumber("15555555555");
		
		NotificationType n3 = new NotificationTypeImpl();
		n3.setId(3L);
		
		MessageRequest mr3 = new MessageRequestImpl();
		mr3.setId(3L);
		mr3.setLanguage(english);
		mr3.setRecipientId("123456789");
		mr3.setNotificationType(n3);
		
		GatewayRequest r3 = new GatewayRequestImpl();
		r3.setId(3000L);
		r3.setMessageRequest(mr3);
		r3.setMessageStatus(MStatus.PENDING);
		r3.setRecipientsNumber("15555555555");
				
		List<GatewayRequest> requests = new ArrayList<GatewayRequest>();
		requests.add(r1);
		requests.add(r2);
		requests.add(r3);
		
		intellivrBean.ivrNotificationMap = mapping;
		
		RequestType request = intellivrBean.createIVRRequest(requests);
		
		RequestType.Vxml expectedVxml = new RequestType.Vxml();
		expectedVxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType a1 = new AudioType();
		a1.setSrc("message.wav");
		AudioType a2 = new AudioType();
		a2.setSrc("message2.wav");
		expectedVxml.getPrompt().getAudioOrBreak().add(a1);
		expectedVxml.getPrompt().getAudioOrBreak().add(a2);
				
		assertEquals("tree", request.getTree());
		assertEquals("15555555555", request.getCallee());
		assertEquals("English", request.getLanguage());
		assertEquals(intellivrBean.getApiID(), request.getApiId());
		assertEquals(intellivrBean.getReportURL(), request.getReportUrl());
		assertEquals(intellivrBean.getMethod(), request.getMethod());
		assertEquals(expectedVxml, request.getVxml());
	
	}


}
