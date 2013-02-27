package com;

import java.util.Date;

import javax.servlet.http.Cookie;

import com.sun.jmx.snmp.Timestamp;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class  ServerUtilities {
	
	public static String getCookieValue(Cookie[] cookies, String cookieName) 
	{
			for(int i=0; i<cookies.length; i++) 
			{
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName()))
				return(cookie.getValue());
			}
			return "";
	}
	
	public static void replace()
	{
		
	}

	public static SessionDetails replaceRoutine(String cookieValue, String message)
	{
		String value[] = cookieValue.split("_");
		SessionDetails sd =  Server.sessionMap.get(value[0]);
		
		if(!(message == null) && !message.equals(""))
		{
			sd.setMessage(message);
			Server.sessionMap.put(value[0], sd);
		}
		return sd;
	}

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

	public static void logoutRoutine(String cookieValue)
	{

		String value[] = cookieValue.split("_");
		SessionDetails sd =  Server.sessionMap.remove(value[0]);
		
	}
	
	
	@SuppressWarnings("deprecation")
	public static SessionDetails register(String sessionID,String locationMetaData)
	{	
		Date expiry = new Date();
		expiry.setMinutes(expiry.getMinutes()+Server.sessionTimeOutDuration);
		SessionDetails sd = new SessionDetails(sessionID,locationMetaData, expiry,"Hello User !",(short) 1);
		Server.sessionMap.put(sessionID, sd);
		return sd;
	}

	public static SessionDetails getRegisteredSession(String id) {
		
		SessionDetails sd = Server.sessionMap.get(id);
		
		return sd;
	}
	
}
