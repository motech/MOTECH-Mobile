package org.motechproject.mobile.omp.manager.intellivr;

public class IVRRecordingStat {

	private String name;
	private long totalListens;
	private double averageTimeListened;
	
	public IVRRecordingStat() {}
	
	public IVRRecordingStat(String name, long totalListens, double averageTimeListened) {
		this.name = name;
		this.totalListens = totalListens;
		this.averageTimeListened = averageTimeListened;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public long getTotalListens() {
		return totalListens;
	}

	public void setTotalListens(long totalListens) {
		this.totalListens = totalListens;
	}

	public double getAverageTimeListened() {
		return averageTimeListened;
	}

	public void setAverageTimeListened(double averageTimeListened) {
		this.averageTimeListened = averageTimeListened;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageTimeListened);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (totalListens ^ (totalListens >>> 32));
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
		IVRRecordingStat other = (IVRRecordingStat) obj;
		if (Double.doubleToLongBits(averageTimeListened) != Double
				.doubleToLongBits(other.averageTimeListened))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (totalListens != other.totalListens)
			return false;
		return true;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		
		builder.append("[IVRRecordingStat");
		builder.append(" name=");
		builder.append(name == null ? "null" : name);
		builder.append(" totalListens=");
		builder.append(totalListens);
		builder.append(" averageTimeListened=");
		builder.append(averageTimeListened);
		builder.append("]");
		
		return builder.toString();
		
	}

}
