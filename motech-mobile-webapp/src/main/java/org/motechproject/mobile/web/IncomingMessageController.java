package org.motechproject.mobile.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IncomingMessageController {

    private static Logger log = Logger.getLogger(IncomingMessageController.class);

    private String redirectionURL;
    private static final String REDIRECT = "redirect:";

    @RequestMapping(value = "/incomingmessage", method = RequestMethod.GET)
    public String sendMail(@ModelAttribute InboundMessage message)  {
        return REDIRECT + redirectionURL + message.requestParameters();
    }

    public void setRedirectionURL(String redirectionURL) {
        this.redirectionURL = redirectionURL;
    }
}
