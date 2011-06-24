package org.motechproject.mobile.domain.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.strategy.ContentExtractionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-incoming-servlet.xml"})
public class SMSMessageTest {

    @Autowired
    private ContentExtractionStrategy supportCaseExtractionStrategy;

    @Test
    public void shouldCreateParsedMessageAfterDecoding() throws UnsupportedEncodingException, ParseException {
        SMSMessage message = new SMSMessage();
        message.setText("SUPPORT+123+Cannot+Upload+Forms");
        message.setNumber("%2B233123456789");
        message.setTime("2011-06-25+09:30:29");

        SimpleDateFormat dateFormat = new MessageDateFormat();

        ParsedMessage parsedMessage = message.parseWith(supportCaseExtractionStrategy);

        assertEquals("Cannot Upload Forms",parsedMessage.caseDescription());
        assertEquals("123",parsedMessage.caseRaisedBy());
        assertEquals("+233123456789",parsedMessage.senderPhoneNumber());
        assertEquals("2011-06-25 09:30:29",dateFormat.format(parsedMessage.getTimeReported()));
    }
}
