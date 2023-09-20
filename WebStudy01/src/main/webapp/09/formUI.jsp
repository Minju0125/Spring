<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/formUI.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.serializejson.js"></script>
</head>
<body>
<h4>클라이언트가 서버로 전송할 데이터를 입력받은 UI</h4>
<div>
<h4>request content type</h4>
<input type="radio" name="contentType" data-content-type="application/json;charset=UTF-8" />JSON
<input type="radio" name="contentType" checked />PARAMETER
</div>
<div>
<h4>response content type</h4>
<input type="radio" name="dataType" data-data-type="json" data-success="jsonSuccess"/>JSON
<input type="radio" name="dataType" data-data-type="xml"  data-success="xmlSuccess"/>XML
<input type="radio" name="dataType" data-data-type="html"  data-success="htmlSuccess" checked />HTML
</div>
<form id="sampleForm" action="<%=request.getContextPath() %>/09/model2/formDataProcess" method="post" enctype="application/x-www-form-urlencoded">
<pre>
	<input type="hidden" name="hdnParam" value="HIDDEN"/>
	<input type="text" name="txtParam" required />
	<input type="number" name="numParam" />
	<input type="date" name="dateParam" />
	<label><input type="radio" name="rdoParam" value="RDO1"/>RDO1</label>
	<input type="radio" name="rdoParam" id="rdoParam2" value="RDO2"/>
	<label for="rdoParam2">RDO2</label>
	<label><input type="checkbox" name="chkParam" value="Chk1"/></label>
	<label><input type="checkbox" name="chkParam" value="Chk2"/></label>
	<label><input type="checkbox" name="chkParam" value="Chk3"/></label>
	<select name="selParam1" required >
		<option value>선택</option>
		<option value="selValue1">selText1</option>
		<option value="selValue2">selText2</option>
	</select>
	<select name="selParam2" multiple>
		<option>mulText1</option>
		<option>mulText2</option>
		<option>mulText3</option>
	</select>
	<input type="submit" value="전송" />
	<button type="reset">취소</button>
	<button type="button">그냥버튼</button>
</pre>	
</form>
<div id="resultArea"></div>
<script type="text/javascript">
// 	target 결정 -> event 종류 -> event handler 구현 -> target 에 handler를 bind
	let $resultArea = $(resultArea);
	let functionObj = {
		jsonSuccess:function(resp){
//				response json
			$resultArea.html(JSON.stringify(resp));
		},
		xmlSuccess:function(resp){
// 				response xml
				let msg = $(resp).find("message").text();
				$resultArea.html(msg);
		}, 
		htmlSuccess:function(resp){
//				response html
				$resultArea.html(resp);
		}
	}
	let submitHandler = function(event){
		event.preventDefault();
		let $form = $(this);
		let settings = {
			url : $form.attr("action"),
			method : $form.attr("method"),
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}
		};
		
		let contentType = $("[name=contentType]:checked").data("contentType");
		let data = null;
		if(contentType){
			// json palyoad - Content-Type : application/json;charset=UTF-8
			settings.contentType = contentType;
			// marshalling
			data = JSON.stringify( $form.serializeJSON() );
		}else{
			// parameter - Content-Type : application/x-www-form-urlencoded (default)
			data = $form.serialize();
		}
		settings.data = data;
		
		let $dataType = $("[name=dataType]:checked");
		settings.dataType = $dataType.data("dataType");		
		let fnName = $dataType.data("success");
		settings.success = functionObj[fnName];		
		
		$.ajax(settings);
	};
	$(sampleForm).on("submit", submitHandler);
</script>
</body>
</html>









