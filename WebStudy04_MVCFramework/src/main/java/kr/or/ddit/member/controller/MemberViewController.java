package kr.or.ddit.member.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberViewController {
	MemberService service = new MemberServiceImpl();

	@RequestMapping("/member/memberView.do")
	public String memberView(HttpServletRequest req, @RequestParam("who") String memId) {

		MemberVO member = service.retrieveMember(memId);

		req.setAttribute("member", member);

		return "member/ajax/memberView";
	}
}
