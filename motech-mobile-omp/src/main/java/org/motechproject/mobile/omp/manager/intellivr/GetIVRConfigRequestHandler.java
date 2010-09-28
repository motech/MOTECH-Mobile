package org.motechproject.mobile.omp.manager.intellivr;

/**
 * Interface for handling request from content for a user that calls into the ivr system
 * @author fcbrooks
 *
 */
public interface GetIVRConfigRequestHandler {

	/**
	 * The {@link ResponseType} will have its private field set to a random unique value.  
	 * {@link GetIVRConfigRequest} is an auto generated jaxb class based on the schema of
	 * the IntellIVR API.
	 * @param request
	 * @return {@link ResponseType} contain content for user
	 */
	ResponseType handleRequest(GetIVRConfigRequest request);
	
	/**
	 * The {@link ResponseType} will have its private field set to the externalId parameter.
 	 * {@link GetIVRConfigRequest} is an auto generated jaxb class based on the schema of
	 * the IntellIVR API.
	 * @param request
	 * @param externalId
	 * @return {@link ResponseType} contain content for user
	 */
	ResponseType handleRequest(GetIVRConfigRequest request, String externalId);
	
}
