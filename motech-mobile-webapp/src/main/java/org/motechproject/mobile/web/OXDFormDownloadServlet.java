/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.web;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fcitmuk.epihandy.EpihandyXformSerializer;
import org.motechproject.mobile.imp.serivce.oxd.FormDefinitionService;
import org.motechproject.mobile.imp.serivce.oxd.StudyDefinitionService;
import org.motechproject.mobile.imp.serivce.oxd.UserDefinitionService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZOutputStream;

/**
 * Handles study, form and user downloads directly. This enables motech-mobile
 * to service download requests directly rather than relying on openxdata
 * server.
 * 
 * @author Brent Atkinson
 */
public class OXDFormDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = -1584248665268894165L;

	private static Logger log = Logger.getLogger(OXDFormDownloadServlet.class);

	public static final byte ACTION_DOWNLOAD_USERS_AND_FORMS = 11;
	public static final byte ACTION_DOWNLOAD_STUDY_LIST = 2;

	public static final byte RESPONSE_ERROR = 0;
	public static final byte RESPONSE_SUCCESS = 1;
	public static final byte RESPONSE_ACCESS_DENIED = 2;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

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

		// Get Spring context so we can lookup our services
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());

		// Lookup our services
		FormDefinitionService formService = (FormDefinitionService) context
				.getBean("oxdFormDefService");
		UserDefinitionService userService = (UserDefinitionService) context
				.getBean("oxdUserDefService");
		StudyDefinitionService studyService = (StudyDefinitionService) context
				.getBean("oxdStudyDefService");

		// Get our raw input and output streams
		InputStream input = request.getInputStream();
		OutputStream output = response.getOutputStream();

		// Wrap the streams for compression
		ZOutputStream zOutput = new ZOutputStream(output,
				JZlib.Z_BEST_COMPRESSION);

		// Wrap the streams so we can use logical types
		DataInputStream dataInput = new DataInputStream(input);
		DataOutputStream dataOutput = new DataOutputStream(zOutput);

		try {

			// Read the common submission data from mobile phone
			String name = dataInput.readUTF();
			String password = dataInput.readUTF();
			String serializer = dataInput.readUTF();
			String locale = dataInput.readUTF();

			byte action = dataInput.readByte();

			// TODO: add authentication, possible M6 enhancement

			log.info("downloading: name=" + name + ", password=" + password
					+ ", serializer=" + serializer + ", locale=" + locale
					+ ", action=" + action);

			EpihandyXformSerializer serObj = new EpihandyXformSerializer();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			// Perform the action specified by the mobile phone
			try {
				if (action == ACTION_DOWNLOAD_STUDY_LIST) {
					serObj.serializeStudies(baos, studyService.getStudies());
				} else if (action == ACTION_DOWNLOAD_USERS_AND_FORMS) {

					serObj.serializeUsers(baos, userService.getUsers());

					int studyId = dataInput.readInt();
					String studyName = studyService.getStudyName(studyId);
					List<String> studyForms = formService
							.getStudyForms(studyId);

					serObj.serializeForms(baos, studyForms, studyId, studyName);

				}
			} catch (Exception e) {
				dataOutput.writeByte(RESPONSE_ERROR);
				throw new ServletException("failed to serialize data", e);
			}

			// Write out successful upload response
			dataOutput.writeByte(RESPONSE_SUCCESS);
			dataOutput.write(baos.toByteArray());
			response.setStatus(HttpServletResponse.SC_OK);

		} finally {
			// Should always do this
			dataOutput.flush();
			zOutput.finish();
			response.flushBuffer();
		}
	}

}
