package org.motechproject.mobile.domain.message;

import org.motechproject.mobile.strategy.ContentExtractionStrategy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;

public class SMSMessage {

    private String text;
    private String number;
    private String key;
    private String code;
    private String time;
    private static final String UTF_8 = "UTF-8";


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


    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public Boolean hasKeyword(String expected) {
        return expected.equalsIgnoreCase(key);
    }

    public ParsedMessage parseWith(ContentExtractionStrategy strategy) throws UnsupportedEncodingException, ParseException {
        SupportCase supportCase = strategy.extract(decode(text));
        return new ParsedMessage(decode(number), key, code, decodeToDate(time), supportCase);
    }

    private Date decodeToDate(String time) throws UnsupportedEncodingException, ParseException {
        String decodedTimeValue = decode(time);
        return new MessageDateFormat().parse(decodedTimeValue);
    }

    private String decode(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, UTF_8);
    }
}
