<%@page import="kr.or.ddit.vo.CalculateVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CalculateVO calVO = (CalculateVO) request.getAttribute("calVO");
%>
<h4>처리한 연산자 종류 : <%=calVO.getOperator() %></h4>
<h4><%=calVO.getExpression() %></h4>
