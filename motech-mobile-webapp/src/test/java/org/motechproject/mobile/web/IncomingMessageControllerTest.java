package org.motechproject.mobile.web;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncomingMessageControllerTest  {

    @Test
    public void shouldRedirectToOpenMRS() {
        InboundMessage message = new InboundMessage();
        message.setCode("1982");
        message.setKey("SUPPORT");
        message.setNumber("%2B233123456789");
        message.setText("Hi");
        message.setTime("2011-03-03+10:10:10");
        IncomingMessageController messageController = new IncomingMessageController();
        messageController.setRedirectionURL("http://localhost:8080/openmrs/module/motechmodule/incomingmessage");
        String redirection = messageController.sendMail(message);
        assertEquals
          ("redirect:http://localhost:8080/openmrs/module/motechmodule/incomingmessage" +
                  "?text=Hi&key=SUPPORT&code=1982&number=%2B233123456789&time=2011-03-03+10:10:10"
                  ,redirection);
    }

}
