<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.text.MessageFormat"%>
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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.min.js"></script>
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

<%!
	final String OPTPTRN = "<option value='%s'>%s</option>";
%>
<%
	Locale locale = request.getLocale();
%>
<form id="calForm"  method="post"
	action="<%=request.getContextPath() %>/calendar">
	<input type="number" name="year"/>
	<select name="month">
	<%=
		Stream.of(Month.values())
			.map((m)->{
				String display = m.getDisplayName(TextStyle.FULL, locale);
				return String.format(OPTPTRN, m.getValue(), display);
				})
			.collect(Collectors.joining("\n"))
	%>
	</select>
	<select name="locale" >
		<%=
         //Locale -> Option Tah String : map
         //element collection : collect(Collectors)         
            Stream.of(Locale.getAvailableLocales())
            .filter((l)->!l.getDisplayName(locale).isEmpty())
                .map((l)->{
                   return String.format(OPTPTRN, l.toLanguageTag(), l.getDisplayName(l));
                }).collect(Collectors.joining("\n"))
         %>
	</select>
</form>
<div id="resultArea">

</div>

<script>
/* 	selector : ex) $('#calForm') - HtmlElement를 jQuery 객체로 wrapping 함.
				Integer : wrapper class, int  -> new Integer(3)
				원래 데이터를 감싸서 원래의 성질을 변경시킴 - adapter design pattern */
// 	console.log(calForm);
// 	console.log($(calForm));

	$(":input[name]").on("change", function(event){
		this.form.requestSubmit();
	});
	
	$(calForm).on("submit", function(event){
		event.preventDefault();
		console.log(event.target);
		console.log(this);
		console.log($(this));
		
// 		동기 요청 -> 비동기 요청으로 전환
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
// 		ex) year=2023&month=8&locale=ko-KR
		let settings = {
			url : url,
			method : method,
			data : data,
			dataType : "html",
			success : function(resp){
				$(resultArea).html(resp);
			}
		};
		$.ajax(settings);
	});
	
	$("#resultArea").on("click", "a", function(event){
		console.log(" a tag click !!! ");
		let year = $(this).data("year");
		let month = $(this).data("month");
		
		calForm.year.value = year;
		calForm["month"]["value"] = month;
		$(calForm).submit();
	});
	
</script>
</body>
</html>