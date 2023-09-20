<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%=request.getHeader("user-agent") %>
<br />
<%
	String[] memRec = (String[]) request.getAttribute("member");
%>
<jsp:include page="<%=memRec[1] %>" />
