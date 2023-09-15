<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collector"%>
<%@page import="java.util.Map"%>
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
<select id="memSel">
	<option value>멤버선택</option>
	<%
		Map<String, String[]> btsMap = (Map)application.getAttribute("btsMap");
		String options = btsMap.entrySet().stream()
									   	  .map(en->String.format("<option value='%s'>%s</option>", en.getKey(), en.getValue()[0]))
										  .collect(Collectors.joining("\n"));
		String savedMemCode = (String) request.getAttribute("savedMemCode");
		
	%>
	<%=options %>
	
	<option value="B001">RM</option>
</select>
<div id="resultArea"></div>
<script>
	$(memSel).on("change", function(event){
		let memCode = $(this).val() //this : change가 발생한 select tag => 제이쿼리 객체로 생성
		//location.href = location.href + "/" + memCode; //동기요청
		let settings = {
			url : `\${location.href}/\${memCode}`,
			dataType : "html", 
			success : function(resp) {
				$(resultArea).html(resp);
			},
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("errer : ", error);
			}
		};
		$.ajax(settings);
	}).val("<%=savedMemCode%>")
	  .trigger("change");
</script>
</body>
</html>