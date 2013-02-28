package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 
 * This class handles requests for the Refresh, Replace & Logout commands
 * from index.jsp. It collects the request, processes it using the functions
 * in ServerUtilities class and then forwards the request to the appropriate
 * location after setting requires attributes.
*/
@WebServlet("/ActionServer")
public class ActionServer extends HttpServlet
{
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cookie[] cookies = request.getCookies();
		String cookieValue = ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION");
		SessionDetails sd;
		if(!cookieValue.equals("")) // means cookie is present
		{
			System.out.println("CookieValue: "+cookieValue); // for debugging
			String command = request.getParameter("cmd");
			if(command.equals("Replace"))
			{
				String message = request.getParameter("NewText");
				sd = ServerUtilities.retrieveFromSessionMap(cookieValue);
				sd = ServerUtilities.updateMessage(sd, message);
				System.out.println(sd); // for debuggin
				sd = ServerUtilities.incrementVersion(sd);
				sd = ServerUtilities.updateTimeStamp(sd);
				ServerUtilities.updateSessionMap(cookieValue, sd);
				
				cookieValue = Server.getOldCookieName(sd); // true will also register it
				
				Cookie cookie = new Cookie ("CS5300PROJ1SESSION", cookieValue);
				cookie.setMaxAge(Server.sessionTimeOutDuration);
				response.addCookie(cookie);
				
				request.setAttribute("name", sd.getMessage());
				request.setAttribute("timestamp", sd.getTimeStamp());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else if(command.equals("Refresh"))
			{
				sd = ServerUtilities.retrieveFromSessionMap(cookieValue);
				System.out.println(sd);
				sd = ServerUtilities.incrementVersion(sd);
				sd = ServerUtilities.updateTimeStamp(sd);
				ServerUtilities.updateSessionMap(cookieValue, sd);
				
				cookieValue = Server.getOldCookieName(sd); 
				
				Cookie cookie = new Cookie ("CS5300PROJ1SESSION", cookieValue);
				cookie.setMaxAge(Server.sessionTimeOutDuration);
				response.addCookie(cookie);
				
				request.setAttribute("name", sd.getMessage());
				request.setAttribute("timestamp", sd.getTimeStamp());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else // logout
			{				
				ServerUtilities.deleteFromSessionMap(cookieValue);
				
				Cookie cookie = new Cookie ("CS5300PROJ1SESSION",null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);	
				response.sendRedirect("logout.jsp");
			}
		}
		else // No cookie found i.e. expired
		{
			String command = request.getParameter("cmd");
			if(command.equals("Replace"))
			{
				String message = request.getParameter("NewText");
				cookieValue = Server.getNewCookieName(request);
				
				sd = ServerUtilities.retrieveFromSessionMap(cookieValue);
				sd = ServerUtilities.updateMessage(sd, message);
				ServerUtilities.updateSessionMap(cookieValue, sd);
				
				Cookie cookie = new Cookie ("CS5300PROJ1SESSION", cookieValue);
				cookie.setMaxAge(Server.sessionTimeOutDuration);
				response.addCookie(cookie);
				
				request.setAttribute("name", sd.getMessage());
				request.setAttribute("timestamp", sd.getTimeStamp());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else if(command.equals("Refresh"))
			{
				cookieValue = Server.getNewCookieName(request); // true will also register it
				
				Cookie cookie = new Cookie ("CS5300PROJ1SESSION", cookieValue);
				cookie.setMaxAge(Server.sessionTimeOutDuration);
				response.addCookie(cookie);
				
				sd = ServerUtilities.retrieveFromSessionMap(cookieValue);
				request.setAttribute("name", sd.getMessage());
				request.setAttribute("timestamp", sd.getTimeStamp());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else // logout
			{				
				response.sendRedirect("logout.jsp");
			}
		}
	}

}
