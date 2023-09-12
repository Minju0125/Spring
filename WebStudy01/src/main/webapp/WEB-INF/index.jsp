<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>웰컴페이지 : <%=request.getAttribute("title") %></h4>
<%
String authId = (String)session.getAttribute("authId");
if(authId!=null){
%>
<h4><%=authId %>로그인 성공</h4>
<%
}else{
	%>
	<a href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인</a>
	<%
}
%>
<script>
	console.log($);
</script>
</body>
</html>