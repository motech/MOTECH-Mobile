package org.motechproject.mobile.omp.manager.intellivr;

/**
 * Interface for a class that process reports from the IVR system about completed calls
 * @author fcbrooks
 *
 */
public interface ReportHandler {
	
	ResponseType handleReport(ReportType report);
	
}
