package org.motechproject.mobile.omp.manager.intellivr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IntellIVRTestServer implements IntellIVRServer {

	Log log = LogFactory.getLog(IntellIVRTestServer.class);
	
	public ResponseType requestCall(RequestType request) {

		log.debug("Received call request " + request.toString());
		
		ResponseType response = new ResponseType();
		response.setStatus(StatusType.OK);
		/*response.setStatus(StatusType.ERROR);
		response.setErrorCode(ErrorCodeType.IVR_UNKNOWN_ERROR);*/
		
		return response;
	}

}
