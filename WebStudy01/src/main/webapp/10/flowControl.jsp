<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/flowControl.jsp</title>
</head>
<body>
<h4>Flow control(페이지 이동 방식, A->B)</h4>
<pre>
   Http : ConnectLess, StateLess
   1.  Request Dispatch : 원본 요청을 그대로 유지한 채 분기하는 방식.
      - forward(Model2) : 이동 후 최종 응답이 B에서 바로 전송.
      - include(Page modulization) : B에서 생성된 일부 결과 데이터와 A에서 만들어진 일부 데이터가 하나로 합쳐져서 응답이 전송됨.
               (A가 B를 내포함)
      <%
         String path = "/05/standardDesc.jsp";
//          RequestDispatcher rd = request.getRequestDispatcher(path);
//          rd.forward(request, response); //dispatch 방식
//          rd.include(request, response); //dispatch 방식 (가지고 돌아옴?)
         String location = request.getContextPath() + path;
         response.sendRedirect(location);
      %>
<%--       <jsp:forward page="/05/standardDesc.jsp" /> --%>
<%--       <jsp:include page="<%=path%>" /> --%>
   2.  Redirect : response body가 없는 응답이 전송되면서, 원본 요청이 제거되고, 완전히 새로운 요청이 발생하는 방식.
   				  Location 헤더를 통해 클라이언트가 발생시킬 새로운 요청의 주소를 설정함.
</pre>
</body>
</html>