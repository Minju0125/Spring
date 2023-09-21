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

import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet{
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	//모듈화를 통해 책임 분리 - 검증
	private boolean validate(MemberVO member) {
		//null checking & white space checking
		boolean valid = true;
		StringUtils.isNotBlank(member.getMemId());
		
		valid &= StringUtils.isNotBlank(member.getMemId()); //and 연산 먼저한 후에, true 또는 false 값 할당
		valid &= StringUtils.isNotBlank(member.getMemPass()); //and 연산 먼저한 후에, true 또는 false 값 할당
		return valid;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. request body 에 대한 디코딩 설정.
		req.setCharacterEncoding("UTF-8"); //모든 controller 에서 처음에 쓰이는 코드 !
		//2. 파라미터 획득
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		String idSave = req.getParameter("idSave"); //saveId/null
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
		//3. 요청에 대한 검증
		boolean valid = validate(inputData); //검증 통과시 true
		int sc = 200;
		String goPage = null;
		if(valid) {
			//4-1. 검증 통과
			//	5-1. 인증 여부 판단
			boolean authentidated = service.authenticate(inputData);
			HttpSession session= req.getSession();
			if(authentidated) {
				//		6-1. 인증 성공
				//			- 월컴 페이지 이동 (redirection)
				goPage = "redirect:/"; //spring은 이렇게 작동함
				session.setAttribute("authId", memId); //session 에도 맵이 들어있음
				
				//로그인 인증 성공한 경우 아이디 쿠키 & 아이디 저장하기 체크여부 쿠키 총 2개의 쿠키 생성
				Cookie loginIdCookie = new Cookie("loginIdCookie", memId);
				Cookie checkSaveIdCookie = new Cookie("checkSaveIdCookie", idSave);
				//체크 여부에 따라서
				if(checkSaveIdCookie!=null) {
					loginIdCookie.setMaxAge(60*60*24*7);
					loginIdCookie.setPath(req.getContextPath());
				}
				//체크여부 setMaxAge(0), setMaxAge(일주일)
				//addCookie
				
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