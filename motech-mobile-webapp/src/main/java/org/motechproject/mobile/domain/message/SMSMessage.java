package org.motechproject.mobile.domain.message;

import org.motechproject.mobile.strategy.ContentExtractionStrategy;

import java.util.Date;

public class SMSMessage {

    private String text;
    private String number;
    private String key;
    private String code;
    private Date time;

    
    public void setText(String text) {
        this.text = text;
    }


    public void setNumber(String number) {
        this.number = number;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public Boolean hasKeyword(String expected) {
        return expected.equalsIgnoreCase(key);
    }

    public ParsedMessage parseWith(ContentExtractionStrategy strategy) {
        SupportCase supportCase = strategy.extract(text);
        return new ParsedMessage(number,key,code,time,supportCase);
    }
}
