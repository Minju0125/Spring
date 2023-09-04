<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.red{
background-color: red;
}
</style>
<body>
<h4>HttpServletRequest : reqeust packaging</h4>
<form method="post"></form>
<pre>
	1. Request Line : URL(URI), request Method
		Request Method : 요청의 목적이자 의도이며, 포장 규칙을 정의하는 단어
		- GET(default method)
		- POST(form 기반의 요청) : requset body가 필요함
		- PUT/PATCH(자원의 갱신) -> 철저한 보안이 필요한 곳은 patch 방식으로 수정하기도 함
		- DELETE(자원의 삭제)
		- OPTION(preFlight 요청)
		- HEAD(response content가 없음)
		- TRACE(server tracking/debugging)
		
		<%
			 StringBuffer requestURL = request.getRequestURL();
			 String requestURI = request.getRequestURI();
			 String requestMethod = request.getMethod();
		%>
		URL : <%=requestURL %>
		URI : <%=requestURI %>
		method : <%=requestMethod %>
		
	2. Request Header : meta data (client + content), name(String)/value(String)
		<%
			String userAgent = request.getHeader("user-agent");
		%>
		<%=userAgent %>
	3. Request Body(Content Body, Message Body)
		1) parameter (String) : 
			String getParameter(name), String [] getParamerValues(name), getParameterMap()
			전달되는 방식이 둘로. -> 편지지 통해 or 편지봉투 통해
			- query string 형태 전송 (보안 취약)
			- content body 영역 전송
		2) multipart (stream)
		3) payload(JSON/XML .., unmarshalling..)
		
</pre>
<div>
	<!-- 브라우저를 그대로 사용해서 파라미터를 넘기겠다는 의미 -->
	<a href="?param1=value1&param2=한글값">Query String 형태 전송</a>
	<form method="post">
		<input type="text" name="param3" value="value3"/>
		<input type="text" name="param3" value="value3-1"/>
		<input type="text" name="param4" value="value4"/>
		<input type="date" name="date1"/>
		<input type="datetime-local" name="date2"/>
		<input type="submit" value="전송"/>
	</form>
</div>

<table>
	<thead>
		<tr>
			<th>파라미터이름</th>
			<th>파라미터값</th>
		</tr>
	</thead>
	
	<tbody>
		<%
			//파라미터를 확보하기 전에 미리 설정(POST 요청의 request body 에 적용됨).
			request.setCharacterEncoding("utf-8");
			String ptrn = "<tr class='%3$s'><td>%1$s</td><td>%2$s</td></tr>";
			
			Map<String, String[]> paramMap = request.getParameterMap();	
			if(paramMap.isEmpty()){
				out.println("<tr><td colspan='2'>파라미터 없음.</td></tr>");
			}else{      //엔트리
				for(Entry<String, String[]> entry: paramMap.entrySet()){ //향상된 for문에 맵이 올 수 없음 set은 가능
					String paramName = entry.getKey();
				
					String[] paramValues = entry.getValue();
					out.println(
						String.format(ptrn, paramName, Arrays.toString(paramValues), "")
					);
				}
			}
		
		/*
			String ptrn2 = "<tr><td>%s</td><td>%s</td></tr>";
			Enumeration <String> paramNames= request.getParameterNames();
			while(paramNames.hasMoreElements()){
				String nameParam = paramNames.nextElement();
				Map<String, String[]> mapParam = request.getParameterMap();
				String[] mapArray = mapParam.get(nameParam);
				for(int i=0; i<mapArray.length; i++){
					String valueParam = mapArray[i];
					out.println(
							String.format(ptrn2, nameParam, valueParam)
							);
				}
			}
			*/
		%>
	</tbody>
	
</table>

<table>
	<thead>
		<tr>
			<th>헤더이름</th>
			<th>헤더값</th>
		</tr>
	</thead>
	
	<tbody>
		<%
			
			//일련의 요소의 집합
			Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()){
				String name = headerNames.nextElement();
				String value = request.getHeader(name);
				String clzValue="user-agent".equals(name) ? "red" : "etc";
				out.println(
						String.format(ptrn, name, value, clzValue)
				);
			}
		%>
	</tbody>
</table>
</body>
</html>