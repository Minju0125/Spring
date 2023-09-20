<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	String message = (String)request.getAttribute("message");
	Enumeration<String> attrNames = request.getAttributeNames();
	while(attrNames.hasMoreElements()){
		String name = attrNames.nextElement();
		Object value = request.getAttribute(name);
		%>
			<div><%=name %> : <%=value %></div>
		<%
	}
%>
