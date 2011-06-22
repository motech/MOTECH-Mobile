package org.motechproject.mobile.domain.message;

import org.junit.Test;
import org.motechproject.mobile.strategy.ContentExtractionStrategy;
import org.motechproject.mobile.strategy.SupportCaseExtractionStrategy;

import static org.junit.Assert.assertEquals;

public class SupportCaseExtractionStrategyTest {

    @Test
    public void shouldExtractMessageContent() {
        String message =  "SUPPORT   465  We cannot upload forms";
        ContentExtractionStrategy strategy = new SupportCaseExtractionStrategy();
        SupportCase supportCase = strategy.extract(message);
        assertEquals("465",supportCase.raisedBy());
        assertEquals("We cannot upload forms",supportCase.description());
    }

}
