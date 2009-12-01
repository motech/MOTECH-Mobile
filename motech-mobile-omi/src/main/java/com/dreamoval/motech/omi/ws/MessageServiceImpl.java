package com.dreamoval.motech.omi.ws;

import com.dreamoval.motech.omi.manager.OMIManager;
import java.util.Date;
import javax.jws.WebParam;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import org.apache.log4j.Logger;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.MessageStatus;
import org.motechproject.ws.NameValuePair;
import org.motechproject.ws.Patient;
import org.motechproject.ws.mobile.MessageService;

/**
 * An implementation of the MessageService interface.
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created 30-07-09
 */

@WebService(targetNamespace = "http://server.ws.motechproject.org/")
public class MessageServiceImpl implements MessageService {
    private OMIManager omiManager;
    private static Logger logger = Logger.getLogger(MessageServiceImpl.class);

    /**
     *
     * @see MessageService.sendPatientMessage
     */
    @WebMethod
    public MessageStatus sendPatientMessage(@WebParam(name="messageId") String messageId, @WebParam(name="personalInfo") NameValuePair[] personalInfo, @WebParam(name="patientNumber") String patientNumber, @WebParam(name="patientNumberType") ContactNumberType patientNumberType, @WebParam(name="langCode") String langCode, @WebParam(name="mediaType") MediaType messageType, @WebParam(name="notificationType") Long notificationType, @WebParam(name="startDate")Date startDate, @WebParam(name="endDate")Date endDate){
        logger.debug("Called MessageService.sendPatientMessage with parameters:\n\rmessageId - "+ messageId + "\n\rclinic - " + patientNumber + "\n\rpatientNumbrType - " + patientNumberType + "\n\rmessageType - " + messageType + "\n\rstartDate - " + startDate + "\n\rendDate - " + endDate);
        logger.info("Processing request...");
        return omiManager.createOMIService().savePatientMessageRequest(messageId, personalInfo, patientNumber, patientNumberType, langCode, messageType, notificationType, startDate, endDate);
    }

    /**
     *
     * @see MessageService.sendCHPSMessage
     */
    @WebMethod
    public MessageStatus sendCHPSMessage(@WebParam(name="messageId") String messageId, @WebParam(name="personalInfo") NameValuePair[] personalInfo, @WebParam(name="workerNumber") String workerNumber, @WebParam(name="patientList") Patient[] patientList, @WebParam(name="langCode") String langCode, @WebParam(name="mediaType") MediaType messageType, @WebParam(name="notificationType") Long notificationType, @WebParam(name="startDate")Date startDate, @WebParam(name="endDate")Date endDate){
        logger.debug("Called MessageService.sendCHPSMessage with parameters:\n\rmessageId - "+ messageId + "\n\rworkerNumber - " + workerNumber + "\n\rstartDate - " + startDate + "\n\rendDate - " + endDate);
        logger.info("Processing request...");
        return this.omiManager.createOMIService().saveCHPSMessageRequest(messageId, personalInfo, workerNumber, patientList, langCode, messageType, notificationType, startDate, endDate);
    }

    /**
     * @return the omiManager
     */   
    @WebMethod(exclude = true)
    public OMIManager getOmiManager() {
        return omiManager;
    }

    /**
     * @param omiManager the omiManager to set
     */
    @WebMethod(exclude = true)
    public void setOmiManager(OMIManager omiManager) {
        logger.debug("Setting MessageServiceImpl.omiManager:");
        logger.debug(omiManager);
        this.omiManager = omiManager;
    }

}
