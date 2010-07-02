package org.motechproject.mobile.omp.manager.intellivr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.motechproject.mobile.core.model.GatewayRequest;

@SuppressWarnings("serial")
public class IVRSession implements Serializable {

	public static int OPEN 			=	0;
	public static int SEND_WAIT		=	1;
	public static int REPORT_WAIT	=	2;
	public static int CLOSED		=	3;
	
	private String sessionId;
	private String userId;
	private String phone;
	private String language;
	private boolean userInitiated;
	private int attempts;
	private int days;
	private int state;
	private Collection<GatewayRequest> requests;
	
	/**
	 * create a server initiated session
	 * @param userId
	 * @param phone
	 * @param language
	 */
	public IVRSession(String userId, String phone, String language) {
		this.sessionId = UUID.randomUUID().toString();
		this.userId = userId;
		this.phone = phone;
		this.language = language;
		this.userInitiated = false;
		requests = new ArrayList<GatewayRequest>();
	}
	
	/**
	 * create a server initiated session
	 * @param userId
	 * @param phone
	 * @param language
	 * @param days
	 */
	public IVRSession(String userId, String phone, String language, int days) {
		this.sessionId = UUID.randomUUID().toString();
		this.userId = userId;
		this.phone = phone;
		this.language = language;
		this.days = days;
		this.userInitiated = false;
		requests = new ArrayList<GatewayRequest>();
	}
	
	/**
	 * create a user initiated session
	 * @param userId
	 */
	public IVRSession(String userId) {
		this.sessionId = UUID.randomUUID().toString();
		this.userId = userId;
		this.userInitiated = true;
		requests = new ArrayList<GatewayRequest>();
	}
	
	/**
	 * String to uniquely identify the session
	 * @return sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	
	/**
	 * Set the session identifier
	 * @param sessionId
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/**
	 * Get user id of the recipient
	 * @return userid
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Set userid of the recipient
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Get phone number of recipient
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set phone number of recipient
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * indicates if session was the result of a user calling the system.  If false then 
	 * the session was the result of the server calling the user.
	 * @return
	 */
	public boolean isUserInitiated() {
		return userInitiated;
	}

	/**
	 * set to true to indicate that the session resulted from a user calling the system
	 * @param userInitiated
	 */
	public void setUserInitiated(boolean userInitiated) {
		this.userInitiated = userInitiated;
	}

	/**
	 * Number of time a call has been attempted
	 * @return attempts
	 */
	public int getAttempts() {
		return attempts;
	}

	/**
	 * Set the number of time the call has been attempted
	 * @param attempts
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	/**
	 * Number of days the call has been attempted
	 * @return int 
	 */
	public int getDays() {
		return days;
	}

	/**
	 * Set the number of days the call has been attempted
	 * @param days
	 */
	public void setDays(int days) {
		this.days = days;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Adds {@link GatewayRequest} to the session
	 * @param r
	 */
	public void addGatewayRequest(GatewayRequest r) {
		boolean found = false;
		for (GatewayRequest gr : requests) {
			if( userInitiated ) {
				if ( gr.getMessageRequest().getId() == r.getMessageRequest().getId() )
					found = true;
			} else {
				if ( gr.getId() == r.getId() )
					found = true;
			}
		}
		if ( !found )
			requests.add(r);
	}

	/**
	 * Removes a {@link GatewayRequest} from the session
	 * @param r
	 */
	public void removeGatewayRequest(GatewayRequest r) {
		ArrayList<GatewayRequest> toRemove = new ArrayList<GatewayRequest>();
		for ( GatewayRequest gr : requests )
			if ( userInitiated ) {
				if ( gr.getMessageRequest().getId() == r.getMessageRequest().getId() )
					toRemove.add(gr);
			} else { 
				if ( gr.getId() == r.getId() )
					toRemove.add(gr);
			}
		for ( GatewayRequest gr : toRemove )
			requests.remove(gr);
	}
	
	/**
	 * Get a {@link Collection} of the {@link GatewayRequest} in session
	 * @return
	 */
	public Collection<GatewayRequest> getGatewayRequests() {
		return requests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IVRSession other = (IVRSession) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}


}
