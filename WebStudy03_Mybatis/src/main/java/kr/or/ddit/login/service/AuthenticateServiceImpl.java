package kr.or.ddit.login.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService{
	private MemberDao memberDao = new MemberDaoImpl();

	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		MemberVO saved = memberDao.selectMemberForAuth(inputData); //현재 로그인 성공한 유저의 정보 (없으면 null)
		ServiceResult result = null;
		if(saved!=null) {
			String inputPass = inputData.getMemPass();
			String savedPass = saved.getMemPass();
			if(savedPass.equals(inputPass)) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}
}
