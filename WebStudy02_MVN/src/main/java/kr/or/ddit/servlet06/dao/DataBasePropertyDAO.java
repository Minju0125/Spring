package kr.or.ddit.servlet06.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

//POJO(Plain Old Java Object)
public class DataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDBPropertyList() {
		List<DataBasePropertyVO> list = new ArrayList<>();

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
	}
}