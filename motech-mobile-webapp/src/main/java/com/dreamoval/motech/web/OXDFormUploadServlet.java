/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.web;

import com.dreamoval.motech.imp.serivce.IMPService;
import com.dreamoval.motech.imp.serivce.oxd.FormDefinitionService;
import com.dreamoval.motech.imp.serivce.oxd.StudyProcessor;

import com.dreamoval.motech.imp.util.exception.MotechParseException;
import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.fcitmuk.epihandy.EpihandyXformSerializer;
import org.jdom.JDOMException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com) and Brent Atkinson
 */
public class OXDFormUploadServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(OXDFormUploadServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FormDefinitionService formService;
        IMPService impService;

        InputStream input = request.getInputStream();
        OutputStream output = response.getOutputStream();

        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        StudyProcessor studyProcessor = (StudyProcessor) context.getBean("studyProcessor");

        formService = (FormDefinitionService) context.getBean("oxdFormDefService");
        impService = (IMPService) context.getBean("impService");

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

        //TODO Authentication of usename and password. Possible M6 enhancement
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

        //Starting processing here
        int faultyForms = 0;
        if (studyForms != null && numForms > 0) {
            for (int i = 0; i < studyForms.length; i++) {
                for (int j = 0; j < studyForms[i].length; j++) {
                    try {
                        studyForms[i][j] = impService.processXForm(studyForms[i][j]);
                    } catch (JDOMException ex) {
                        log.error(ex.getMessage());
                        studyForms[i][j] = ex.getMessage();
                    } catch (MotechParseException ex) {
                        log.error(ex.getMessage());
                        studyForms[i][j] = ex.getMessage();
                    } catch (IOException ex) {
                        log.error(ex.getMessage());
                        studyForms[i][j] = ex.getMessage();
                    } catch (Exception ex){
                        log.error(ex.getMessage());
                        studyForms[i][j] = ex.getMessage();
                    }
                    if(!impService.getFormProcessSuccess().equalsIgnoreCase(studyForms[i][j])){
                        faultyForms++;
                    }
                }
            }
        }

        //Return reult via zOutput stre4am

        // Write out usual upload response
        dataOutput.writeByte(1);

        dataOutput.writeByte((byte)studyProcessor.getNumForms());
        dataOutput.writeByte((byte)faultyForms);
        
            for (int s = 0; s < studyForms.length; s++) {
                for (int f = 0; f < studyForms[s].length; f++) {
                    if (!impService.getFormProcessSuccess().equalsIgnoreCase(studyForms[s][f])) {
                        dataOutput.writeByte((byte) s);
                        dataOutput.writeByte((byte) f);
                        dataOutput.writeUTF(studyForms[s][f]);
                    }
                }
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
