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
		Cookie temp_cookie;
		if (cookies == null)
		{
			return "";
		}
		else
		{
			for(int i=0; i<cookies.length; i++)
			{
				temp_cookie = cookies[i];
				if (cookieName.equals(temp_cookie.getName()))
				return(temp_cookie.getValue());
			}
		}
		return "";
	}

	public static SessionDetails retrieveFromSessionMap (String cookieValue)
	{
		String value[] = cookieValue.split("_");
		SessionDetails sd =  Server.sessionMap.get(value[0]);
		return sd;
	}
	
	public static void updateSessionMap(String cookieValue, SessionDetails sd)
	{
		String value[] = cookieValue.split("_");
		Server.sessionMap.put(value[0], sd);
	}
	
	public static SessionDetails updateVersion(SessionDetails sd, short version)
    {
		sd.setVersion(version);
    	return sd;
    }
	
	public static SessionDetails incrementVersion(SessionDetails sd)
    {
		sd.setVersion((short) (sd.getVersion()+1));
    	return sd;
    }
    
	
	public static SessionDetails updateMessage(SessionDetails sd, String message)
	{	
		if(!(message == null) && !message.equals(""))
		{
			sd.setMessage(message);
		}
		return sd;
	}
	
	@SuppressWarnings("deprecation")
	public static SessionDetails updateTimeStamp(SessionDetails sd)
	{
		Date newTimeStamp = new Date();
		newTimeStamp.setMinutes(newTimeStamp.getMinutes()+(Server.sessionTimeOutDuration/60));
		sd.setTimeStamp(newTimeStamp);
		return sd;
	}

	/* 
	 * This function is used delete an entry from the sessionMap when a user logs
	 * out. It uses sessionID as key, which is extracted from cookieValue
	 */
	public static void deleteFromSessionMap(String cookieValue)
	{
		String value[] = cookieValue.split("_");
		Server.sessionMap.remove(value[0]);
	}
	/* 
	 * This function is used to put the session details in an object and
	 * map it against a given sessionID
	 */
	
	@SuppressWarnings("deprecation")
	public static SessionDetails register(String sessionID,String locationMetadata)
	{	
		Date expiry = new Date();
		expiry.setMinutes(expiry.getMinutes()+(Server.sessionTimeOutDuration/60));
		SessionDetails sd = new SessionDetails(sessionID,locationMetadata, expiry,"Hello User !",(short) 1);
		Server.sessionMap.put(sessionID, sd);
		return sd;
	}
}
