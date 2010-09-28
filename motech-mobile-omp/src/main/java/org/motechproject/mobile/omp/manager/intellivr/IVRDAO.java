package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Date;
import java.util.List;

/**
 * DAO for accessing the IVR specific types in the database
 * @author fcbrooks
 *
 */
public interface IVRDAO {

	/**
	 * 
	 * @param callSession
	 * @return id of saved/updated session
	 */
	public long saveIVRCallSession(IVRCallSession callSession);
	
	/**
	 * 
	 * @return List of all {@link IVRCallSession}
	 */
	public List<IVRCallSession> loadIVRCallSessions();
	
	/**
	 * 
	 * @param user
	 * @return List of {@link IVRCallSession} for provided user
	 */
	public List<IVRCallSession> loadIVRCallSessionsByUser(String user);
	
	/**
	 * 
	 * @param phone
	 * @return List of {@link IVRCallSession} for provided phone number
	 */
	public List<IVRCallSession> loadIVRCallSessionsByPhone(String phone);
	
	/**
	 * 
	 * @param id
	 * @return {@link IVRCallSession} with provided id
	 */
	public IVRCallSession loadIVRCallSession(long id);
	
	/**
	 * 
	 * @param states
	 * @return List of {@link IVRCallSession} in provided states
	 */
	public List<IVRCallSession> loadIVRCallSessionsByState(Integer[] states);
	
	/**
	 * 
	 * @param user
	 * @param phone
	 * @param language
	 * @param states
	 * @param attempts
	 * @param days
	 * @param callDirection
	 * @return List of {@link IVRCallSession} matching criteria
	 */
	public List<IVRCallSession> loadIVRCallSessions(String user,String phone,String language, Integer[] states, int attempts, int days, String callDirection);
	
	/**
	 * 
	 * @param states
	 * @param date
	 * @return List of {@link IVRCallSession} in provided state with nextAttempt prior to date
	 */
	public List<IVRCallSession> loadIVRCallSessionsByStateNextAttemptBeforeDate(Integer[] states, Date date);
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return List of {@link IVRCallSession} with created between start and end
	 */
	public List<IVRCallSession> loadIVRCallSessionsCreatedBetweenDates(Date start, Date end);
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return Count of {@link IVRCallSession} with created between start and end
	 */
	public int countIVRCallSessionsCreatedBetweenDates(Date start, Date end);
	
	/**
	 * 
	 * @return Count of all {@link IVRCallSession}
	 */
	public int countIVRCallSesssions();
	
	/**
	 * 
	 * @return Count of all {@link IVRCall}
	 */
	public int countIVRCalls();
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return Count of {@link IVRCall} created between start and end
	 */
	public int countIVRCallsCreatedBetweenDates(Date start, Date end);
	
	/**
	 * 
	 * @param status
	 * @return Count of all {@link IVRCall} with provided {@link IVRCallStatus}
	 */
	public int countIVRCallsWithStatus(IVRCallStatus status);
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @param status
	 * @return
	 */
	public int countIVRCallsCreatedBetweenDatesWithStatus(Date start, Date end, IVRCallStatus status);
	
	/**
	 * 
	 * @param externalId
	 * @return {@link IVRCall} with provided external id
	 */
	public IVRCall loadIVRCallByExternalId(String externalId);
	
	/**
	 * The system is onyl aware of recordings it has received reports about.  This
	 * may be fewer than those defined on the actual IVR system
	 * @return List of stats for each recording
	 */
	public List<IVRRecordingStat> getIVRRecordingStats();
	
	/**
	 * 
	 * @return List with one entry for each {@link IVRCallStatus} type
	 */
	public List<IVRCallStatusStat> getIVRCallStatusStats();
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return Same as above but for {@link IVRCall} with created between start and end
	 */
	public List<IVRCallStatusStat> getIVRCallStatusStatsBetweenDates(Date start, Date end);	
}