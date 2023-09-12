<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int minDan = (Integer)request.getAttribute("minDan");
	int maxDan = (Integer)request.getAttribute("maxDan");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
	border-collapse: collapse;
}
tr,td{
	border: 1px solid black;
}
</style>
</head>
<body>
<form id="gugudanForm">
<input type="number" placeholder="min dan" name="minDan">
<input type="number" placeholder="max dan" name="maxDan">
<button type="submit">전송</button>
</form>

<h4>table 태그를 이용한 구구단 출력(<%=minDan %>단~<%=maxDan %>단, 승수 1~9)</h4>
<%!
	private StringBuffer gugudan(int first, int last){
		StringBuffer trTags = new StringBuffer();
		
		for(int i=1; i<=9; i++){
			trTags.append("<tr>");
			for(int j=first; j<=last; j++){			
				trTags.append(
					String.format("<td>%d*%d=%d</td>", j, i, j*i) 
				);
			}
			trTags.append("</tr>");
		}
		
		return trTags;
	}
%>
<table>
	<%=gugudan(minDan, maxDan) %>
</table>
<hr/>
<table>
	<%
		StringBuffer trTags = new StringBuffer();
		for(int i=1; i<=9; i++){
			trTags.append("<tr>");
			for(int j=minDan; j<=maxDan; j++){			
				trTags.append(
					String.format("<td>%d*%d=%d</td>", j, i, j*i) 
				);
			}
			trTags.append("</tr>");
		}
	%>
	<%=trTags %>
</table>
<hr/>
<table>
	<%
		for(int i=1; i<=9; i++){
			out.println("<tr>");
			for(int j=minDan; j<=maxDan; j++){			
				out.println(
					String.format("<td>%d*%d=%d</td>", j, i, j*i) 
				);
			}
			out.println("</tr>");
			
		}
	%>
</table>
<hr/>
<table>
	<%
		for(int i=1; i<=9; i++){ 
	%>
		<tr>
	<%
			for(int j=minDan; j<=maxDan; j++){
	%>

			<td><%=String.format("%d*%d=%d", j, i, j*i) %></td>
	<%
			}
	%>
		</tr>
	<%	
		}
	%>

</table>
</body>
</html>