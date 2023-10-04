package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage")
public class MypageControllerServlet extends HttpServlet{
	MemberService service = new MemberServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//모델 확보
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("authId");
		MemberVO member = service.retrieveMember(loginId);
		
		//받아온 모델(VO)를 view로 넘기기
		req.setAttribute("member", member);
		
		String viewName = "member/myPage";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
