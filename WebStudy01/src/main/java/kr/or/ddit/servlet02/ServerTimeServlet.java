package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/serverNow.do")
public class ServerTimeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("now", new Date());
		req.setAttribute("title", "컨트롤러에서 만들어준 제목데이터");
		
		String viewName = "/WEB-INF/views/03/serverNow.c35";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
