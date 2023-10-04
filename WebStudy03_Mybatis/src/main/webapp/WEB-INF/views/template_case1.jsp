<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<tiles:insertAttribute name="preScript"/>
</head>
<body data-context-path="${pageContext.request.contextPath }">
<h4>Tiles layout</h4>
<main>
	<tiles:insertAttribute name="content"/>
</main>
<tiles:insertAttribute name="postScript"/>
</body>
</html>