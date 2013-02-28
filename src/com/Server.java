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
	static int sessionTimeOutDuration = 1; // in minutes
	private static final long serialVersionUID = 1L;
	DaemonThread garbageCollector=new DaemonThread();
     
    public Server() {
        super();
        garbageCollector.start();
        }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null &&  ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION")!= "")
		{
			String values[] = ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION").split("_");
			SessionDetails sd = ServerUtilities.getRegisteredSession(values[0]);
			
			request.setAttribute("name", sd.getMessage());
			request.setAttribute("timestamp", sd.getTimeStamp());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			// Setup the server for the first time over here
			String locationMetadata=request.getLocalAddr()+":"+request.getServerPort();
			String sessionid = request.getLocalAddr().replace(".", "")+((Server.counter++)%99999); 
			SessionDetails sd = ServerUtilities.register(sessionid,locationMetadata);
			
			String cookieValue = sd.getSessionID()+"_"+sd.getVersion()+"_"+sd.getLocationMetadata();
			Cookie cookie = new Cookie ("CS5300PROJ1SESSION", cookieValue);
			cookie.setMaxAge(sessionTimeOutDuration*60);
			request.setAttribute("name", sd.getMessage());
			request.setAttribute("timestamp", sd.getTimeStamp());
			response.addCookie(cookie);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Nothing here
	}
}
