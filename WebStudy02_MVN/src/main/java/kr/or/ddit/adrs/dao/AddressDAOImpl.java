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

	@Override
	public int insertAddress(AddressVO adrsVO) {
		return 0;
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
					AddressVO vo = SampleDataMapperUtils.recordToVO(rs, AddressVO.class);
					ardsList.add(vo);
				}
				return ardsList;
		}catch (Exception e) {
			throw new PersistenceException(e); //예외정보 너무 포괄적임 (다오인지, 로직인지 명확하지 않음 !)
		}
	}

	
	
	@Override
	public int updateAddress(AddressVO adrsVO) {
		return 0;
	}

	@Override
	public int deleteAddress(int adrsNo) {
		return 0;
	}
	
}
