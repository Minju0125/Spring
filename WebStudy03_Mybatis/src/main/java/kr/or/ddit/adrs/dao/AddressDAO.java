package kr.or.ddit.adrs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.AddressVO;

/**
 * 주소록 관리(CRUD) persistence layer (
 *
 */
public interface AddressDAO {
	/**
	 * 신규 주소 등록
	 * @param adrsVO
	 * @return 등록 성공시(>=1)
	 */
	public int insertAddress(AddressVO adrsVO);
	
	/**
	 * 특정 소유자의 주소록 조회
	 * @param memId 검색 조건에 사용될 소유자 아이디
	 * @return size에 따라 주소록 존재 여부 확인
	 */
	public List<AddressVO> selectAddressList(String memId);
	
	/**
	 * 기존 주소록 수정
	 * @param adrsVO 수정할 대상이 되는 정보를 가진 객체
	 * @return 수정 성공시(>=1)
	 */
	public int updateAddress(AddressVO adrsVO);
	
	/**
	 * 주소록 삭제
	 * @param adrsNo 삭제할 주소록 번호
	 * @return 삭제 성공시(>=1)
	 */
	public int deleteAddress(@Param("code") int adrsNo);
}
