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
<h4>
<a href="javascript:;" onclick="clickHandler(event);" data-year="<%=beforeMonth.getYear()%>" data-month="<%=beforeMonth.getMonthValue()%>">&lt;&lt;&lt;</a> <!-- data- : data속성 (키밸류 생성됨)-->
<%=String.format(locale, "%1$tY, %1$tB", targetMonth) %>
<a href="javascript:;" onclick="clickHandler(event);" data-year="<%=nextMonth.getYear()%>" data-month="<%=nextMonth.getMonthValue()%>">&gt;&gt;&gt;</a>
<!-- 모든 이벤트 핸들러는  -->
<!-- 1. 이벤트 핸들러 안에서는 발생한 이벤트에 대한 레퍼런스를 처리할 수 있어야함 -->
<!-- 2. 모든 이벤트 객체는 그 이벤트를 발생시킨 타켓에 대한 정보를 가지고 있다. -->
</h4>
<%!
	final String OPTPTRN = "<option value='%s' %s>%s</option>";
%>

<form id="calForm" onchange="this.requestSubmit()" method="post">
	<input type="number" name="year" value="<%=targetMonth.getYear()%>"/>
	<select name="month">
	<%=
	Stream.of(Month.values())
		  .map(m->{
			  String selected = m.equals(targetMonth.getMonth()) ? "selected" : "" ;
			  String display = m.getDisplayName(TextStyle.FULL, locale);
			  return String.format(OPTPTRN, m.getValue(), selected, display);
		}).collect(Collectors.joining("\n"))
// 					.map((m)->{
// 					String selected = m.equals() ? "selected" : "";
// 			    	return String.format(OPTPTRN, l.toLanguageTag(), selected, l.getDisplayName(l));
// 					})
				
	%>
	</select>
	<select id = "localeText" name="locale"  onchange="console.log(this);">
		<%=
			/*
         //Locale -> Option Tah String : map
         //element collection : collect(Collectors)         
         Stream.of(Locale.getAvailableLocales())
            .filter((l)->!l.getDisplayName(locale).isEmpty())
                .map((l)->String.format("<option value='%1$s' %3$s>%2$s</option>", l.toLanguageTag(), l.getDisplayName(l), l.toLanguageTag().equals(localeParam)?"selected":""))
                .collect(Collectors.joining("\n"))
         	*/
         	Stream.of(Locale.getAvailableLocales())
            .filter((l)->!l.getDisplayName(locale).isEmpty())
                .map((l)->{
                	String selected = l.equals(locale) ? "selected" : "";
                	return String.format(OPTPTRN, l.toLanguageTag(), selected, l.getDisplayName(l));
                }).collect(Collectors.joining("\n"))
         %>
	</select>
</form>

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