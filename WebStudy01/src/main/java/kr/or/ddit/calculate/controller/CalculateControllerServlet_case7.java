package kr.or.ddit.calculate.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.calculate.NumericOperatorType;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate/case7")
public class CalculateControllerServlet_Case7 extends HttpServlet{
	/**
	 * UI 제공
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/calculate/case7/calForm.jsp";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	private CalculateVO getCalculateVOFromJson(HttpServletRequest req) throws Exception {
		try(
			InputStream is = req.getInputStream();
		){
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(is, CalculateVO.class);
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
	
	private CalculateVO getCalculateVOFromParameters(HttpServletRequest req) throws Exception {
		Map<String, String> errors = new LinkedHashMap<>();
		if(validate(req, errors)) {
			String leftParam = req.getParameter("leftOp");
			String rightParam = req.getParameter("rightOp");
			String opParam = req.getParameter("operator");
			
			int leftOp = Integer.parseInt(leftParam);
			int rightOp = Integer.parseInt(rightParam);
			NumericOperatorType operator = NumericOperatorType.valueOf(opParam);
			
			return new CalculateVO(leftOp, rightOp, operator);
		}else {
			throw new Exception(errors.toString());
		}
	}
	
	
	
	/**
	 * UI 를 통해 입력한 데이터(parameter) 처리
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String requestContentType = req.getContentType();
		
//		deSerialization -> unMarshalling
		int sc = 200;
		String message = null;
		CalculateVO calVO = null;
		try{
			if(requestContentType.contains("json")) {
				calVO = getCalculateVOFromJson(req);
			}else if(requestContentType.contains("xml")) {
				sc = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
			}else {
				calVO = getCalculateVOFromParameters(req);
			}
			
		}catch (Exception e) {
			sc = 400;
			message = e.getMessage();
		}
		
		if(sc!=200) {
			resp.sendError(sc, message);
			return;
		}
		
		req.setAttribute("calVO", calVO);
		
		String goPage = "/jsonView.view";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
























