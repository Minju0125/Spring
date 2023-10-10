<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="adrsForm" method="post" action="<%=request.getContextPath()%>/adrs/address">
	<input type="text" name="adrsName" placeholder="이름" required/>
	<input type="text" name="adrsHp" placeholder="휴대폰" required/>
	<input type="text" name="adrsAdd" placeholder="주소" required/>
	<input type="submit" value="등록">
</form>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/app/adrs/address.js"></script>