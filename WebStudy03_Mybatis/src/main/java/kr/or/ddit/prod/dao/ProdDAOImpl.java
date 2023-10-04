package kr.or.ddit.prod.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO{
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public ProdVO selectProd(String prodId) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	//autocomm
			){
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				return mapperProxy.selectProd(prodId);
			}
	}

	@Override
	public int selectTotalRecord() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	//autocomm
			){
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				return mapperProxy.selectTotalRecord();
			}
	}

	@Override
	public List<ProdVO> selectProdList(PaginationInfo<ProdVO> paging) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	//autocomm
			){
				ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
				return mapperProxy.selectProdList(paging);
			}
	}
}
