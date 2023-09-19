package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A a1 = new A();
 * A a2 = new A();
 * 1. A 클래스가 로딩. (가비지 컬렉션의 대상x)
 * 2. instance 생성 	(가비지 컬렉션의 대상ㅇ) => heap memory
 * 3. a1에 생성된 인스턴스의 참조 주소 할당.
 * 
 * Factory Object[Method] Pattern
 * : 인스턴스 생성에 대한 책임을 지는 factory 객체의 운영
 * 
 */
public class ConnectionFactory {
	
	static String url ;
	static String user ;
	static String password ;
	
	static {
		Properties dbInfo = new Properties();
		// classpath resource 형태 자원 읽기
		// kr/or/ddit/db/dbinfo.properties
		//인스턴스가 생성되기 전 한번 실행 (어플리케이션을 통틀어서 한번만 실행됨)
		try(
				InputStream is = ConnectionFactory.class.getResourceAsStream("dbinfo.properties");
		) {
			dbInfo.load(is);
			String drvierClassName = dbInfo.getProperty("driverClassName");
			Class.forName(drvierClassName);
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
		} catch (ClassNotFoundException  | IOException e) {  //checkedException(처리안하면 error) & uncheckedException(처리안해도 error x)
			throw new RuntimeException(e); //예외를 만들어서 강제로 넘겨줌 (uncheckedException 중 가장 최상위 runtimeException)
										  //예외의 종류를 변환해서 서버에 넘겨줌 (예외종류 변경시에는 원본 예외 종류 꼭 넘겨줘야함 !)
		}
	}
	
	public static Connection getConnection() throws SQLException { //호출자에게 떠넘기는 방법
		return DriverManager.getConnection(url, user, password);
		
	}
}

