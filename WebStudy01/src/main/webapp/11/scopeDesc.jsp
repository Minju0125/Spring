<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/scopeDesc.jsp</title>
</head>
<body>
<h4>Scope</h4>
<pre>
	: 생명주기가 다른 4개의 기본 객체에 의해 관리되는 저장 공간(Map)
	attribute : scope 를 통해 저장 및 공유되는 데이터 (name-String / value-Object)
	page scope - PageContext
	request scope - HttpServletRequest
	session scope - HttpSession
	application scope- ServletContext
	setAttribute, getAttribute, removeAttribute
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성값");
		request.setAttribute("requestAttr", "요청 속성값");
		session.setAttribute("sessionAttr", "세션 속성값");
		application.setAttribute("applicationAttr", "어플리케이션 속성값");
		
		RequestDispatcher rd = request.getRequestDispatcher("/11/attrView.jsp");
// 		rd.forward(request, response);
		rd.include(request, response);
// 		response.sendRedirect(request.getContextPath() + "/11/attrView.jsp");
	%> 
</pre>

<!-- <a href="attrView.jsp">attribute 확인하기</a> -->

</body>
</html>
















