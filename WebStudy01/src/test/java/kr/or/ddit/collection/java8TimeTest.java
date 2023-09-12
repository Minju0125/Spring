package kr.or.ddit.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Java8TimeTest {
	
	@BeforeEach
	void setUp() {
		System.out.println("set Up");
	}
	
	@Test
	void test1() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2020);
		
		LocalDateTime now = LocalDateTime.now();
		LocalDate today = LocalDate.now();
		YearMonth thisMonth = YearMonth.now();
		Year thisYear = Year.now();
		System.out.printf("now : %s \n",now);
		System.out.printf("today : %s, %s \n",today, LocalDate.from(now));
		System.out.printf("thisMonth : %s, %s \n",thisMonth, YearMonth.from(now));
		System.out.printf("thisYear : %s, %s \n",thisYear, Year.from(now));
		
	}

	@Test
	void test2() {
		System.out.println("===========");
	}
}
