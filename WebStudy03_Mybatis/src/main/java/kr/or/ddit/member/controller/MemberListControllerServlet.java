package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 목록 조회 : /member/memberList.do
 * 마이페이지 : /mypage
 * 정보 수정 : /member/memberUpdate.do
 * 탈퇴 : /member/memberDelete.do
 * 가입 : /member/memberInsert.do
 */
@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	MemberService service = new MemberServiceImpl();
	//리스트를 달라는 요청을 받음(동기요청 get)
	//리스트의 사이즈가 0인지 아닌지에 따라서 응답 달라짐 (if문)
	//list를 req scope에 담아서
	//뷰 선택
	//뷰는 memberList
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemberVO> memberList = service.retrieveMemberList();
		req.setAttribute("memberList", memberList);

		String goPage = "/WEB-INF/views/member/memberList.jsp";		
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location); 
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}