package org.motechproject.mobile.web;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.easymock.IArgumentMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.domain.mail.MailTemplate;
import org.motechproject.mobile.domain.message.SMSMessage;
import org.motechproject.mobile.strategy.SupportCaseExtractionStrategy;
import org.motechproject.ws.MotechStaff;
import org.motechproject.ws.server.RegistrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-incoming-servlet.xml"})
public class IncomingMessageControllerTest  {

    @Autowired
    private MailTemplate mailTemplate;

    @Test
    public void shouldSendMail() {
        JavaMailSender messenger = createMock(JavaMailSender.class);
        RegistrarService registrarService = createMock(RegistrarService.class);

        SupportCaseExtractionStrategy contentExtractionStrategy = new SupportCaseExtractionStrategy();
        contentExtractionStrategy.setMessageExpression("(SUPPORT\\s+\\d+){1}");
        IncomingMessageController controller = new IncomingMessageController();
        controller.setMessenger(messenger);
        controller.setContentExtractionStrategy(contentExtractionStrategy);
        controller.setRegistrarService(registrarService);
        SMSMessage message = new SMSMessage();
        message.setText("SUPPORT 465 Network Failure");
        message.setCode("1982");
        message.setNumber("0123456789");
        message.setKey("SUPPORT");
        message.setTime(new Date());

        controller.setMailTemplate(mailTemplate);

        SimpleMailMessage templateMessage = new SimpleMailMessage();
        templateMessage.setFrom("abc@abc.com");
        templateMessage.setTo("xyz@xyz.com");
        controller.setTemplateMessage(templateMessage);

        MotechStaff staff = new MotechStaff("123", "Joe", "Jee");
        expect(registrarService.getStaffDetails("465")).andReturn(staff);


        messenger.send(equalsMessage(expectedMessage()));
        expectLastCall();

        replay(messenger,registrarService);

        ModelAndView modelAndView = controller.sendMail(message);

        verify(messenger,registrarService);

        assertEquals("response_success", modelAndView.getViewName());
    }

    @Test
    public void shouldReturnUnknownKeywordMessageIfKeywordNotRecognized() {
        IncomingMessageController controller = new IncomingMessageController();
        SMSMessage message = new SMSMessage();
        message.setText("Some 465 Issue");
        ModelAndView modelAndView = controller.sendMail(message);
        assertEquals("response_unknown_keyword",modelAndView.getViewName());
    }

    private SimpleMailMessage expectedMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("abc@abc.com");
        mailMessage.setTo("xyz@xyz.com");
        mailMessage.setSubject("Support: Case reported by Joe Jee on " + new SimpleDateFormat("dd-mm-yyyy").format(new Date()));
        mailMessage.setText("Joe Jee with Staff id 123 reported the following issue: Network Failure");
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
