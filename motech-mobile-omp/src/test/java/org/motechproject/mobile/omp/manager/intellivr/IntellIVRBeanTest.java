package org.motechproject.mobile.omp.manager.intellivr;


import static org.junit.Assert.assertEquals;
import static org.easymock.EasyMock.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.dao.MessageRequestDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.LanguageImpl;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageRequestImpl;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationType;
import org.motechproject.mobile.core.model.NotificationTypeImpl;
import org.motechproject.ws.server.RegistrarService;
import org.motechproject.ws.server.ValidationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:META-INF/test-omp-config.xml"})
public class IntellIVRBeanTest {

	@Resource
	IntellIVRBean intellivrBean;
	
	Language english;
	NotificationType n1,n2,n3,n4,n5;
	String recipientId1 = "1234567";
	String phone1 = "5555551";
	String recipientId2 = "recipientId2";
	String phone2 = "5555552";
	List<ErrorCodeType> serverErrorCodes = new ArrayList<ErrorCodeType>();

	@Before
	public void setUp() throws Exception {
		
		english = new LanguageImpl();
		english.setCode("en");
		english.setId(1L);
		english.setName("English");
		
		n1 = new NotificationTypeImpl();
		n1.setId(1L);
		
		n2 = new NotificationTypeImpl();
		n2.setId(2L);
		
		n3 = new NotificationTypeImpl();
		n3.setId(3L);
		
		n4 = new NotificationTypeImpl();
		n4.setId(4L);

		n5 = new NotificationTypeImpl();
		n5.setId(5L);

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
		intellivrBean.setBundlingDelay(-1);
		intellivrBean.setAvailableDays(1);
		
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
	
	@Test
	public void testSendMessageResponse() {
		
		GatewayRequest gr = getNormalGatewayRequest();
		Set<GatewayResponse> actualGrsSet = intellivrBean.sendMessage(gr);
		assertEquals(1, actualGrsSet.size());
		
		for ( GatewayResponse r : actualGrsSet )
			assertEquals(StatusType.OK.value(), r.getResponseText());

	}
	
	@Test
	public void testSendMessageResponseHouserholdPhone(){
		
		GatewayRequest gr = getNormalGatewayRequest();
		gr.getMessageRequest().setPhoneNumberType("HOUSEHOLD");
		Set<GatewayResponse> actualGrsSet = intellivrBean.sendMessage(gr);
		
		for ( GatewayResponse r : actualGrsSet )
			assertEquals(StatusType.OK.value(), r.getResponseText());
		
	}
	
	@Test
	public void testSendMessageResponseNullRecipientId() {

		GatewayRequest gr = getNormalGatewayRequest();
		gr.getMessageRequest().setRecipientId(null);
		Set<GatewayResponse> actualGrsSet = intellivrBean.sendMessage(gr);

		for ( GatewayResponse r : actualGrsSet ) 
			assertEquals(StatusType.ERROR.value(), r.getResponseText());
		
	}
	
	@Test
	public void testSendMessageResponseNullPhone() {
		
		GatewayRequest gr = getNormalGatewayRequest();
		gr.setRecipientsNumber(null);
		Set<GatewayResponse> actualGrsSet = intellivrBean.sendMessage(gr);
		
		for ( GatewayResponse r : actualGrsSet ) 
			assertEquals(StatusType.ERROR.value(), r.getResponseText());
		
	}
	
	@Test
	public void testSendMessageResponseTextMessage() {
		
		GatewayRequest gr = getNormalGatewayRequest();
		gr.getMessageRequest().setMessageType(MessageType.TEXT);
		Set<GatewayResponse> actualGrsSet = intellivrBean.sendMessage(gr);
		
		for ( GatewayResponse r : actualGrsSet ) 
			assertEquals(StatusType.ERROR.value(), r.getResponseText());
		
	}
	
	@Test
	public void testSendMessageSetToDateOnNullToDate() {
		
		GatewayRequest gr = getNormalGatewayRequest();
		gr.getMessageRequest().setDateTo(null);
		Date expectedToDate = addToDate(gr.getMessageRequest().getDateFrom(), GregorianCalendar.DAY_OF_MONTH, intellivrBean.getAvailableDays());
		intellivrBean.sendMessage(gr);
		assertEquals(expectedToDate, gr.getMessageRequest().getDateTo());
		
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
	public void testHandleRequestNoPending() throws NumberFormatException, ValidationException {
		
		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid(recipientId1);
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType audio = new AudioType();
		audio.setSrc(intellivrBean.getNoPendingMessagesRecordingName());
		vxml.getPrompt().getAudioOrBreak().add(audio);
		expectedResponse.setVxml(vxml);
		expectedResponse.setReportUrl(intellivrBean.getReportURL());

		CoreManager mockCoreManager = createMock(CoreManager.class);
		intellivrBean.setCoreManager(mockCoreManager);

		MessageRequestDAO<MessageRequest> mockDao = createMock(MessageRequestDAO.class);

		RegistrarService mockRegistrarService = createMock(RegistrarService.class);
		intellivrBean.setRegistrarService(mockRegistrarService);
		
		String[] registrarResponse = { "string1" };
		expect(mockRegistrarService.getPatientEnrollments(Integer.parseInt(recipientId1))).andReturn(registrarResponse);
		
		expect(mockCoreManager.createMessageRequestDAO()).andReturn(mockDao);
		
		List<MessageRequest> expectedDAOResponse = new ArrayList<MessageRequest>();
		expect(mockDao.getMsgRequestByRecipientAndSchedule(EasyMock.eq(recipientId1), (Date)EasyMock.anyObject())).andReturn(expectedDAOResponse);
		
		replay(mockCoreManager,mockDao,mockRegistrarService);
		
		ResponseType actualResponse = intellivrBean.handleRequest(request);
		expectedResponse.setPrivate(actualResponse.getPrivate());
		assertEquals(expectedResponse, actualResponse);
		
		verify(mockCoreManager,mockDao,mockRegistrarService);

	}
	
	@Test
	public void testHandleRequestValidationException() throws NumberFormatException, ValidationException {
		
		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid(recipientId1);
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
		expectedResponse.setStatus(StatusType.ERROR);

		RegistrarService mockRegistrarService = createMock(RegistrarService.class);
		intellivrBean.setRegistrarService(mockRegistrarService);

		expect(mockRegistrarService.getPatientEnrollments(Integer.parseInt(recipientId1))).andThrow(new ValidationException());
		replay(mockRegistrarService);

		ResponseType actualResponse = intellivrBean.handleRequest(request);
		assertEquals(expectedResponse.getErrorCode(), actualResponse.getErrorCode());
		assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());

		verify(mockRegistrarService);
		
	}
	
	@Test
	public void testHandleRequestUnenrolledId() throws NumberFormatException, ValidationException {
		
		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid(recipientId1);
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
		expectedResponse.setStatus(StatusType.ERROR);

		String[] registrarResponse = new String[0];
		
		RegistrarService mockRegistrarService = createMock(RegistrarService.class);
		intellivrBean.setRegistrarService(mockRegistrarService);

		expect(mockRegistrarService.getPatientEnrollments(Integer.parseInt(recipientId1))).andReturn(registrarResponse);
		replay(mockRegistrarService);

		ResponseType actualResponse = intellivrBean.handleRequest(request);
		assertEquals(expectedResponse.getErrorCode(), actualResponse.getErrorCode());
		assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());

		verify(mockRegistrarService);
		
	}
	
