package kr.or.ddit.member.controller;

import javax.servlet.http.HttpServletRequest;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.paging.BootstrapPaginationRenderer;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.SearchVO;

/**
 * 목록 조회 : /member/memberList.do   
 * 마이페이지 : /mypage
 * 정보 수정 : /member/memberUpdate.do
 * 탈퇴 : /member/memberDelete.do
 * 가입 : /member/memberInsert.do
 * 
 * 상세조회 : /member/memberView.do?who=a001
 */
@Controller
public class MemberListController{
	MemberService service = new MemberServiceImpl(); //의존 관계 형성
	
	@RequestMapping("/member/memberList.do")
	public String doGet(
			HttpServletRequest req,
			@RequestParam(value="searchType", required = false) String searchType,
			@RequestParam(value="searchWord", required = false) String searchWord,
			@RequestParam(value="page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("simpleCondition") SearchVO simpleCondition
			){
		
		PaginationInfo<MemberVO> paging = new PaginationInfo<>(5,2);
		paging.setSimpleCondition(simpleCondition); //키워드 검색 조건
		paging.setCurrentPage(currentPage);
		
		service.retrieveMemberList(paging); 
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		req.setAttribute("paging", paging); //request scope 이용해서 전달
		
		return "member/memberList";
	}
}