package org.motechproject.mobile.domain.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.strategy.ContentExtractionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-incoming-servlet.xml"})
public class SupportCaseExtractionStrategyTest {

    @Autowired
    private ContentExtractionStrategy supportCaseExtractionStrategy;


    @Test
    public void shouldExtractSupportCase() {
        String message =  "SUPPORT   465  We cannot upload forms";
        SupportCase supportCase = supportCaseExtractionStrategy.extract(message);
        assertEquals("465",supportCase.raisedBy());
        assertEquals("We cannot upload forms",supportCase.description());
    }

}
