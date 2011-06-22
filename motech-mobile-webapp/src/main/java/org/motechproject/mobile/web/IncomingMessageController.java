package org.motechproject.mobile.web;

import org.motechproject.mobile.domain.mail.Mail;
import org.motechproject.mobile.domain.mail.MailTemplate;
import org.motechproject.mobile.domain.message.ParsedMessage;
import org.motechproject.mobile.domain.message.SMSMessage;
import org.motechproject.mobile.strategy.ContentExtractionStrategy;
import org.motechproject.ws.MotechStaff;
import org.motechproject.ws.server.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IncomingMessageController {

    private static final String SUCCESS = "response_success";
    private static final String UNKNOWN_KEYWORD = "response_unknown_keyword";

    @Autowired
    private JavaMailSender messenger;

    @Autowired
    private SimpleMailMessage templateMessage;

    @Autowired
    @Qualifier("registrarClient")
    private RegistrarService registrarService;


    @Autowired
    private ContentExtractionStrategy contentExtractionStrategy;

    @Autowired
    private MailTemplate mailTemplate;


    private static final String KEYWORD = "SUPPORT";


    @RequestMapping(value = "/incomingmessage", method = RequestMethod.GET)
    public ModelAndView sendMail(@ModelAttribute SMSMessage smsMessage) {
        if (smsMessage.hasKeyword(KEYWORD)) {
            ParsedMessage parsedMessage = smsMessage.parseWith(contentExtractionStrategy);
            SimpleMailMessage message = new SimpleMailMessage(templateMessage);
            Map data = dataFrom(parsedMessage);
            Mail mail = new Mail(mailTemplate).with(data);
            message.setSubject(mail.subject());
            message.setText(mail.body());
            messenger.send(message);
            return new ModelAndView(SUCCESS);
        }
        return new ModelAndView(UNKNOWN_KEYWORD);
    }

    private Map dataFrom(ParsedMessage parsedMessage) {
        Map data = new HashMap();
        MotechStaff staff = registrarService.getStaffDetails(parsedMessage.caseRaisedBy());
        data.put("staff", staff);
        data.put("date", new SimpleDateFormat("dd-mm-yyyy").format(parsedMessage.getTimeReported()));
        data.put("message", parsedMessage.caseDescription());
        return data;
    }

    public void setMessenger(JavaMailSender messenger) {
        this.messenger = messenger;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void setContentExtractionStrategy(ContentExtractionStrategy contentExtractionStrategy) {
        this.contentExtractionStrategy = contentExtractionStrategy;
    }

    public void setMailTemplate(MailTemplate mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public void setRegistrarService(RegistrarService registrarService) {
        this.registrarService = registrarService;
    }
}
