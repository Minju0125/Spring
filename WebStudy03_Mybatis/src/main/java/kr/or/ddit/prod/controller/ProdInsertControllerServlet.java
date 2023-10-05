package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertControllerServlet extends HttpServlet {
	private OthersDAO othersDAO = new OthersDAOImpl();
	private ProdService prodService = new ProdServiceImpl();

	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addRequestAttribute(req);

		String viewName = "prod/prodForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addRequestAttribute(req);

		// 1. 디코딩 설정
		req.setCharacterEncoding("UTF-8");

		// 2. 파라미터 확보
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(prod, parameterMap);

		HashMap<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// 3. 검증
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class);
		String viewName = "null";

		if (valid) { // 검증통과
//			검증통과
//			4. createProd 등록 처리
			ServiceResult result = prodService.createProd(prod);
			switch (result) {
			case PKDUPLICATED:
//				1) PKDUPLICATED
//					prodForm으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "아이디 중복");
				viewName = "prod/prodForm";
				break;
			case OK:
//				2) OK(기존클라이언트의 요청 처리 완료)
//					welcome page로 이동 (redirect)
				viewName = "redirect:/";
				break;

			default:
//				3) FAIL
//					prodForm으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "prod/prodForm"; // logical viewName
				break;
			}
		} else {
//			검증불통
//			prodForm으로 이동 (기존 입력 데이터, 검증결과 메시지들, dispatch)
			viewName = "prod/prodForm";
		}
	}
}
