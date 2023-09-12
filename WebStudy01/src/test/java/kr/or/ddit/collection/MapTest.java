package kr.or.ddit.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

class MapTest {

	@Test
	void test() {
		Map<String, Object> sampleMap = new HashMap<String, Object>();
		sampleMap.put("key1", new Date());
		sampleMap.put("key2", Calendar.getInstance());
		sampleMap.put("key3", LocalDateTime.now());
		sampleMap.put("key4", LocalDate.now());
		sampleMap.put("key5", YearMonth.now());
		
		
		for(Entry<String, Object> entry : sampleMap.entrySet()) {
			System.out.printf("%s : %s\n",entry.getKey(), entry.getValue());
		}
		
		// Epoch time (Unix time)
		// GMT
		
	}

}
