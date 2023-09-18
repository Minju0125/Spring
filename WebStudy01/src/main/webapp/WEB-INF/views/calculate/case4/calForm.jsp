<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.ddit.calculate.controller.NumericOperatorType"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<script src="${pageContext.request.contextPath}/resources/js/app/calculate/calForm_case4.js"></script>
</head>
<body>
<form id="calForm" method="post">
	<input type="number" name="leftOp" required value="${param.leftOp}"/>
	<select name="operator" required data-init-value="${param.operator}">
		<option value>연산자</option>
		<%=
			Arrays.stream(NumericOperatorType.values())
				  .map(ot->String.format("<option value='%s'>%c<option>",ot.name(),ot.getSign()))
				  .collect(Collectors.joining("\n"))
		%>
	</select> 
	<input type="number" name="rightOp" required value="${param.rightOp}"/> 
	<input type="submit" value="="/>
</form>
<div id="resultArea"></div>
</body>
</html>