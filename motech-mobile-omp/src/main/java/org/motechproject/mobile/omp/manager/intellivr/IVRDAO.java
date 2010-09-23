package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Date;
import java.util.List;

public interface IVRDAO {

	public long saveIVRCallSession(IVRCallSession callSession);
	public IVRCallSession loadIVRCallSession(long id);
	public List<IVRCallSession> loadIVRCallSessionsByState(Integer[] states);
	public List<IVRCallSession> loadIVRCallSessions(String user,String phone,String language, Integer[] states, int attempts, int days, String callDirection);
	public List<IVRCallSession> loadIVRCallSessionsByStateNextAttemptBeforeDate(Integer[] states, Date date);
	public IVRCallSession loadIVRCallSessionByExternalId(String externalId);
	public IVRCall loadIVRCallByExternalId(String externalId);
	
}
