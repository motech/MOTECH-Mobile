package org.motechproject.mobile.web;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class IncomingMessageControllerTest  {

    @Test
    public void shouldRedirectToOpenMRS() throws UnsupportedEncodingException {
        InboundMessage message = new InboundMessage();
        message.setCode("1982");
        message.setKey("SUPPORT");
        message.setNumber("+233123456789");
        message.setText("Hi");
        message.setTime("2011-03-03 10:10:10");
        IncomingMessageController messageController = new IncomingMessageController();
        messageController.setRedirectionURL("http://localhost:8080/openmrs/module/motechmodule/incomingmessage");
        String redirection = messageController.sendMail(message);
        assertEquals
          ("redirect:http://localhost:8080/openmrs/module/motechmodule/incomingmessage" +
                  "?text=Hi&number=%2B233123456789&key=SUPPORT&time=2011-03-03+10%3A10%3A10&code=1982"
                  ,redirection);
    }

}
