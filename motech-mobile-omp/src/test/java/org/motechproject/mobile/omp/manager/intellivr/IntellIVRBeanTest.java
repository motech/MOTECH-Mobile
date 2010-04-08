package org.motechproject.mobile.omp.manager.intellivr;


import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath:META-INF/test-omp-config.xml"})
public class IntellIVRBeanTest {

	@Resource
	IntellIVRBean intellivrBean;
	private IntellIVRServer ivrServer;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testHandleGetIVRConfigTestData() {
		
		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid("123456789");
		
		ResponseType expected = new ResponseType();
		expected.setStatus(StatusType.OK);
		expected.setLanguage("ENGLISH");
		expected.setPrivate("PRIVATE");
		expected.setReportUrl(intellivrBean.getReportURL());
		expected.setTree("TestTree");
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType audio = new AudioType();
		audio.setSrc("test1.wav");
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
	
	@Test
	public void testSendMessage() {
		
		GatewayRequest request = new GatewayRequestImpl();
		request.setRecipientsNumber("+0115555555555");
		request.setRequestId("test_request_id");

		RequestType r = new RequestType();
		r.setApiId(intellivrBean.getApiID());
		r.setCallee("+0115555555555");
		r.setMethod(intellivrBean.getMethod());
		r.setLanguage(intellivrBean.getDefaultLanguage());
		r.setPrivate("test_request_id");
		r.setReportUrl(intellivrBean.getReportURL());
		r.setTree("TestTree");
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType audio = new AudioType();
		audio.setSrc("test1.wav");
		vxml.getPrompt().getAudioOrBreak().add(audio);
		r.setVxml(vxml);
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);
		
		ivrServer = createMock(IntellIVRServer.class);
		
		expect(ivrServer.requestCall(r)).andReturn(expectedResponse);
		
		replay(ivrServer);
		
		intellivrBean.setIvrServer(ivrServer);
		intellivrBean.sendMessage(request, null);
		
		verify(ivrServer);
				
	}

}
