package com.dreamoval.motech.omi.ws;

import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.omi.manager.OMIManager;
import com.dreamoval.motech.omi.service.ContactNumberType;
import com.dreamoval.motech.omi.service.PatientImpl;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.apache.log4j.Logger;

/**
 * An implementation of the MessageService interface.
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created 30-07-09
 */
@WebService(endpointInterface = "com.dreamoval.motech.web.webservices.MessageService", serviceName = "messagingService")
public class MessageServiceImpl implements MessageService {
    private OMIManager omiManager;
    private static Logger logger = Logger.getLogger(MessageServiceImpl.class);

    /**
     *
     * @see MessageService.sendPatientMessage
     */
    public String sendPatientMessage(Long messageId, String patientName, String patientNumber, ContactNumberType patientNumberType, String langCode, String messageType, Long notificationType, Date startDate, Date endDate){
        logger.debug("Called MessageService.sendPatientMessage with parameters:\n\rmessageId - "+ messageId + "\n\rclinic - " + patientNumber + "\n\rpatientNumbrType - " + patientNumberType + "\n\rmessageType - " + messageType + "\n\rstartDate - " + startDate + "\n\rendDate - " + endDate);
        logger.info("Calling OMIService.sendPtientMessage");
        return omiManager.createOMIService().savePatientMessageRequest(messageId, patientName, patientNumber, patientNumberType, langCode, MessageType.valueOf(messageType), notificationType, startDate, endDate);
    }

    /**
     *
     * @see MessageService.sendCHPSMessage
     */
    public String sendCHPSMessage(Long messageId, String workerName, String workerNumber, List<PatientImpl> patientList, Date startDate, Date endDate) {
        logger.debug("Called MessageService.sendCHPSMessage with parameters:\n\rmessageId - "+ messageId + "\n\rworkerName - " + workerName + "\n\rworkerNumber - " + workerNumber + "\n\rpatientList - " + patientList + "\n\rstartDate - " + startDate + "\n\rendDate - " + endDate);
        logger.info("Calling OMIService.sendCHPSMessage");
        return this.omiManager.createOMIService().saveCHPSMessageRequest(messageId, workerName, workerNumber, patientList, startDate, endDate);
    }

    /**
     * @return the omiManager
     */
    public OMIManager getOmiManager() {
        return omiManager;
    }

    /**
     * @param omiManager the omiManager to set
     */
    public void setOmiManager(OMIManager omiManager) {
        logger.debug("Setting MessageServiceImpl.omiManager:");
        logger.debug(omiManager);
        this.omiManager = omiManager;
    }

}
