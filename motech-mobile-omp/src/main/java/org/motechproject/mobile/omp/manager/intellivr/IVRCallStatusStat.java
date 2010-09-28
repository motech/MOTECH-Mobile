package org.motechproject.mobile.omp.manager.intellivr;

/**
 * For holding stats about a particular call status
 * @author fcbrooks
 *
 */
public class IVRCallStatusStat {

	private IVRCallStatus status;
	private long count;
	
	public IVRCallStatusStat() {}
	
	public IVRCallStatusStat(IVRCallStatus status, long count) {
		this.status = status;
		this.count = count;
	}

	/**
	 * 
	 * @return {@link IVRCallStatus} 
	 */
	public IVRCallStatus getStatus() {
		return status;
	}

	public void setStatus(IVRCallStatus status) {
		this.status = status;
	}

	/**
	 * 
	 * @return Count of {@link IVRCall} with this status
	 */
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (count ^ (count >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		IVRCallStatusStat other = (IVRCallStatusStat) obj;
		if (count != other.count)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
	
		builder.append("[IVRCallStatusStat");
		builder.append(" status=");
		builder.append(status == null ? "null" : status);
		builder.append(" count=");
		builder.append(count);
		builder.append("]");
		
		return builder.toString();
		
	}
	
}
