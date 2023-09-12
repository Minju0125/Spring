<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery.serializejson.js"></script>
</head>
<body>
<h4>클라이언트가 서버로 전송할 데이터를 입력받은 UI</h4>
<!-- method, enctype은 생략되면 기본값으로 적용 -->
<!-- enctype => get방식으로는 적용X 인코딩을 하려면 컨텐츠가 있어야함- 바디가 있어야함 - 바디가 있으려면 post여야함 -->
<form id="sampleForm" action="<%=request.getContextPath() %>/09/formDataProcess" method="post" enctype="application/x-www-form-urlencoded">
<pre> 
	<!-- 꼭 입력받아야할 때 -> required -->
	<input type="hidden" name="hdnParam" value="HIDDEN">
	<input type="text" name="txtParam" required/>
	<input type="number" name="numParam" />
	<input type="date" name="dateParam" />
	<!-- radio버튼뿐만이 아니라 텍스트를 클릭해도 클릭이벤트가 전파되도록 하기 위해 label로 묶음 -->
	<label><input type="radio" name="rdoParam" value="RDO1"/>RDO1</label>
	<input type="radio" name="rdoParam" id="raoParam2" value="RDO2"/>
	<label for="raoParam2">RDO2</label>
	<label><input type="checkbox" name="chkParam" value="Chk1"/></label>
	<label><input type="checkbox" name="chkParam" value="Chk2"/></label>
	<label><input type="checkbox" name="chkParam" value="Chk3"/></label>
	<select name="selParam1" required>
	<!-- 	value값이 없다면 화이트스페이스 - 입력을 안받은 상태 -->
		<option value>선택</option>
		<option value="selValue1">selText1</option>
		<option value="selValue2">selText2</option>
	</select>
	<select name="selParam2" multiple>
	<!-- attr - 무조건 문자열 / prop - 태그의 상태값을 가져옴 -->
		<option>mulText1</option> 
		<option>mulText2</option> 
		<option>mulText3</option>
	</select>
<!-- 	reset, submit - form태그 대상으로 실행 - 이벤트핸들러를 이용하고싶다면 form에다가 해아함 -->
	<input type="submit" value="전송" />
	<button type="reset">취소</button>
	<button type="button">그냥버튼</button>
</pre>
</form> 
<div id="resultArea"></div>
<script>
// 	taget 결정 -> event 종류결정 -> event handler 구현 -> target에 handler를 bind
	let $resultArea = $(resultArea);
	let submitHandler = function(event){
		event.preventDefault();
		let $form = $(this);
		let settings = {
			url : $form.attr("action"),
			method : $form.attr("method"),
//  			data : $form.serialize(),	//application/x-www-form-urlencoded => parameter로 전송한다는 의미
											//application/json;charset=UTF-8 : 데이터가 json으로 들어간다
 			data : JSON.stringify($form.serializeJSON()),
			contentType: "application/json;charset=UTF-8", 
			dataType : "json", //Accept request header : Content-Type response header
			success : function(resp) {
				//response가 xml일 때 사용하는 코드
// 				let msg = $(resp).find("message").text();
// 				$resultArea.html(msg);
				
				//response가 json일 때 사용하는 코드
// 				$resultArea.html(resp.message);
				$resultArea.html(JSON.stringify(resp)); //객체의 구조를 문자열로 직접 확인하고싶을 때 - 마셜링해서 확인
				
				//response가 html일 때 사용하는 코드
// 				$resultArea.html(resp);
			},
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("errer : ", error);
			}
		};
		$.ajax(settings);
	};
	$(sampleForm).on("submit", submitHandler);
</script>
</body>
</html>