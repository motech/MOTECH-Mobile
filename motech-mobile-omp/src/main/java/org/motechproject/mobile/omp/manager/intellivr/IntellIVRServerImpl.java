package org.motechproject.mobile.omp.manager.intellivr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IntellIVRServerImpl implements IntellIVRServer {

	private String serverURL;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	private Log log = LogFactory.getLog(IntellIVRServerImpl.class);
	
	public void init() {
		try {
			JAXBContext jaxbc = JAXBContext.newInstance("org.motechproject.mobile.omp.manager.intellivr");
			marshaller = jaxbc.createMarshaller();
			unmarshaller = jaxbc.createUnmarshaller();
		} catch (JAXBException e) {
			log.error(e.getMessage());
		}
	}
	
	public ResponseType requestCall(RequestType request) {
		
		AutoCreate ac = new AutoCreate();
		ac.setRequest(request);
		
		ResponseType response = null;
		
		try {
			
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			marshaller.marshal(ac, byteStream);

			String xml = byteStream.toString();

			URL url = new URL(this.serverURL);
			URLConnection con = url.openConnection();

			con.setDoInput(true);
			con.setDoOutput(true);

			con.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());

			log.info("Sending request: " + xml);

			out.write(xml);
			out.flush();
			out.close();
			
			InputStreamReader in = new InputStreamReader(con.getInputStream());
			
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[2048];
			int  num;
			
			while ( -1 != (num=in.read(cbuf))) {
				sb.append(cbuf, 0, num);
			}
			
			log.info("Received response: " + sb.toString());
			
			Object o = unmarshaller.unmarshal(new ByteArrayInputStream(sb.toString().getBytes()));
			
			if ( o instanceof AutoCreate ) {
				AutoCreate acr = (AutoCreate)o;
				response = acr.getResponse();
			}
			
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (JAXBException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}	
		return response;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

}
