<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4>JSP 스펙과 Container</h4>
<pre>
	JSP(Jakarta Server Page)  : 자바와 서블릿 스펙을 기반으로 한, 템플릿 엔진
	JSP container ?  템플릿 엔진의 제공자로 jsp 인스턴스의 생명주기 관리자
	JSP container 동작 단계
	1. jsp 템플릿 파일로부터 서블릿 소스 생성.
	2. 컴파일
	3. jsp 인스턴스(싱글턴) 생성
	4. request callback 호출 후 응답 전송...
	
	스펙에 따른 소스 구성 요소
	1. 정적 텍스트, HTML, JS, CSS.. : Fornt-End 정적 요소
	2. Back-End 요소(script 요소)
		1) scriptlet : 
			<% // java code -> request callback 메소드의 지역 코드화
				Date today = new Date();
				
			%>
		2) directive(지시자) : 실행코드나 응답 컨텐츠를 직접 구성하지 않고, 환경 설정에 사용. &lt;%@ page  %&gt;
			- page (required) : jsp 페이지 자체에 대한 설정 변경(속성으로 변경함.)
					ex) 버퍼의 사용여부, 버퍼의 용량, 세션 사용 여부, el 사용여부 등...
			- taglib (optional)
			- include  (optional)
		3) expression(표현식) : <%=today  %>
		4) declaration(선언부) : 
			<%! // 전역 field 나 method 선언  
			public void test(){}
			%>
		5) comment
<!-- 			comment1 -->
			<%-- comment5  --%>
			- client side comment(X) : HTML comment, JS comment, CSS comment
			- server side comment : Java comment, JSP comment
		6) action tag : 커스텀 태그의 일종으로 jsp 스펙에서 기본 제공되는 커스텀 태그
			커스텀 태그 사용 방법
			&lt;prefix:tagName attribute_name="value" /&gt;
			
		7) EL(Expression Language, 표현 언어), \${elVariables }
		8) JSTL(Jsp Standard Tag Library)
</pre>
<script type="text/javascript">
// 	comment2
</script>










