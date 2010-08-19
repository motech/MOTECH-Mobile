package org.motechproject.mobile.omp.manager.intellivr;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:META-INF/test-omp-config.xml"})
public class IntellIVRBeanTest {

	@Resource
	IntellIVRBean intellivrBean;
	
	Language english;
	NotificationType n1,n2,n3,n4,n5;
	String recipientId1 = "recipientId1";
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
