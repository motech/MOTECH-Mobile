/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.serivce;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.Duplicatable;
import org.motechproject.mobile.imp.manager.IMPManager;
import org.motechproject.mobile.imp.util.CommandAction;
import org.motechproject.mobile.imp.util.IncomingMessageParser;
import org.motechproject.mobile.core.model.IncomingMessage;
import org.motechproject.mobile.core.model.IncomingMessageResponse;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.imp.util.IncomingMessageXMLParser;
import org.motechproject.mobile.imp.util.exception.MotechParseException;
import org.motechproject.mobile.model.dao.imp.IncomingMessageDAO;
import org.motechproject.mobile.omi.manager.OMIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.jdom.JDOMException;
import org.motechproject.mobile.core.model.IncMessageFormStatus;

/**
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public class IMPServiceImpl implements IMPService {

    private static Logger logger = Logger.getLogger(IMPServiceImpl.class);
    private int duplicatePeriod;
    private IMPManager impManager;
    private OMIManager omiManager;
    private CoreManager coreManager;
    private IncomingMessageParser parser;
    private Map<String, CommandAction> cmdActionMap;
    private IncomingMessageXMLParser xmlParser;
    private String formProcessSuccess;
    private int maxConcat;
    private int charsPerSMS;
    private int concatAllowance;
    private int maxSMS;
    private String localNumberExpression;
    private String defaultCountryCode;

    /**
     *
     * @see IMPService.processRequest
     */
    public synchronized IncomingMessageResponse processRequest(String message, String requesterPhone, boolean isDemo) {
        MotechContext context = coreManager.createMotechContext();
        IncomingMessageDAO msgDao = coreManager.createIncomingMessageDAO(context);
        IncomingMessageResponse response = coreManager.createIncomingMessageResponse();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 0 - duplicatePeriod);
        Date beforeDate = cal.getTime();
        IncomingMessage inMsg = msgDao.getByContentNonDuplicatable(message);

        if (inMsg != null && inMsg.getContent().equalsIgnoreCase(message)) {
            if (inMsg.getIncomingMessageForm() != null && inMsg.getIncomingMessageForm().getMessageFormStatus() == IncMessageFormStatus.SERVER_VALID) {
                if (inMsg.getIncomingMessageForm().getIncomingMsgFormDefinition().getDuplicatable() == Duplicatable.DISALLOWED || (inMsg.getIncomingMessageForm().getIncomingMsgFormDefinition().getDuplicatable() == Duplicatable.TIME_BOUND && inMsg.getDateCreated().after(beforeDate))) {
                    response.setContent("Error:\nThis form has already been processed!");
                    return response;
                }
            }
        }

        inMsg = parser.parseRequest(message);
        String cmd = parser.getCommand(message);

        Transaction tx = (Transaction) msgDao.getDBSession().getTransaction();
        tx.begin();
        msgDao.save(inMsg);
        tx.commit();

        CommandAction action = cmdActionMap.get(cmd.toUpperCase());
        if (action == null) {
            //TODO change error to unknown form type or command
            response.setContent("Error: Unknown Form!\nPlease check the name of the form.");
            return response;
        }

        response = action.execute(inMsg, requesterPhone, context);

        if (inMsg.getIncomingMessageForm().getIncomingMsgFormDefinition().getSendResponse() && inMsg.getIncomingMessageForm().getMessageFormStatus() == IncMessageFormStatus.SERVER_VALID) {
            sendResponse(response.getContent(), response.getIncomingMessage().getIncomingMsgSession().getRequesterPhone());
        }

        return response;
    }

    public String processRequest(String message) {
        IncomingMessageResponse result = null;
        String requester = null;

        //TODO We must also separate the processing of java forms - Logically
        result = processRequest(message, null, false);

        if (result.getContent().toLowerCase().indexOf("error") < 0) {
            return formProcessSuccess;
        } else {
            return result.getContent();
        }
    }

    /**
     * <p>Processes xForms as Motech Forms by converting them to SMS format. It then goes through normal
     * SMS processing.</p>
     *
     * @param xForms
     * @return a List of responses
     * @throws org.jdom.JDOMException
     * @throws java.io.IOException
     * @throws org.motechproject.mobile.imp.util.exception.MotechParseException
     */
    public ArrayList<String> processXForms(ArrayList<String> xForms) throws JDOMException, IOException, MotechParseException {
        ArrayList<String> result = null;

        if (xForms != null) {
            result = new ArrayList<String>();
            ArrayList<String> smses = xmlParser.parseXML(xForms);
            for (String sms : smses) {
                result.add(processRequest(sms));
            }
        }

        return result;
    }

    /**
     * Validates and processes an xForm.
     *
     * @param xForm the XForm to be validated and processed
     * @return ok if successful otherwise the specifics of the error for reporting
     * @throws org.jdom.JDOMException
     * @throws java.io.IOException
     * @throws org.motechproject.mobile.imp.util.exception.MotechParseException
     */
    public String processXForm(String xForm) throws JDOMException, IOException, MotechParseException {
        String result = null;

        if (xForm != null) {
            result = processXFormSMS(xmlParser.toSMSMessage(xForm));
        }
        return result;
    }

    /**
     * Processes motech mobile understandable name/value pair SMS
     *
     * @param xFormSMS 
     * @return ok if processing is successfully otherwise error message
     */
    private String processXFormSMS(String xFormSMS) {
        String result = null;

        if (xFormSMS != null) {
            result = processRequest(xFormSMS);
        }

        return result;
    }

    /**
     * Sends a response for a mobile query message
     * 
     * @param response the response message
     * @param recipient the phone number to send the response to
     */
    private void sendResponse(String response, String recipient) {
        int msgLength = (charsPerSMS - concatAllowance) * maxConcat;
        recipient = formatPhoneNumber(recipient);

        if (recipient == null || recipient.isEmpty()) {
            return;
        }

        if (response.length() <= msgLength) {
            omiManager.createOMIService().sendMessage(response, recipient);
        } else {
            int start = 0;
            int end = 0;

            for (byte smsNum = 1; smsNum <= maxSMS; smsNum++) {
                String currSMS = "";
                end = start + msgLength;

                if(response.length() < start)
                    break;

                if (response.length() > start + 2) {
                    currSMS = response.length() < end ? response.substring(start) : response.substring(start, end);
                }

                if (currSMS.contains("\n")) {
                    String message = "";
                    String[] lines = currSMS.split("\n");

                    for (String line : lines) {
                        int currLen = message.length() + line.length() + 1;
                        if (currLen < msgLength) {
                            message += line + "\n";
                        }
                    }
                    currSMS = message.trim();
                }
                omiManager.createOMIService().sendMessage(currSMS, recipient);
                start += currSMS.length();
            }
        }
    }

    public String formatPhoneNumber(
            String requesterPhone) {
        if (requesterPhone == null || requesterPhone.isEmpty()) {
            return null;
        }

        String formattedNumber = requesterPhone;
        if (Pattern.matches(localNumberExpression, requesterPhone)) {
            formattedNumber = defaultCountryCode + requesterPhone.substring(1);
        }

        return formattedNumber;
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    /**
     * @return the parser
     */
    public IncomingMessageParser getParser() {
        return parser;
    }

    /**
     * @param parser the parser to set
     */
    public void setParser(IncomingMessageParser parser) {
        this.parser = parser;
    }

    /**
     * @return the cmdActionMap
     */
    public Map<String, CommandAction> getCmdActionMap() {
        return cmdActionMap;
    }

    /**
     * @param cmdActionMap the cmdActionMap to set
     */
    public void setCmdActionMap(Map<String, CommandAction> cmdActionMap) {
        this.cmdActionMap = cmdActionMap;
    }

    /**
     * @return the impManager
     */
    public IMPManager getImpManager() {
        return impManager;
    }

    /**
     * @param impManager the impManager to set
     */
    public void setImpManager(IMPManager impManager) {
        this.impManager = impManager;
    }

    /**
     * @param duplicatePeriod the duplicatePeriod to set
     */
    public void setDuplicatePeriod(int duplicatePeriod) {
        this.duplicatePeriod = duplicatePeriod;
    }

    /**
     * @return the xmlParser
     */
    public IncomingMessageXMLParser getXmlParser() {
        return xmlParser;
    }

    /**
     * @param xmlParser the xmlParser to set
     */
    public void setXmlParser(IncomingMessageXMLParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    /**
     * @return the formProcessSuccess
     */
    public String getFormProcessSuccess() {
        return formProcessSuccess;
    }

    /**
     * @param formProcessSuccess the formProcessSuccess to set
     */
    public void setFormProcessSuccess(String formProcessSuccess) {
        this.formProcessSuccess = formProcessSuccess;
    }

    /**
     * @param omiManager the omiManager to set
     */
    public void setOmiManager(OMIManager omiManager) {
        this.omiManager = omiManager;
    }

    /**
     * @param maxConcat the maxConcat to set
     */
    public void setMaxConcat(int maxConcat) {
        this.maxConcat = maxConcat;
    }

    /**
     * @param charsPerSMS the charsPerSMS to set
     */
    public void setCharsPerSMS(int charsPerSMS) {
        this.charsPerSMS = charsPerSMS;
    }

    /**
     * @param concatAllowance the concatAllowance to set
     */
    public void setConcatAllowance(int concatAllowance) {
        this.concatAllowance = concatAllowance;
    }

    /**
     * @param maxSMS the maxSMS to set
     */
    public void setMaxSMS(int maxSMS) {
        this.maxSMS = maxSMS;
    }

    /**
     * @param localNumberExpression the localNumberExpression to set
     */
    public void setLocalNumberExpression(String localNumberExpression) {
        this.localNumberExpression = localNumberExpression;
    }

    /**
     * @param defaultCountryCode the defaultCountryCode to set
     */
    public void setDefaultCountryCode(String defaultCountryCode) {
        this.defaultCountryCode = defaultCountryCode;
    }
}
