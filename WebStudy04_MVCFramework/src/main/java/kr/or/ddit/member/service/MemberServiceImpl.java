package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;

public class MemberServiceImpl implements MemberService{
	private MemberDao dao = new MemberDaoImpl();
	private AuthenticateService authService = new AuthenticateServiceImpl();
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result =null;
		if(dao.selectMember(member.getMemId())==null){ //가입가능
			int rowcnt = dao.insertMember(member);
			result = rowcnt>0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else { //아이디 중복
			result =ServiceResult.PKDUPLICATED;
		}
		return result;
	}
	@Override
	public MemberVO retrieveMember(String memId) {
		return dao.selectMember(memId);
	}
	@Override
	public List<MemberVO> retrieveMemberList(PaginationInfo paging) {
		 int totalRecord = dao.selectTotalRecord(paging);
		 paging.setTotalRecord(totalRecord);
		 List<MemberVO> dataList =  dao.selectMemberList(paging);
		 paging.setDataList(dataList);
		 return dataList;
	}
	@Override
	public ServiceResult modifyMember(MemberVO member) {
		MemberVO inputData = new MemberVO(); //인증을 위한 memberVO (Id, password)
		inputData.setMemId(member.getMemId());
		inputData.setMemPass(member.getMemPass());
		
		//1)인증실패 2)인증성공후 수정실패 3) 인증성공후 수정완료
		ServiceResult authenticated = authService.authenticate(inputData);
		ServiceResult result = null;
		if(authenticated==ServiceResult.OK) { //인증성공
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
		ServiceResult result = authService.authenticate(member);
		if(result==ServiceResult.OK) { //인증성공
			String memId = member.getMemId();
			int rowcnt = dao.deleteMember(memId);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}
}