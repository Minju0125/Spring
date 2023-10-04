package kr.or.ddit.prod.service;

import java.util.List;

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
		int totalRecord = dao.selectTotalRecord();
		paging.setTotalRecord(totalRecord);
		List<ProdVO> dataList =  dao.selectProdList(paging);
		paging.setDataList(dataList);
	}
}
