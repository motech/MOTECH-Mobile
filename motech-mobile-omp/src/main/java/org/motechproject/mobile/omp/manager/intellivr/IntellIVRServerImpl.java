/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2010-11 The Trustees of Columbia University in the City of
 * New York and Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, Columbia University, or
 * their respective contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA, COLUMBIA UNIVERSITY
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION
 * USA, COLUMBIA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.motechproject.mobile.omp.manager.intellivr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Implementation of the {@link IntellIVRServer} interface for requesting calls
 * be initiated to the user
 *
 * @author fcbrooks
 */
public class IntellIVRServerImpl implements IntellIVRServer {

    private String serverURL;
    private JAXBContext jaxbc;

    private Log log = LogFactory.getLog(IntellIVRServerImpl.class);

    public void init() {
        try {
            jaxbc = JAXBContext.newInstance("org.motechproject.mobile.omp.manager.intellivr");
        } catch (JAXBException e) {
            log.error(e.getMessage());
        }
    }

    public ResponseType requestCall(RequestType request) {

        AutoCreate autoCreate = new AutoCreate();
        autoCreate.setRequest(request);
        ResponseType response = null;

        try {
            URLConnection connection = getConnection();
            sendRequest(autoCreate, connection);
            response = getResponse(connection);
        }
        catch (MalformedURLException e) {
            log.error("", e);
        }
        catch (IOException e) {
            log.error("", e);
        }
        catch (JAXBException e) {
            log.error("", e);
        }
        finally {
            if (response == null) {
                response = createErrorResponse();
            }
        }
        return response;
    }


    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    private ResponseType createErrorResponse() {
        ResponseType response = new ResponseType();
        response.setStatus(StatusType.ERROR);
        response.setErrorCode(ErrorCodeType.MOTECH_UNKNOWN_ERROR);
        response.setErrorString("Unknown error occurred sending request to IVR server");
        return response;
    }

    private ResponseType getResponse(URLConnection connection) throws JAXBException, IOException {
        String responseText = getResponseString(connection);
        Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
        Object object = unmarshaller.unmarshal(new ByteArrayInputStream(responseText.getBytes()));

        ResponseType response = null;
        if (object instanceof AutoCreate) {
            AutoCreate acr = (AutoCreate) object;
            response = acr.getResponse();
        }
        return response;
    }

    private void sendRequest(AutoCreate autoCreate, URLConnection connection) throws JAXBException, IOException {
        Marshaller marshaller = jaxbc.createMarshaller();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        marshaller.marshal(autoCreate, byteStream);

        String xml = byteStream.toString();
        log.info("Sending request: " + xml);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(xml);
        out.flush();
        out.close();
    }

    private String getResponseString(URLConnection connection) throws IOException {
        InputStream in = connection.getInputStream();

        int len = 4096;
        byte[] buffer = new byte[len];
        int off = 0;
        int read = 0;
        while ((read = in.read(buffer, off, len)) != -1) {
            off += read;
            len -= off;
        }

        String responseText = new String(buffer, 0, off);
        log.debug("Received response: " + responseText);
        return responseText;
    }

    private URLConnection getConnection() throws IOException {
        URL url = new URL(this.serverURL);
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("Content-transfer-encoding", "text");
        return connection;
    }


}
