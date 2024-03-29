package kr.or.ddit.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/loginProcess.do")
public class LogginProcessControllerServlet extends HttpServlet{
	
	//모듈화를 통해 책임 분리 - 검증
	private boolean validate(String memId, String memPass) {
		//null checking & white space checking
		boolean valid = true;
		valid &= memId != null && !memId.trim().isEmpty(); //and 연산 먼저한 후에, true 또는 false 값 할당
		valid &= memPass != null && !memPass.trim().isEmpty(); //and 연산 먼저한 후에, true 또는 false 값 할당
		return valid;
	}
	
	//모듈화를 통해 책임 분리 - 인증
	private boolean authenticated(String memId, String memPass) {
		return memId.equals(memPass);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. request body 에 대한 디코딩 설정.
		req.setCharacterEncoding("UTF-8"); //모든 controller 에서 처음에 쓰이는 코드 !
		//2. 파라미터 획득
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		//3. 요청에 대한 검증
		boolean valid = validate(memId, memPass); //검증 통과시 true
		int sc = 200;
		String goPage = null;
		if(valid) {
			//4-1. 검증 통과
			//	5-1. 인증 여부 판단
			boolean authentidated = authenticated(memId, memPass);
			HttpSession session= req.getSession();
			if(authentidated) {
				//		6-1. 인증 성공
				//			- 월컴 페이지 이동 (redirection)
				goPage = "redirect:/"; //spring은 이렇게 작동함
				session.setAttribute("authId", memId); //session 에도 맵이 들어있음
			}else {
				//		6-2. 인증 실패
				//			- loginForm 으로 이동 (dispatch)
				goPage = "redirect:/login/loginForm.jsp";
				session.setAttribute("message", "아이디나 비밀번호 오류");
			}//if(authentidated) end
		}else {
			//4-2. 검증 불통과
			//	5-2. Bad request 전송
			sc = HttpServletResponse.SC_BAD_REQUEST; //400
		}//if(valid) end
		
		if(sc==200) {
			//검증 통과
			if(goPage.startsWith("redirect")) { //redirection
				String location = req.getContextPath() + goPage.substring("redirect:".length());
				resp.sendRedirect(location); //goPage 에서 redirect 떼고 contextpath 붙여줘야함
			}else { //dispatch
				req.getRequestDispatcher(goPage).forward(req, resp);
			}
		}else {
			resp.sendError(sc);
		}
	}
}