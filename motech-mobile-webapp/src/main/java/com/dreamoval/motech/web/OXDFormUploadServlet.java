/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.web;

import com.dreamoval.motech.web.oxd.FormDefinitionService;
import com.dreamoval.motech.web.oxd.FormDefinitionServiceImpl;
import com.dreamoval.motech.web.oxd.StudyProcessor;

import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZOutputStream;
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
import org.apache.log4j.Logger;
import org.fcitmuk.epihandy.EpihandyXformSerializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author administrator
 */
public class OXDFormUploadServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(OXDFormUploadServlet.class);
    FormDefinitionService formService;

    @Override
	public void init() throws ServletException {
		// Normally we'd wire this service in using dependency injection
		FormDefinitionServiceImpl fds = new FormDefinitionServiceImpl();
		fds.setExportStream(getClass().getResourceAsStream("/SampleStudy.xml"));
		formService = fds;
	}

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream input = request.getInputStream();
        OutputStream output = response.getOutputStream();

        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        StudyProcessor studyProcessor = (StudyProcessor) context.getBean("studyProcessor");

        // Wrap the streams for compression
        ZOutputStream zOutput = new ZOutputStream(output,
                JZlib.Z_BEST_COMPRESSION);

        // Wrap the streams so for logical types
        DataInputStream dataInput = new DataInputStream(input);
        DataOutputStream dataOutput = new DataOutputStream(zOutput);

        String name = dataInput.readUTF();
        String password = dataInput.readUTF();
        String serializer = dataInput.readUTF();
        String locale = dataInput.readUTF();

        byte action = dataInput.readByte();

        log.info("uploading: name=" + name + ", password=" + password + ", serializer=" + serializer + ", locale=" + locale + ", action=" + action);

        EpihandyXformSerializer serObj = new EpihandyXformSerializer();
        serObj.addDeserializationListener(studyProcessor);

        try {
            Map<Integer, String> formVersionMap = formService.getXForms();
            serObj.deserializeStudiesWithEvents(dataInput, formVersionMap);
        } catch (Exception e) {
            throw new ServletException("failed to deserialize forms", e);
        }

        String[][] studyForms = studyProcessor.getConvertedStudies();
        int numForms = studyProcessor.getNumForms();

        log.debug("upload contains: studies=" + studyForms.length + ", forms=" + numForms);

        // Write out usual upload response
        dataOutput.writeByte(1);

        // Write out upload status (# forms handled, # of forms failed, failure
        // messages)
        dataOutput.writeByte(numForms);
        if (numForms > 1) {

            int pickedForm = (int) (Math.random() * numForms);

            dataOutput.writeByte(1); // forms in error

            int form = 0;
            for (int s = 0; s < studyForms.length; s++) {
                for (int f = 0; f < studyForms[s].length; f++) {
                    log.info("uploaded form:\n" + studyForms[s][f]);
                    if (form == pickedForm) {
                        log.debug("returning error in form " + pickedForm + ": s=" + s + ", f=" + f);
                        dataOutput.writeByte((byte) s);
                        dataOutput.writeByte((byte) f);
                    //dataOutput.writeUTF(GENERIC_ERROR);
                    }
                    form++;
                }
            }
        } else {
            log.info("only one form, returning no errors:\n" + studyForms[0][0]);
            dataOutput.writeByte(0);
        }

        dataOutput.flush();
        zOutput.finish();

        response.setStatus(HttpServletResponse.SC_OK);

        response.flushBuffer();
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
