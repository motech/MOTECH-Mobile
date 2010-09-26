package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Date;
import java.util.List;

public interface IVRDAO {

	public long saveIVRCallSession(IVRCallSession callSession);
	public List<IVRCallSession> loadIVRCallSessions();
	public IVRCallSession loadIVRCallSession(long id);
	public List<IVRCallSession> loadIVRCallSessionsByState(Integer[] states);
	public List<IVRCallSession> loadIVRCallSessions(String user,String phone,String language, Integer[] states, int attempts, int days, String callDirection);
	public List<IVRCallSession> loadIVRCallSessionsByStateNextAttemptBeforeDate(Integer[] states, Date date);
	public List<IVRCallSession> loadIVRCallSessionsCreatedBetweenDates(Date start, Date end);
	public int countIVRCallSessionsCreatedBetweenDates(Date start, Date end);
	public int countIVRCallSesssions();
	public int countIVRCalls();
	public int countIVRCallsCreatedBetweenDates(Date start, Date end);
	public int countIVRCallsWithStatus(IVRCallStatus status);
	public int countIVRCallsCreatedBetweenDatesWithStatus(Date start, Date end, IVRCallStatus status);
	public IVRCall loadIVRCallByExternalId(String externalId);
	public List<IVRRecordingStat> getIVRRecordingStats();
	public List<IVRCallStatusStat> getIVRCallStatusStats();
	public List<IVRCallStatusStat> getIVRCallStatusStatsBetweenDates(Date start, Date end);	
}
