<%@page import="java.util.Calendar"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
table{
border-collapse: collapse;}

td,tr,th {
border: 1px solid black;
}
</style>
<body>

	<% 
	//오늘 날짜 가져오기
	LocalDate now = LocalDate.now();
	
	//오늘이 속한 달
	int year = now.getYear();//2023
	String monthStr = now.getMonth().toString();//AUGUST
	int monthInt = now.getMonthValue();//8
	String dayOfWeek = now.getDayOfWeek().toString();//THURSDAY
	int dayOfWeekValue = now.getDayOfWeek().getValue();//요일을 숫자로 월(1)~일(7)
	
	//오늘이 속한 달이 며칠까지 있는지
	Calendar today  = Calendar.getInstance();
	System.out.println("이 해의 년도 : " + today.get(Calendar.YEAR));
	System.out.println("월 : " + today.get(Calendar.MONTH));
	System.out.println("일 : " + today.get(Calendar.DATE));
	System.out.println("일 : " + today.get(Calendar.DAY_OF_MONTH)); // DATE 와 DAY_OF_MONTH는 같다.
	System.out.println("요일 : " + today.get(Calendar.DAY_OF_WEEK));
	// 1: 일요일, 2:월요일 3: 화요일 ... 7: 토요일
	System.out.println("이 달의 몇번째 요일 : " + today.get(Calendar.DAY_OF_WEEK_IN_MONTH));
	
	//이달의 마지막 날(일수)
	int lastDay = today.getActualMaximum(Calendar.DATE);
	System.out.println("이 달의 마지막 날(일수) : " + today.getActualMaximum(Calendar.DATE));
	%>


	<h4><%=today.get(Calendar.YEAR) %>, <%= monthStr%></h4>
	
	<form onsubmit='submitHandler(event);'>
	<div class="section_selectToday">
		<label>Year : <input type="text" placeholder="2023" id="inputYear"></label>
		<label>&nbsp;Month : 
			<select id="selectMonth" onchange='this.form.requestSubmit();'>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
		</label>
		
	</div>
	</form>
	
	<table id="tableCal">
		<tr>
			<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
		</tr>
		
		<%
		for(int i=1; i<=lastDay; i++){
			switch(dayOfWeekValue){
			
			}
		}










		%>
	</table>
	
	
</body>
</html>