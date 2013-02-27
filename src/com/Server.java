package com;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random; 
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Server")
public class Server extends HttpServlet {
	
	static Hashtable<String, SessionDetails> sessionMap = new Hashtable<String, SessionDetails>(); 
	static int counter = 0; 
	static int sessionTimeOutDuration = 1; // in minutes
	private static final long serialVersionUID = 1L;
	DaemonThread dt=new DaemonThread();
       
 
    public Server() {
        super();
        dt.start();
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
			
			String locationMetadata = request.getRemoteHost()+":"+request.getServerPort();
			String sessionid = request.getRemoteHost().replace(".", "")+Server.counter++; 
			System.out.println(locationMetadata+"*********"+sessionid);
			SessionDetails sd = ServerUtilities.register(sessionid,locationMetadata);
			
			String cookieValue = sd.getSessionID()+"_"+sd.getVersion()+"_"+sd.getLocationMetadata();
			System.out.println("*********"+cookieValue+"<<<");
			Cookie cookie = new Cookie ("CS5300PROJ1SESSION", cookieValue);
			cookie.setMaxAge(sessionTimeOutDuration*60);
			
			
			request.setAttribute("name", sd.getMessage());
			request.setAttribute("timestamp", sd.getTimeStamp());
			response.addCookie(cookie);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
