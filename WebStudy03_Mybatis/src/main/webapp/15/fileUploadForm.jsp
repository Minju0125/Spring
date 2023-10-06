<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>파일 업로드 양식</h4>
<form action="${pageContext.request.contextPath }/15/fileUpload.do" method="post" enctype="multipart/form-data">
	<input type="text" name="uploader"/>
	<input type="file" name="uploadFile"/>
	<input type="submit" value="업로드"/>
</form>
<c:if test="${not empty uploader }">
	uploader : ${uploader}
	<c:remove var="uploader" scope="session"/>
</c:if>
<c:if test="${not empty fileUrl }">
	<img src="<c:url value='${ fileUrl}'/>">
	<c:remove var="fileUrl" scope="session"/>
</c:if>
</body>
</html>