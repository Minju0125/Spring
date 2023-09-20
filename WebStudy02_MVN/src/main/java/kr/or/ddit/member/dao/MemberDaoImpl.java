package kr.or.ddit.member.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.common.exception.PersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO2;
import oracle.ucp.common.Chunk.Metadata;

public class MemberDaoImpl implements MemberDao {

	@Override
	public MemberVO2 selectMemberForAuth(MemberVO2 inputData){
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select mem_id, mem_pass, mem_name, mem_hp, mem_mail from member    ");
		sql.append(" where mem_id= ? and mem_pass=?");
		
		try(		
				Connection conn = ConnectionFactory.getConnection(); //3.connection 생성
				//Statement stmt =  conn.createStatement(); //4. 쿼리객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
				
				System.out.println(sql);
				pstmt.setString(1, inputData.getMemId());
				pstmt.setString(2, inputData.getMemPass());
				
				//ResultSet rs = stmt.executeQuery(sql.toString()); //5.쿼리실행
				ResultSet rs = pstmt.executeQuery(); //5.쿼리실행(runtime에는 쿼리를 못가져감)
				
				MemberVO2 saved = null;
				
				if(rs.next()) { 
					saved = new MemberVO2();
					//엔터티를 자바 객체로 바꾸는 과정에서 코드 부하 (-> mybatis 사용 이유)
					saved.setMemId(rs.getString("MEM_ID"));
					saved.setMemPass(rs.getString("MEM_PASS"));
					saved.setMemName(rs.getString("MEM_NAME"));
					saved.setMemHp(rs.getString("MEM_HP"));
					saved.setMemMail(rs.getString("MEM_MAIL"));
				}
				return saved;
		}catch (SQLException e) {
			//throw e; //checked Exception 이기 때문에 컴파일 에러(처리하지 않았다는 의미) 
			//throw new RuntimeException(e); //예외정보 너무 포괄적임 (다오인지, 로직인지 명확하지 않음 !)
			throw new PersistenceException(e); //예외정보 너무 포괄적임 (다오인지, 로직인지 명확하지 않음 !)
						//호출자가 떠안음 (호출자의 호출자 ...-> 톰캣이 나오고, 톰캣이 500 error로 바꿈)
		}
	}
}
