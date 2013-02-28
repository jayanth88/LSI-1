package com;

import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/* 
 * This servlet handles the initial requests when the project url is hit for the
 * first time. The GET request it receives from the user is handled here. A new
 * cookie with a unique name is instantiated and added to the response. This is used
 * for identifying user session if it sends further requests.
 */

@WebServlet("/Server")
public class Server extends HttpServlet
{
	static Hashtable<String, SessionDetails> sessionMap = new Hashtable<String, SessionDetails>(); 
	static  int counter = 0; 
	static int sessionTimeOutDuration = 60; // in seconds
	private static final long serialVersionUID = 1L;
	DaemonThread garbageCollector=new DaemonThread();
     
    public Server() {
        super();
        garbageCollector.start();
        }
 

	public static String getNewCookieName(HttpServletRequest request)
	{
		String locationMetadata=request.getLocalAddr()+":"+request.getServerPort();
		String sessionID = request.getLocalAddr().replace(".", "")+((counter++)%99999);
		String cookieValue = sessionID+"_"+1+"_"+locationMetadata;
		ServerUtilities.register(sessionID, locationMetadata);
		return cookieValue;
	}
	
	public static String getOldCookieName(SessionDetails sd)
	{
		String cookieValue = sd.getSessionID()+"_"+sd.getVersion()+"_"+sd.getLocationMetadata();
		return cookieValue;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cookie[] cookies = request.getCookies();
		String cookieValue = ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION");
		SessionDetails sd;
		
		if(!cookieValue.equals("")) // cookie present. User must have manually refreshed page
		{
			sd = ServerUtilities.retrieveFromSessionMap(cookieValue);
			request.setAttribute("name", sd.getMessage());
			request.setAttribute("timestamp", sd.getTimeStamp());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			// Setup the server for the first time over here
			cookieValue = getNewCookieName(request); // will also register it
			
			
			Cookie cookie = new Cookie ("CS5300PROJ1SESSION", cookieValue);
			cookie.setMaxAge(sessionTimeOutDuration);
			response.addCookie(cookie);
			
			sd = ServerUtilities.retrieveFromSessionMap(cookieValue);

			request.setAttribute("name", sd.getMessage());
			request.setAttribute("timestamp", sd.getTimeStamp());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Nothing here
	}
}
