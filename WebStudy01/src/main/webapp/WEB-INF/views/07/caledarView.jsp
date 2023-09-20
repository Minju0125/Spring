<%@page import="kr.or.ddit.vo.CalendarVO"%>
<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.time.Year"%>
<%@page import="java.util.Optional"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	Locale locale = (Locale) request.getAttribute("locale");
	CalendarVO calVO = (CalendarVO) request.getAttribute("calVO");
	Locale locale = calVO.getLocale();
	YearMonth targetMonth = calVO.getTargetMonth();
	YearMonth beforeMonth = calVO.getBeforeMonth();
	YearMonth nextMonth = calVO.getNextMonth();
%>    
<h4>
<a href="javascript:;" 
	data-year="<%=beforeMonth.getYear()%>" data-month="<%=beforeMonth.getMonthValue()%>">&lt;&lt;&lt;</a>
<%=String.format(locale, "%1$tY, %1$tB", targetMonth) %>
<a href="javascript:;" 
	data-year="<%=nextMonth.getYear()%>" data-month="<%=nextMonth.getMonthValue()%>">&gt;&gt;&gt;</a>
</h4>
<table>
	<thead>
		<tr>
		<%
			WeekFields weekFields = WeekFields.of(locale);
			DayOfWeek firstDayOfWeek = weekFields.getFirstDayOfWeek();
			String ptrn = "<td class='%2$s'>%1$s</td>";
// 			DayOfWeek[] weeks = DayOfWeek.values();
			for(int col=0; col<7; col++){
				DayOfWeek tmp = firstDayOfWeek.plus(col);
				out.println(
					String.format(ptrn, tmp.getDisplayName(TextStyle.SHORT, locale), tmp.name())	
				);
			}
		%>
		</tr>
	</thead>
	<tbody>
		<%
			LocalDate firstDate = targetMonth.atDay(1);
			int offset = firstDate.get(weekFields.dayOfWeek()) - firstDayOfWeek.get(weekFields.dayOfWeek());
			LocalDate date = firstDate.minusDays(offset);
			for(int row=0; row<6; row++){
				out.println("<tr>");
				for(int col=0; col<7; col++){
					YearMonth thisMonth = YearMonth.from(date);
					String clz = thisMonth.isBefore(targetMonth) ? "before" :
									thisMonth.isAfter(targetMonth) ? "after" : 
										date.getDayOfWeek().name();
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









