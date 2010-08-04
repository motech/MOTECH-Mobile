/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.web;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fcitmuk.epihandy.EpihandyXformSerializer;
import org.fcitmuk.epihandy.FormNotFoundException;
import org.fcitmuk.epihandy.ResponseHeader;
import org.motechproject.mobile.imp.serivce.IMPService;
import org.motechproject.mobile.imp.serivce.oxd.FormDefinitionService;
import org.motechproject.mobile.imp.serivce.oxd.StudyProcessor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZOutputStream;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com) and Brent Atkinson
 */
public class OXDFormUploadServlet extends HttpServlet {

	private static final long serialVersionUID = -7887474593037558262L;

	private static Logger log = Logger.getLogger(OXDFormUploadServlet.class);
	
	private static Logger rawUploadLog = Logger.getLogger(OXDFormUploadServlet.class.getName() + ".mformsraw");

    /**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		FormDefinitionService formService;
		IMPService impService;

		InputStream input = request.getInputStream();
		OutputStream output = response.getOutputStream();

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		StudyProcessor studyProcessor = (StudyProcessor) context
				.getBean("studyProcessor");

		formService = (FormDefinitionService) context
				.getBean("oxdFormDefService");
		impService = (IMPService) context.getBean("impService");

		ZOutputStream zOutput = null; // Wrap the streams for compression

		// Wrap the streams so for logical types
		DataInputStream dataInput = null;
		DataOutputStream dataOutput = null;

		// Set the MIME type so clients don't misinterpret
		response.setContentType("application/octet-stream");
		
		try {
			zOutput = new ZOutputStream(output, JZlib.Z_BEST_COMPRESSION);
			dataInput = new DataInputStream(input);
			dataOutput = new DataOutputStream(zOutput);
			
			if (rawUploadLog.isInfoEnabled()) {
				byte[] rawPayload = IOUtils.toByteArray(dataInput);
				String hexEncodedPayload = Hex.encodeHexString(rawPayload);
				rawUploadLog.info(hexEncodedPayload);
				// Replace the original input stream with one using read payload
				dataInput.close();
				dataInput = new DataInputStream(new ByteArrayInputStream(
						rawPayload));
			}
			
			String name = dataInput.readUTF();
			String password = dataInput.readUTF();
			String serializer = dataInput.readUTF();
			String locale = dataInput.readUTF();

			byte action = dataInput.readByte();

			// TODO Authentication of usename and password. Possible M6
			// enhancement
			log.info("uploading: name=" + name + ", password=" + password
					+ ", serializer=" + serializer + ", locale=" + locale
					+ ", action=" + action);

			EpihandyXformSerializer serObj = new EpihandyXformSerializer();
			serObj.addDeserializationListener(studyProcessor);

			
			try {
				Map<Integer, String> formVersionMap = formService.getXForms();
				serObj.deserializeStudiesWithEvents(dataInput, formVersionMap);
			} catch (FormNotFoundException fne) {
				String msg = "failed to deserialize forms: ";
				log.error(msg + fne.getMessage());
				dataOutput.writeByte(ResponseHeader.STATUS_FORMS_STALE);
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			} catch (Exception e) {
				String msg = "failed to deserialize forms";
				log.error(msg, e);
				dataOutput.writeByte(ResponseHeader.STATUS_ERROR);
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			}

			String[][] studyForms = studyProcessor.getConvertedStudies();
			int numForms = studyProcessor.getNumForms();

			log.debug("upload contains: studies=" + studyForms.length
					+ ", forms=" + numForms);

			// Starting processing here
			int faultyForms = 0;
			if (studyForms != null && numForms > 0) {
				for (int i = 0; i < studyForms.length; i++) {
					for (int j = 0; j < studyForms[i].length; j++) {
						try {
							studyForms[i][j] = impService
									.processXForm(studyForms[i][j]);
						} catch (Exception ex) {
							log.error("processing form failed", ex);
							studyForms[i][j] = ex.getMessage();
						}
						if (!impService.getFormProcessSuccess()
								.equalsIgnoreCase(studyForms[i][j])) {
							faultyForms++;
						}
					}
				}
			}

			// Write out usual upload response
			dataOutput.writeByte(ResponseHeader.STATUS_SUCCESS);

			dataOutput.writeByte((byte) studyProcessor.getNumForms());
			dataOutput.writeByte((byte) faultyForms);

			for (int s = 0; s < studyForms.length; s++) {
				for (int f = 0; f < studyForms[s].length; f++) {
					if (!impService.getFormProcessSuccess().equalsIgnoreCase(
							studyForms[s][f])) {
						dataOutput.writeByte((byte) s);
						dataOutput.writeByte((byte) f);
						dataOutput.writeUTF(studyForms[s][f]);
					}
				}
			}

			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch (Exception e) {
			log.error("failure during upload",e);		
		} finally {
			if (dataOutput != null)
				dataOutput.flush();
			if (zOutput != null)
				zOutput.finish();
			response.flushBuffer();
		}
	}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "OXD Upload Service";
    }// </editor-fold>
}
