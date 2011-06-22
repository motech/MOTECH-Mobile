package org.motechproject.mobile.web;

import org.motechproject.mobile.domain.WebMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IncomingMessageController {

    private static final String VIEW = "response";

    @Autowired
    private JavaMailSender messenger ;

    @Autowired
    private SimpleMailMessage templateMessage ;


    @RequestMapping(value = "/incomingmessage",method = RequestMethod.GET)
    public ModelAndView sendMail(@ModelAttribute WebMessage webMessage) {
        SimpleMailMessage message = new SimpleMailMessage(templateMessage);
        message.setSubject(webMessage.subject());
        message.setText(webMessage.content());
        messenger.send(message);
        return new ModelAndView(VIEW);
    }

    public void setMessenger(JavaMailSender messenger) {
        this.messenger = messenger;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
}
