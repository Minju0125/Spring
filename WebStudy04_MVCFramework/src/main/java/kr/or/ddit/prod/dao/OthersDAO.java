package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 분류 선택과 제조사 선택 UI를 구성하기 위한 Persistence Layer
 * @author PC-13
 *
 */
public interface OthersDAO {
	
	/**
	 * 전체상품분류 조회
	 */
	public List<LprodVO> selectLprodList();
	
	/**
	 * 특정 분류에 해당하는 제조사 목록 조회
	 * @param lprodGu 없는 경우, 전체 제조사 목록 조회
	 */
	public List<BuyerVO> selectBuyerList(@Param("lprodGu") String lprodGu);
}
