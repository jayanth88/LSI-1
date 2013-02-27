package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuxiliaryServer
 */
@WebServlet("/AuxiliaryServer")
public class ActionServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cookie[] cookies = request.getCookies();	
		if(cookies!=null &&  ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION")!= "")
		{
			String command = request.getParameter("cmd");
			String cookieValue = ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION");
			System.out.println(";;;;;;;;;;;;;;; "+command+" ---------" + cookieValue);
			if(command.equals("Replace"))
			{
				System.out.println("Replace");
				String message = request.getParameter("NewText");
				SessionDetails sd = ServerUtilities.replaceRoutine(cookieValue,message);
				request.setAttribute("name", sd.getMessage());
				request.setAttribute("timestamp", sd.getTimeStamp());
				System.out.println("Replace  :)");
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
