package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

class CustomSqlSessionFactoryBuilderTest {

	@Test
	void testGetSqlSessionFactoryy() {
		SqlSessionFactory factory = assertDoesNotThrow(()->{
			return CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
		});
		assertNotNull(factory);
	}

}
