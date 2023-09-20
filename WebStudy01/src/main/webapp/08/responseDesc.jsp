<%@ page language="java" pageEncoding="UTF-8"%>
<%--
	response.setContentType("text/plain;charset=UTF-8");
	response.setContentLengthLong(100);
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/responseDesc.jsp</title>
</head>
<body>
<h4>HttpServletResponse</h4>
<pre>
	1. Response Line : Status Code(응답 상태 코드) - response.sendError(sc[, message]), setStatus(sc)
		Status Code : 서버에서 요청 처리 결과의 성공 여부를 표현하는 상태 코드
			Http : connectLess + stateLess
		100~ : ING... WebSocket(connectFull)
		200~ : OK
		300~ : 요청 처리가 최종적으로 완료되려면, 클라이언트로부터 추가 액션이 필요함. response body 가 없음.
			302, 307(Moved) + Location(자원의 새로운 주소) 헤더 사용
			304 (Not Modified) : 일반적으로 브라우저는 정적 자원에 대해 캐싱을 해서 사용함.
				한번 캐싱된 자원이 변경된 적 없으므로, 캐시 자원을 그대로 사용하라는 표현.
		
		400~ : 처리 실패의 원인이 클라이언트측에 있을때.
			404 (Not Found)
			405 (Method Not Allowed)
			400 (Bad Request , 요청 검증에 주로 활용됨.)
			
			--  어플리케이션 보호를 위한 접근 제어에서 활용됨.
			401 (UnAuthorized)
			403 (Forbidden)
			
			406 (Not Acceptable) : 클라이언트가 요청한 Mime content 를 전송할 수 없음.
					request header(Accept), reponse header(Content-Type)
			415 (UnSupported Media Type) : 클라이언트가 전송한 content 를 판독할 수 없음.
					request header(Content-Type)
			
		500~ : 처리 실패의 원인이 서버측에 있을때. 500(Internal Server Error)
		
	2. Response Header : response.set[Int|Date]Header(name, value), addHeader...
		1) Content-* : Content-Type, Content-Length - response body 컨텐츠를 수식해줌.
		2) Cache 제어 : Cache-Control(Http ver 1.1), Expires, Pragma(Http ver 1.0)
		<%
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.addHeader("Pragma", "no-store");
			response.setDateHeader("Expires", 0);
		%>
		3) Auto Request : Refresh
			<a href="<%=request.getContextPath() %>/08/autoRequest.jsp">auto request</a>
		4) Redirection : Location
		
	3. Response Body(Content Body, Message Body)
		response.getWriter() : char 기반의 문자 컨텐츠를 기록할 출력 스트림
		response.getOutputStream() : byte 기반의 스트림(binary) 컨텐츠를 기록할 출력 스트림
</pre>
</body>
</html>