	@Test
	public void testHandleRequestNonNumericId(){

		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid("NaN");
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setErrorCode(ErrorCodeType.MOTECH_INVALID_USER_ID);
		expectedResponse.setStatus(StatusType.ERROR);

		ResponseType actualResponse = intellivrBean.handleRequest(request);
		assertEquals(expectedResponse.getErrorCode(), actualResponse.getErrorCode());
		assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
		
	}

	private GatewayRequest getNormalGatewayRequest() {
		
		MessageRequest mr = new MessageRequestImpl();
		mr.setDateCreated(new Date());
		mr.setDateFrom(new Date());
		mr.setDaysAttempted(0);
		mr.setId(1L);
		mr.setLanguage(english);
		mr.setMessageType(MessageType.VOICE);
		mr.setNotificationType(n1);
		mr.setPhoneNumberType("PERSONAL");
		mr.setRecipientId(recipientId1);
		mr.setRecipientNumber(phone1);
		mr.setRequestId(UUID.randomUUID().toString());
		mr.setStatus(MStatus.PENDING);
		mr.setTryNumber(1);
		mr.setVersion(0);
		
		GatewayRequest gr = new GatewayRequestImpl();
		gr.setDateFrom(new Date());
		gr.setId(1L);
		gr.setMessageStatus(MStatus.SCHEDULED);
		gr.setMessageRequest(mr);
		gr.setRecipientsNumber(phone1);
		gr.setRequestId(mr.getRequestId());
		gr.setTryNumber(1);
		gr.setVersion(0);

		return gr;
		
	}
	
	private Date addToDate(Date start, int field, int amount) {
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.add(field, amount);
		return cal.getTime();
		
	}

	
}
