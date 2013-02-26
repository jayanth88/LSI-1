package com;

import javax.servlet.http.Cookie;

public class ServerUtilities {
	
	public static String getCookieValue(Cookie[] cookies,
            String cookieName) {
			for(int i=0; i<cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
			return(cookie.getValue());
			}
			return "";
			}

}
