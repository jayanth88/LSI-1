package com;

import java.util.Date;
import javax.servlet.http.Cookie;

/* 
 * This class contains static methods that the servlets Server and ActionServer
 * use.
 */

public class  ServerUtilities
{ 
	/* 
	 * This function iterates through the cookie list and extracts the cookie
	 * with value equal to cookieName
	 */
	public static String getCookieValue(Cookie[] cookies, String cookieName) 
	{
		Cookie cookie;
		for(int i=0; i<cookies.length; i++) 
		{
			cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
			return(cookie.getValue());
		}
		return "";
	}

	/* 
	 * This function takes the cookieValue and extracts the sessionID from it.
	 * This sessionID is used to get the SessionDetail object from the sessionMap.
	 * The message is written to the object and the object is returned back to the
	 * caller.
	 */
	public static SessionDetails replaceRoutine(String cookieValue, String message)
	{
		String value[] = cookieValue.split("_");
		SessionDetails sd =  Server.sessionMap.get(value[0]);
		
		if(!(message == null) && !message.equals(""))
		{
			sd.setMessage(message);
			Server.sessionMap.put(value[0], sd);
		}
		
		// This function also additionally needs to refresh the timestamp value so...
		sd = refreshRoutine(cookieValue); 
		return sd;
	}

	/* 
	 * This function extracts the SessionDetails object from the sessionMap using
	 * the cookieValue. It then replaces the expiry timeStamp by a new value. This
	 * new value is equal to the current time + expiry duration
	 */
	@SuppressWarnings("deprecation")
	public static SessionDetails refreshRoutine(String cookieValue)
	{
		String value[] = cookieValue.split("_");
		SessionDetails sd =  Server.sessionMap.get(value[0]);
		Date currTime = new Date();
		currTime.setMinutes(currTime.getMinutes()+Server.sessionTimeOutDuration);
		sd.setTimeStamp(currTime);
		Server.sessionMap.put(value[0], sd);
		return sd;
	}

	/* 
	 * This function is used delete an entry from the sessionMap when a user logs
	 * out. It uses sessionID as key, which is extracted from cookieValue
	 */
	public static void logoutRoutine(String cookieValue)
	{
		String value[] = cookieValue.split("_");
		Server.sessionMap.remove(value[0]);
		
	}
	/* 
	 * This function is used to put the session details in an object and
	 * map it against a given sessionID
	 */
	
	@SuppressWarnings("deprecation")
	public static SessionDetails register(String sessionID,String locationMetaData)
	{	
		Date expiry = new Date();
		expiry.setMinutes(expiry.getMinutes()+Server.sessionTimeOutDuration);
		SessionDetails sd = new SessionDetails(sessionID,locationMetaData, expiry,"Hello User !",(short) 1);
		Server.sessionMap.put(sessionID, sd);
		return sd;
	}

	/*
	 * This function is used to return SessionDetails associated with a 
	 * given sessionID
	 */
	public static SessionDetails getRegisteredSession(String id)
	{	
		SessionDetails sd = Server.sessionMap.get(id);	
		return sd;
	}
}
