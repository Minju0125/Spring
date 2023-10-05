package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService{
	ProdDAO dao = new ProdDAOImpl();
	
	@Override
	public ProdVO retrieveProd(String prodId) {
		return dao.selectProd(prodId);
	}

	@Override
	public void retrieveProdList(PaginationInfo<ProdVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ProdVO> dataList =  dao.selectProdList(paging);
		paging.setDataList(dataList);
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		int cnt = dao.insertProd(prod);
		return cnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}
}
