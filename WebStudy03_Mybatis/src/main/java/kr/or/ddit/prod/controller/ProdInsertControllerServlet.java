package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandartMultipartHttpServletRequest;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.ProdVO;
@WebServlet("/prod/prodInsert.do")
@MultipartConfig
public class ProdInsertControllerServlet extends HttpServlet{
	private String prodImagesUrl = "/resources/prodImages";
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
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
		
//		2. 파라미터 확보 --> ProdVO
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(prod, parameterMap);
		
		//multipart 처리
		//이미 여기까지온거는 wrapper 
		if(req instanceof StandartMultipartHttpServletRequest) { //업로드 된 이미지 있을 때
			//여기안으로 들어온건 multipart 요청이 온것
			MultipartFile prodImage = ((StandartMultipartHttpServletRequest) req).getFile("prodImage");
			
			//검증 : 비어있는지 판단
			if(!prodImage.isEmpty()) {
				String realPath = req.getServletContext().getRealPath(prodImagesUrl);
				File saveFolder = new File(realPath); //저장장소 결정
				
				String filename = UUID.randomUUID().toString(); //저장되는 이름은 랜덤
				
				//저장할 파일 이름 결정
				//String filename = prodImage.getOriginalFilename();
				File saveFile = new File(saveFolder, filename);
				//상품이미지의 이진데이터 저장
				prodImage.transferTo(saveFile); //저장할 파일을 넘김
				prod.setProdImg(filename);
			}
		}
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
//		3. 검증 (대상 : ProdVO)
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class);
		
		String viewName = null;
		if(valid) {
//			통과
//				4. createProd 등록 처리
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
//					2) OK 
//						상품 상세 페이지로 이동 (redirect)
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default:
//					3) FAIL
//						prodForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "prod/prodForm";
				break;
			}
		}else {
//			불통
//				prodForm 으로 이동 (기존 입력 데이터, 검증 결과 메시지들.., dispatch)
			viewName = "prod/prodForm";
		}
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
