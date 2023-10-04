package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.paging.BootstrapPaginationRenderer;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet{
	
	ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//뷰에서 page
		String pageParam = req.getParameter("page");
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		log.info("currentPage : {}", currentPage);
		
		PaginationInfo<ProdVO> paging = new PaginationInfo<>();
		paging.setCurrentPage(currentPage);
		
		service.retrieveProdList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		req.setAttribute("paging", paging);
		
		String viewName = "prod/prodList";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
