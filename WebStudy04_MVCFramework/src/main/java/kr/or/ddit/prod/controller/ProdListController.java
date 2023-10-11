package kr.or.ddit.prod.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.paging.BootstrapPaginationRenderer;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdListController {

	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();

	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}

	@RequestMapping(value = "/prod/prodList.do", method = RequestMethod.GET)
	public String prodList(@ModelAttribute("detailCondition") ProdVO detailCondition, HttpServletRequest req,
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage) {
		addAttribute(req);

		listData(detailCondition, currentPage, req);

		return "prod/prodList";
	}

	@RequestMapping("/prod/ajax/prodListUI.do")
	public String prodListUI(HttpServletRequest req) {
		addAttribute(req);
		return "prod/prodListUI";
	}

	@RequestMapping("/prod/ajax/prodListData.do")
	public String listData(@ModelAttribute("prod") ProdVO detailCondition,
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			HttpServletRequest req) {
		PaginationInfo<ProdVO> paging = new PaginationInfo<>(3, 2);
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);

		service.retrieveProdList(paging);

		paging.setRenderer(new BootstrapPaginationRenderer());

		req.setAttribute("paging", paging);

		return "jsonView";
	}
}