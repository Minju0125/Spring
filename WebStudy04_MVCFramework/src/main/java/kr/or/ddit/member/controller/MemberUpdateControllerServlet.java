package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandartMultipartHttpServletRequest;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
@MultipartConfig
public class MemberUpdateControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Model
		//String loginId = (String)req.getSession().getAttribute("authId"); //principal 쓰기 전
		
		Principal principal = req.getUserPrincipal(); //memberVO wrapper
		String memId = principal.getName();
		
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		
		String viewName ="member/memberForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 2. 파라미터 확보 --> MemberVO
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		PopulateUtils.populate(member, parameterMap);
		
		if(req instanceof StandartMultipartHttpServletRequest) {
			//wrapper req라면 이 if 문 안으로 들어옴 !
			MultipartFile memImage =  ((StandartMultipartHttpServletRequest) req).getFile("memImage"); //input 태그에 추가한 이름
			//아무것도 선택하지 않아도 비어있는 part 가 전달되기 때문에, 비어있는지 확인
			if(memImage!=null && !memImage.isEmpty()) {
				//blob
				//미들티어에 아무것도 저장하지 않아도됨
				member.setMemImg(memImage.getBytes()); //이미지 업로드
			}
		}
			
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		//3. 검증 (대상 : MemberVO)
		boolean valid = ValidationUtils.validate(member, errors, UpdateGroup.class);
		String viewName = "null"; // 하나의 definition을 선택
		
		if(valid) { //검증통과
//			검증통과
//			4. modifyMember 등록 처리
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
//				1) INVALIDPASSWORD
//					memberForm으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
				
			case OK:
//				2) OK(기존클라이언트의 요청 처리 완료)
//					mypage로 이동 (redirect)
				viewName = "redirect:/mypage";
				break;

			default:
//				3) FAIL
//					memberForm으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "member/memberForm";
				break;
			}
		}else {
//			검증불통
//			memberForm으로 이동 (기존 입력 데이터, 검증결과 메시지들, dispatch)
//				-> 메시지가 어떤 종류일지, 몇개일지 정해진게 없음
			viewName = "member/memberForm";
		}

		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}