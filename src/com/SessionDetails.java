package com;

import java.util.Date;

/* 
 * This is a container class for storing all the session details for a given
 * sessionID. The cookie in the client holds the session ID which maps to an
 * instance of this class using Server.sessionMap
 */
public class SessionDetails
{
	String sessionID;
	String locationMetadata;
	Date timeStamp;
	String message;
	short version;
	
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
	
	public synchronized String getSessionID()
	{
		return sessionID;
	}
	public synchronized void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}
	public synchronized String getLocationMetadata()
	{
		return locationMetadata;
	}
	public synchronized void setLocationMetadata(String locationMetadata)
	{
		this.locationMetadata = locationMetadata;
	}
	public synchronized Date getTimeStamp()
	{
		return timeStamp;
	}
	public synchronized void setTimeStamp(Date timeStamp)
	{
		this.timeStamp = timeStamp;
	}
	public synchronized String getMessage()
	{
		return message;
	}
	public synchronized void setMessage(String message)
	{
		this.message = message;
	}
	public synchronized short getVersion()
	{
		return version;
	}
	public synchronized void setVersion(short version)
	{
		this.version = version;
	}
	
	// To check if the timeStamp of the instance has expired or not
	public boolean isExpired(Date currentTimeStamp)
	{
		if(timeStamp.compareTo(currentTimeStamp)>0)
		{	
			return false;
		}
		return true;
	}
	
	public String toString()
	{
		String text = "SessionID: "+sessionID+" LocationMetadata: "+locationMetadata+
				" TimeStamp: "+timeStamp+" Version: "+version+"\nMessage: \""+message+"\"";
		return text;
	}
}
 
