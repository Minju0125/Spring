<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<%=request.getHeader("user-agent") %>
<br/>
<%
	String[] memRec = (String[])request.getAttribute("member");
%>
<jsp:include page="<%=memRec[1] %>"/>
</body>
</html>