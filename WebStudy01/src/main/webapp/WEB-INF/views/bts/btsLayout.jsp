<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 페이지 모듈화를 위한 레이아웃 
     전체 document 의 구조를 결정하는 jsp -->
<%=request.getHeader("user-agent") %>
<br/>
<%
	String[] memRec = (String[])request.getAttribute("member");
%>
<jsp:include page="<%=memRec[1] %>"/>
