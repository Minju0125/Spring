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
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.7.1.min.js"></script>
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
<form id="calForm" onchange="this.requestSubmit();" method="post"
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
   <select name="locale">
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
<script type="text/javascript">
	//selector : ex) $("#calForm") - HtmlElement를 jQuery 객체로 wrapping 함.
	//				Integer : wrapper class, int -> new Integer(3) (adaptor), adaptor design pattern
	
	//console.log(calForm);  
	//console.log($(calForm)); 
	
	//모든 입력 태그가 대상이 됨 (select, button까지)
	//name 속성 있으면 전송 목적
	//name 속성이 있는 input 태그
	$(":input[name]").on("change", function(event){ //여기서 받은 event => change
		event.target.form.requestSubmit();
		this.form.requestSubmit();
 	});
	
	$(calForm).on("submit",  function(event){ //여기서 받은 event => submit
		event.preventDefault(); //고유의 행동을 중단 
		//form의 submit 은 동기요청 (이 동기요청을 중단)
		console.log(event.target);
		console.log(this); // 여기서 this 는 html element
		console.log($(this)); // 제이쿼리 객체로 wrapping
		// 동기 요청 -> 비동기 요청으로 전환
		// 요청의 기본적인 객체를 가지고 있는 form 의 기본적인 특성은 변하지 않음
		// 원래의 element 인 form 이 가지고 있는 조건을 가져와야함
		let url = this.action;  //form의 action 속성
		let method = this.method;
		
		//serialize(제이쿼리 함수 => 제이쿼리 객체로 생성을 해야 사용할 수 있음)
		// year month locale
		// ex) year=20&month=8&locale=ko-KR
		let data = $(this).serialize();
		
		//form에서 넘어온 데이터
		let settings = {
			//요청을 어떻게 보낼 것인지
			url:url,
			mehtod:method,
			data:data,
			
			//응답데이터
			//응답데이터의 형태 (달력을 출력할 table 태그가 필요하기 때문에)
			dataType:"html",
			success:function(resp){
				$(resultArea).html(resp);
			}
		};
		$.ajax(settings);
	});
	
	//동적 element 에 대한 핸들링 필요한 경우에는 이런식으로 처리해야함
	$("#resultArea").on("click", "a", function(){
		console.log("a tag click!!!");
		//원래 데이터 타입을 그대로 받을 수 있음
		let year = $(this).data("year"); //제워쿼리 객체
		let month = $(this).data("month");
		let locale = $(this).data("locale");
      	calForm.year.value = year;
	    calForm["month"]["value"] = month;
	    //calForm.requestSubmit(); //이건 html element 함수
	    //requestSubmit을 쓴 이유 : event 발생시키기 떄문에 위에 있는 함수 발생
	    //여기서 submit 쓰면 이벤트 발생없이 그냥 전송
	    $(calForm).submit(); //제이쿼리 함수로 쓰면 이벤트핸들러 작동
	}) 
</script>
</body>
</html>