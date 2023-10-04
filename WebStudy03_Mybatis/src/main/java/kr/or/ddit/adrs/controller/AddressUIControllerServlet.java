package kr.or.ddit.adrs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/adrs/view") //UI 제공
public class AddressUIControllerServlet extends HttpServlet{

// 동기요청
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "adrs/adrsView";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
