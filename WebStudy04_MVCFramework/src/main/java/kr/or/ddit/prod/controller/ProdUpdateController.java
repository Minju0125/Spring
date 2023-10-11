package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandartMultipartHttpServletRequest;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

//@WebServlet("/prod/prodUpdate.do")
//@MultipartConfig
@Slf4j
@Controller
public class ProdUpdateController{
	private String prodImagesUrl = "/resources/prodImages";
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@RequestMapping("/prod/prodUpdate.do")
	   public String selectProd(HttpServletRequest req, @RequestParam(value="what", required = true) String prodId ){ //prodId를 통해 요청 파라미터 한건 받음
	      addRequestAttribute(req);
	      
	      ProdVO prod = service.retrieveProd(prodId);
	      
	      req.setAttribute("prod", prod);
	      
	      return "prod/prodEdit";
	   }

	@RequestMapping(value="/prod/prodUpdate.do", method = RequestMethod.POST)
	public String doPost(@ModelAttribute("prod") ProdVO prod, HttpServletRequest req) throws IOException {
		addRequestAttribute(req);
		
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
				String filename = service.retrieveProd(prod.getProdId()).getProdImg();
				
				log.info("realPath : {}", realPath);
				log.info("saveFolder : {}", saveFolder);
				log.info("prod : {}", prod);
				
				File saveFile = new File(saveFolder, filename);
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
		return viewName;
	}
}
