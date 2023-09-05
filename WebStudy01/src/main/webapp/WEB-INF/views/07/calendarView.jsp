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
   String yearParam = request.getParameter("year");
   String monthParam = request.getParameter("month");
   String localeParam = request.getParameter("locale");
   
   Locale locale = Optional.ofNullable(localeParam)
   		  					.map(lp->Locale.forLanguageTag(lp)) //여기서 lp은 localeParam(문자열))
		 					.orElse(request.getLocale());

   //Locale locale = request.getLocale();   //reqeust header(Accept-Language)
   
   int year = Optional.ofNullable(yearParam)
               .filter((yp)->yp.matches("\\d{4}"))
               .map((yp)->Integer.parseInt(yp))
               .orElse(Year.now().getValue());

   
   YearMonth targetMonth = Optional.ofNullable(monthParam)
                        .filter((mp)->mp.matches("[1-9]|1[0-2]"))
                        .map((mp)->Integer.parseInt(mp))
                        .map((m)->YearMonth.of(year, m))
                        .orElse(YearMonth.now());
   YearMonth beforeMonth = targetMonth.minusMonths(1);
   YearMonth nextMonth = targetMonth.plusMonths(1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   .before,.after{
      color: silver;
   }
   table{
      border-collapse: collapse;
      width : 100%;
      min-height : 500px;
      text-align: center;
      font-size: large;
   }
   th,td{
      border : 1px solid black;
   }
   .SUNDAY{
      color: red;
   }
   .SATURDAY{
      color: blue;
   }
</style>
</head>
<body>


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
<script type="text/javascript">
	function clickHandler(event){
		console.log(event);
		let aTag = event.target;
		//html 은 dataset으로 관리함
		console.log(aTag.dataset);
		let year = aTag.dataset.year
		let month = aTag.dataset["month"]
		calForm.year.value = year; //year라는 input 태그에 접근
		calForm["month"]["value"] = month; //자바 스크립트가 동적타입 언어이기 때문에 => 객체 구조를 동적으로 자유롭게 변경가능
		calForm.requestSubmit();
	}
</script>
</body>
</html>