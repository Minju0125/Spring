package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

class ConnectionPoolingTest {
	static String url ;
	static String user ;
	static String password ;
	private static String drvierClassName;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Properties dbInfo = new Properties();
		// classpath resource 형태 자원 읽기
		// kr/or/ddit/db/dbinfo.properties
		//인스턴스가 생성되기 전 한번 실행 (어플리케이션을 통틀어서 한번만 실행됨)
		try(
				InputStream is = ConnectionFactory.class.getResourceAsStream("dbinfo.properties");
		) {
			dbInfo.load(is);
			drvierClassName = dbInfo.getProperty("driverClassName");
			Class.forName(drvierClassName);
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
		} catch (ClassNotFoundException  | IOException e) {  //checkedException(처리안하면 error) & uncheckedException(처리안해도 error x)
			throw new RuntimeException(e); //예외를 만들어서 강제로 넘겨줌 (uncheckedException 중 가장 최상위 runtimeException)
										  //예외의 종류를 변환해서 서버에 넘겨줌 (예외종류 변경시에는 원본 예외 종류 꼭 넘겨줘야함 !)
		}
	}

	@Test
	void test4() throws SQLException{
	}
	
	//이 경우는 내부적으로 풀링 정책 설정하도록 할 수 있음
	//2번은 가장 처음에 열개의 커넥션을 만들어놓지만, 3번은 처음에 5개만 만듦 => 임의로 변경도 가능함
	@Test
	void test3() throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(drvierClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		
		for(int i=0; i<100; i++) { //10번 연결했다가, 10번 끊기위해 (100번 돌리면 오류남)
			try(
					Connection conn = dataSource.getConnection();
					){
				
			}
		}
	}
	
	@Test
	void test2() throws SQLException{
		OracleConnectionPoolDataSource dataSource = new OracleConnectionPoolDataSource();
		dataSource.setURL(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		
		for(int i=0; i<100; i++) { //10번 연결했다가, 10번 끊기위해 (100번 돌리면 오류남)
			try(
					Connection conn = dataSource.getConnection();
					){
				
			}
		}
	}
	
	@Test
	void test1() throws SQLException {
		for(int i=0; i<100; i++) { //10번 연결했다가, 10번 끊기위해 (100번 돌리면 오류남)
			try(
					Connection conn = DriverManager.getConnection(url, user, password);
					){
				
			}
		}
		
	}

}
