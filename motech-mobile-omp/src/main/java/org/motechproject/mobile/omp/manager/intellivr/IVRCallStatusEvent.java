package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Date;

public class IVRCallStatusEvent {

	private long id;
	private int version;
	private IVRCallStatus ivrCallStatus;
	private Date time;
	private String reason;
	
	public IVRCallStatusEvent() {}
	
	public IVRCallStatusEvent(IVRCallStatus ivrCallStatus, Date time, String reason) {
		this.ivrCallStatus = ivrCallStatus;
		this.time = time;
		this.reason = reason;
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

	public IVRCallStatus getIvrCallStatus() {
		return ivrCallStatus;
	}
	
	public void setIvrCallStatus(IVRCallStatus ivrCallStatus) {
		this.ivrCallStatus = ivrCallStatus;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ivrCallStatus == null) ? 0 : ivrCallStatus.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		IVRCallStatusEvent other = (IVRCallStatusEvent) obj;
		if (ivrCallStatus == null) {
			if (other.ivrCallStatus != null)
				return false;
		} else if (!ivrCallStatus.equals(other.ivrCallStatus))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
}
