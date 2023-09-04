<%@page import="java.time.LocalDate"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Locale locale = request.getLocale(); //locale 의 기준도 클라이언트 기준으로 해야함!
	YearMonth targetMonth = YearMonth.now();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.before, .after{
		color : silver;
	}
</style>
</head>
<body>
<table>
	<thead>
		<tr>
		<%
			String ptrn = "<td class='%2$s'>%1$s</td>";
			DayOfWeek[] weeks= DayOfWeek.values();
			for(int col=0; col<7; col++){
				out.println(
					String.format(ptrn, weeks[col].getDisplayName(TextStyle.SHORT,locale),"")
				);
			}
		%>
		</tr>
	</thead>
	<tbody>
		<%
			LocalDate firstDate = targetMonth.atDay(1);
			LocalDate lastDate = targetMonth.atEndOfMonth();
			int offset = firstDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue();
			LocalDate date = firstDate.minusDays(offset);
			for(int row=0; row<6; row++){
				out.println("<tr>");
				for(int col=0; col<7; col++){
					//clz 값 결정
					String clz= "";
					
					if(date.isBefore(firstDate)){
						clz="before";
					}
					if(date.isAfter(lastDate)){
						clz="after";
					}
// 					date.isAfter(other)
					
					out.println(
							String.format(ptrn, date.getDayOfMonth(), clz)
					);
					date = date.plusDays(1);
				}
				out.println("</tr>");
			}
		%>
	</tbody>
</table>
</body>
</html>