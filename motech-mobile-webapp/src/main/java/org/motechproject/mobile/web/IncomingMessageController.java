package org.motechproject.mobile.web;

import org.apache.log4j.Logger;
import org.motechproject.ws.Response;
import org.motechproject.ws.SMS;
import org.motechproject.ws.server.RegistrarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@Controller
public class IncomingMessageController {

    private static Logger log = Logger.getLogger(IncomingMessageController.class);

    private RegistrarService registrarService;
    private static final String VIEW = "sms_response";

    @RequestMapping(value = "/incomingmessage", method = RequestMethod.GET)
    public ModelAndView processSMS(@ModelAttribute SMS sms) throws UnsupportedEncodingException {
        log.info(sms);
        ModelAndView modelAndView = new ModelAndView(VIEW);
        Response response = registrarService.processSMS(sms);
        modelAndView.addObject("response",response.getContent());
        return modelAndView;
    }

    public void setRegistrarService(RegistrarService registrarService) {
        this.registrarService = registrarService;
    }
}
