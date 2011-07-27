package org.motechproject.mobile.web;

import org.junit.Test;
import org.motechproject.ws.Response;
import org.motechproject.ws.SMS;
import org.motechproject.ws.server.RegistrarService;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IncomingMessageControllerTest  {

    @Test
    public void shouldRedirectToOpenMRS() throws UnsupportedEncodingException {
        RegistrarService registrarService = createMock(RegistrarService.class);
        IncomingMessageController messageController = new IncomingMessageController();
        messageController.setRegistrarService(registrarService);

        SMS sms = sms();
        expect(registrarService.processSMS(sms)).andReturn(new Response("OK.Tested"));

        replay(registrarService);

        ModelAndView modelAndView = messageController.processSMS(sms);

        verify(registrarService);

        assertNotNull(modelAndView);
        assertEquals("sms_response",modelAndView.getViewName());
        assertEquals("OK.Tested",modelAndView.getModel().get("response"));

    }

    private SMS sms() {
        SMS sms = new SMS();
        sms.setCode("1982");
        sms.setKey("SUPPORT");
        sms.setNumber("+233123456789");
        sms.setText("Hi");
        sms.setTime("2011-03-03 10:10:10");
        return sms;
    }

}
