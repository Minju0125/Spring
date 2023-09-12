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
<form id="selectionForm" method="post">
<select id="memberSelection">
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
$( document ).ready( function() {
	$('#memberSelection').change(function() {
	    var value = $(this).val();
		$("#selectionForm").attr("action", <%=request.getContextPath()%> + "/bts/"+value +".jsp");
	    $("#selectionForm")
	});
} );
</script>
</html>