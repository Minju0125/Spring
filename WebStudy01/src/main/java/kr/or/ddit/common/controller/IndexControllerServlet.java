package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.do")
public class IndexControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = "컨트롤러에서 만든 Model 타이틀";
		
		req.setAttribute("title", title);
		
		String viewName = "/WEB-INF/views/index.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
