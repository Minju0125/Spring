package kr.or.ddit.servlet06.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet06.dao.DataBasePropertyDAO;
import kr.or.ddit.servlet06.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

@WebServlet("/13/jdbcDesc.do")
public class DataBasePropertiesControllerServlet extends HttpServlet{
	private DataBasePropertyServiceImpl service = new DataBasePropertyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			List<DataBasePropertyVO> list = service.retrieveDBPropertyList();
			
			req.setAttribute("list", list);
			
			String goPage = "/WEB-INF/views/13/jdbcDesc.jsp";
			
			if(goPage.startsWith("redirect")) { 
				String location = req.getContextPath() + goPage.substring("redirect:".length());
				resp.sendRedirect(location);
				return;
			}else { //dispatch
				req.getRequestDispatcher(goPage).forward(req, resp);
			}
	}
}
