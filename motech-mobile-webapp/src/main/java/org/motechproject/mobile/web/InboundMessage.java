package org.motechproject.mobile.web;

import org.motechproject.ws.RequestParameterBuilder;

import java.io.UnsupportedEncodingException;

public class InboundMessage {
    private String text;
    private String number;
    private String key;
    private String code;
    private String time;
    private static final String AMP = "&";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String requestParameters() throws UnsupportedEncodingException {
        RequestParameterBuilder builder = new RequestParameterBuilder("?","UTF-8");
        builder.append("text",text);
        builder.append("number",number);
        builder.append("key",key);
        builder.append("time",time);
        builder.append("code",code);
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Issue { " + text + " } sent from " + number;
    }
}
