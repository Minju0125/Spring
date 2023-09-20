<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="1"> -->
<title>08/autoRequest.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/app/08/serverTime.js"></script>
</head>
<body data-context-path="<%=request.getContextPath() %>">
<h4>주기적인 갱신 자원을 대상으로 한 자동 요청</h4>
<input type="radio" name="dataType" value="html" checked />HTML
<input type="radio" name="dataType" value="json" />JSON
<pre>
	server side : Refresh 헤더 사용 (동기 요청으로 DOM 에 lock 을 거는 경우 동작.)
	<span id="serverTimeArea"></span>
	client side : HTML(meta), JS(scheuling 함수)
</pre>

</body>
</html>










