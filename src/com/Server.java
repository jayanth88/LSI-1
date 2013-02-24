package com;

import java.io.IOException;
import java.util.Date;

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
		
		
		Cookie cookie = new Cookie ("CS5300PROJ1SESSION", "SessionID_v$$ersion_lo^^cationmetadata");
		cookie.setMaxAge(5);
		response.addCookie(cookie);
		response.sendRedirect("index.html");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Cookie cookie = new Cookie ("CS5300PROJ1SESSION", "SessionID_v$$ersion_lo^^cationmetadata");
		cookie.setMaxAge(5);
		response.addCookie(cookie);
		response.sendRedirect("index.html");
	}

}
