package org.motechproject.mobile.omp.manager.intellivr;

import java.util.List;

/**
 * Interface to a variety of IVR data that is useful for creating 
 * operational reports.
 * 
 * @author fcbrooks
 *
 */
public interface IVRCallStatsProvider {
	
	/**
	 * 
	 * @return Count of all the IVRCallSession created
	 */
	int getCountIVRCallSessions();
	
	/**
	 * @param minutes
	 * @return Count of the IVRCallSessions created in prior minutes provided
	 */
	int getCountIVRSessionsInLastMinutes(int minutes);
	
	/**
	 * @param hours
	 * @return Count of the IVRCallSessions created in the prior hours
	 */
	int getCountIVRCallSessionsInLastHours(int hours);
	
	/**
	 * @param days
	 * @return Count of the IVRCallSessions created in the prior days calendar days
	 */
	int getCountIVRCallSessionsInLastDays(int days);
	
	/**
	 * 
	 * @return Count of all IVRCalls created
	 */
	int getCountIVRCalls();
	
	/**
	 * 
	 * @param minutes
	 * @return Count of all IVRCalls in prior minutes
	 */
	int getCountIVRCallsInLastMinutes(int minutes);
	
	/**
	 * 
	 * @param hours
	 * @return Count of all IVRCalls in prior hours
	 */
	int getCountIVRCallsInLastHours(int hours);
	
	/**
	 * 
	 * @param days
	 * @return Count of allIVRCalls in prior calendar days
	 */
	int getCountIVRCallsInLastDays(int days);
	
	/**
	 * 
	 * @param status
	 * @return Count of IVRCalls with given status
	 */
	int getCountIVRCallsWithStatus(IVRCallStatus status);
	
	/**
	 * 
	 * @param minutes
	 * @param status
	 * @return Count of IVRCalls in prior minutes with provided status
	 */
	int getCountIVRCallsInLastMinutesWithStatus(int minutes, IVRCallStatus status);
	
	/**
	 * 
	 * @param hours
	 * @param status
	 * @return Count of IVRCalls in the prior hours with provided status
	 */
	int getCountIVRCallsInLastHoursWithStatus(int hours, IVRCallStatus status);
	
	/**
	 * 
	 * @param days
	 * @param status
	 * @return Count of IVRCalls with the prior calendar days with provided status
	 */
	int getCountIVRCallsInLastDaysWithStatus(int days, IVRCallStatus status);
	
	/**
	 *	Returns one {@link IVRRecordingStat} entry for each distinct recording
	 *	name a report has been received about.
	 * @return List of {@link IVRRecordingStat} 
	 */
	List<IVRRecordingStat> getIVRRecordingStats();
	
	/**
	 * 
	 * @return List with one entry for each {@link IVRCallStatus} type
	 */
	List<IVRCallStatusStat> getIVRCallStatusStats();
	
	/**
	 * 
	 * @param minutes
	 * @return Same as above but filtered by prior minutes
	 */
	List<IVRCallStatusStat> getIVRCallStatusStatsFromLastMinutes(int minutes);
	
	/**
	 * 
	 * @param hours
	 * @return Same as above but filtered by prior hours
	 */
	List<IVRCallStatusStat> getIVRCallStatusStatsFromLastHours(int hours);
	
	/**
	 * 
	 * @param days
	 * @return Same as above but filtered by prior calendar day
	 */
	List<IVRCallStatusStat> getIVRCallStatusStatsFromLastDays(int days);
	
	/**
	 * 
	 * @return All {@link IVRCallSession}
	 */
	List<IVRCallSession> getIVRCallSessions();
	
	/**
	 * 
	 * @param minutes
	 * @return {@link IVRCallSession} created in prior minutes
	 */
	List<IVRCallSession> getIVRCallSessionsInLastMinutes(int minutes);
	
	/**
	 * 
	 * @param hours
	 * @return {@link IVRCallSession} created in prior hours
	 */
	List<IVRCallSession> getIVRCallSessionsInLastHours(int hours);
	
	/**
	 * 
	 * @param days
	 * @return {@link IVRCallSession} created in prior calendar days
	 */
	List<IVRCallSession> getIVRCallSessionsInLastDays(int days);
	
	/**
	 * 
	 * @param user
	 * @return {@link IVRCallSession} for provided user
	 */
	List<IVRCallSession> getIVRCallSessionsForUser(String user);
	
	/**
	 * 
	 * @param phone
	 * @return {@link IVRCallSession} for provided phone number
	 */
	List<IVRCallSession> getIVRCallSessionsForPhone(String phone);
}
