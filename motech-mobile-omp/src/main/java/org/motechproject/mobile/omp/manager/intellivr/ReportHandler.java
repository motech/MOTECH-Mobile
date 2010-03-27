package org.motechproject.mobile.omp.manager.intellivr;

import org.motechproject.omp.manager.intellivr.ReportType;
import org.motechproject.omp.manager.intellivr.ResponseType;

public interface ReportHandler {
	
	ResponseType handleReport(ReportType report);
	
}
