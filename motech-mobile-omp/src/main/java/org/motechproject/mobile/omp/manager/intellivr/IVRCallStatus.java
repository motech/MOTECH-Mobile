package org.motechproject.mobile.omp.manager.intellivr;

public enum IVRCallStatus {
	
	/*
	 * For outbound calls - a request has been placed with the ivr server to call a user
	 * For inbound calls - a request from the IVR server for content has been processed
	 */
	REQUESTED,
	
	/*
	 * an error was received placing a request for an outbound call with the ivr server
	 */
	APIERROR,
	
	/*
	 * the call completed normally - user hung up.
	 */
	COMPLETED,
	
	/*
	 * the call was rejected by the user
	 */
	REJECTED,
	
	/*
	 * the phone was busy
	 */
	BUSY,
	
	/*
	 * the call was not connected due to network congestion
	 */
	CONGESTION,
	
	/*
	 * the call was not answered
	 */
	NOANSWER,
	
	/*
	 * an error occurred on the ivr server	
	 */
	INTERNALERROR,
	
	/*
	 * call was completed but user did not listen to a sufficient ammount of the messages
	 */
	BELOWTHRESHOLD
	
}
