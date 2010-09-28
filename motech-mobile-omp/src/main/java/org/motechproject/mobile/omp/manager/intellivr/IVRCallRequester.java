package org.motechproject.mobile.omp.manager.intellivr;

/**
 * Interface of a class that makes requests to the ivr system.  Honestly, this was just
 * added so the call could be mocked for testing purposes.  It serve no useful purpose
 * beyond that.
 * @author fcbrooks
 *
 */
public interface IVRCallRequester {
	public void requestCall(IVRCallSession session, String externalId);
}
