package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet{
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	private boolean validate(MemberVO member) {
		boolean valid = true;
		valid &= StringUtils.isNotBlank(member.getMemId()); 
		valid &= StringUtils.isNotBlank(member.getMemPass()); 
		return valid;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 아이디나 비밀번호가 누락된 경우, Bad Request 전송
		// 2. 인증 성공 : 웰컴 페이지로 이동 -> "a001" 님 로그인 성공이라는 메시지를 웰컴페이지에 alert 창으로 출력.
		// 3. 인증 실패 : 로그인 폼으로 이동 -> "아이디나 비밀번호 오류" 라는 메시지를 로그인 폼에서 alert 창으로 출력.
		
//		2. 파라미터 획득
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
//		3. 요청 검증
		boolean valid = validate(inputData);
		int sc = 200;
		String viewName = null;
		if(valid) {
	//		4-1. 검증 통과
	//			5-1. 인증 여부 판단
				ServiceResult result = service.authenticate(inputData);
				HttpSession session = req.getSession();
				switch (result) {
				case OK:
					//				6-1. 인증 성공
					//					- 웰컴 페이지 이동
					viewName = "redirect:/";
					session.setAttribute("authMember", inputData);
					break;
				case INVALIDPASSWORD:
					//				6-2. 인증 실패
					//					- loginForm 으로 이동
					viewName = "redirect:/login/loginForm.jsp";
					session.setAttribute("message", "비밀번호 오류");
					break;
				default:
					viewName = "redirect:/login/loginForm.jsp";
					session.setAttribute("message", "아직 가입하지 않았거나 이미 탈퇴한 회원");
					break;
				}
		}else {
	//		4-2. 검증 불통과
	//			5-2. Bad request 전송
			sc = HttpServletResponse.SC_BAD_REQUEST;
		}// if(valid) end
		
		if(sc == 200) {
			// goPage로 이동
			new ViewResolverComposite().resolveView(viewName, req, resp);
		}else {
			resp.sendError(sc);
		}		
	}
}