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

import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate/case7")

public class CalculateControllerServlet_case7 extends HttpServlet{
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
   
   //파라미터, json payload 전송이 됐든, calVO 로 만들어줘야함 (request content 의 형태에 따라) => 공통점은 둘다 reuqest 안에 들어있음
   private CalculateVO getCalculateVOFromJson(HttpServletRequest req) throws Exception{
	   try(
			 InputStream is = req.getInputStream();
       ){
	    	 ObjectMapper mapper = new ObjectMapper();
	    	 return mapper.readValue(is, CalculateVO.class);
       }
   }
   private CalculateVO getCalculateVOFromParameters(HttpServletRequest req) throws Exception {
	   Map<String, String> errors = new LinkedHashMap<>();
	   
	   //문자열 -> 검증 -> parsing (case3)
	   if(validate(req, errors)) {
		   //검증 통과 (return 값 존재함)
		   String leftParam = req.getParameter("leftOp");
		   String rightParam = req.getParameter("rightOp");
		   String opParam = req.getParameter("operator");
		   
		   int leftOp = Integer.parseInt(leftParam);
	       int rightOp = Integer.parseInt(rightParam);
	       NumericOperatorType operator = NumericOperatorType.valueOf(opParam); 
		   
		   return new CalculateVO(leftOp, rightOp, operator);
	   }else {
		   //검증 통과 -> VO 만들어지면 x (반환시킬 객체 x)
		   //문제가 생겼다는 것을 호출자에게 전달해야함
		  throw new Exception(errors.toString());//예외의 객체를 생성해서 던지겠다.
	   }
   }
   
   private boolean validate(HttpServletRequest req, Map<String, String> errors) {
	      boolean valid = true;
	      String leftParam = req.getParameter("leftOp");
	      String rightParam = req.getParameter("rightOp");
	      String opParam = req.getParameter("operator");
	      
	      if(leftParam==null || leftParam.trim().isEmpty() || !leftParam.matches("\\d+")) {
	         valid &= false;
	         errors.put("leftOp","좌측 피연산자 오류");
	      }
	      if(rightParam==null || rightParam.trim().isEmpty() || !rightParam.matches("\\d+")) {
	         valid &= false;
	         errors.put("rightOp","우측 피연산자 오류");
	      }
	      if(opParam==null || opParam.trim().isEmpty()) {
	         valid &= false;
	         errors.put("operator","연산자 누락");
	      }else {
	    	 try {//예외로 던져서 처리하기 때문에, 받아서 handling
	    		 NumericOperatorType.valueOf(opParam); 
	    	 }catch (IllegalArgumentException e) {
	    		 valid &= false;
	             errors.put("operator","연산자 종류 오류");
			}
	      }
	      return valid;
   }
   
   /**
    * UI를 통해 입력한 데이터(parameter) 처리
    */
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      
      String requestContentType = req.getContentType();
      
      //deSerialize -> unMarshalling 
      int sc = 200;
      String message = null;
      CalculateVO calVO = null;
      try{
		  if(requestContentType.contains("json")) {
			  calVO = getCalculateVOFromJson(req);
		  }else if(requestContentType.contains("xml")) {
			  sc = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE; // 415
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
      req.setAttribute("calVO", calVO); //정상 처리시 calVO 존재
      
      String goPage = "/jsonView.view";
      
      if(goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      }else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
   }
}