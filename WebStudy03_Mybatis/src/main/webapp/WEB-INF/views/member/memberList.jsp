<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>생일</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	<%
		List<MemberVO> memberList = (List) request.getAttribute("memberList");
		if(memberList.isEmpty()){
			%>
			<tr>
				<td colspan="5">검색 조건에 맞는 회원 없음.</td>
			</tr>
			<%
		}else{
			for(MemberVO member : memberList){
				%>
				<tr data-mem-id="<%=member.getMemId()%>">
					<td><%=member.getMemName() %>[<%=member.getProdCount() %>]</td>
					<td><%=member.getMemHp() %></td>
					<td><%=member.getMemMail() %></td>
					<td><%=member.getMemBir() %></td>
					<td><%=member.getMemAdd1() %></td>
					<td><%=member.getMemMileage() %></td>
				</tr>
				<%
			}			
		}
	%>
	</tbody>
</table>
</body>
</html>