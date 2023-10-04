package kr.or.ddit.servlet06.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;

//POJO(Plain Old Java Object)
public class DataBasePropertyDAO{
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); //싱글톤으로 팩토리 받아오기
	
	//Mybatis 프레임워크는 필요한 객체를 대신 만들고, 어플리케이션 내부로 주입하는 역할을 함.
	//-IOC (Inversion Of controll, DI : Dependency ㅑnjection) 패턴 활용 
	public List<DataBasePropertyVO> selectDBPropertyList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectList("kr.or.ddit.servlet06.dao.DataBasePropertyDAO.selectDBPropertyList");
		}
	}
}
/*
		try (Connection conn = ConnectionFactory.getConnection(); Statement stmt = conn.createStatement(); // 4. 쿼리객체 생성
		) {
			StringBuffer sql = new StringBuffer();
			sql.append(" select property_name, property_value, description");
			sql.append(" from database_properties                         ");
			ResultSet rs = stmt.executeQuery(sql.toString());// 5.쿼리실행

			while (rs.next()) {
				DataBasePropertyVO vo = new DataBasePropertyVO();
				list.add(vo);
				vo.setPropertyName(rs.getString("PROPERTY_NAME"));
				vo.setPropertyValue(rs.getString("PROPERTY_NAME"));
				vo.setDescription(rs.getString("DESCRIPTION"));
			}
			return list ; //예외가 발생하지 않을 때만 반환함
						  //예외가 발생하면 반환값 없음
		} catch (SQLException e) {
			throw new RuntimeException(e); //여기에 걸린다면 throw 에서 걸리기 때문에 throw 이후에 return 불가능
		}
 */