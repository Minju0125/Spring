package kr.or.ddit.member.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MypageController{
	MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/mypage")
	public String doGet(MemberVOWrapper principal, HttpServletRequest req){ //MemberVOWrapper 가 principal을 구현하고 있기 때문에
		String memId = principal.getName();
		
		MemberVO member = service.retrieveMember(memId);
		
		//받아온 모델(VO)를 view로 넘기기
		req.setAttribute("member", member);
		
		return "member/myPage";
	}
}