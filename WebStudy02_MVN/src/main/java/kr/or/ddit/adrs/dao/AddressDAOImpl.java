package kr.or.ddit.adrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.common.exception.PersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.utils.SampleDataMapperUtils;
import kr.or.ddit.vo.AddressVO;

public class AddressDAOImpl implements AddressDAO{

	private int generateAdrsNo(Connection conn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select NVL(MAX(ADRS_NO), 0)+1 ");
		sql.append("from addressbook              ");
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			if(rs.next());
			return rs.getInt(1);
		}
	}
	
	@Override
	public int insertAddress(AddressVO adrsVO) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO ADDRESSBOOK (                              ");
		sql.append("	    ADRS_NO, MEM_ID, ADRS_NAME, ADRS_HP, ADRS_ADD  ");
		sql.append("	) VALUES (                                         ");
		sql.append("	   #{adrsNo},                                             ");
		sql.append("	    #{memId},                                             ");
		sql.append("	   #{adrsName},                                             ");
		sql.append("	    #{adrsHp},                                             ");
		sql.append("	    #{adrsAdd})                                             ");
		
		try(		
				Connection conn = ConnectionFactory.getConnection();
				//PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			
			int adrsNo = generateAdrsNo(conn);
			adrsVO.setAdrsNo(adrsNo);
			PreparedStatement pstmt = SampleDataMapperUtils.generatePreparedstatement(conn,sql.toString(), adrsVO);
			
			int rowcnt = pstmt.executeUpdate(); 
			
			pstmt.close();
			
			return rowcnt;
			
		}catch (SQLException e) {
			throw new PersistenceException(e); //예외정보 너무 포괄적임 (다오인지, 로직인지 명확하지 않음 !)
		}
		
		//만든 후에 정상동작 확인을 위해 단위테스트 실행하고 오기.
	}

	@Override
	public List<AddressVO> selectAddressList(String memId) {
		List<AddressVO> ardsList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ADRS_NO, MEM_ID, ADRS_NAME, ADRS_HP, ADRS_ADD ");
		sql.append("FROM ADDRESSBOOK                                     ");
		sql.append("WHERE MEM_ID = ?             		                 "); //쿼리파라미터
		
		try(		
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
				pstmt.setString(1, memId);
				ResultSet rs = pstmt.executeQuery(); //5.쿼리실행(runtime에는 쿼리를 못가져감)
				
				while(rs.next()) { 
					AddressVO vo = SampleDataMapperUtils.recordToVO(rs, AddressVO.class); //Data Mapping 
					ardsList.add(vo);
				}
				return ardsList;
		}catch (SQLException e) {
			throw new PersistenceException(e); //예외정보 너무 포괄적임 (다오인지, 로직인지 명확하지 않음 !)
		}
	}
	
	@Override
	public int updateAddress(AddressVO adrsVO) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE addressbook ");
		sql.append("SET                ");
		sql.append("    ADRS_NAME = ?, ");
		sql.append("    ADRS_HP = ?,   ");
		sql.append("    ADRS_ADD = ?   ");
		sql.append("WHERE              ");
		sql.append("        ADRS_NO = ?");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			int idx = 0;
			pstmt.setString(++idx, adrsVO.getAdrsName());
			pstmt.setString(++idx, adrsVO.getAdrsHp());
			pstmt.setString(++idx, adrsVO.getAdrsAdd());
			pstmt.setInt(++idx, adrsVO.getAdrsNo());
			
			return pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteAddress(int adrsNo) {

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM addressbook    ");
		sql.append("WHERE adrs_no = ?          ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, adrsNo);
			return pstmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
