package org.motechproject.mobile.omp.manager.intellivr;

public interface GetIVRConfigRequestHandler {

	ResponseType handleRequest(GetIVRConfigRequest request);
	ResponseType handleRequest(GetIVRConfigRequest request, String externalId);
	
}
