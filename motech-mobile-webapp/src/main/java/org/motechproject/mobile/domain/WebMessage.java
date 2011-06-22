package org.motechproject.mobile.domain;

import java.util.Date;

public class WebMessage {

    private String text;
    private String number;
    private String key;
    private String code;
    private Date time;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    //TODO: This will change : just skeletal code
    public String subject() {
        String[] messagePieces = text.split(" ");
        return "Support: Case reported by " + messagePieces[1] + " on " + new Date();
    }

    //TODO: This will change : just skeletal code
    public String content() {
        String[] messagePieces = text.split(" ");
        return messagePieces[2];
    }
}
