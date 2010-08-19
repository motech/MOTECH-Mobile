package org.motechproject.mobile.omp.manager.intellivr;

import java.util.List;

public interface IVRDAO {

	public long saveIVRCallSession(IVRCallSession callSession);
	public IVRCallSession loadIVRCallSession(long id);
	public List<IVRCallSession> loadIVRCallSessionsByState(Integer[] states);
	public List<IVRCallSession> loadIVRCallSessionsByUserPhoneAndState(String user,String phone,Integer[] states);
	public IVRCallSession loadIVRCallSessionByExternalId(String externalId);
	
}
