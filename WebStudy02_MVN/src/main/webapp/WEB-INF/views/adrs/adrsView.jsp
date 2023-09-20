<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<body data-context-path="<%=request.getContextPath()%>">
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>휴대폰</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody id="listBody">
			
		</tbody>
	</table>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/app/adrs/address.js"></script>
</body>
</html>