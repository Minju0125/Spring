package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService{
	private MemberDao dao = new MemberDaoImpl();
	private AuthenticateService authService = new AuthenticateServiceImpl();
	@Override
	public ServiceResult createMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberVO retrieveMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<MemberVO> retrieveMemberList() {
		 return dao.selectMemberList();
	}
	@Override
	public ServiceResult ModifyMember(MemberVO member) {
		//1)인증실패 2)인증성공후 수정실패 3) 인증성공후 수정완료
		boolean authenticated = authService.authenticate(member);
		ServiceResult result = null;
		if(authenticated) { //인증성공
			int rowcnt = dao.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}
	@Override
	public ServiceResult removeMember(MemberVO member) {
		//1)인증실패 2)인증성공후 탈퇴실패 3) 인증성공후 탈퇴완료
		return null;
	}

}
