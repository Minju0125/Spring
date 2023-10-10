<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	1. 드라이버를 빌드 패스에 추가
	2. 드라이버(클래스) 로딩 (-메모리에 로딩, 버추얼머신은 이 드라이버의 존재를 모르고 있기 때문)
	3. connection 생성 (connection 수립할 때 드라이버 사용, 이 connection 하나가 session의 의미)
	4. 쿼리 객체 생성 (sql로 명령 내렸을 때 대신 컴파일 해줄 쿼리 객체 생성)
	5. 쿼리 실행
	6. 결과 집합 핸들링(select ..)
	7. (***) close (session을 끊겠다는 의미) - try with resource 구조 활용
		모든 서버은 가용 자원이 제한되어있음
		close 하지 않으면 계속 close 만 생성
		DB는 connectfull 구조 이기 때문에 한번 수립한 connection은 계속 유지됨.
		더이상 connection을 생성할 수 없어지면 서버가 다운됨.
</pre>
<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
</table>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/app/13/jdbcDesc.js"></script>
</body>
</html>