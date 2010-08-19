package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class IVRCall {

	private long id;
	private int version;
	private Date created;
	private Date connected;
	private Date disconnected;
	private int duration;
	private Set<IVRMenu> menus;
	private Set<IVRCallStatusEvent> callStatusEvents;
	
	public IVRCall() {}
	
	public IVRCall(Date created,Date connected,Date disconnected,int duration) {
		this.created = created;
		this.connected = connected;
		this.disconnected = disconnected;
		this.duration = duration;
		menus = new HashSet<IVRMenu>();
		callStatusEvents = new HashSet<IVRCallStatusEvent>();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getConnected() {
		return connected;
	}
	public void setConnected(Date connected) {
		this.connected = connected;
	}
	public Date getDisconnected() {
		return disconnected;
	}
	public void setDisconnected(Date disconnected) {
		this.disconnected = disconnected;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Set<IVRMenu> getMenus() {
		return menus;
	}

	public void setMenus(Set<IVRMenu> menus) {
		this.menus = menus;
	}

	public Set<IVRCallStatusEvent> getCallStatusEvents() {
		return callStatusEvents;
	}

	public void setCallStatusEvents(Set<IVRCallStatusEvent> callStatusEvents) {
		this.callStatusEvents = callStatusEvents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((callStatusEvents == null) ? 0 : callStatusEvents.hashCode());
		result = prime * result
				+ ((connected == null) ? 0 : connected.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((disconnected == null) ? 0 : disconnected.hashCode());
		result = prime * result + duration;
		result = prime * result + ((menus == null) ? 0 : menus.hashCode());
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
		IVRCall other = (IVRCall) obj;
		if (callStatusEvents == null) {
			if (other.callStatusEvents != null)
				return false;
		} else if (!callStatusEvents.equals(other.callStatusEvents))
			return false;
		if (connected == null) {
			if (other.connected != null)
				return false;
		} else if (!connected.equals(other.connected))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (disconnected == null) {
			if (other.disconnected != null)
				return false;
		} else if (!disconnected.equals(other.disconnected))
			return false;
		if (duration != other.duration)
			return false;
		if (menus == null) {
			if (other.menus != null)
				return false;
		} else if (!menus.equals(other.menus))
			return false;
		return true;
	}
}
