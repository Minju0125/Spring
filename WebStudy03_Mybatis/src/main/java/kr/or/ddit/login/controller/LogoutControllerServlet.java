package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);//현재 세션이 없으면 null 반환
													//최초의 요청이거나, tracking mode 가 아예 없는 경우 -> false
		
		//요청에 대한 검증 작업
		if(session == null || session.isNew()){//로그아웃은 최초의 요청이 될 수 없음 !
			//정상적인 로그아웃 과정이 아님
			resp.sendError(400, "로그인 하지도 않았는데!!");
			return;
		}//검증 통과
		
		//정상적인 세션인 경우
		//1. authId 지우기
		//session.removeAttribute("authId");
		//2. 세션 만료
		session.invalidate(); //저장된 데이터가 있으면 다 지움 => 앞에서 remove 안해도됨
		
		//3. 만료 후, 웰컴페이지로 이동
		String goPage = "redirect:/";
		
		if(goPage.startsWith("redirect")) { //redirection
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location); //goPage 에서 redirect 떼고 contextpath 붙여줘야함
		}else { //dispatch
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
