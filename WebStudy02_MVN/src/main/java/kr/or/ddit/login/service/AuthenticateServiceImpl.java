package kr.or.ddit.login.service;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService{
	private MemberDao memberDao = new MemberDaoImpl();

	@Override
	public boolean authenticate(MemberVO inputData) {
		
		MemberVO saved = memberDao.selectMemberForAuth(inputData); //현재 로그인 성공한 유저의 정보 (없으면 null)
		return saved!=null; //saved 가 null 이 아니면 true 반환
	}
}
