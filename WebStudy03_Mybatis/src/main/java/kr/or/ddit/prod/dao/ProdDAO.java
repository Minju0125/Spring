package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Persistence Layer
 *
 */
public interface ProdDAO {
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return 없으면, null 반환
	 */
	public ProdVO selectProd(String prodId);
	
	/**
	 * totalRecord 조회
	 * @param paging TODO
	 * @return
	 */
	public int selectTotalRecord(PaginationInfo paging);
	
	/**
	 * 페이징 처리 기반의 상품 목록 조회
	 * @param paging
	 * @return
	 */
	public List<ProdVO> selectProdList(PaginationInfo<ProdVO> paging);
	
	/**
	 * 상품코드를 생성하고, 신규 상품을 등록함.
	 * 
	 * @param prod
	 * @return 등록 성공( >= 1)
	 */
	public int insertProd(ProdVO prod);
}
