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
		if(cookies!=null &&  ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION")!= "")
		{
			String command = request.getParameter("cmd");
			String cookieValue = ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION");
			
			if(command.equals("Replace"))
			{
				String message = request.getParameter("NewText");
				SessionDetails sd = ServerUtilities.replaceRoutine(cookieValue,message);
				request.setAttribute("name", sd.getMessage());
				request.setAttribute("timestamp", sd.getTimeStamp());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else if(command.equals("Refresh"))
			{
				SessionDetails sd = ServerUtilities.refreshRoutine(cookieValue);
				request.setAttribute("name", sd.getMessage());
				request.setAttribute("timestamp", sd.getTimeStamp());
				Cookie cookie = new Cookie ("CS5300PROJ1SESSION",sd.getSessionID()+"_"+sd.getVersion()
											+"_"+sd.getLocationMetadata());
				cookie.setMaxAge(Server.sessionTimeOutDuration*60);
				response.addCookie(cookie);			
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else
			{				
				ServerUtilities.logoutRoutine(cookieValue);
				Cookie cookie = new Cookie ("CS5300PROJ1SESSION",null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);	
				response.sendRedirect("logout.jsp");
			}
		}
		else
		{
			response.sendRedirect("logout.jsp");
		}
	}

}
