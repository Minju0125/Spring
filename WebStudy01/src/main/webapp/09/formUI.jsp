<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.serializejson.js"></script>
</head>
<body>
<h4>클라이언트가 서버로 전송할 데이터를 입력받은 UI</h4>
<form id="sampleForm" action="<%=request.getContextPath()%>/09/formDataProcess" method="post" enctype="application/x-www-form-urlenScoded">
<pre>
	<input type="hidden" name="hdnParam" value="HIDDEN">
	<input type="text" name="txtParam" required/>
	<input type="number" name="numParam" />
	<input type="date" name="dateParam" />
	<label><input type="radio" name="rdoParam" value="RDO1"/>RDO1</label>
	<input type="radio" name="rdoParam" id="rdoParam2" value="RDO2" />
	<label for="rdoParam2">RDO02</label>
	<label><input type="checkbox" name="chkParam" value="Chk1"/></label>
	<label><input type="checkbox" name="chkParam" value="Chk2"/></label>
	<label><input type="checkbox" name="chkParam" value="Chk3"/></label>
	<select name="selParam1" required>
		<option value>선택</option> <!-- prompt text -->
		<option value="selValue1">selText1</option>
		<option value="selValue2">selText2</option>
	</select>
	<select name="selParam2" multiple>
		<option>mulText1</option>
		<option>mulText2</option>
		<option>mulText3</option>
	</select>
	<input type="submit" value="전송"/>
	<button type="reset">취소</button>
	<button type="button">그냥버튼</button>
</pre>
</form>
<div id="resultArea"></div>
<script type="text/javascript">
	//target 결정 -> event 종류 -> event handler 구현 -> target에 handler를 bind
	let $resultArea = $(resultArea);
	let submitHandler = function(event){
		event.preventDefault();
		let $form = $(this);
		let settings= {
				//동기, 비동기 요청의 조건을 똑같이 맞춘 과정
				url : $form.attr("action"), //$form 이 제이쿼리 객체이기 때문에 $form.action 사용할수 없음
				method : $form.attr("method"),
// 				data : $form.serialize(),  //serialize 가 쿼리스트링 만들어줌 // application/x-www-form-urlencoded
				data : JSON.stringify($form.serializeJSON()),
				contentType : "application/json; charset=UTF-8",
				dataType :"json", // Accept request header : Content-Type response header
				success : function(resp){ //resp 는 서버사이드에서 만들어진 html 소스
					//----------------1) resonse xml----------------
					//let msg = $(resp).find("message").text();
					//$resultArea.html(msg); //$resultArea는 제이쿼리, html는 html 이기 때문에 함수사용
					//----------------2) response json----------------
					$resultArea.html(JSON.stringify(resp));
					//----------------3) response html----------------
// 					$resultArea.html(resp);
				}, error : function(jqXhr, status, error){
					console.log("jqXhr : ", jqXhr)
					console.log("status : ", status)
					console.log("error : ", error)
				}
			}
			$.ajax(settings); 
	};
	$(sampleForm).on("submit", submitHandler);
</script>
</body>
</html>