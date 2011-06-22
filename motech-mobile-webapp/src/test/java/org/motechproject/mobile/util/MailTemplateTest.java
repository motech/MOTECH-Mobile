package org.motechproject.mobile.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.domain.mail.MailTemplate;
import org.motechproject.ws.MotechStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-incoming-servlet.xml"})
public class MailTemplateTest {

    @Autowired
    private MailTemplate mailTemplate;


    @Test
    public void shouldCreateTextFromTemplate() {
        Map data = new HashMap();
        MotechStaff staff = new MotechStaff("123", "Joe", "Jee");
        data.put("staff", staff);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        data.put("date",dateFormat.format(new Date()));
        data.put("message","Network Failure");


        assertEquals("Support: Case reported by Joe Jee on " + dateFormat.format(new Date()), mailTemplate.subject(data));
        assertEquals("Joe Jee with Staff id 123 reported the following issue: Network Failure", mailTemplate.body(data));
    }
}
