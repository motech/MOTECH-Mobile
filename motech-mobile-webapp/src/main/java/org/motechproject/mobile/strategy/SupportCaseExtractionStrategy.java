package org.motechproject.mobile.strategy;

import org.motechproject.mobile.domain.message.SupportCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupportCaseExtractionStrategy implements ContentExtractionStrategy {

    private String messageExpression = "(SUPPORT\\s+\\d+){1}";
    private static final String SPACE = " ";
    private static final String WHITESPACE_CHARS = "\\s+";

    public SupportCase extract(String message) {
        message = mergeSpaces(message);
        Matcher matcher = Pattern.compile(messageExpression).matcher(message);
        if (matcher.find()) {
            String firstMatch = matcher.group(0);
            String staffId = firstMatch.split(SPACE)[1];
            String text = message.substring(firstMatch.length()).trim();
            return new SupportCase(staffId, text);
        }
        return SupportCase.EMPTY;
    }

    protected String mergeSpaces(String message) {
        return message.trim().replaceAll(WHITESPACE_CHARS, SPACE);
    }

    public void setMessageExpression(String messageExpression) {
        this.messageExpression = messageExpression;
    }
}
