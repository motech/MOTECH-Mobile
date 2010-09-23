package org.motechproject.mobile.omp.manager.intellivr;

import java.util.Date;

public class IVRMenu {

	private long id;
	private int version;
	private String name;
	private Date entryTime;
	private int duration;
	private String keyPressed;
	private String recording;
	
	public IVRMenu() {}
	
	public IVRMenu(String name, Date entryTime, int duration, String keyPressed, String recording) {
		this.name = name;
		this.entryTime = entryTime;
		this.duration = duration;
		this.keyPressed = keyPressed;
		this.recording = recording;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getKeyPressed() {
		return keyPressed;
	}
	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}
	public String getRecording() {
		return recording;
	}
	public void setRecording(String recording) {
		this.recording = recording;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		result = prime * result
				+ ((entryTime == null) ? 0 : entryTime.hashCode());
		result = prime * result
				+ ((keyPressed == null) ? 0 : keyPressed.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((recording == null) ? 0 : recording.hashCode());
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
		IVRMenu other = (IVRMenu) obj;
		if (duration != other.duration)
			return false;
		if (entryTime == null) {
			if (other.entryTime != null)
				return false;
		} else if (!entryTime.equals(other.entryTime))
			return false;
		if (keyPressed == null) {
			if (other.keyPressed != null)
				return false;
		} else if (!keyPressed.equals(other.keyPressed))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (recording == null) {
			if (other.recording != null)
				return false;
		} else if (!recording.equals(other.recording))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("[IVRMenu");
		builder.append(" id=");
		builder.append(id);
		builder.append(" version=");
		builder.append(version);
		builder.append(" name=");
		builder.append(name);
		builder.append(" entryTime=");
		builder.append(entryTime == null ? "null" : entryTime);
		builder.append(" duration=");
		builder.append(duration);
		builder.append(" keyPressed=");
		builder.append(keyPressed);
		builder.append(" recording=");
		builder.append(recording);
		builder.append("]");

		return builder.toString();
		
	}
	
	
	
}
