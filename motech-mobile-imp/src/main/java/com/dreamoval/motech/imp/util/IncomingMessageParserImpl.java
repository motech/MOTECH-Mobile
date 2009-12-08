package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.model.imp.IncMessageFormParameterStatus;
import com.dreamoval.motech.model.imp.IncMessageStatus;
import com.dreamoval.motech.model.imp.IncomingMessage;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.*;

/**
 * Converts IncomingMessages into IncomingMessageForms
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Dec 2, 2009
 */
public class IncomingMessageParserImpl implements IncomingMessageParser {

    private CoreManager coreManager;

    private static final String CMD_REGEX = "^\\*\\w+\\*";
    private static final String FC_REGEX = "^\\*\\w+\\*\\s*\\d+";
    private static final String PARAM_REGEX = "[a-zA-Z0-9_\\-]+\\s*=([a-zA-Z0-9_\\-\\s/.'](,,)*)+";

    /**
     * @see IncomingMessageParser.parseRequest
     */
    public synchronized IncomingMessage parseRequest(String message){
        IncomingMessage inMsg = coreManager.createIncomingMessage();
        inMsg.setContent(message.trim());
        inMsg.setDateCreated(new Date());
        inMsg.setMessageStatus(IncMessageStatus.PROCESSING);

        return inMsg;
    }

    /**
     *
     * @see IncomingMessageParserImpl.getComand
     */
    public synchronized String getCommand(String message) {
        String command = "";

        Pattern pattern = Pattern.compile(CMD_REGEX);
        Matcher matcher = pattern.matcher(message.trim());

        if (matcher.find()) {
            command = matcher.group();
        }
        return command.toLowerCase();
    }

    /**
     *
     * @see IncomingMessageParserImpl.getFormCode
     */
    public synchronized String getFormCode(String message) {
        String command = "";
        String formCode = "";

        Pattern pattern = Pattern.compile(FC_REGEX);
        Matcher matcher = pattern.matcher(message.trim());

        if (matcher.find()) {
            command = matcher.group();
            String[] formCodeParts = command.trim().split("\\s+");
            if (formCodeParts.length == 2) {
                formCode = formCodeParts[1];
            }
        }
        return formCode;
    }

    /**
     *
     * @see IncomingMessageParserImpl.getParams
     */
    public synchronized List<IncomingMessageFormParameter> getParams(String message) {
        List<IncomingMessageFormParameter> params = new ArrayList<IncomingMessageFormParameter>();

        Pattern pattern = Pattern.compile(PARAM_REGEX);
        Matcher matcher = pattern.matcher(message.trim());

        while (matcher.find()) {
            String param = matcher.group();
            param = param.trim();
            param = param.replace(",,", ",");
            String[] paramArr = param.split("=");

            if (paramArr.length == 2) {
                IncomingMessageFormParameter imParam = coreManager.createIncomingMessageFormParameter();
                imParam.setDateCreated(new Date());
                imParam.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
                imParam.setName(paramArr[0].trim());
                imParam.setValue(paramArr[1].trim());
                params.add(imParam);
            }
        }
        return params;
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
}
