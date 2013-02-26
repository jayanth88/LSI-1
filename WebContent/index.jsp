<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';'); // document.cookie returns all cookie for the site
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        if (c.indexOf(nameEQ) == 0) 
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        	{
        	
 	         var s = c.substring(nameEQ.length,c.length)
 	         //alert(s);
 	         s=s.replace(/[^a-zA-Z0-9_.-]/g, '');
 	         //alert(s);
        	 var serverInfo = s.split('_');
        	 if(serverInfo.length == 3)
        		 {
        			 document.getElementById("sessionInfo").innerHTML = serverInfo[1];
        			 document.getElementById("exprationInfo").innerHTML = serverInfo[2];
        			 document.getElementById("cookieinfo").innerHTML = serverInfo;
        			 
        		 }
        	}
    }
    return null;
}


</script>


</head>
		<body onload="readCookie('CS5300PROJ1SESSION')">
		<big><big><b id = "userGreeting">
		<!--  Hello, User!-->
			<%=request.getAttribute("name")%>		
		</b>
		<br>&nbsp;<br></big></big>
		<form method=GET action="index">
		<input type=submit name=cmd value=Replace>&nbsp;&nbsp;
		<input type=text name=NewText size=40 maxlength=512>&nbsp;&nbsp;
		</form>
		<!--<form method=POST action="index">
		<input type=submit name=cmd value=Refresh>
		</form>-->
		<form method=GET action="index">
		<input type=submit name=cmd value=LogOut>
		</form>
		<p>
		
		<b>Session on</b><div id  = "sessionInfo"><u>Dummydata 123.45.67.89:80</u></div><br/>
		
		<p>
		
		<b>Expires</b><div id  = "exprationInfo"><u>Dummy DataFebruary 29, 2012 2:09:29 PM EST</u></div><br/>
		
		<br/>
		<div id = "cookieinfo"></div>
		</body>
</html>