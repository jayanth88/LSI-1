package com;

import java.util.Date;

public class SessionDetails
{
	String sessionID;
	
	public SessionDetails(String sessionID, String locationMetadata,
			Date timeStamp, String message, short version) {
		this.sessionID = sessionID;
		this.locationMetadata = locationMetadata;
		this.timeStamp = timeStamp;
		this.message = message;
		this.version = version;
	}

	public SessionDetails()
	{
		
	}
	String locationMetadata;
	Date timeStamp;
	String message;
	short version;
	
	
	
	public synchronized String getSessionID() {
		return sessionID;
	}
	public synchronized void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public synchronized String getLocationMetadata() {
		return locationMetadata;
	}
	public synchronized void setLocationMetadata(String locationMetadata) {
		this.locationMetadata = locationMetadata;
	}
	public synchronized Date getTimeStamp() {
		return timeStamp;
	}
	public synchronized void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public synchronized String getMessage() {
		return message;
	}
	public synchronized void setMessage(String message) {
		this.message = message;
	}
	public synchronized short getVersion() {
		return version;
	}
	public synchronized void setVersion(short version) {
		this.version = version;
	}
	
	public boolean isExpired(Date currentTimeStamp)
	{
		if(timeStamp.compareTo(currentTimeStamp)>0)
		{	
			return false;
		}
		return true;
	}
}
 
