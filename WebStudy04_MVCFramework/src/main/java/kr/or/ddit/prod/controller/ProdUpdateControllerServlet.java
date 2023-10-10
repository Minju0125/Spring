package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

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
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodUpdate.do")
@MultipartConfig
public class ProdUpdateControllerServlet extends HttpServlet{
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
	      
	      // 필수파라미터이므로 검증필요
	      String prodId = req.getParameter("what");
	      if(StringUtils.isBlank(prodId)) {
	         resp.sendError(400, "필수파라미터 누락");
	         return;
	      }
	      
	      ProdVO prod = service.retrieveProd(prodId);
	      
	      req.setAttribute("prod", prod);
	      
	      String viewName = "prod/prodEdit";
	      new ViewResolverComposite().resolveView(viewName, req, resp);
	   }

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addRequestAttribute(req);
		// 파라미터 확보
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();

		// 캡슐화
		PopulateUtils.populate(prod, parameterMap); //img 제외한 나머지 property 들어있음 !
		
		// 래퍼클래스인지 판별후 part 데이터 처리
		if(req instanceof StandartMultipartHttpServletRequest) { //업로드 된 이미지 있을 때
			//여기안으로 들어온건 multipart 요청이 온것
			MultipartFile prodImage = ((StandartMultipartHttpServletRequest) req).getFile("prodImage");
			
			//검증 : 비어있는지 판단
			if(!prodImage.isEmpty()) {
				String realPath = req.getServletContext().getRealPath(prodImagesUrl);
				File saveFolder = new File(realPath); //저장장소 결정
				//원본 파일의 이름을 그대로 저장x, 확장자를 그대로 저장 x
				String filename = UUID.randomUUID().toString(); //저장되는 이름은 랜덤
				
				//저장할 파일 이름 결정
				//String filename = prodImage.getOriginalFilename();
				File saveFile = new File(saveFolder, filename);
				//상품이미지의 이진데이터 저장
				prodImage.transferTo(saveFile); //저장할 파일을 넘김 
				prod.setProdImg(filename); //(메타데이터 저장)
			}else {
				String realPath = req.getServletContext().getRealPath(prodImagesUrl);
				File saveFolder = new File(realPath); //저장장소 결정
				String filename = prod.getProdImg();
				
				File saveFile = new File(saveFolder, filename);
				System.out.println(filename);
				//상품이미지의 이진데이터 저장
				prodImage.transferTo(saveFile); //저장할 파일을 넘김
				prod.setProdImg(filename);
			}
		}
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = ValidationUtils.validate(prod, errors, UpdateGroup.class);
		String viewName = "null"; // 하나의 definition을 선택
		
		if(valid) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case OK:
//				2) OK(기존클라이언트의 요청 처리 완료)
//					mypage로 이동 (redirect)
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default:
//				3) FAIL
//					prodEdit으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "prod/prodEdit";
				break;
			}
		}else {
			viewName = "prod/prodEdit";
		}

		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
