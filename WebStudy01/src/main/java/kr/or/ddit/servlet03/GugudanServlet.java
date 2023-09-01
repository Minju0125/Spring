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
		String minDanStr = req.getParameter("minDan");
		String maxDanStr =req.getParameter("maxDan");
		boolean valid=true;
		
		//minDan								[2~9까지 한글자]
		if(minDanStr!=null && minDanStr.matches("[2-9]")){
			minDan = Integer.parseInt(minDanStr);
		}else if(minDanStr!=null && !minDanStr.matches("[2-9]")){
			//파라미터로 넘어온 값이 있지만, 숫자가 아닌 경우 (검증실패)
			valid=false;
		} //파라미터로 넘어온 값이 없는 경우에는 기본 값 2를 사용함
		
		//maxDan	
		if(maxDanStr!=null && maxDanStr.matches("[2-9]")){
			maxDan = Integer.parseInt(maxDanStr);
		}else if(maxDanStr!=null && !maxDanStr.matches("[2-9]")){
			//파라미터로 넘어온 값이 있지만, 숫자가 아닌 경우 (검증실패)
			valid=false;
		} 
		
		if(!valid){
			//상태코드 변경 => 응답데이터 변경
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "파라미터에 문제 있어 검증 실패");
			return; //request callback 메소드 종료 
		}
		
		req.setAttribute("minDan", minDan);
		req.setAttribute("maxDan", maxDan);
		
		String viewName = "/WEB-INF/views/05/gugudan.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
