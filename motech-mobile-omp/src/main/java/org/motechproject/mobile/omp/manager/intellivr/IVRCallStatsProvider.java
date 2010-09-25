package org.motechproject.mobile.omp.manager.intellivr;

public interface IVRCallStatsProvider {
	int getCountIVRCallSessions();
	int getCountIVRSessionsInLastMinutes(int minutes);
	int getCountIVRCallSessionsInLastHours(int hours);
	int getCountIVRCallSessionsInLastDays(int days);
}
