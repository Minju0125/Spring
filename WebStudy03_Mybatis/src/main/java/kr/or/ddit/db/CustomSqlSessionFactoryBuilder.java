package kr.or.ddit.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	//{
		//생성자와 같은 의미로 매번 인스턴스가 만들어질때마다 반복실행
	//}
	
	private static SqlSessionFactory sqlSessionFactory;
	static {//한번만 실행
		String configFile = "kr/or/ddit/mybatis/Configuration.xml";
		//이 파일을 가지고 읽어야하기 때문에 입력스트림
		try(
			Reader reader = Resources.getResourceAsReader(configFile);//문자로 된 xml 파일을 가지고 있기 때문에
			
		){
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); //싱글톤으로 sessionFactory 생성
																			  //이 코드블럭(static)은 한번말 실행되기 때문에 
		}catch (IOException e) {
			//예외를 톰캣에 던져줘야하는데, 선언부가 존재하지 않기 때문에 throws 할수 없음
			// => 예외를 unchecked exception으로 전환시켜줌
			throw new RuntimeException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
