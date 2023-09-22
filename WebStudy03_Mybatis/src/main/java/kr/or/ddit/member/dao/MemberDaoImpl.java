package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements MemberDao {
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); //싱글톤으로 팩토리 받아오기
	
	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData){
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				
		){
			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDao.selectMemberForAuth",inputData);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO selectMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> selectMemberList() {
		try (
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDao mapperProxy = sqlSession.getMapper(MemberDao.class);
			return mapperProxy.selectMemberList();
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}
}
