<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	1. 드라이버를 빌드 패스에 추가
	2. 드라이버(클래스) 로딩 (-메모리에 로딩, 버추얼머신은 이 드라이버의 존재를 모르고 있기 때문)
	3. connection 생성 (connection 수립할 때 드라이버 사용, 이 connection 하나가 session의 의미)
	4. 쿼리 객체 생성 (sql로 명령 내렸을 때 대신 컴파일 해줄 쿼리 객체 생성)
		- statement : 쿼리 객체 생성시 쿼리가 고정되지 않기 때문에, runtime 에 동적 쿼리 실행이 가능.
		- preparedStatement (선컴파일된 쿼리 객체) : 쿼리를 미리 컴파일 하고 쿼리 객체를 생성함.
												runtime에 쿼리에 사용되는 literal(값)을 변경하여 쿼리를 재사용함.
		- CallableStatement : 절차적 코드집합인 function/procedure를 호출할때 사용함.
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
	<tbody>
		<c:set var="propList" value="${requestScope.list}"/>
		<c:if test="${empty propList}">
			<tr>
				<td colspan="3">조회 결과 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty propList}">
			<c:forEach items="${propList}" var="record">
				<tr>
					<td>${record.propertyName}</td>
					<td>${record.propertyValue}</td>
					<td>${record.description}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
</body>
</html>