package org.motechproject.mobile.omp.manager.intellivr;

import org.motechproject.mobile.core.model.NotificationType;

/**
 * Provides the mapping between a {@link NotificationType} and a recording or tree name
 * in the IVR system.  Tells if is an informational or reminder message
 * @author fcbrooks
 *
 */
public class IVRNotificationMapping {
	
	public static String INFORMATIONAL = "I";
	public static String REMINDER = "R";
	
	private long id;
	private String type;
	private String ivrEntityName;

	/**
	 * 
	 * @return id of the {@link NotificationType} 
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return INFORMATIONAL or REMINDER depending on type
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = null;
		if ( type.equalsIgnoreCase(INFORMATIONAL) ||
				type.equalsIgnoreCase(REMINDER) ) 
			this.type = type;
	}

	/**
	 * 
	 * @return recording or tree name in the IVR system
	 */
	public String getIvrEntityName() {
		return ivrEntityName;
	}

	public void setIvrEntityName(String ivrEntityName) {
		this.ivrEntityName = ivrEntityName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		IVRNotificationMapping other = (IVRNotificationMapping) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
