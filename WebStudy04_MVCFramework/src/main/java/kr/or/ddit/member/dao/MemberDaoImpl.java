package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;

public class MemberDaoImpl implements MemberDao {
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDao.selectMemberForAuth", inputData);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);	//autocomm
			){
				MemberDao mapperProxy = sqlSession.getMapper(MemberDao.class);
				return mapperProxy.insertMember(member);
			}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	
			){
				MemberDao mapperProxy = sqlSession.getMapper(MemberDao.class);
				return mapperProxy.selectMember(memId);
			}
	}

	@Override
	public List<MemberVO> selectMemberList(PaginationInfo paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			MemberDao mapperProxy = sqlSession.getMapper(MemberDao.class);
			return mapperProxy.selectMemberList(paging);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);	
			){
				MemberDao mapperProxy = sqlSession.getMapper(MemberDao.class);
				return mapperProxy.updateMember(member);
			}
	}

	@Override
	public int deleteMember(String memId) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);	
				){
			MemberDao mapperProxy = sqlSession.getMapper(MemberDao.class);
			return mapperProxy.deleteMember(memId);
		}
	}

	@Override
	public int selectTotalRecord(PaginationInfo<MemberVO> paging) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	
			)
			{
				MemberDao mapperProxy = sqlSession.getMapper(MemberDao.class);
				return mapperProxy.selectTotalRecord(paging);
			}
	}
}