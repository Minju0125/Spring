package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet{
	private boolean validate(String memId, String memPass) {
		boolean valid = true;
		valid &= memId != null && !memId.trim().isEmpty(); 
		valid &= memPass != null && !memPass.trim().isEmpty(); 
		return valid;
	}
	private boolean authenticated(String memId, String memPass) {
		return memId.equals(memPass);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 아이디나 비밀번호가 누락된 경우, Bad Request 전송
		// 2. 인증 성공 : 웰컴 페이지로 이동 -> "a001" 님 로그인 성공이라는 메시지를 웰컴페이지에 alert 창으로 출력.
		// 3. 인증 실패 : 로그인 폼으로 이동 -> "아이디나 비밀번호 오류" 라는 메시지를 로그인 폼에서 alert 창으로 출력.
		
//		1. request body 영역에 대한 디코딩 설정.
		req.setCharacterEncoding("UTF-8");
//		2. 파라미터 획득
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
//		3. 요청 검증
		boolean valid = validate(memId, memPass);
		int sc = 200;
		String goPage = null;
		if(valid) {
	//		4-1. 검증 통과
	//			5-1. 인증 여부 판단
				boolean authenticated = authenticated(memId, memPass);
				HttpSession session = req.getSession();
				if(authenticated) {
		//				6-1. 인증 성공
		//					- 웰컴 페이지 이동
					goPage = "redirect:/";
					session.setAttribute("authId", memId);
				}else {
		//				6-2. 인증 실패
		//					- loginForm 으로 이동
					goPage = "redirect:/login/loginForm.jsp";
					session.setAttribute("message", "아이디나 비밀번호 오류");
				} // if(authenticated) end
		}else {
	//		4-2. 검증 불통과
	//			5-2. Bad request 전송
			sc = HttpServletResponse.SC_BAD_REQUEST;
		}// if(valid) end
		
		if(sc == 200) {
			// goPage로 이동
			if(goPage.startsWith("redirect:")) {
				String location = req.getContextPath() + goPage.substring("redirect:".length());
				resp.sendRedirect(location);
			}else {
				req.getRequestDispatcher(goPage).forward(req, resp);
			}
		}else {
			resp.sendError(sc);
		}		
	}
}











