<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body data-context-path="<%=request.getContextPath()%>">
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

<!-- modal -->

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">회원정보 수정하기</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">회원ID(여긴고정값리드온리)</label>
            <input type="text" class="form-control" id="memId">
          </div>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">이름</label>
            <input type="text" class="form-control" id="adrsName">
          </div>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">휴대폰번호</label>
            <input type="text" class="form-control" id="adrsHp">
          </div>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">주소</label>
            <input type="text" class="form-control" id="adrsAdd">
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="sendBtn">Send message</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/app/adrs/address.js"></script>
<script>
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>