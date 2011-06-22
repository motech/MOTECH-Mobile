package org.motechproject.mobile.domain.mail;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Map;

public class MailTemplate {

    private VelocityEngine velocityEngine;
    private String template;
    private Map data;
    private String subjectTemplate;
    private String bodyTemplate;

    public MailTemplate(String subjectTemplate, String bodyTemplate) {
        this.subjectTemplate = subjectTemplate;
        this.bodyTemplate = bodyTemplate;
    }

    public MailTemplate withTemplate(String templateFile) {
        this.template = templateFile;
        return this;
    }

    public MailTemplate andData(Map data) {
        this.data = data;
        return this;
    }


    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public String subject(Map data) {
        return renderWith(subjectTemplate,data).trim();
    }

    public String body(Map data) {
        return renderWith(bodyTemplate,data);
    }

    private String renderWith(String template, Map data) {
        return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, data);
    }
}
