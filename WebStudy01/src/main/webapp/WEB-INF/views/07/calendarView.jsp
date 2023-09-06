<%@page import="kr.or.ddit.vo.CalenderVO"%>
<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.List"%>
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
//Locale locale = (Locale)request.getAttribute("locale"); //리턴타입이 object 이기 때문에 casting 필요함
  //원래는 넘어오는 데이터 4개이기 때문에 캐스팅하기 귀찮음 => VO로
  
  CalenderVO calVO = (CalenderVO)request.getAttribute("calVO");
  Locale locale = calVO.getLocale();
  YearMonth targetMonth = calVO.getTargetMonth();
  YearMonth beforeMonth = calVO.getBeforeMonth();
  YearMonth nextMonth = calVO.getNextMonth();
%>


<h4>
<a href="javascript:;"  data-year="<%=beforeMonth.getYear()%>" data-month="<%=beforeMonth.getMonthValue()%>">&lt;&lt;&lt;</a> <!-- data- : data속성 (키밸류 생성됨)-->
<%=String.format(locale, "%1$tY, %1$tB", targetMonth) %>
<a href="javascript:;"  data-year="<%=nextMonth.getYear()%>" data-month="<%=nextMonth.getMonthValue()%>">&gt;&gt;&gt;</a>
<!-- 모든 이벤트 핸들러는  -->
<!-- 1. 이벤트 핸들러 안에서는 발생한 이벤트에 대한 레퍼런스를 처리할 수 있어야함 -->
<!-- 2. 모든 이벤트 객체는 그 이벤트를 발생시킨 타켓에 대한 정보를 가지고 있다. -->
</h4>

<table>
   <thead>
      <tr>
      <%
         WeekFields weekFields = WeekFields.of(locale);
         DayOfWeek firstDayOfWeek = weekFields.getFirstDayOfWeek();
         String ptrn = "<td class='%2$s'>%1$s</td>";
//          DayOfWeek[] weeks = DayOfWeek.values();
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
         int offset = firstDate.get(weekFields.dayOfWeek()) -firstDayOfWeek.get(weekFields.dayOfWeek());
         LocalDate date = firstDate.minusDays(offset);
         for(int row=0; row<6; row++){
            out.println("<tr>");
            for(int col=0; col<7; col++){
               YearMonth thisMonth = YearMonth.from(date);
   
               String clz = thisMonth.isBefore(targetMonth)? "before":
                  thisMonth.isAfter(targetMonth) ? "after" : date.getDayOfWeek().name();
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