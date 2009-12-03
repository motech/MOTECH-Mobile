package com.dreamoval.motech.imp.util;

import java.util.HashSet;
import java.util.Set;
import org.motechproject.ws.NameValuePair;
import java.util.regex.*;

/**
 * Converts IncomingMessages into IncomingMessageForms
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Dec 2, 2009
 */
public class IncomingMessageParserImpl implements IncomingMessageParser {

    private static final String CMD_REGEX = "^\\*\\w+\\*";
    private static final String FC_REGEX = "^\\*\\w+\\*\\s*\\d+";
    private static final String PARAM_REGEX = "[a-zA-Z0-9_\\-]+\\s*=([a-zA-Z0-9_\\-\\s/.](,,)*)+";//"[a-zA-Z0-9_\\-]+\\s*=\\s*\\w+((\\s*,,)*\\s*\\w+)*";

    /**
     *
     * @see IncomingMessageParserImpl.getComand
     */
    public String getCommand(String message) {
        String command = "";

        Pattern pattern = Pattern.compile(CMD_REGEX);
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            command = matcher.group();
        }
        return command;
    }

    /**
     *
     * @see IncomingMessageParserImpl.getFormCode
     */
    public String getFormCode(String message) {
        String command = "";
        String formCode = "";

        Pattern pattern = Pattern.compile(FC_REGEX);
        Matcher matcher = pattern.matcher(message);

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
    public Set<NameValuePair> getParams(String message) {
        Set<NameValuePair> params = new HashSet<NameValuePair>();

        Pattern pattern = Pattern.compile(PARAM_REGEX);
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {

            String param = matcher.group();

            param = param.trim();
            param = param.replace(",,", ",");
            String[] nvpArr = param.split("=");

            if (nvpArr.length == 2) {
                NameValuePair nvp = new NameValuePair();
                nvp.setName(nvpArr[0].trim());
                nvp.setValue(nvpArr[1].trim());
                params.add(nvp);
            }
        }
        return params;
    }
}
