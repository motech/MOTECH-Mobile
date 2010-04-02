package org.motechproject.mobile.web.ivr.intellivr;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.io.ByteArrayInputStream;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.omp.manager.intellivr.AudioType;
import org.motechproject.mobile.omp.manager.intellivr.AutoCreate;
import org.motechproject.mobile.omp.manager.intellivr.GetIVRConfigRequest;
import org.motechproject.mobile.omp.manager.intellivr.GetIVRConfigRequestHandler;
import org.motechproject.mobile.omp.manager.intellivr.ReportHandler;
import org.motechproject.mobile.omp.manager.intellivr.RequestType;
import org.motechproject.mobile.omp.manager.intellivr.ResponseType;
import org.motechproject.mobile.omp.manager.intellivr.StatusType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ( locations = { "classpath:WEB-INF/intellivr-servlet.xml" } )
public class IntellIVRControllerTest {

	@Resource
	IntellIVRController intellivrController;
	private ReportHandler mockReportHandler;
	private GetIVRConfigRequestHandler mockIVRConfigHandler;
	private MockHttpServletRequest mockRequest;
	private MockHttpServletResponse mockResponse;
	private JAXBContext jaxbContext;
	private Unmarshaller unmarshaller;
	
	@Before
	public void setUp() throws JAXBException {
		mockRequest = new MockHttpServletRequest();
		mockResponse = new MockHttpServletResponse();
		mockIVRConfigHandler = createMock(GetIVRConfigRequestHandler.class);
		
		jaxbContext = JAXBContext.newInstance("org.motechproject.mobile.omp.manager.intellivr");
		unmarshaller = jaxbContext.createUnmarshaller();
		
	}
	
	@Test
	public void testGetIvrConfig() throws Exception {
		
		GetIVRConfigRequest request = new GetIVRConfigRequest();
		request.setUserid("123456789");
		
		ResponseType expectedResponse = new ResponseType();
		expectedResponse.setStatus(StatusType.OK);
		expectedResponse.setLanguage("ENGLISH");
		expectedResponse.setPrivate("PRIVATE");
		expectedResponse.setReportUrl("http://localhost");
		expectedResponse.setTree("TestTree");
		RequestType.Vxml vxml = new RequestType.Vxml();
		vxml.setPrompt(new RequestType.Vxml.Prompt());
		AudioType audio = new AudioType();
		audio.setSrc("test1.wav");
		vxml.getPrompt().getAudioOrBreak().add(audio);
		expectedResponse.setVxml(vxml);
		
		expect(mockIVRConfigHandler.handleRequest(request)).andReturn(expectedResponse);
		
		replay(mockIVRConfigHandler);
		
		intellivrController.setIvrConfigHandler(mockIVRConfigHandler);
		mockRequest.setContent(getIvrConfigXML.getBytes());
		intellivrController.handleRequestInternal(mockRequest, mockResponse);

		verify(mockIVRConfigHandler);
		
		Object o = unmarshaller.unmarshal(new ByteArrayInputStream(mockResponse.getContentAsByteArray()));
		
		assertTrue(o instanceof AutoCreate);
		assertEquals(expectedResponse, ((AutoCreate)o).getResponse());
		
	}

	String getIvrConfigXML = "<getIVRConfigRequest><userid>123456789</userid></getIVRConfigRequest>";
	
}
