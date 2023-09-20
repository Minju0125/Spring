package kr.or.ddit.calculate.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.calculate.NumericOperatorType;

@WebServlet("/calculate/case2")
public class CalculateControllerServlet_Case2 extends HttpServlet{
	/**
	 * UI 제공
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/calculate/case2/calForm.jsp";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	private boolean validate(HttpServletRequest req, Map<String, String> errors) {
		boolean valid = true;
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		
		if(leftParam==null || leftParam.trim().isEmpty() || !leftParam.matches("\\d+")) {
			valid &= false;
			errors.put("leftOp", "좌측 피연산자 오류");
		}
		if(rightParam==null || rightParam.trim().isEmpty() || !rightParam.matches("\\d+")) {
			valid &= false;
			errors.put("rightOp", "우측 피연산자 오류");
		}
		if(opParam==null || opParam.trim().isEmpty()) {
			valid &= false;
			errors.put("operator", "연산자 누락");
		}else {
			try {
				NumericOperatorType.valueOf(opParam);
			}catch (IllegalArgumentException e) {
				valid &= false;
				errors.put("operator", "연산자 종류 오류");
			}
		}
		return valid;
	}
	
	/**
	 * UI 를 통해 입력한 데이터(parameter) 처리
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		
		
		String goPage = null;
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		if(validate(req, errors)) {
			int leftOp = Integer.parseInt(leftParam);
			int rightOp = Integer.parseInt(rightParam);
			NumericOperatorType operator = NumericOperatorType.valueOf(opParam);
			
			int result = operator.operate(leftOp, rightOp);
			
			String expression = operator.getExpression(leftOp, rightOp);
			
			req.setAttribute("expression", expression);
			goPage = "/WEB-INF/views/calculate/case2/calculateView.jsp";
		}else {
			goPage = "/WEB-INF/views/calculate/case2/messageView.jsp";
		}
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
























