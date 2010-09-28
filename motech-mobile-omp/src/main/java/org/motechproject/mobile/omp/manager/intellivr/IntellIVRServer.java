package org.motechproject.mobile.omp.manager.intellivr;

/**
 * Interface to the Intell IVR Server.
 * @author fcbrooks
 *
 */
public interface IntellIVRServer {

	/**
	 * Request that a call be initiate with parameters provided in the RequestType.
	 * {@link RequestType} and {@link ResponseType} are auto generated jaxb classes.
	 * See intellivr.xsd for the schema from which they originate.
	 * @param request
	 * @return
	 */
	ResponseType requestCall(RequestType request);
	
}
