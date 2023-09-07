<%@ page language="java" pageEncoding="UTF-8"%>
<%--
	response.setContentType("text/plain; charset=UTF-8");
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
	1. Response Line : Status Code (응답상태 코드) - response.sendError(sc[, message]), setStatus(sc)
		Status Code : 서버에서 요청 처리 결과의 성공 여부를 표현하는 상태 코드
				Http : connectLess + stateLess
			100~ : ING~ WebSocket (connectFull)
			200~ : OK (성공)
			300~ : 요청 처리가 최종적으로 완료되려면, 클라이언트로부터 추가 액션이 필요함. response body 가 없음.
				302, 307(Moved) + Location(자원의 새로운 주소) 헤더 사용
				304 (Not Modified): 일반적으로 브라우저는 정적 자원에 대해 캐싱을 해서 사용함.
					한번 캐싱된 자원이 변경된 적 없으므로, 캐시 자원을 그대로 사용하라는 표현	
					상태코드로 캐시데이터의 갱신 유무를 표현함 (?)
					안바꼈다면 캐시데이터 그대로 사용함	
					바꼈다면 200 으로 내보냄 옹		?
					응답상태코드는 이 응답을 받은 후에 브라우저가 무엇을 할 것이인지
			400~ : 처리실패의 원인이 클라이언트 측에 있을 때. (개발자가 직접 만듦)
				404 (Not Found)
					1) request line (URI 포함) -> URI 잘못된 경우
					2) controller에서 view 로 이동하기 위한 path 가 잘못된 경우
				405 (Method Not Allowd)
				400 (Bad Request, 요청 검증에 주로 활용됨.)
				-- 어플리케이션의 보호를 위한 접근 제어에서 활용됨.
				401 (UnAuthorized) "접근하려는 자원은 보호자원이기 때문에 인증을 거치고 들어와야함"
				403 (Forbidden)
				-- Mime 관련 status code
				406 (Not Acceptable) 
					클라이언트가 요청한 Mime content 를 전송할 수 없음. => 응답데이터 생성 불가
						request header(Accept), response header(Content-type)
				415 (Unsupported Media Type) : 클라이언트가 전송한 content를 판독할 수 없음
					request header(Content-type) ** request entity = request body
					
			500~ : 처리실패의 원인이 서버 측에 있을 때. 500 (Internal Server Error)
	2. Response Header : response.set[Int|Date]Header(name,value), addHeader...
		1) Content-* : Content-Type, Content-Length - response body 컨텐츠를 수식해줌
		2) Cache 제어 : Cache-Control(Http ver 1.1), Expires, Pragma(Http ver 1.0)
		<%
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Cache-Control", "no-store"); //경우에 따라 no-cache 해석 못하고 no-store만 인식하는 브라우저가 있음 ! "add"
			response.setHeader("Pragma", "no-cache");
			response.addHeader("Pragma", "no-store");
			response.setDateHeader("Expires", 0); //1970년 1월 1일 0시 0분 0초로 셋팅 -> 캐시를 남기지 말라는 의미
			//특정 클라이언트를 타켓팅하면 안되기 때문에 Cach-controll, pragma 
			
		%>
			- 응답 데이터의 모든 기준은 클라이언트
				-> 클라이언트는 불특정 다수이며, 1.0을 쓸지, 1.1을 쓸지 모르기 때문에 둘다 사용 (=웹 표준화) - 접근성 보장
		3) Auto Request : Refresh
			<a href="/08/autoRequest.jsp">auto request</a>
		4) Redirection : Location
			
	3. Response Body(Content Body, Message Body)
		** channel 은 양방향 / stream 은 단방향
			=> 데이터 기록할 때 일반적으로 stream으로 . 단방향이기 때문에 입력스트림&출력스트림
		response.getWriter() : char 기반의 문자 컨텐츠를 기록할 출력스트림
		response.getOutputStream() : byte 기반의 스트림(binary) 컨텐츠를 기록할 출력스트림
			** Stream 일련의 바이트 집합 : 바이트 계열 
</pre>
</body>
</html>