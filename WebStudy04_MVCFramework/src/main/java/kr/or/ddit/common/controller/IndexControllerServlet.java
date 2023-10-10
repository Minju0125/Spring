package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/index.do")
public class IndexControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.doGet(req, resp); //이 코드 사용하면 상태코드 405 오류
		//응답이 나가려면 view 필요
		
		//model 만들고 넘기기
		String title = "컨트롤러에서 만든 Model 타이틀";
		
		req.setAttribute("title", title);
		
		//view 를 선택하고, 이동
		String viewName = "index"; //서버에서 이동하는 위임방식
		new ViewResolverComposite().resolveView(viewName, req, resp);
	
	}
}
