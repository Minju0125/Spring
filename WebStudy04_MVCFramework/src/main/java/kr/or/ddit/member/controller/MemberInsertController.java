package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandartMultipartHttpServletRequest;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberInsertController {
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberInsert.do")
	public String memberForm(){
		return "member/memberForm";
	}

	@RequestMapping(value="/member/memberInsert.do", method = RequestMethod.POST)
	public String insertProcess(
			@ModelAttribute("member") MemberVO member,
			HttpServletRequest req
			) throws IOException {

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
		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class);
		String viewName = "null"; // 하나의 definition을 선택
		
		if(valid) { //검증통과
//			검증통과
//			4. createMember 등록 처리
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
//				1) PKDUPLICATED
//					memberForm으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case OK:
//				2) OK(기존클라이언트의 요청 처리 완료)
//					welcome page로 이동 (redirect)
				viewName = "redirect:/";
				break;

			default:
//				3) FAIL
//					memberForm으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "member/memberForm"; //logical viewName
				break;
			}
		}else {
//			검증불통
//			memberForm으로 이동 (기존 입력 데이터, 검증결과 메시지들, dispatch)
//				-> 메시지가 어떤 종류일지, 몇개일지 정해진게 없음
			viewName = "member/memberForm";
		}
		return viewName;
	}
}