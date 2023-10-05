package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Business Logic Layer
 *
 */
public interface ProdService {
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return 존재하지 않으면, null 반환
	 */
	public ProdVO retrieveProd(String prodId);
	
	/**
	 * 페이징 처리 기반의 상품 목록 조회
	 * 페이징 처리의 결과로 totalRecord/dataList 프로퍼티를 만들어줘야함.
	 * @param currentPage 를 비롯한 프로퍼티를 가진 {@link PaginationInfo}
	 */
	public void retrieveProdList(PaginationInfo<ProdVO> paging);
	
	/**
	 * 상품코드를 생성하고, 신규 상품을 등록함.
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult createProd(ProdVO prod);
}
