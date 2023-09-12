package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/05/gugudan.do")
public class GugudanServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int minDan = 2;
		int maxDan = 9;
		String minParam = req.getParameter("minDan");
		String maxParam = req.getParameter("maxDan");
		boolean valid = true;
		if(minParam!=null && minParam.matches("[2-9]")){
			minDan = Integer.parseInt(minParam);
		}else if(minParam!=null && !minParam.matches("[2-9]")){
			// 검증 실패
			valid = false;
		}
		if(minParam!=null && minParam.matches("[2-9]")){
			maxDan = Integer.parseInt(maxParam);
		}else if(maxParam!=null && !maxParam.matches("[2-9]")){
			// 검증 실패
			valid = false;
		}
		if(!valid){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"파라미터에 문제 있어 검증 실패");
			return;
		}
		
		req.setAttribute("minDan", minDan);
		req.setAttribute("maxDan", maxDan);
		
		String viewName = "/WEB-INF/views/05/gugudan.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
