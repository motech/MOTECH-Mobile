package org.motechproject.mobile.domain.mail;

import java.util.Map;

public class Mail {
    private MailTemplate template;
    private Map data;

    public Mail(MailTemplate mailTemplate) {
        this.template = mailTemplate;
    }

    public Mail with(Map data) {
        this.data = data ;
        return this;
    }

    public String subject() {
        return template.subject(data);
    }

    public String body() {
        return template.body(data);
    }
}
