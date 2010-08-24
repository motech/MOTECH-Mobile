package org.motechproject.mobile.omp.manager.intellivr;


import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.dao.GatewayRequestDAO;
import org.motechproject.mobile.core.dao.MessageRequestDAO;
import org.motechproject.mobile.core.manager.CoreManager;
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
import org.motechproject.mobile.omp.manager.utils.MessageStatusStore;
import org.motechproject.ws.server.RegistrarService;
import org.motechproject.ws.server.ValidationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:META-INF/test-omp-config.xml"})
public class IntellIVRBeanTest {

	@Resource
	IntellIVRBean intellivrBean;

	List<ErrorCodeType> serverErrorCodes = new ArrayList<ErrorCodeType>();

	@Before
	public void setUp() throws Exception {

		serverErrorCodes.add(ErrorCodeType.IVR_BAD_REQUEST);
		serverErrorCodes.add(ErrorCodeType.IVR_INVALID_API_ID);
		serverErrorCodes.add(ErrorCodeType.IVR_INVALID_CALLEE);
		serverErrorCodes.add(ErrorCodeType.IVR_INVALID_LANGUAGE);
		serverErrorCodes.add(ErrorCodeType.IVR_INVALID_METHOD);
		serverErrorCodes.add(ErrorCodeType.IVR_INVALID_SOUND_FILENAME_FORMAT);
		serverErrorCodes.add(ErrorCodeType.IVR_INVALID_TREE);
		serverErrorCodes.add(ErrorCodeType.IVR_INVALID_URL_FORMAT);
		serverErrorCodes.add(ErrorCodeType.IVR_MALFORMED_XML);
		serverErrorCodes.add(ErrorCodeType.IVR_NO_ACTION);
		serverErrorCodes.add(ErrorCodeType.IVR_UNKNOWN_ERROR);
		serverErrorCodes.add(ErrorCodeType.IVR_UNSUPPORTED_REPORT_TYPE);

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

	}

	@After
	public void tearDown() {

		intellivrBean.ivrSessions.clear();

		try {
			intellivrBean.getIvrSessionSerialResource().getFile().delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	@SuppressWarnings("unchecked")
	@Test
	public void testSendMessage() {

		String recipient1 = "1";
		String phone1 = "5555555";
		String recipient2 = "2";
		String phone2 = "5555556";

		intellivrBean.setBundlingDelay(-1);
		intellivrBean.setAvailableDays(1);

		GatewayMessageHandler mockMessageHandler = createMock(GatewayMessageHandler.class);
		intellivrBean.setMessageHandler(mockMessageHandler);

		MessageStatusStore mockStatusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(mockStatusStore);

		IntellIVRServer mockIVRServer = createMock(IntellIVRServer.class);
		intellivrBean.setIvrServer(mockIVRServer);

		Language english = new LanguageImpl();
		english.setCode("en");
		english.setId(30000000000l);
		english.setName("English");
		
		Date expectedDateFrom = new Date();
		
		GregorianCalendar dateToCal = new GregorianCalendar();
		dateToCal.setTime(expectedDateFrom);
		dateToCal.add(GregorianCalendar.DAY_OF_MONTH, intellivrBean.getAvailableDays());
		Date expectedDataTo = dateToCal.getTime();

		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);

		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId(30000000001l);
		mr1.setDaysAttempted(1);
		mr1.setLanguage(english);
		mr1.setRecipientId(recipient1);
		mr1.setRequestId("mr1");
		mr1.setMessageType(MessageType.VOICE);
		mr1.setNotificationType(n1);
		mr1.setPhoneNumberType("PERSONAL");
		mr1.setDateFrom(expectedDateFrom);

		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId(30000000002l);
		r1.setMessageRequest(mr1);
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber(phone1);

		GatewayResponse gr1 = new GatewayResponseImpl();
		gr1.setGatewayMessageId(mr1.getId().toString());
		gr1.setGatewayRequest(r1);
		gr1.setRecipientNumber(mr1.getRecipientNumber());
		gr1.setMessageStatus(r1.getMessageStatus());
		gr1.setResponseText(StatusType.OK.value());

		Set<GatewayResponse> grs1 = new HashSet<GatewayResponse>();
		grs1.add(gr1);

		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest mr2 = new MessageRequestImpl();
		mr2.setId(30000000003l);
		mr2.setDaysAttempted(1);
		mr2.setLanguage(english);
		mr2.setRecipientId(recipient1);
		mr2.setRequestId("mr2");
		mr2.setMessageType(MessageType.VOICE);
		mr2.setNotificationType(n2);
		mr2.setPhoneNumberType("PERSONAL");
		mr2.setDateFrom(expectedDateFrom);

		GatewayRequest r2 = new GatewayRequestImpl();
		r2.setId(30000000004l);
		r2.setMessageRequest(mr2);
		r2.setMessageStatus(MStatus.PENDING);
		r2.setRecipientsNumber(phone1);

		GatewayResponse gr2 = new GatewayResponseImpl();
		gr2.setGatewayMessageId(mr2.getId().toString());
		gr2.setGatewayRequest(r2);
		gr2.setRecipientNumber(mr2.getRecipientNumber());
		gr2.setMessageStatus(r2.getMessageStatus());
		gr2.setResponseText(StatusType.OK.value());

		Set<GatewayResponse> grs2 = new HashSet<GatewayResponse>();
		grs2.add(gr2);

		NotificationType n3 = new NotificationTypeImpl();
		n3.setId(3L);

		MessageRequest mr3 = new MessageRequestImpl();
		mr3.setId(30000000005l);
		mr3.setDaysAttempted(3);
		mr3.setLanguage(english);
		mr3.setRecipientId(recipient2);
		mr3.setRequestId("mr3");
		mr3.setMessageType(MessageType.VOICE);
		mr3.setNotificationType(n3);
		mr3.setPhoneNumberType("PERSONAL");
		mr3.setDateFrom(expectedDateFrom);

		GatewayRequest r3 = new GatewayRequestImpl();
		r3.setId(30000000006l);
		r3.setMessageRequest(mr3);
		r3.setMessageStatus(MStatus.PENDING);
		r3.setRecipientsNumber(phone2);

		GatewayResponse gr3 = new GatewayResponseImpl();
		gr3.setGatewayMessageId(mr3.getId().toString());
		gr3.setGatewayRequest(r3);
		gr3.setRecipientNumber(mr3.getRecipientNumber());
		gr3.setMessageStatus(r3.getMessageStatus());
		gr3.setResponseText(StatusType.OK.value());

		Set<GatewayResponse> grs3 = new HashSet<GatewayResponse>();
		grs3.add(gr3);

		NotificationType n4 = new NotificationTypeImpl();
		n4.setId(4L);

		MessageRequest mr4 = new MessageRequestImpl();
		mr4.setId(30000000007l);
		mr4.setLanguage(english);
		mr4.setRecipientId(recipient1);
		mr4.setRequestId("mr4");
		mr4.setNotificationType(n4);
		mr4.setMessageType(MessageType.VOICE);
		mr4.setPhoneNumberType("PUBLIC");

		GatewayRequest r4 = new GatewayRequestImpl();
		r4.setId(30000000008l);
		r4.setMessageRequest(mr4);
		r4.setMessageStatus(MStatus.PENDING);
		r4.setRecipientsNumber("15555555556");

		GatewayResponse gr4 = new GatewayResponseImpl();
		gr4.setGatewayMessageId(mr4.getId().toString());
		gr4.setGatewayRequest(r4);
		gr4.setRecipientNumber(mr4.getRecipientNumber());
		gr4.setMessageStatus(r4.getMessageStatus());
		gr4.setResponseText(StatusType.OK.value());

		Set<GatewayResponse> grs4 = new HashSet<GatewayResponse>();
		grs4.add(gr4);

		NotificationType n5 = new NotificationTypeImpl();
		n5.setId(5L);

		MessageRequest mr5 = new MessageRequestImpl();
		mr5.setId(30000000009l);
		mr5.setLanguage(english);
		mr5.setRecipientId(recipient1);
		mr5.setRequestId("mr5");
		mr5.setNotificationType(n5);
		mr5.setMessageType(MessageType.TEXT);
		mr5.setPhoneNumberType("PERSONAL");

		GatewayRequest r5 = new GatewayRequestImpl();
		r5.setId(30000000010l);
		r5.setMessageRequest(mr5);
		r5.setMessageStatus(MStatus.PENDING);
		r5.setRecipientsNumber(phone1);

		GatewayResponse gr5 = new GatewayResponseImpl();
		gr5.setGatewayMessageId(mr5.getId().toString());
		gr5.setGatewayRequest(r5);
		gr5.setRecipientNumber(mr5.getRecipientNumber());
		gr5.setMessageStatus(r5.getMessageStatus());
		gr5.setResponseText(StatusType.ERROR.value());

		Set<GatewayResponse> grs5 = new HashSet<GatewayResponse>();
		grs5.add(gr5);

		IVRSession expectedSession1 = new IVRSession(recipient1, phone1, english.getName(), mr1.getDaysAttempted());
		expectedSession1.addGatewayRequest(r1);
		expectedSession1.addGatewayRequest(r2);

		IVRSession expectedSession2 = new IVRSession(recipient2, phone2, english.getName(), mr3.getDaysAttempted());
		expectedSession2.addGatewayRequest(r3);

		Map<String, IVRSession> expectedIVRSessions = new HashMap<String, IVRSession>();
		expectedIVRSessions.put(expectedSession1.getSessionId(),expectedSession1);
		expectedIVRSessions.put(expectedSession2.getSessionId(),expectedSession2);

		/*
		 * Test non-null recipient
		 */
		expect(mockMessageHandler.parseMessageResponse(r1, StatusType.OK.value())).andReturn(grs1);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr1.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);
		assertEquals(grs1,intellivrBean.sendMessage(r1));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);

