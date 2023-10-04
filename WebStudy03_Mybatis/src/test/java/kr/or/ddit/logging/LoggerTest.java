package kr.or.ddit.logging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class LoggerTest {

	//private static final Logger log= LoggerFactory.getLogger(LoggerTest.class);

	@Test
	public void test() {
//		System.out.println("출력로그");
		log.debug("debug message");
		log.info("info message");
		log.error("error message");
		
		int number = 34;
		log.info("number = {}", number);
		try {
			if(1==1)
					throw new NullPointerException("강제 예외 발생");
		}catch (NullPointerException e) {
			log.error(e.getMessage(), e);
		}
	}
}
