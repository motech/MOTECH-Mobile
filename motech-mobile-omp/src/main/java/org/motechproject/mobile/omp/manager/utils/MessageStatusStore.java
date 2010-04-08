package org.motechproject.mobile.omp.manager.utils;

/*
 * Originally written by Brent Atkinson
 */
public interface MessageStatusStore {

	public abstract void updateStatus(String id, String status);

	public abstract String getStatus(String id);
	
}
