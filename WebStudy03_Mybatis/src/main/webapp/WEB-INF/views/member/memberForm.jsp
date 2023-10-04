<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>

<h4>가입 양식</h4>
<form id="insertForm" method="post">
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td><input type="text" name="memId" class="form-control"
				 value="${member.memId}" /><span class="error">${errors.memId}</span></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="memPass" class="form-control"
				 /><span class="error">${errors.memPass}</span></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input type="text" name="memName" class="form-control"
				 value="${member.memName}" /><span class="error">${errors.memName}</span></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><input type="text" name="memRegno1" class="form-control"
				value="${member.memRegno1}" /><span class="error">${errors.memRegno1}</span></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><input type="text" name="memRegno2" class="form-control"
				value="${member.memRegno2}" /><span class="error">${errors.memRegno2}</span></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="date" name="memBir" class="form-control"
				value="${member.memBir}" /><span class="error">${errors.memBir}</span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="memZip" class="form-control"
				 value="${member.memZip}" /><span class="error">${errors.memZip}</span></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" name="memAdd1" class="form-control"
				 value="${member.memAdd1}" /><span class="error">${errors.memAdd1}</span></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" name="memAdd2" class="form-control"
				 value="${member.memAdd2}" /><span class="error">${errors.memAdd2}</span></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><input type="text" name="memHometel" class="form-control"
				value="${member.memHometel}" /><span class="error">${errors.memHometel}</span></td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td><input type="text" name="memComtel" class="form-control"
				value="${member.memComtel}" /><span class="error">${errors.memComtel}</span></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><input type="text" name="memHp" class="form-control"
				value="${member.memHp}" /><span class="error">${errors.memHp}</span></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="memMail" class="form-control"
				 value="${member.memMail}" /><span class="error">${errors.memMail}</span></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="memJob" class="form-control"
				value="${member.memJob}" /><span class="error">${errors.memJob}</span></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="memLike" class="form-control"
				value="${member.memLike}" /><span class="error">${errors.memLike}</span></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input type="text" name="memMemorial" class="form-control"
				value="${member.memMemorial}" /><span class="error">${errors.memMemorial}</span></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input type="date" name="memMemorialday"
				class="form-control" value="${member.memMemorialday}" /><span
				class="error">${errors.memMemorialday}</span></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="전송"
				class="btn btn-primary" /> <input type="reset" value="취소"
				class="btn btn-warning" /></td>
		</tr>
	</table>
</form>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/app/member/member.js"></script>
