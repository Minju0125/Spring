<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div id="header">
</div>
<form id="selectionForm" method="post" action="<%=request.getContextPath()%>/B001">
<select id="memberSelection" name="memberSelection">
	<option value="rm">RM</option>
	<option value="jin">진</option>
	<option value="suga">슈가</option>
	<option value="jhop">제이홉</option>
	<option value="jimin">지민</option>
	<option value="bui">뷔</option>
	<option value="jungkuk">정국</option>
</select>
</form>
<div id="divSection"></div>
</body>
<script>
$(document).ready(function() {
	$('#memberSelection').change(function(){
		<%
		String userAgent = request.getHeader("User-Agent");
		%>
		var printText="";
		printText += "<%=userAgent%>";
		printText += "<br>jQuery와 bootstrap 사용 가능 페이지";
		$("#header").html(printText);
		
	    var value = $(this).val();
	    
		$("#selectionForm").submit();
	});
});
</script>
</html>