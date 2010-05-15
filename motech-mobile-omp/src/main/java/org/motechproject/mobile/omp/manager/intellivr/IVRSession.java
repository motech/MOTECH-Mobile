package org.motechproject.mobile.omp.manager.intellivr;

import java.util.ArrayList;
import java.util.Collection;

import org.motechproject.mobile.core.model.GatewayRequest;

public class IVRSession {

	private String userId;
	private String phone;
	private String language;
	private int attempts;
	private Collection<GatewayRequest> requests;
	
	public IVRSession(String userId, String phone, String language) {
		this.userId = userId;
		this.phone = phone;
		this.language = language;
		requests = new ArrayList<GatewayRequest>();
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
	 * String to uniquely identify the session
	 * @return sessionId
	 */
	public String getSessionId() {
		return userId + "-" + phone;
	}

	/**
	 * Adds {@link GatewayRequest} to the session
	 * @param r
	 */
	public void addGatewayRequest(GatewayRequest r) {
		boolean found = false;
		for (GatewayRequest gr : requests) {
			if ( gr.getId() == r.getId() )
				found = true;
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
			if ( gr.getId() == r.getId() )
				toRemove.add(gr);
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
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
