package kr.or.ddit.prod.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdViewController{
	ProdService service = new ProdServiceImpl();
	
	@RequestMapping("/prod/prodView.do") 
	public String prodView(@RequestParam("what") String prodId, HttpServletRequest req){ //요청파라미터 중 what 이라는 이름이 있다면 prodId에 넣음  
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		return "prod/prodView";
	}
}