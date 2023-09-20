<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/applicationDesc.jsp</title>
</head>
<body>
<h4>ServletContext</h4>
<pre>
	: 서버와 현재 컨텍스트에 대한 정보를 가진 싱글턴 객체.
	
	1. 서버의 정보 획득
		<%=application.getServerInfo() %>
		<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
		<%=application.getMimeType("sample.jpg") %>
		<%=application.getMimeType("sample.hwp") %>
	2. context parameter 획득 : web.xml 을 통해 등록된 파라미터 획득
		<%
			Enumeration<String> names = application.getInitParameterNames();
			while(names.hasMoreElements()){
				String paramName = names.nextElement();
				String paramValue = application.getInitParameter(paramName);
				out.println(
					String.format("%s : %s", paramName, paramValue)	
				);
			}
		%>	
	3. log 기록	
		<% application.log("샘플 로그 메시지"); %>
		
	4(***). 웹 리소스(url) 획득
	 	<%
	 		String url = "/resources/images/cat4.png";
			String realPath = application.getRealPath(url);
			File srcFile = new File(realPath);
			
			
			String destUrl = "/11/cat4.png";
			String destPath = application.getRealPath(destUrl);
			File destFile = new File(destPath);

			try(
				InputStream is = application.getResourceAsStream(url);
// 				FileInputStream fin = new FileInputStream(srcFile); // 1차 스트림
				BufferedInputStream bin = new BufferedInputStream(is); // 2차 스트림(연결형)
				FileOutputStream fout = new FileOutputStream(destFile);
				BufferedOutputStream bout = new BufferedOutputStream(fout);
			){
				int c=-1;
				while((c=bin.read())!=-1){
					bout.write(c);
				}
				bout.flush();
			}
	 	%>
	 	srcFile : <%=srcFile.getCanonicalPath() %>
</pre>
<img src="<%=request.getContextPath() %>/11/cat4.png" />
</body>
</html>












