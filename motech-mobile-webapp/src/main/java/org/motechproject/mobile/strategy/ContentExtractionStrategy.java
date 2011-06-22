package org.motechproject.mobile.strategy;

import org.motechproject.mobile.domain.message.SupportCase;

public interface ContentExtractionStrategy {
    SupportCase extract(String message);
}
