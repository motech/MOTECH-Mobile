package org.motechproject.mobile.web;

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

    public String requestParameters() {
        StringBuilder builder = new StringBuilder("?");
        builder.append("text=").append(text).append(AMP);
        builder.append("key=").append(key).append(AMP);
        builder.append("code=").append(code).append(AMP);
        builder.append("number=").append(number).append(AMP);
        builder.append("time=").append(time);
        return builder.toString();
    }

}
