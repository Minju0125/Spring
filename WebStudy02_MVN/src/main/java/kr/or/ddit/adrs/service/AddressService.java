package kr.or.ddit.adrs.service;

import java.util.List;

import kr.or.ddit.vo.AddressVO;

/**
 * 주소록 관리를 위한 Business Logic Layer
 * @author PC-13
 *
 */
public interface AddressService {
	/**
	 * adrsVO을 파라미터로, insert 구문을 실행 
	 * @param adrsVO
	 * @return insert 명령성공시 1이상 반환, 실패시 0반환
	 */
	public boolean createAddress(AddressVO adrsVO);
	
	/**
	 * 
	 * @param memId
	 * @return 조회된 list 의 크기가 0보다 크다면 list 를 반환하고, 아니라면 null 반환
	 */
	public List<AddressVO> retriveAddressList(String memId);
	public boolean modifyAddress(AddressVO adrsVO);
	public boolean removeAddress(int adrsNo);
}
