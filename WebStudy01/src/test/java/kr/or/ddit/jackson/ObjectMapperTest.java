package kr.or.ddit.jackson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ObjectMapperTest {
	
	static ObjectMapper mapper;

	@BeforeAll
	static void setUp() {
		System.out.println("before class");
		mapper = new ObjectMapper();
	}
	
	@Test
	void marshallingTest() throws JsonProcessingException {
		System.out.println("=======Java to JSON=======");
		Map<String, Object> target = new HashMap<>();
		target.put("key1", "value1");
		target.put("key2", 123456);
		target.put("key3", false);
		target.put("key4", new Date()); //--마샬링
		
		String json = mapper.writeValueAsString(target); //-- 직렬화
		System.out.println(json);
		
	}
	
	@Test
	void marshallingTestAndSerialization() throws IOException {
		System.out.println("=======Java to JSON to byte stream=======");
		Map<String, Object> target = new HashMap<>();
		target.put("key1", "value1");
		target.put("key2", 123456);
		target.put("key3", false);
		target.put("key4", new Date()); //--마샬링
		
		//String json = mapper.writeValueAsString(target); //-- 직렬화
		//System.out.println(json);
		mapper.writeValue(System.out, target); //직렬화=출력스트림에 기록 => ioExcpetion
		
	}
	
	@Test
	void unMarshallingTest() throws JsonMappingException, JsonProcessingException {
		System.out.println("=======JSON to Java=======");
		String json = "{\"key1\":\"value1\",\"key2\":123456,\"key3\":1694140615739}";
		Map<String, Object> target = mapper.readValue(json, HashMap.class); //map은 인터페이스이기 때문에 실제 객체가 생성될 수 있는 HashMap
		System.out.println(target);
	}
	
	@Test
	void deSerializationAndunMarshallingTest() throws IOException {
		System.out.println("=======byte stream to JSON to Java=======");
		
		FileInputStream fis = new FileInputStream(new File("d:/sample.json")); //fis: 데이터를 읽어들일 수 있는 입력 스트림
		//=> 역직렬화 => 역직렬화의 결과를 언마샬링
		
		Map<String, Object> target = mapper.readValue(fis, HashMap.class);
		System.out.println(target);
	}

}