		expect(mockMessageHandler.parseMessageResponse(r2, StatusType.OK.value())).andReturn(grs2);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr2.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);
		assertEquals(grs2,intellivrBean.sendMessage(r2));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);

		expect(mockMessageHandler.parseMessageResponse(r3, StatusType.OK.value())).andReturn(grs3);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr3.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);
		assertEquals(grs3,intellivrBean.sendMessage(r3));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);

		expect(mockMessageHandler.parseMessageResponse(r4, StatusType.OK.value())).andReturn(grs4);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr4.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);
		assertEquals(grs4,intellivrBean.sendMessage(r4));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);

		expect(mockMessageHandler.parseMessageResponse(r5, StatusType.ERROR.value())).andReturn(grs5);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr5.getGatewayMessageId(), StatusType.ERROR.value());
		replay(mockStatusStore);
		assertEquals(grs5,intellivrBean.sendMessage(r5));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);

		IVRSession actualSession1 = null;
		IVRSession actualSession2 = null;
		String actualSession1Id = null;
		String actualSession2Id = null;

		assertEquals(2, intellivrBean.ivrSessions.size());

		for ( String key : intellivrBean.ivrSessions.keySet() ) {

			IVRSession actualSession = intellivrBean.ivrSessions.get(key);

			if ( actualSession.getUserId().equalsIgnoreCase(expectedSession1.getUserId())) {
				assertEquals(expectedSession1.getPhone(), actualSession.getPhone());
				assertEquals(mr1.getDaysAttempted(), actualSession.getDays());
				assertTrue(actualSession.getGatewayRequests().contains(r1));
				assertTrue(actualSession.getGatewayRequests().contains(r2));
				for ( GatewayRequest gr : actualSession.getGatewayRequests())
					assertEquals(expectedDataTo, gr.getMessageRequest().getDateTo());
				actualSession1 = actualSession;
				actualSession1Id = actualSession.getSessionId();
			}

			if ( actualSession.getUserId().equalsIgnoreCase(expectedSession2.getUserId())) {
				assertEquals(expectedSession2.getPhone(), actualSession.getPhone());
				assertEquals(mr3.getDaysAttempted(), actualSession.getDays());
				assertTrue(actualSession.getGatewayRequests().contains(r3));
				for ( GatewayRequest gr : actualSession.getGatewayRequests())
					assertEquals(expectedDataTo, gr.getMessageRequest().getDateTo());
				actualSession2 = actualSession;
				actualSession2Id = actualSession.getSessionId();
			}

		}

		/*
		 * Now test sendPending and see if it sends the right RequestType
		 */
		RequestType expectedRequest = intellivrBean.createIVRRequest(actualSession1);

		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);

		expect(mockIVRServer.requestCall(expectedRequest)).andReturn(expectedResponse);
		replay(mockIVRServer);
		mockStatusStore.updateStatus(gr1.getGatewayMessageId(), StatusType.OK.value());
		mockStatusStore.updateStatus(gr2.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);

		intellivrBean.sendPending(actualSession1Id);

		verify(mockIVRServer);
		verify(mockStatusStore);
		reset(mockIVRServer);
		reset(mockStatusStore);

		expectedRequest = intellivrBean.createIVRRequest(actualSession2);

		expectedResponse.setStatus(StatusType.OK);

		expect(mockIVRServer.requestCall(expectedRequest)).andReturn(expectedResponse);
		replay(mockIVRServer);
		mockStatusStore.updateStatus(gr3.getGatewayMessageId(), StatusType.OK.value());
		replay(mockStatusStore);

		intellivrBean.sendPending(actualSession2Id);

		verify(mockIVRServer);
		verify(mockStatusStore);
		reset(mockIVRServer);
		reset(mockStatusStore);

		/*
		 * test sendPending with errors
		 */
		for ( Iterator<ErrorCodeType> iterator = serverErrorCodes.iterator(); iterator.hasNext();) {

			intellivrBean.ivrSessions.put(actualSession1.getSessionId(), actualSession1);

			ErrorCodeType errorCode = iterator.next();

			expectedRequest = intellivrBean.createIVRRequest(actualSession1);

			expectedResponse = new ResponseType();
			expectedResponse.setStatus(StatusType.ERROR);
			expectedResponse.setErrorCode(errorCode);

			expect(mockIVRServer.requestCall(expectedRequest)).andReturn(expectedResponse);
			replay(mockIVRServer);
			mockStatusStore.updateStatus(gr1.getGatewayMessageId(), errorCode.value());
			mockStatusStore.updateStatus(gr2.getGatewayMessageId(), errorCode.value());
			replay(mockStatusStore);

			intellivrBean.sendPending(actualSession1Id);

			verify(mockIVRServer);
			verify(mockStatusStore);
			reset(mockIVRServer);
			reset(mockStatusStore);

		}


		/*
		 * Test null recipient
		 */
		mr1.setRecipientId(null);
		gr1.setResponseText(StatusType.ERROR.value());

		expect(mockMessageHandler.parseMessageResponse(r1, StatusType.ERROR.value())).andReturn(grs1);
		replay(mockMessageHandler);
		mockStatusStore.updateStatus(gr1.getGatewayMessageId(), StatusType.ERROR.value());
		replay(mockStatusStore);
		assertEquals(grs1,intellivrBean.sendMessage(r1));
		verify(mockMessageHandler);
		verify(mockStatusStore);
		reset(mockMessageHandler);
		reset(mockStatusStore);

	}

	@Test
	public void testSendPending() {

		String recipient1 = "1";
		String phone1 = "5555555";

		Language english = new LanguageImpl();
		english.setCode("en");
		english.setId(30000000011l);
		english.setName("English");

		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);

		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId(30000000012l);
		mr1.setLanguage(english);
		mr1.setRecipientId(recipient1);
		mr1.setRequestId("mr1");
		mr1.setMessageType(MessageType.VOICE);
		mr1.setNotificationType(n1);
		mr1.setPhoneNumberType("PERSONAL");

		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId(30000000013l);
		r1.setMessageRequest(mr1);
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber(phone1);

		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest mr2 = new MessageRequestImpl();
		mr2.setId(30000000014l);
		mr2.setLanguage(english);
		mr2.setRecipientId(recipient1);
		mr2.setRequestId("mr2");
		mr2.setMessageType(MessageType.VOICE);
		mr2.setNotificationType(n2);
		mr2.setPhoneNumberType("PERSONAL");

		GatewayRequest r2 = new GatewayRequestImpl();
		r2.setId(30000000015l);
		r2.setMessageRequest(mr2);
		r2.setMessageStatus(MStatus.PENDING);
		r2.setRecipientsNumber(phone1);

		IVRSession session = new IVRSession(recipient1, phone1, english.getName());
		session.addGatewayRequest(r1);
		session.addGatewayRequest(r2);

		Map<String, IVRSession> expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		IntellIVRServer mockServer = createMock(IntellIVRServer.class);
		intellivrBean.setIvrServer(mockServer);

		RequestType expectedRequest = intellivrBean.createIVRRequest(session);

		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);

		expect(mockServer.requestCall(expectedRequest)).andReturn(expectedResponse);
		replay(mockServer);

		assertEquals(IVRSession.OPEN, session.getState());

		intellivrBean.sendPending(session.getSessionId());

		verify(mockServer);

		assertEquals(1, session.getAttempts());
		assertEquals(IVRSession.REPORT_WAIT, session.getState());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testHandleReport() {

		String recipientID = "123456789";
		String phone = "15555555555";
		GregorianCalendar start = new GregorianCalendar(2010, 1, 1, 12, 00);
		GregorianCalendar end = new GregorianCalendar(2010, 1, 1, 13, 00);

		Language english = new LanguageImpl();
		english.setCode("en");
		english.setId(30000000016l);
		english.setName("English");

		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);

		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId(30000000017l);
		mr1.setLanguage(english);
		mr1.setRecipientId(recipientID);
		mr1.setRequestId("mr1");
		mr1.setNotificationType(n1);
		mr1.setPhoneNumberType("PERSONAL");

		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId(30000000018l);
		r1.setMessageRequest(mr1);
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber(phone);
		r1.setDateFrom(start.getTime());
		r1.setDateTo(end.getTime());

		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest mr2 = new MessageRequestImpl();
		mr2.setId(30000000019l);
		mr2.setLanguage(english);
		mr2.setRecipientId(recipientID);
		mr2.setRequestId("mr2");
		mr2.setNotificationType(n2);
		mr2.setPhoneNumberType("PERSONAL");

		GatewayRequest r2 = new GatewayRequestImpl();
		r2.setId(30000000020l);
		r2.setMessageRequest(mr2);
		r2.setMessageStatus(MStatus.PENDING);
		r2.setRecipientsNumber(phone);
		r2.setDateFrom(start.getTime());
		r2.setDateTo(end.getTime());

		NotificationType n3 = new NotificationTypeImpl();
		n3.setId(3L);

		MessageRequest mr3 = new MessageRequestImpl();
		mr3.setId(30000000021l);
		mr3.setLanguage(english);
		mr3.setRecipientId(recipientID);
		mr3.setRequestId("mr3");
		mr3.setNotificationType(n3);
		mr3.setPhoneNumberType("PERSONAL");

		GatewayRequest r3 = new GatewayRequestImpl();
		r3.setId(30000000022l);
		r3.setMessageRequest(mr3);
		r3.setMessageStatus(MStatus.PENDING);
		r3.setRecipientsNumber(phone);
		r3.setDateFrom(start.getTime());
		r3.setDateTo(end.getTime());

		IVRNotificationMapping m1 = new IVRNotificationMapping();
		m1.setId(1);
		m1.setIvrEntityName("info");
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

		Map<String, Long> ivrReminderIds = new HashMap<String, Long>();
		ivrReminderIds.put("message.wav", 2L);
		ivrReminderIds.put("message2.wav", 3L);

		intellivrBean.ivrReminderIds = ivrReminderIds;

		intellivrBean.setCallCompletedThreshold(30);

		//COMPLETED - Mixed reminder/info messages - over threshold
		IVRSession session = new IVRSession(recipientID, phone, english.getName());

		ReportType report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		//session = new IVRSession(recipientID, phone, english.getName());
		session.setAttempts(1);
		session.setDays(0);
		session.addGatewayRequest(r1);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);

		Map<String, IVRSession> expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		MessageStatusStore statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(30);
		report.setINTELLIVREntryCount(2);

		IvrEntryType b1 = new IvrEntryType();
		b1.setDuration(0);
		b1.setMenu("break");
		b1.setKeypress("1s");
		
		IvrEntryType w1 = new IvrEntryType();
		w1.setDuration(0);
		w1.setMenu("welcome.wav");
		w1.setKeypress("1s");

		IvrEntryType e1 = new IvrEntryType();
		e1.setDuration(10);
		e1.setMenu("message.wav");
		e1.setKeypress("");

		IvrEntryType e2 = new IvrEntryType();
		e2.setDuration(10);
		e2.setMenu("message2.wav");
		e2.setKeypress("");

		IvrEntryType e3 = new IvrEntryType();
		e3.setDuration(30);
		e3.setMenu("info1.wav");
		e3.setKeypress("");

		List<IvrEntryType> entryList = new ArrayList<IvrEntryType>();
		entryList.add(b1);
		entryList.add(w1);
		entryList.add(e1);
		entryList.add(e2);
		entryList.add(e3);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr1.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		ResponseType response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);


		//COMPLETED - Only reminder messages - over threshold
		session = new IVRSession(recipientID, phone, english.getName());

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		//session = new IVRSession(recipientID, phone, english.getName());
		session.setAttempts(1);
		session.setDays(0);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(30);
		report.setINTELLIVREntryCount(2);

		e1 = new IvrEntryType();
		e1.setDuration(15);
		e1.setMenu("message.wav");
		e1.setKeypress("");

		e2 = new IvrEntryType();
		e2.setDuration(15);
		e2.setMenu("message2.wav");
		e2.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e1);
		entryList.add(e2);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);

		//COMPLETED - Only reminder messages - under threshold
		session = new IVRSession(recipientID, phone, english.getName());

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		//session = new IVRSession(recipientID, phone, english.getName());
		session.setAttempts(1);
		session.setDays(0);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(20);
		report.setINTELLIVREntryCount(2);

		e1 = new IvrEntryType();
		e1.setDuration(10);
		e1.setMenu("message.wav");
		e1.setKeypress("");

		e2 = new IvrEntryType();
		e2.setDuration(10);
		e2.setMenu("message2.wav");
		e2.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e1);
		entryList.add(e2);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);


		//COMPLETED - server initiated - only info messages - over threshold
		session = new IVRSession(recipientID, phone, english.getName());

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		//session = new IVRSession(recipientID, phone, english.getName());
		session.setAttempts(1);
		session.setDays(0);
		session.addGatewayRequest(r1);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(30);
		report.setINTELLIVREntryCount(2);

		e3 = new IvrEntryType();
		e3.setDuration(30);
		e3.setMenu("info1.wav");
		e3.setKeypress("");

		IvrEntryType e4 = new IvrEntryType();
		e4.setDuration(10);
		e4.setMenu("info2.wav");
		e4.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e3);
		entryList.add(e4);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr1.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);


		//COMPLETED - user initiated - info, reminder, and id prompt messages - over threshold
		session = new IVRSession(recipientID);

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		session.setAttempts(1);
		session.setDays(0);
		session.addGatewayRequest(r1);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(105);
		report.setINTELLIVREntryCount(2);

		IvrEntryType e5 = new IvrEntryType();
		e5.setDuration(5);
		e5.setMenu("prompt.wav");
		e5.setKeypress("");

		b1 = new IvrEntryType();
		b1.setDuration(0);
		b1.setMenu("break");
		b1.setKeypress("1s");

		e1 = new IvrEntryType();
		e1.setDuration(15);
		e1.setMenu("message.wav");
		e1.setKeypress("");

		e2 = new IvrEntryType();
		e2.setDuration(15);
		e2.setMenu("message2.wav");
		e2.setKeypress("");

		e3 = new IvrEntryType();
		e3.setDuration(30);
		e3.setMenu("info1.wav");
		e3.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e5);
		entryList.add(b1);
		entryList.add(e1);
		entryList.add(e2);
		entryList.add(e3);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr1.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);


		//COMPLETED - user initiated - info and id prompt messages - over threshold
		session = new IVRSession(recipientID);

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		session.setAttempts(1);
		session.setDays(0);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(35);
		report.setINTELLIVREntryCount(2);

		e5 = new IvrEntryType();
		e5.setDuration(5);
		e5.setMenu("prompt.wav");
		e5.setKeypress("");

		e1 = new IvrEntryType();
		e1.setDuration(15);
		e1.setMenu("message.wav");
		e1.setKeypress("");

		e2 = new IvrEntryType();
		e2.setDuration(15);
		e2.setMenu("message2.wav");
		e2.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e5);
		entryList.add(e1);
		entryList.add(e2);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);

		//COMPLETED - user initiated - info and id prompt messages - over threshold
		session = new IVRSession(recipientID);

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		session.setAttempts(1);
		session.setDays(0);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(30);
		report.setINTELLIVREntryCount(2);

		e5 = new IvrEntryType();
		e5.setDuration(5);
		e5.setMenu("prompt.wav");
		e5.setKeypress("");

		e3 = new IvrEntryType();
		e3.setDuration(5);
		e3.setMenu("info1.wav");
		e3.setKeypress("");

		e4 = new IvrEntryType();
		e4.setDuration(20);
		e4.setMenu("info2.wav");
		e4.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e5);
		entryList.add(e3);
		entryList.add(e4);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);

		//COMPLETED - user initiated replay - info, reminder, and id prompt messages - over threshold
		session = new IVRSession(recipientID);

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		session.setAttempts(1);
		session.setDays(0);
		r1.getMessageRequest().setStatus(MStatus.DELIVERED);
		session.addGatewayRequest(r1);
		r2.getMessageRequest().setStatus(MStatus.DELIVERED);
		session.addGatewayRequest(r2);
		r3.getMessageRequest().setStatus(MStatus.DELIVERED);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(105);
		report.setINTELLIVREntryCount(2);

		e5 = new IvrEntryType();
		e5.setDuration(5);
		e5.setMenu("prompt.wav");
		e5.setKeypress("");

		e1 = new IvrEntryType();
		e1.setDuration(15);
		e1.setMenu("message.wav");
		e1.setKeypress("");

		e2 = new IvrEntryType();
		e2.setDuration(15);
		e2.setMenu("message2.wav");
		e2.setKeypress("");

		e3 = new IvrEntryType();
		e3.setDuration(30);
		e3.setMenu("info1.wav");
		e3.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e5);
		entryList.add(e1);
		entryList.add(e2);
		entryList.add(e3);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr1.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
		statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);

		//COMPLETED - user initiated replay - info, reminder, and id prompt messages - under threshold
		session = new IVRSession(recipientID);

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		session.setAttempts(1);
		session.setDays(0);
		r1.getMessageRequest().setStatus(MStatus.DELIVERED);
		session.addGatewayRequest(r1);
		r2.getMessageRequest().setStatus(MStatus.DELIVERED);
		session.addGatewayRequest(r2);
		r3.getMessageRequest().setStatus(MStatus.DELIVERED);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		report.setDuration(90);
		report.setINTELLIVREntryCount(2);

		e5 = new IvrEntryType();
		e5.setDuration(5);
		e5.setMenu("prompt.wav");
		e5.setKeypress("");

		e1 = new IvrEntryType();
		e1.setDuration(15);
		e1.setMenu("message.wav");
		e1.setKeypress("");

		e2 = new IvrEntryType();
		e2.setDuration(15);
		e2.setMenu("message2.wav");
		e2.setKeypress("");

		e3 = new IvrEntryType();
		e3.setDuration(15);
		e3.setMenu("info1.wav");
		e3.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e5);
		entryList.add(e1);
		entryList.add(e2);
		entryList.add(e3);

		report.intellivrEntry = entryList;

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());

		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		reset(statusStore);

		r1.getMessageRequest().setStatus(MStatus.PENDING);
		r2.getMessageRequest().setStatus(MStatus.PENDING);
		r3.getMessageRequest().setStatus(MStatus.PENDING);

		//test failures outbound calls
		for ( ReportStatusType type : ReportStatusType.values()) {

			session = new IVRSession(recipientID, phone, english.getName());

			report = new ReportType();
			report.setCallee(phone);
			report.setConnectTime(null);
			report.setDisconnectTime(null);
			report.setPrivate(session.getSessionId());
			report.setStatus(type);

			session.setAttempts(1);
			session.setDays(0);
			session.addGatewayRequest(r1);
			session.addGatewayRequest(r2);
			session.addGatewayRequest(r3);

			expectedSessions = new HashMap<String, IVRSession>();
			expectedSessions.put(session.getSessionId(), session);

			intellivrBean.ivrSessions = expectedSessions;

			statusStore = createMock(MessageStatusStore.class);
			intellivrBean.setStatusStore(statusStore);

			intellivrBean.setRetryDelay(-1);
			intellivrBean.setMaxAttempts(2);
			intellivrBean.setMaxDays(2);

			//completed but below call completed threshold value
			if ( type == ReportStatusType.COMPLETED ) {

				intellivrBean.setCallCompletedThreshold(30);

				report.setDuration(49);
				report.setINTELLIVREntryCount(2);

				e1 = new IvrEntryType();
				e1.setDuration(10);
				e1.setMenu("message.wav");
				e1.setKeypress("");

				e2 = new IvrEntryType();
				e2.setDuration(10);
				e2.setMenu("message2.wav");
				e2.setKeypress("");

				e3 = new IvrEntryType();
				e3.setDuration(29);
				e3.setMenu("info1.wav");
				e3.setKeypress("");

				entryList = new ArrayList<IvrEntryType>();
				entryList.add(e1);
				entryList.add(e2);
				entryList.add(e3);

				report.intellivrEntry = entryList;

				statusStore.updateStatus(mr1.getId().toString(), "BELOWTHRESHOLD");
				statusStore.updateStatus(mr2.getId().toString(), "BELOWTHRESHOLD");
				statusStore.updateStatus(mr3.getId().toString(), "BELOWTHRESHOLD");

			} else {

				report.setDuration(0);
				report.setINTELLIVREntryCount(0);
				report.intellivrEntry = null;

				statusStore.updateStatus(mr1.getId().toString(), report.getStatus().value());
				statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
				statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());

			}

			replay(statusStore);

			assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

			response = intellivrBean.handleReport(report);

			assertEquals(StatusType.OK, response.getStatus());
			assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

			verify(statusStore);
			reset(statusStore);

			/*
			 * last attempt on first day
			 */
			session.setAttempts(2);

			GregorianCalendar expectedNewStart = new GregorianCalendar(2010, 1, 2, 12, 00);
			GregorianCalendar expectedNewEnd = new GregorianCalendar(2010, 1, 2, 13, 00);

			CoreManager mockCoreManager = createMock(CoreManager.class);
			intellivrBean.setCoreManager(mockCoreManager);
			GatewayRequestDAO<GatewayRequest> mockGatewayRequestDAO = createMock(GatewayRequestDAO.class);

			
			expect(mockCoreManager.createGatewayRequestDAO()).andReturn(mockGatewayRequestDAO);

			GatewayRequest mockGatewayRequest1 = createMock(GatewayRequest.class);
			GatewayRequest mockGatewayRequest2 = createMock(GatewayRequest.class);
			GatewayRequest mockGatewayRequest3 = createMock(GatewayRequest.class);

			expect(mockGatewayRequestDAO.getById(EasyMock.anyInt())).andReturn(mockGatewayRequest1);

			expect(mockGatewayRequest1.getDateFrom()).andReturn(start.getTime());
			expect(mockGatewayRequest1.getDateTo()).andReturn(end.getTime());
			mockGatewayRequest1.setDateFrom(expectedNewStart.getTime());
			mockGatewayRequest1.setDateTo(expectedNewEnd.getTime());
			mockGatewayRequest1.setMessageStatus(MStatus.SCHEDULED);
			
			expect(mockGatewayRequestDAO.save(mockGatewayRequest1)).andReturn(mockGatewayRequest1);

			expect(mockGatewayRequestDAO.getById(EasyMock.anyInt())).andReturn(mockGatewayRequest2);

			expect(mockGatewayRequest2.getDateFrom()).andReturn(start.getTime());
			expect(mockGatewayRequest2.getDateTo()).andReturn(end.getTime());
			mockGatewayRequest2.setDateFrom(expectedNewStart.getTime());
			mockGatewayRequest2.setDateTo(expectedNewEnd.getTime());
			mockGatewayRequest2.setMessageStatus(MStatus.SCHEDULED);
			
			expect(mockGatewayRequestDAO.save(mockGatewayRequest2)).andReturn(mockGatewayRequest2);

			expect(mockGatewayRequestDAO.getById(EasyMock.anyInt())).andReturn(mockGatewayRequest3);

			expect(mockGatewayRequest3.getDateFrom()).andReturn(start.getTime());
			expect(mockGatewayRequest3.getDateTo()).andReturn(end.getTime());
			mockGatewayRequest3.setDateFrom(expectedNewStart.getTime());
			mockGatewayRequest3.setDateTo(expectedNewEnd.getTime());
			mockGatewayRequest3.setMessageStatus(MStatus.SCHEDULED);
			
			expect(mockGatewayRequestDAO.save(mockGatewayRequest3)).andReturn(mockGatewayRequest3);

			MessageRequest mockMessageRequest1 = createMock(MessageRequest.class);
			MessageRequest mockMessageRequest2 = createMock(MessageRequest.class);
			MessageRequest mockMessageRequest3 = createMock(MessageRequest.class);

			MessageRequestDAO<MessageRequest> mockMessageRequestDAO = createMock(MessageRequestDAO.class);

			expect(mockCoreManager.createMessageRequestDAO()).andReturn(mockMessageRequestDAO);

			expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest1);
			mockMessageRequest1.setDaysAttempted(1);
			
			expect(mockMessageRequestDAO.save(mockMessageRequest1)).andReturn(mockMessageRequest1);

			expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest2);
			mockMessageRequest2.setDaysAttempted(1);
			
			expect(mockMessageRequestDAO.save(mockMessageRequest2)).andReturn(mockMessageRequest2);

			expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest3);
			mockMessageRequest3.setDaysAttempted(1);
			
			expect(mockMessageRequestDAO.save(mockMessageRequest3)).andReturn(mockMessageRequest3);

	

			if ( type == ReportStatusType.COMPLETED ) {
				statusStore.updateStatus(mr1.getId().toString(), "BELOWTHRESHOLD");
				statusStore.updateStatus(mr2.getId().toString(), "BELOWTHRESHOLD");
				statusStore.updateStatus(mr3.getId().toString(), "BELOWTHRESHOLD");
			} else {
				statusStore.updateStatus(mr1.getId().toString(), report.getStatus().value());
				statusStore.updateStatus(mr2.getId().toString(), report.getStatus().value());
				statusStore.updateStatus(mr3.getId().toString(), report.getStatus().value());
			}

			replay(statusStore);
			replay(mockCoreManager);
		
			replay(mockGatewayRequest1);
			replay(mockGatewayRequest2);
			replay(mockGatewayRequest3);
			replay(mockGatewayRequestDAO);
			replay(mockMessageRequest1);
			replay(mockMessageRequest2);
			replay(mockMessageRequest3);
			replay(mockMessageRequestDAO);
	

			assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

			response = intellivrBean.handleReport(report);

			assertEquals(StatusType.OK, response.getStatus());
			assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

			verify(mockCoreManager);

			verify(mockGatewayRequestDAO);

			verify(mockGatewayRequest1);
			verify(mockGatewayRequest2);
			verify(mockGatewayRequest3);
			verify(mockMessageRequest1);
			verify(mockMessageRequest2);
			verify(mockMessageRequest3);
			verify(mockMessageRequestDAO);
			verify(statusStore);
			reset(mockCoreManager);

			reset(mockGatewayRequestDAO);
	
			reset(mockGatewayRequest1);
			reset(mockGatewayRequest2);
			reset(mockGatewayRequest3);
			reset(mockMessageRequest1);
			reset(mockMessageRequest2);
			reset(mockMessageRequest3);
			reset(mockMessageRequestDAO);
			reset(statusStore);

			/*
			 * last attempt on last day
			 */
			 expectedSessions.put(session.getSessionId(), session);
			intellivrBean.ivrSessions = expectedSessions;
			session.setAttempts(2);
			session.setDays(1);

			
			expect(mockCoreManager.createMessageRequestDAO()).andReturn(mockMessageRequestDAO);

			expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest1);
			mockMessageRequest1.setDaysAttempted(2);
			
			expect(mockMessageRequestDAO.save(mockMessageRequest1)).andReturn(mockMessageRequest1);

			expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest2);
			mockMessageRequest2.setDaysAttempted(2);
			
			expect(mockMessageRequestDAO.save(mockMessageRequest2)).andReturn(mockMessageRequest2);

			expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest3);
			mockMessageRequest3.setDaysAttempted(2);
			
			expect(mockMessageRequestDAO.save(mockMessageRequest3)).andReturn(mockMessageRequest3);

	

			statusStore.updateStatus(mr1.getId().toString(), "MAXATTEMPTS");
			statusStore.updateStatus(mr2.getId().toString(), "MAXATTEMPTS");
			statusStore.updateStatus(mr3.getId().toString(), "MAXATTEMPTS");

			replay(mockCoreManager);
		
			replay(mockMessageRequest1);
			replay(mockMessageRequest2);
			replay(mockMessageRequest3);
			replay(mockMessageRequestDAO);
			replay(statusStore);

			assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

			response = intellivrBean.handleReport(report);

			assertEquals(2, session.getDays());
			assertEquals(StatusType.OK, response.getStatus());
			assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

			verify(mockCoreManager);
		
			verify(mockMessageRequest1);
			verify(mockMessageRequest2);
			verify(mockMessageRequest3);
			verify(mockMessageRequestDAO);
			verify(statusStore);
			reset(mockCoreManager);
		
			reset(mockMessageRequest1);
			reset(mockMessageRequest2);
			reset(mockMessageRequest3);
			reset(mockMessageRequestDAO);
			reset(statusStore);

		}

		/*
		 * An additional outbound case for complete but below threshold call
		 * User has an informational message pending but hangs up before listening to it
		 *
		 */
		session = new IVRSession(recipientID, phone, english.getName());

		report = new ReportType();
		report.setCallee(phone);
		report.setConnectTime(null);
		report.setDisconnectTime(null);
		report.setPrivate(session.getSessionId());
		report.setStatus(ReportStatusType.COMPLETED);

		session.setAttempts(1);
		session.setDays(0);
		session.addGatewayRequest(r1);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);

		expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(session.getSessionId(), session);

		intellivrBean.ivrSessions = expectedSessions;

		statusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(statusStore);

		intellivrBean.setRetryDelay(-1);
		intellivrBean.setMaxAttempts(2);
		intellivrBean.setMaxDays(2);

		intellivrBean.setCallCompletedThreshold(30);

		report.setDuration(40);
		report.setINTELLIVREntryCount(2);

		e1 = new IvrEntryType();
		e1.setDuration(20);
		e1.setMenu("message.wav");
		e1.setKeypress("");

		e2 = new IvrEntryType();
		e2.setDuration(20);
		e2.setMenu("message2.wav");
		e2.setKeypress("");

		entryList = new ArrayList<IvrEntryType>();
		entryList.add(e1);
		entryList.add(e2);

		report.intellivrEntry = entryList;

		statusStore.updateStatus(mr1.getId().toString(), "BELOWTHRESHOLD");
		statusStore.updateStatus(mr2.getId().toString(), "BELOWTHRESHOLD");
		statusStore.updateStatus(mr3.getId().toString(), "BELOWTHRESHOLD");

		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());
		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(statusStore);
		reset(statusStore);

		/*
		 * last attempt on first day
		 */
		session.setAttempts(2);

		GregorianCalendar expectedNewStart = new GregorianCalendar(2010, 1, 2, 12, 00);
		GregorianCalendar expectedNewEnd = new GregorianCalendar(2010, 1, 2, 13, 00);

		MotechContext mockContext = createMock(MotechContext.class);

		CoreManager mockCoreManager = createMock(CoreManager.class);
		intellivrBean.setCoreManager(mockCoreManager);

	

		GatewayRequestDAO<GatewayRequest> mockGatewayRequestDAO = createMock(GatewayRequestDAO.class);

	
		expect(mockCoreManager.createGatewayRequestDAO()).andReturn(mockGatewayRequestDAO);

		GatewayRequest mockGatewayRequest1 = createMock(GatewayRequest.class);
		GatewayRequest mockGatewayRequest2 = createMock(GatewayRequest.class);
		GatewayRequest mockGatewayRequest3 = createMock(GatewayRequest.class);

		expect(mockGatewayRequestDAO.getById(EasyMock.anyInt())).andReturn(mockGatewayRequest1);

		expect(mockGatewayRequest1.getDateFrom()).andReturn(start.getTime());
		expect(mockGatewayRequest1.getDateTo()).andReturn(end.getTime());
		mockGatewayRequest1.setDateFrom(expectedNewStart.getTime());
		mockGatewayRequest1.setDateTo(expectedNewEnd.getTime());
		mockGatewayRequest1.setMessageStatus(MStatus.SCHEDULED);
		
		expect(mockGatewayRequestDAO.save(mockGatewayRequest1)).andReturn(mockGatewayRequest1);

		expect(mockGatewayRequestDAO.getById(EasyMock.anyInt())).andReturn(mockGatewayRequest2);

		expect(mockGatewayRequest2.getDateFrom()).andReturn(start.getTime());
		expect(mockGatewayRequest2.getDateTo()).andReturn(end.getTime());
		mockGatewayRequest2.setDateFrom(expectedNewStart.getTime());
		mockGatewayRequest2.setDateTo(expectedNewEnd.getTime());
		mockGatewayRequest2.setMessageStatus(MStatus.SCHEDULED);
		
		expect(mockGatewayRequestDAO.save(mockGatewayRequest2)).andReturn(mockGatewayRequest2);

		expect(mockGatewayRequestDAO.getById(EasyMock.anyInt())).andReturn(mockGatewayRequest3);

		expect(mockGatewayRequest3.getDateFrom()).andReturn(start.getTime());
		expect(mockGatewayRequest3.getDateTo()).andReturn(end.getTime());
		mockGatewayRequest3.setDateFrom(expectedNewStart.getTime());
		mockGatewayRequest3.setDateTo(expectedNewEnd.getTime());
		mockGatewayRequest3.setMessageStatus(MStatus.SCHEDULED);
		
		expect(mockGatewayRequestDAO.save(mockGatewayRequest3)).andReturn(mockGatewayRequest3);

		MessageRequest mockMessageRequest1 = createMock(MessageRequest.class);
		MessageRequest mockMessageRequest2 = createMock(MessageRequest.class);
		MessageRequest mockMessageRequest3 = createMock(MessageRequest.class);

		MessageRequestDAO<MessageRequest> mockMessageRequestDAO = createMock(MessageRequestDAO.class);

		expect(mockCoreManager.createMessageRequestDAO()).andReturn(mockMessageRequestDAO);

		expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest1);
		mockMessageRequest1.setDaysAttempted(1);
		
		expect(mockMessageRequestDAO.save(mockMessageRequest1)).andReturn(mockMessageRequest1);

		expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest2);
		mockMessageRequest2.setDaysAttempted(1);
		
		expect(mockMessageRequestDAO.save(mockMessageRequest2)).andReturn(mockMessageRequest2);

		expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest3);
		mockMessageRequest3.setDaysAttempted(1);
		
		expect(mockMessageRequestDAO.save(mockMessageRequest3)).andReturn(mockMessageRequest3);

	

		statusStore.updateStatus(mr1.getId().toString(), "BELOWTHRESHOLD");
		statusStore.updateStatus(mr2.getId().toString(), "BELOWTHRESHOLD");
		statusStore.updateStatus(mr3.getId().toString(), "BELOWTHRESHOLD");

		replay(statusStore);
		replay(mockCoreManager);
	
		replay(mockGatewayRequest1);
		replay(mockGatewayRequest2);
		replay(mockGatewayRequest3);
		replay(mockGatewayRequestDAO);
		replay(mockMessageRequest1);
		replay(mockMessageRequest2);
		replay(mockMessageRequest3);
		replay(mockMessageRequestDAO);


		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(StatusType.OK, response.getStatus());
		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(mockCoreManager);

		verify(mockGatewayRequestDAO);

		verify(mockGatewayRequest1);
		verify(mockGatewayRequest2);
		verify(mockGatewayRequest3);
		verify(mockMessageRequest1);
		verify(mockMessageRequest2);
		verify(mockMessageRequest3);
		verify(mockMessageRequestDAO);
		verify(statusStore);
		reset(mockCoreManager);

		reset(mockGatewayRequestDAO);

		reset(mockGatewayRequest1);
		reset(mockGatewayRequest2);
		reset(mockGatewayRequest3);
		reset(mockMessageRequest1);
		reset(mockMessageRequest2);
		reset(mockMessageRequest3);
		reset(mockMessageRequestDAO);
		reset(statusStore);

		/*
		 * last attempt on last day
		 */
		 expectedSessions.put(session.getSessionId(), session);
		intellivrBean.ivrSessions = expectedSessions;
		session.setAttempts(2);
		session.setDays(1);

	
		expect(mockCoreManager.createMessageRequestDAO()).andReturn(mockMessageRequestDAO);

		expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest1);
		mockMessageRequest1.setDaysAttempted(2);
	
		expect(mockMessageRequestDAO.save(mockMessageRequest1)).andReturn(mockMessageRequest1);

		expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest2);
		mockMessageRequest2.setDaysAttempted(2);
		
		expect(mockMessageRequestDAO.save(mockMessageRequest2)).andReturn(mockMessageRequest2);

		expect(mockMessageRequestDAO.getById(EasyMock.anyInt())).andReturn(mockMessageRequest3);
		mockMessageRequest3.setDaysAttempted(2);
	
		expect(mockMessageRequestDAO.save(mockMessageRequest3)).andReturn(mockMessageRequest3);



		statusStore.updateStatus(mr1.getId().toString(), "MAXATTEMPTS");
		statusStore.updateStatus(mr2.getId().toString(), "MAXATTEMPTS");
		statusStore.updateStatus(mr3.getId().toString(), "MAXATTEMPTS");

		replay(mockCoreManager);

		replay(mockMessageRequest1);
		replay(mockMessageRequest2);
		replay(mockMessageRequest3);
		replay(mockMessageRequestDAO);
		replay(statusStore);

		assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		response = intellivrBean.handleReport(report);

		assertEquals(2, session.getDays());
		assertEquals(StatusType.OK, response.getStatus());
		assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		verify(mockCoreManager);


		verify(mockMessageRequest1);
		verify(mockMessageRequest2);
		verify(mockMessageRequest3);
		verify(mockMessageRequestDAO);
		verify(statusStore);
		reset(mockCoreManager);
	
		reset(mockMessageRequest1);
		reset(mockMessageRequest2);
		reset(mockMessageRequest3);
		reset(mockMessageRequestDAO);
		reset(statusStore);

		//test report on inbound call - under threshold
		for ( ReportStatusType type : ReportStatusType.values()) {

			session = new IVRSession(recipientID);

			report = new ReportType();
			report.setCallee(phone);
			report.setConnectTime(null);
			report.setDisconnectTime(null);
			report.setPrivate(session.getSessionId());
			report.setStatus(type);

			session.setAttempts(1);
			session.setDays(0);
			session.addGatewayRequest(r1);
			session.addGatewayRequest(r2);
			session.addGatewayRequest(r3);

			expectedSessions = new HashMap<String, IVRSession>();
			expectedSessions.put(session.getSessionId(), session);

			intellivrBean.ivrSessions = expectedSessions;

			intellivrBean.setRetryDelay(-1);
			intellivrBean.setMaxAttempts(2);
			intellivrBean.setMaxDays(2);

			intellivrBean.setCallCompletedThreshold(30);

			report.setDuration(49);
			report.setINTELLIVREntryCount(2);

			e1 = new IvrEntryType();
			e1.setDuration(10);
			e1.setMenu("message.wav");
			e1.setKeypress("");

			e2 = new IvrEntryType();
			e2.setDuration(10);
			e2.setMenu("message2.wav");
			e2.setKeypress("");

			e3 = new IvrEntryType();
			e3.setDuration(29);
			e3.setMenu("info1.wav");
			e3.setKeypress("");

			e4 = new IvrEntryType();
			e4.setDuration(10);
			e4.setMenu("prompt.wav");
			e4.setKeypress("");

			entryList = new ArrayList<IvrEntryType>();
			entryList.add(e4);
			entryList.add(e1);
			entryList.add(e2);
			entryList.add(e3);

			report.intellivrEntry = entryList;

			assertTrue(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

			response = intellivrBean.handleReport(report);

			assertEquals(StatusType.OK, response.getStatus());
			assertFalse(intellivrBean.ivrSessions.containsKey(session.getSessionId()));

		}

	}

	@Test
	public void testCreateRequestType() throws ParseException {

		String recipient = "123456789";
		String phone = "5555555555";

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

		IVRNotificationMapping m4 = new IVRNotificationMapping();
		m4.setId(4);
		m4.setIvrEntityName("tree2");
		m4.setType(IVRNotificationMapping.INFORMATIONAL);

		Map<Long, IVRNotificationMapping> mapping = new HashMap<Long, IVRNotificationMapping>();
		mapping.put(m1.getId(), m1);
		mapping.put(m2.getId(), m2);
		mapping.put(m3.getId(), m3);
		mapping.put(m4.getId(), m4);

		LanguageImpl english = new LanguageImpl();
		english.setCode("en");
		english.setId(30000000023l);
		english.setName("English");

		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setDateFrom(sdf.parse("2010-01-01"));
		mr1.setId(30000000024l);
		mr1.setLanguage(english);
		mr1.setRecipientId(recipient);
		mr1.setNotificationType(n1);

		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId(30000000025l);
		r1.setMessageRequest(mr1);
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber(phone);

		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest mr2 = new MessageRequestImpl();
		mr2.setId(30000000026l);
		mr2.setLanguage(english);
		mr2.setRecipientId(recipient);
		mr2.setNotificationType(n2);

		GatewayRequest r2 = new GatewayRequestImpl();
		r2.setId(30000000027l);
		r2.setMessageRequest(mr2);
		r2.setMessageStatus(MStatus.PENDING);
		r2.setRecipientsNumber(phone);

		NotificationType n3 = new NotificationTypeImpl();
		n3.setId(3L);

		MessageRequest mr3 = new MessageRequestImpl();
		mr3.setId(30000000028l);
		mr3.setLanguage(english);
		mr3.setRecipientId(recipient);
		mr3.setNotificationType(n3);

		GatewayRequest r3 = new GatewayRequestImpl();
		r3.setId(30000000029l);
		r3.setMessageRequest(mr3);
		r3.setMessageStatus(MStatus.PENDING);
		r3.setRecipientsNumber(phone);

		NotificationType n4 = new NotificationTypeImpl();
		n4.setId(4L);

		MessageRequest mr4 = new MessageRequestImpl();
		mr4.setDateFrom(sdf.parse("2010-01-02"));
		mr4.setId(30000000030l);
		mr4.setLanguage(english);
		mr4.setRecipientId(recipient);
		mr4.setNotificationType(n4);

		GatewayRequest r4 = new GatewayRequestImpl();
		r4.setId(30000000031l);
		r4.setMessageRequest(mr4);
		r4.setMessageStatus(MStatus.PENDING);
		r4.setRecipientsNumber(phone);

		IVRSession session = new IVRSession(recipient, phone, english.getName());
		session.addGatewayRequest(r1);
		session.addGatewayRequest(r2);
		session.addGatewayRequest(r3);
		session.addGatewayRequest(r4);

		intellivrBean.ivrNotificationMap = mapping;
		intellivrBean.setPreReminderDelay(1);
		intellivrBean.setWelcomeMessageRecordingName("welcome.wav");

		RequestType request = intellivrBean.createIVRRequest(session);

		RequestType.Vxml expectedVxml = new RequestType.Vxml();
		expectedVxml.setPrompt(new RequestType.Vxml.Prompt());
		BreakType b1 = new BreakType();
		b1.setTime("1s");
		AudioType w1 = new AudioType();
		w1.setSrc("welcome.wav");
		AudioType a1 = new AudioType();
		a1.setSrc("message.wav");
		AudioType a2 = new AudioType();
		a2.setSrc("message2.wav");
		expectedVxml.getPrompt().getAudioOrBreak().add(b1);
		expectedVxml.getPrompt().getAudioOrBreak().add(w1);
		expectedVxml.getPrompt().getAudioOrBreak().add(a1);
		expectedVxml.getPrompt().getAudioOrBreak().add(a2);

		assertEquals("tree2", request.getTree());
		assertEquals("5555555555", request.getCallee());
		assertEquals("English", request.getLanguage());
		assertEquals(intellivrBean.getApiID(), request.getApiId());
		assertEquals(intellivrBean.getReportURL(), request.getReportUrl());
		assertEquals(intellivrBean.getMethod(), request.getMethod());
		assertEquals(expectedVxml, request.getVxml());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testHandleRequest() throws NumberFormatException, ValidationException {

		String recipientID = "1";

		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid(recipientID);

		Language english = new LanguageImpl();
		english.setCode("en");
		english.setId(30000000032l);
		english.setName("English");

		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);

		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId(30000000033l);
		mr1.setLanguage(english);
		mr1.setRecipientId(recipientID);
		mr1.setRequestId("mr1");
		mr1.setNotificationType(n1);
		mr1.setStatus(MStatus.PENDING);

		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest mr2 = new MessageRequestImpl();
		mr2.setId(30000000034l);
		mr2.setLanguage(english);
		mr2.setRecipientId(recipientID);
		mr2.setRequestId("mr2");
		mr2.setNotificationType(n2);
		mr2.setStatus(MStatus.PENDING);

		NotificationType n3 = new NotificationTypeImpl();
		n3.setId(3L);

		MessageRequest mr3 = new MessageRequestImpl();
		mr3.setId(30000000035l);
		mr3.setLanguage(english);
		mr3.setRecipientId(recipientID);
		mr3.setRequestId("mr3");
		mr3.setNotificationType(n3);
		mr3.setStatus(MStatus.PENDING);

		List<MessageRequest> expectedDAOResponse = new ArrayList<MessageRequest>();
		expectedDAOResponse.add(mr1);
		expectedDAOResponse.add(mr2);
		expectedDAOResponse.add(mr3);

		GatewayRequest gr1 = new GatewayRequestImpl();
		gr1.setMessageRequest(mr1);

		GatewayRequest gr2 = new GatewayRequestImpl();
		gr2.setMessageRequest(mr2);

		GatewayRequest gr3 = new GatewayRequestImpl();
		gr3.setMessageRequest(mr3);

		IVRSession expectedSession = new IVRSession(recipientID);
		expectedSession.addGatewayRequest(gr1);
		expectedSession.addGatewayRequest(gr2);
		expectedSession.addGatewayRequest(gr3);

		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setPrivate(expectedSession.getSessionId());
		expectedResponse.setReportUrl(intellivrBean.getReportURL());
		expectedResponse.setStatus(StatusType.OK);
		expectedResponse.setTree("tree");
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType a1 = new AudioType();
		a1.setSrc("message.wav");
		AudioType a2 = new AudioType();
		a2.setSrc("message2.wav");
		vxml.getPrompt().getAudioOrBreak().add(a1);
		vxml.getPrompt().getAudioOrBreak().add(a2);
		expectedResponse.setVxml(vxml);

		intellivrBean.setPreReminderDelay(0);

		MotechContext mockContext = createMock(MotechContext.class);

		CoreManager mockCoreManager = createMock(CoreManager.class);
		intellivrBean.setCoreManager(mockCoreManager);

		MessageRequestDAO<MessageRequest> mockDao = createMock(MessageRequestDAO.class);

		MessageStatusStore mockStatusStore = createMock(MessageStatusStore.class);
		intellivrBean.setStatusStore(mockStatusStore);

		RegistrarService mockRegistrarService = createMock(RegistrarService.class);
		intellivrBean.setRegistrarService(mockRegistrarService);

		String[] registrarResponse = { "string1" };

		expect(mockRegistrarService.getPatientEnrollments(Integer.parseInt(recipientID))).andReturn(registrarResponse);
		replay(mockRegistrarService);
		
		expect(mockCoreManager.createMessageRequestDAO()).andReturn(mockDao);
		replay(mockCoreManager);
		expect(mockDao.getMsgRequestByRecipientAndSchedule(EasyMock.eq(recipientID), (Date)EasyMock.anyObject())).andReturn(expectedDAOResponse);
		replay(mockDao);
		mockStatusStore.updateStatus(mr1.getId().toString(), StatusType.OK.value());
		mockStatusStore.updateStatus(mr2.getId().toString(), StatusType.OK.value());
		mockStatusStore.updateStatus(mr3.getId().toString(), StatusType.OK.value());
		replay(mockStatusStore);

		ResponseType actualResponse = intellivrBean.handleRequest(request);
		expectedResponse.setPrivate(actualResponse.getPrivate());//no way to predict this value
		assertEquals(expectedResponse, actualResponse);
		assertTrue(intellivrBean.ivrSessions.containsKey(actualResponse.getPrivate()));

		verify(mockRegistrarService);
		verify(mockCoreManager);
		verify(mockDao);
		verify(mockStatusStore);
		reset(mockRegistrarService);
		reset(mockCoreManager);
		reset(mockDao);
		reset(mockStatusStore);

		/*
		 * Test response when there are no pending messages
		 */
		expectedDAOResponse = new ArrayList<MessageRequest>();

		expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);
		vxml.getPrompt().getAudioOrBreak().clear();
		AudioType a3 = new AudioType();
		a3.setSrc(intellivrBean.getNoPendingMessagesRecordingName());
		vxml.getPrompt().getAudioOrBreak().add(a3);
		expectedResponse.setVxml(vxml);
		expectedResponse.setReportUrl(intellivrBean.getReportURL());

		expect(mockRegistrarService.getPatientEnrollments(Integer.parseInt(recipientID))).andReturn(registrarResponse);
		replay(mockRegistrarService);
	
		expect(mockCoreManager.createMessageRequestDAO()).andReturn(mockDao);
		replay(mockCoreManager);
		expect(mockDao.getMsgRequestByRecipientAndSchedule(EasyMock.eq(recipientID), (Date)EasyMock.anyObject())).andReturn(expectedDAOResponse);
		replay(mockDao);

		actualResponse = intellivrBean.handleRequest(request);
		expectedResponse.setPrivate(actualResponse.getPrivate());
		assertEquals(expectedResponse, actualResponse);

		verify(mockRegistrarService);
		verify(mockCoreManager);
		verify(mockDao);
		reset(mockRegistrarService);
		reset(mockCoreManager);
		reset(mockDao);

		/*
		 * Test id validation error
		 */
		expectedResponse.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
		expectedResponse.setStatus(StatusType.ERROR);

		expect(mockRegistrarService.getPatientEnrollments(Integer.parseInt(recipientID))).andThrow(new ValidationException());
		replay(mockRegistrarService);

		actualResponse = intellivrBean.handleRequest(request);
		assertEquals(expectedResponse.getErrorCode(), actualResponse.getErrorCode());
		assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());

		verify(mockRegistrarService);
		reset(mockRegistrarService);

		/*
		 * Test unenrolled id
		 */
		expectedResponse.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
		expectedResponse.setStatus(StatusType.ERROR);

		registrarResponse = new String[0];

		expect(mockRegistrarService.getPatientEnrollments(Integer.parseInt(recipientID))).andReturn(registrarResponse);
		replay(mockRegistrarService);

		actualResponse = intellivrBean.handleRequest(request);
		assertEquals(expectedResponse.getErrorCode(), actualResponse.getErrorCode());
		assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());

		verify(mockRegistrarService);
		reset(mockRegistrarService);

		/*
		 * Test non numeric input
		 */
		expectedResponse.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
		expectedResponse.setStatus(StatusType.ERROR);

		request.setUserid("NaN");

		actualResponse = intellivrBean.handleRequest(request);
		assertEquals(expectedResponse.getErrorCode(), actualResponse.getErrorCode());
		assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());

	}

	@Test
	public void testSessionSerialization() {

		String recipientID = "123456789";
		String phone = "15555555555";
		GregorianCalendar start = new GregorianCalendar(2010, 1, 1, 12, 00);
		GregorianCalendar end = new GregorianCalendar(2010, 1, 1, 13, 00);

		Language english = new LanguageImpl();
		english.setCode("en");
		english.setId(30000000036l);
		english.setName("English");

		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);

		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId(30000000037l);
		mr1.setLanguage(english);
		mr1.setRecipientId(recipientID);
		mr1.setRequestId("mr1");
		mr1.setNotificationType(n1);
		mr1.setPhoneNumberType("PERSONAL");

		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId(30000000038l);
		r1.setMessageRequest(mr1);
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber(phone);
		r1.setDateFrom(start.getTime());
		r1.setDateTo(end.getTime());

		IVRSession expectedSession = new IVRSession(recipientID, phone, english.getName());
		expectedSession.setAttempts(1);
		expectedSession.setDays(0);
		expectedSession.setState(IVRSession.SEND_WAIT);

		Map<String, IVRSession> expectedSessions = new HashMap<String, IVRSession>();
		expectedSessions.put(expectedSession.getSessionId(), expectedSession);

		intellivrBean.ivrSessions = expectedSessions;

		intellivrBean.saveIvrSessions();

		Map<String, IVRSession> loadedSessions = intellivrBean.loadIvrSessions();

		IVRSession loadedSession = loadedSessions.get(expectedSession.getSessionId());

		assertEquals(expectedSession.getAttempts(), loadedSession.getAttempts());
		assertEquals(expectedSession.getDays(), loadedSession.getDays());
		assertEquals(expectedSession.getLanguage(), loadedSession.getLanguage());
		assertEquals(expectedSession.getPhone(), loadedSession.getPhone());
		assertEquals(expectedSession.getState(), loadedSession.getState());
		assertEquals(expectedSession.getUserId(), loadedSession.getUserId());
		assertFalse(loadedSession.isUserInitiated());



	}

}
