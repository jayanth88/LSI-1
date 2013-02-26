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




/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	Hashtable sessionMap = new Hashtable(); 
	Random sessionID = new Random();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub    
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//TODO receienves message and updaes hashmap
		/*
		String newText = "Hello, User!";
		
			
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null &&  ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION")!= "")
		{
				String cookieVal = ServerUtilities.getCookieValue(cookies, "CS5300PROJ1SESSION");
				if(cookieVal != null)
				{
					String cookieArgs[] = cookieVal.split("_");
					if(cookieArgs.length>0)
					{
						String userSessionID = cookieArgs[0];
						String version = cookieArgs[1];
						String locationMetadata = cookieArgs[2];
						
						if(cookieArgs.length ==4)
						{
							String message = cookieArgs[3];	
						}
						Cookie cookie = new Cookie ("CS5300PROJ1SESSION", sessionID.nextInt(6533550)+"_"+version+"_"+locationMetadata);
						cookie.setMaxAge(5);
						response.addCookie(cookie);
						sessionMap.put(sessionID, sessionID+"_"+version+"_"+locationMetadata);
					}
						
				}
				
				if(request.getParameter("NewText") != null && request.getParameter("NewText") != "")
				{
					System.out.print(">>"+request.getParameter("NewText")+"<<");
					newText = request.getParameter("NewText");
				}
				
				
				//send new updated cookie
				request.setAttribute("name", newText);				
//				response.sendRedirect("index.jsp");
				request.setAttribute("name", newText);
				request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
				
			// create session ID , host information, version	
		
			int version = 1;
			//String locationMetadata = request.getRemoteHost()+"  port:-"+request.getServerPort();
			String locationMetadata = request.getServerName()+":"+request.getServerPort();
			String sessionid = request.getRemoteHost().replace(".", "")+request.getServerPort(); 
			
			// Send data via cookie
			
			Cookie cookie = new Cookie ("CS5300PROJ1SESSION", sessionID.nextInt(6533550)+"_"+version+"_"+locationMetadata);
			cookie.setMaxAge(5);
			response.addCookie(cookie);
			sessionMap.put(sessionID, sessionID+"_"+version+"_"+locationMetadata);
			System.out.println(sessionMap+"****************");
			
			if(request.getParameter("NewText") != null && request.getParameter("NewText") != "")
			{
				System.out.print(">>"+request.getParameter("NewText")+"<<");
				newText = request.getParameter("NewText");
				request.setAttribute("name", newText);
			}
			
//			response.sendRedirect("index.html");
			request.getRequestDispatcher("index.jsp").forward(request, response);

		
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Cookie cookie = new Cookie ("CS5300PROJ1SESSION", "SessionID_v$$ersion_lo^^cationmetadata");
		cookie.setMaxAge(5);
		response.addCookie(cookie);
		response.sendRedirect("index.jsp");
	}

}
