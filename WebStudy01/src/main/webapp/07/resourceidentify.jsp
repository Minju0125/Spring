<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/resourceIdentify.jsp</title>
<style type="text/css">
	img {
	width: 100px;
	height: 100px;
	}
</style>
</head>
<body>
	<h4>자원의 종류와 식별 방법</h4>
	<pre>
	* 자원을 식별할 때는 변경되는 경로는 사용하지 않는다.
	1. file system resource : 자원의 실제 파일 시스템 상의 경로(물리 주소)를 그대로 사용해서 식별함.
		ex) D:\02.medias\images\cute1.PNG 
			-> 경로 구분자: 역슬래시 (윈도우라서) -> os의 표현 방식을 따름
		<%
			File res1 = new File("D:\\02.medias\\images\\cute1.PNG");
		%>
		파일의 크기 : <%=res1.length() %>
	2. class path resource : classpath 이후의 논리 주소 형태로 자원을 식별함.
		ex) /kr/or/ddit/images/cat1.jpg
		<%
			ClassLoader loader = DescriptionServlet.class.getClassLoader(); //하나의 어플리케이션 안에는 로더 여러개
			URL url = loader.getResource("kr/or/ddit/images/cat1.jpg"); //qualified name으로는 실제 파일을 찾아낼 수 없음 
			if(url!=null){
			//=> 자원 찾기( 파일 찾기 , qualified name을 매개변수로)
				String realPath = url.getFile();
				File res2 = new File(realPath);
		%>
		
			파일의 크기 : <%=res2.length() %>
			파일의 물리 주소 : <%=realPath %>
		<%
		}
		%>
	
	(**중요**)3. web resource (context resource) : URL 형태의 식별자 체계로 네트워크 반대편의 클라이언트가 접근할 수 있는 자원.
		ex) http://localhost/WebStudy01/resources/images/cat4.png (URL)
		<%
			String logical = "/resources/images/cat4.png";
			String pysical = application.getRealPath(logical); //전체를 통틀어서 싱글톤
			File res3 = new File(pysical);
		%>
		
		파일의 크기 : <%=res3.length() %>
		파일의 물리 주소 : <%=pysical %>
		
	* 웹 자원의 식별자
	URI (Uniform Resource Identify, 통합 자원 식별자) :  네트워크 자원을 식별하는 체계
	- URN (Uniform Resource Naming)
	- URC (Uniform Resource Content)
		-> 이거 두개는 식별성이 없어서 사용 안함
	- URL (Uniform Resource Locator)
		=> 이거 세개는 URI를 구현하는 방법
	
	case 1 - http://localhost/WebStudy01/resources/images/cat4.png (URL)
	case 2 - http://localhost/WebStudy01/ver4/imageForm.do 
	
	URL 표기 방식
	protocol : //IP[domain]:port/context/depth.../resource_name
	* 네이버 서버의 전체 영역에는 접근할 수 없고, 일부 영역(appbase)에 물리주소를 통해 접근
	
	절대경로
		1) http://localhost/WebStudy01/resources/images/cat4.png
			*  root 에서부터 전체를 다 표현하는 방식
		2) //localhost/WebStudy01/resources/images/cat4.png
		3) clienct side - <%=request.getContextPath() %>/resources/images/cat4.png (**)
	
		** 하드코딩 되어 있는 1,2번 -> 도메인이 변경되면 다 고쳐야함
			=> 2,3번은 물리주소를 통해서 논리주소 잡는 과정이 필요함
		** 3번은 도메인이 바뀌더라도 소스를 수정할 필요 없음 => 이 접근 방식을 많이 사용함
		4) server side - /resources/images/cat4.png 
				
	상대경로 : 현재 페이지의 출처를 기준으로 경로를 표기함.
		../resources/images/cat4.png
	
	
	* location 이 가지고 있는 출처 정보중에 localhost 포함 => 고양이 사진 요청은 한번만 함 *
	</pre>
	<img src="http://localhost/WebStudy01/resources/images/cat4.png"/>
	<img src="//localhost/WebStudy01/resources/images/cat4.png"/>
	<img src="/WebStudy01/resources/images/cat4.png"/>
	<img src="../resources/images/cat4.png"/>
	
</body> 
</html>