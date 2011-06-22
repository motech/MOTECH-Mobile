package org.motechproject.mobile.domain.message;

import java.util.Date;

public class ParsedMessage {
    private String senderPhoneNumber;
    private String key;
    private String shortCode;
    private Date time;
    private SupportCase supportCase;

    public ParsedMessage(String senderPhoneNumber, String key, String shortCode, Date time, SupportCase supportCase) {
        this.senderPhoneNumber = senderPhoneNumber;
        this.key = key ;
        this.shortCode = shortCode;
        this.time = time ;
        this.supportCase = supportCase;
    }

    public String caseRaisedBy() {
        return supportCase.raisedBy();
    }

    public Date getTimeReported() {
        return time;
    }

    public String caseDescription() {
        return supportCase.description();
    }
}
