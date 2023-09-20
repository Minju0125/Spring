<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>동일 경로에서 확인한 쿠키</h4>
<pre>
<%
	Cookie[] cookies = request.getCookies();
	Cookie findedCookie = null;
	if(cookies!=null){
		for(Cookie tmp : cookies){
			String name = tmp.getName();
			String value = URLDecoder.decode( tmp.getValue(), "UTF-8" );
			out.println(String.format("%s : %s", name, value));
		}
	}
%>
</pre>
</body>
</html>