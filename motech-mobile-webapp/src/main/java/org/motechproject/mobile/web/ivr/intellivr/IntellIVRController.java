package org.motechproject.mobile.web.ivr.intellivr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import org.motechproject.omp.manager.intellivr.AutoCreate;
import org.motechproject.omp.manager.intellivr.ErrorCodeType;
import org.motechproject.omp.manager.intellivr.ResponseType;
import org.motechproject.omp.manager.intellivr.StatusType;
import org.motechproject.omp.manager.intellivr.RequestType.Vxml;

public class IntellIVRController extends AbstractController implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	private DocumentBuilder parser;
	private Validator validator;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public void init() {		
		Resource schemaResource = resourceLoader.getResource("classpath:intellivr-in.xsd");
		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		try {
			Schema schema = factory.newSchema(schemaResource.getFile());
			parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			validator = schema.newValidator();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			JAXBContext jaxbc = JAXBContext.newInstance("org.motechproject.omp.manager.intellivr");
			marshaller = jaxbc.createMarshaller();
			unmarshaller = jaxbc.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String content = getContent(request);
		
		Object output = null;
		
		if ( !contentIsValid(content) ) {
			AutoCreate ac = new AutoCreate();
			ResponseType rt = new ResponseType();
			rt.setStatus(StatusType.ERROR);
			rt.setErrorCode(ErrorCodeType.MOTECH_MALFORMED_XML);
			rt.setErrorString("Malformed XML content");
			ac.setResponse(rt);
			output = ac;
		} else {
			AutoCreate ac = new AutoCreate();
			ResponseType rt = new ResponseType();
			rt.setStatus(StatusType.OK);
			rt.setLanguage("ENGLISH");
			rt.setPrivate("PRIVATE");
			rt.setReportUrl("http://130.111.123.83:8080/motech-mobile-webapp/intellivr");
			rt.setTree("TestTree");
			Vxml vxml = new Vxml();
			rt.setVxml(vxml);
			ac.setResponse(rt);
			output = ac;
		}

		if ( output == null ) {
			AutoCreate ac = new AutoCreate();
			ResponseType rt = new ResponseType();
			rt.setStatus(StatusType.ERROR);
			rt.setErrorCode(ErrorCodeType.MOTECH_UNKNOWN_ERROR);
			rt.setErrorString("An unknown error has occured");
			ac.setResponse(rt);
			output = ac;
		}
		
		PrintWriter out = response.getWriter();
		
		try {
			response.setContentType("text/xml");
			marshaller.marshal(output, out);
		} catch (JAXBException e) {
			e.printStackTrace();
			out.println("<test>jaxb error</test>");
		}

		
		return null;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	private String getContent(HttpServletRequest request) throws Exception {
		InputStream in = request.getInputStream();
		int length = request.getContentLength();
		byte[] buffer = new byte[length];
		in.read(buffer, 0, length);
		return new String(buffer);
	}
	
	private boolean contentIsValid(String content) {
		
		try {
			Document doc = parser.parse(new ByteArrayInputStream(content.getBytes()));
			validator.validate(new DOMSource(doc));
			return true;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
