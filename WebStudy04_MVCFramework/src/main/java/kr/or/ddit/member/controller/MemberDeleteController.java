package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	MemberService service = new MemberServiceImpl();
	
	/* 내 코드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName ="member/ajax/memberDeleteConfirm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputPass = req.getParameter("inputPass");
		String authId = (String)req.getSession().getAttribute("authId");
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(authId);
		memberVO.setMemPass(inputPass);
		ServiceResult result = service.removeMember(memberVO);
		String viewName ="";
		//INVALIDPASSWORD, OK, FAIL
		switch (result) {
		case INVALIDPASSWORD:
			req.setAttribute("message", "비밀번호 오류");
			viewName = "mypage";
			break;
		case OK: //탈퇴처리 완료 -> alert 띄워주고 싶은뎅..
			req.setAttribute("message", "탈퇴완료되었습니다.");
			viewName = "index";
			break;
		default: //FAIL
			req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
			viewName = "member/memberForm";
			break;
		}
	}
	*/
	
	@RequestMapping(value="/member/memberDelete.do", method = RequestMethod.POST)
	public String doPost(
			HttpSession session,
			MemberVOWrapper principal,
			@RequestParam("password") String password //필수파라미터 (required true 생략)
			)
	{
		String memId = principal.getName();
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(password);
		
		//검증
		Map<String, List<String>> errors = new HashMap<>();
		boolean valid = ValidationUtils.validate(inputData, errors, DeleteGroup.class); //id와 pass만 검증
		String viewName = "";
		if(valid) {
			ServiceResult result = service.removeMember(inputData);
			switch (result) {
			case INVALIDPASSWORD:
				viewName = "redirect:/mypage";
				//메세지 세션에 담기
				session.setAttribute("message", "비밀번호 오류"); //flsah attribute
				break;
			case OK:
				viewName = "redirect:/";
				session.invalidate();
				break;
			default:
				viewName = "redirect:/mypage";
				session.setAttribute("message", "서버 오류");
				break;
			}
		}else { //id 또는 비밀번호 누락인데, id는 session Scope에서 받았기 때문에 다시 비밀번호를 입력받을 수 있는 마이페이지로 이동
			//인증시스템 관련 프로세스는 redirection으로 페이지 이동
			viewName = "redirect:/mypage";
			session.setAttribute("message", "비밀번호 누락");
		}
		return viewName;
	}
}