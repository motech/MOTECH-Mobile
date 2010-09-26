package org.motechproject.mobile.omp.manager.intellivr;

import java.util.List;

public interface IVRCallStatsProvider {
	int getCountIVRCallSessions();
	int getCountIVRSessionsInLastMinutes(int minutes);
	int getCountIVRCallSessionsInLastHours(int hours);
	int getCountIVRCallSessionsInLastDays(int days);
	int getCountIVRCalls();
	int getCountIVRCallsInLastMinutes(int minutes);
	int getCountIVRCallsInLastHours(int hours);
	int getCountIVRCallsInLastDays(int days);
	int getCountIVRCallsWithStatus(IVRCallStatus status);
	int getCountIVRCallsInLastMinutesWithStatus(int minutes, IVRCallStatus status);
	int getCountIVRCallsInLastHoursWithStatus(int hours, IVRCallStatus status);
	int getCountIVRCallsInLastDaysWithStatus(int days, IVRCallStatus status);
	List<IVRRecordingStat> getIVRRecordingStats();
}
