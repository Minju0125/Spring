package kr.or.ddit.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class java8TimeTest {
	
	@BeforeEach
	//각각의 test case 실행 전 한번씩 실행됨
	void setUp() {
		System.out.println("set up");
	}
	@Test
	void test1() {
		Calendar calendar = Calendar.getInstance();
		//LocalDateTime.now() //=> 현재시점을 기준으로 상수 객체 생성
		//LocalDateTime.of() //=> 특정 시준을 기점으로 상수 객체 생성
		calendar.set(Calendar.YEAR, 2020);
		
		LocalDateTime now = LocalDateTime.now();
		LocalDate today = LocalDate.now();
		YearMonth thisMonth = YearMonth.now(); //now() :이번달 / of() : 특정달
		Year thisYear = Year.now();
		System.out.printf("now : %s \n", now);
		System.out.printf("today : %s, %s \n", today, LocalDate.from(now));
		System.out.printf("thisMonth : %s, %s \n", thisMonth, YearMonth.from(now));
		System.out.printf("thisYear : %s, %s \n", thisYear, Year.from(now));
	}
	@Test
	void test2() {
		System.out.println("=============");
	}
}
