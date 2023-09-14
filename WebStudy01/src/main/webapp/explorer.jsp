<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String contextPath = request.getContextPath();
	//현재 컨텍스트패스가 있는 경로의 절대경로 받아와서

	//(directory list) 해당경로에 위치한 디렉토리 파일들 list로 가져오기
	//(file list) list에 위치한 파일 목록 가져오기

	String realPath = application.getRealPath(contextPath); //톰캣의 정보를 통해서 가져옴
	File srcFile = new File(realPath); //파일 객체 생성시 물리 주소 필요 => 중간과정 필요한게 web 자원 & classpath 자원

	// 	String[] filenames = srcFile.list();
	// 	for (String filename : filenames) {
	// 		System.out.println("filename : " + filename);
	// 	}

	File path = new File("D:\\00.tools\\eGovFrameDev-4.1.0-64bit\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WebStudy01");
	File[] fList = path.listFiles();

	for (int i = 0; i < fList.length; i++) {

		if (fList[i].isFile()) {
			System.out.println("이건 파일 : " + fList[i].getName()); // 파일의 FullPath 출력 
			System.out.println("이건 파일 : " + fList[i].getName()); // 파일의 FullPath 출력 
		} else if (fList[i].isDirectory()) {
			System.out.println("이건 디렉토리 : " + fList[i].getName()); // 파일의 FullPath 출력 
		}
	}
//파일인지 폴더인지는 구분했는데 어떻게 하위, 상위 폴더/ 디렉토리인지 알지?
		
	%>
	<%=realPath%><br>
	D:\00.tools\eGovFrameDev-4.1.0-64bit\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WebStudy01
</body>
</html>