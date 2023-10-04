package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteController extends HttpServlet{
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String memId = (String)session.getAttribute("authId");
		//req.getUserPrincipal();
		req.setCharacterEncoding("UTF-8");
		
		String password = req.getParameter("password");
		
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
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}