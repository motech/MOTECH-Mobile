package org.motechproject.mobile.web;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.easymock.IArgumentMatcher;
import org.junit.Test;
import org.motechproject.mobile.domain.WebMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class IncomingMessageControllerTest {

    @Test
    public void shouldSendMail() {
        JavaMailSender messenger = createMock(JavaMailSender.class);
        IncomingMessageController controller = new IncomingMessageController();
        controller.setMessenger(messenger);
        WebMessage message = new WebMessage();
        message.setText("Support 465 Issue");
        message.setCode("1982");
        message.setNumber("0123456789");
        message.setKey("key");
        message.setTime(new Date());

        SimpleMailMessage templateMessage = new SimpleMailMessage();
        templateMessage.setFrom("abc@abc.com");
        templateMessage.setTo("xyz@xyz.com");

        controller.setTemplateMessage(templateMessage);


        SimpleMailMessage expectedMessage = expectedMessage();

        messenger.send(equalsMessage(expectedMessage));
        expectLastCall();

        replay(messenger);

        ModelAndView modelAndView = controller.sendMail(message);

        verify(messenger);

        assertEquals("response", modelAndView.getViewName());
    }

    private SimpleMailMessage expectedMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText("Issue");
        mailMessage.setSubject("Support: Case reported by 465 on " + new Date());
        mailMessage.setFrom("abc@abc.com");
        mailMessage.setTo("xyz@xyz.com");
        return mailMessage;
    }

    private static SimpleMailMessage equalsMessage(SimpleMailMessage mailMessage){
        reportMatcher(new MessageMatcher(mailMessage));
        return mailMessage;
    }
}

class MessageMatcher implements IArgumentMatcher {

    private SimpleMailMessage mailMessage;

    MessageMatcher(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    public boolean matches(Object argument) {
        SimpleMailMessage other = (SimpleMailMessage) argument;
        return EqualsBuilder.reflectionEquals(mailMessage, other);
    }

    public void appendTo(StringBuffer buffer) {
        buffer.append("Expected : ").append(mailMessage);
    }
}
