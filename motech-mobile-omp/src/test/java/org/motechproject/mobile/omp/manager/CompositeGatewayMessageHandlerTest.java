package org.motechproject.mobile.omp.manager;

import static org.easymock.EasyMock.*;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

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
import org.motechproject.mobile.core.service.MotechContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:META-INF/test-omp-config.xml"})
public class CompositeGatewayMessageHandlerTest {

	@Resource
	CompositeGatewayMessageHandler compositeHandler;
	
	GatewayRequest voiceGatewayRequest;
	GatewayRequest textGatewayRequest;
	
	GatewayMessageHandler voiceHandler;
	GatewayMessageHandler textHandler;
	
	@Before
	public void setUp() {
		
		voiceHandler = createMock(GatewayMessageHandler.class);
		textHandler = createMock(GatewayMessageHandler.class);
		
		compositeHandler.setVoiceHandler(voiceHandler);
		compositeHandler.setTextHandler(textHandler);
		
		Language english = new LanguageImpl();
		english.setCode("en");
		english.setId(1L);
		english.setName("English");
		
		NotificationType n1 = new NotificationTypeImpl();
		n1.setId(1L);
		
		MessageRequest voiceMessageRequest = new MessageRequestImpl();
		voiceMessageRequest.setId(1L);
		voiceMessageRequest.setLanguage(english);
		voiceMessageRequest.setRecipientId("123456789");
		voiceMessageRequest.setRequestId("mr1");
		voiceMessageRequest.setMessageType(MessageType.VOICE);
		voiceMessageRequest.setNotificationType(n1);
		voiceMessageRequest.setPhoneNumberType("PERSONAL");
	
		voiceGatewayRequest = new GatewayRequestImpl();
		voiceGatewayRequest.setId(1000L);
		voiceGatewayRequest.setMessageRequest(voiceMessageRequest);
		voiceGatewayRequest.setMessageStatus(MStatus.PENDING);
		voiceGatewayRequest.setRecipientsNumber("15555555555");

		NotificationType n2 = new NotificationTypeImpl();
		n2.setId(2L);

		MessageRequest textMessageRequest = new MessageRequestImpl();
		textMessageRequest.setId(2L);
		textMessageRequest.setLanguage(english);
		textMessageRequest.setRecipientId("123456789");
		textMessageRequest.setRequestId("mr2");
		textMessageRequest.setMessageType(MessageType.TEXT);
		textMessageRequest.setNotificationType(n2);
		textMessageRequest.setPhoneNumberType("PERSONAL");
			
		textGatewayRequest = new GatewayRequestImpl();
		textGatewayRequest.setId(2000L);
		textGatewayRequest.setMessageRequest(textMessageRequest);
		textGatewayRequest.setMessageStatus(MStatus.PENDING);
		textGatewayRequest.setRecipientsNumber("15555555555");

		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testParseMessageResponse(){
		
		MotechContext context = createMock(MotechContext.class);
		
		Set<GatewayResponse> response = new HashSet<GatewayResponse>();
		
		expect(voiceHandler.parseMessageResponse(voiceGatewayRequest, "OK", context)).andReturn(response);
		replay(voiceHandler);
		compositeHandler.parseMessageResponse(voiceGatewayRequest, "OK", context);
		verify(voiceHandler);
		reset(voiceHandler);
		
		expect(textHandler.parseMessageResponse(textGatewayRequest, "OK", context)).andReturn(response);
		replay(textHandler);
		compositeHandler.parseMessageResponse(textGatewayRequest, "OK", context);
		verify(textHandler);
		reset(textHandler);
		
	}
	
}
