package kr.or.ddit.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

class MemberVOTest {

	@Test
	void test() throws JsonProcessingException {
		MemberVO target = new MemberVO();
		target.setMemBir(LocalDate.now());
	
		String json = new ObjectMapper().registerModule(new JavaTimeModule())
										.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
										.writeValueAsString(target);
		System.out.println(json);
	}

}
