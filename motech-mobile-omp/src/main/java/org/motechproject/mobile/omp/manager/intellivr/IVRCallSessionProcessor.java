package org.motechproject.mobile.omp.manager.intellivr;

/**
 * Process {@link IVRCallSession} from OPEN to SEND_WAIT and SEND_WAIT to REPORT_WAIT state
 * @author fcbrooks
 *
 */
public interface IVRCallSessionProcessor {

	public void processOpenSessions();
	public void processWaitingSessions();
	
}
