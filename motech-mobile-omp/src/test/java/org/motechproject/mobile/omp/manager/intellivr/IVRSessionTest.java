package org.motechproject.mobile.omp.manager.intellivr;

import static org.junit.Assert.*;
import org.junit.Test;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.LanguageImpl;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageRequestImpl;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationType;
import org.motechproject.mobile.core.model.NotificationTypeImpl;


public class IVRSessionTest {

	@Test
	public void testIVRSession() {
		
		String userid = "1";
		String phone = "5555555555";
		String language = "English";
		String sessionId = userid + "-" + phone;
				
		Language english = new LanguageImpl();
		english.setCode("en");
		english.setId(1L);
		english.setName("English");
		
		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);
		
		MessageRequest mr1 = new MessageRequestImpl();
		mr1.setId(1L);
		mr1.setLanguage(english);
		mr1.setRecipientId(userid);
		mr1.setRequestId("mr1");
		mr1.setMessageType(MessageType.VOICE);
		mr1.setNotificationType(n1);
		mr1.setPhoneNumberType("PERSONAL");
	
		GatewayRequest r1 = new GatewayRequestImpl();
		r1.setId(1000L);
		r1.setMessageRequest(mr1);
		r1.setMessageStatus(MStatus.PENDING);
		r1.setRecipientsNumber(phone);

		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest mr2 = new MessageRequestImpl();
		mr2.setId(2L);
		mr2.setLanguage(english);
		mr2.setRecipientId(userid);
		mr2.setRequestId("mr2");
		mr2.setMessageType(MessageType.VOICE);
		mr2.setNotificationType(n2);
		mr2.setPhoneNumberType("PERSONAL");
			
		GatewayRequest r2 = new GatewayRequestImpl();
		r2.setId(2000L);
		r2.setMessageRequest(mr2);
		r2.setMessageStatus(MStatus.PENDING);
		r2.setRecipientsNumber(phone);
		
		IVRSession session = new IVRSession(userid, phone, language);		
		session.addGatewayRequest(r1);
		session.addGatewayRequest(r2);
		
		assertEquals(userid, session.getUserId());
		assertEquals(phone, session.getPhone());
		assertEquals(sessionId, session.getSessionId());
		
		assertEquals(0, session.getAttempts());
		session.setAttempts(session.getAttempts() + 1);
		assertEquals(1, session.getAttempts());
		
		assertTrue(session.getGatewayRequests().contains(r1));
		assertTrue(session.getGatewayRequests().contains(r2));

		session.removeGatewayRequest(r2);
		assertFalse(session.getGatewayRequests().contains(r2));
		
	}
	
}
