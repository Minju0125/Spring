package kr.or.ddit.servlet06.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet06.service.DataBasePropertyService;
import kr.or.ddit.servlet06.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

@WebServlet("/13/case2/jdbcDesc.do")
public class DataBasePropertiesControllerServlet_case2 extends HttpServlet{

	//1. 의존관계 형성
	private DataBasePropertyService service = new DataBasePropertyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//2. 사용자의 요청이 어떤 content를 요구하고 잇는지 식별(Accept 헤더 활용
		String accept = req.getHeader("Accept");
		String goPage = null;
		if(accept.contains("json")) { //1. json 인 경우
			//data 요청 (Model이 있어야함)
			List<DataBasePropertyVO> list = service.retrieveDBPropertyList();
			req.setAttribute("dataList", list);
			goPage = "/jsonView.view";
		}else { // 2. html인 경우
			//UI 요청
			goPage = "/WEB-INF/views/13/jdbcDescCase2.jsp";
		}		
		
		if(goPage.startsWith("redirect")) { 
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else { //dispatch
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}