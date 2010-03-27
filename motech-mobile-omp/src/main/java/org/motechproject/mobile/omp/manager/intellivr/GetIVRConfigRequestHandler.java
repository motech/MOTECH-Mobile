package org.motechproject.mobile.omp.manager.intellivr;

import org.motechproject.omp.manager.intellivr.GetIVRConfigRequest;
import org.motechproject.omp.manager.intellivr.ResponseType;

public interface GetIVRConfigRequestHandler {

	ResponseType handleRequest(GetIVRConfigRequest request);
	
}
