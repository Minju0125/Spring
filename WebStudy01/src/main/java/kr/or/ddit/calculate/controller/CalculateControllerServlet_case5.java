package kr.or.ddit.calculate.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate/case5")

public class CalculateControllerServlet_case5 extends HttpServlet{
   /**
    * UI 제공
    */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String goPage = "/WEB-INF/views/calculate/case5/calForm.jsp";
      
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
      String leftParam = req.getParameter("leftOp");
      String rightParam = req.getParameter("rightOp");
      String opParam = req.getParameter("operator");
      
      String goPage = null;
      Map<String, String> errors = new LinkedHashMap<String, String>();
      req.setAttribute("errors", errors);
      if(validate(req, errors)) {
         int leftOp = Integer.parseInt(leftParam);
         int rightOp = Integer.parseInt(rightParam);
         
         //PLUS 라는 상수의 이름을 넘기면, 이 name을 기준으로 해당 상수를 검색해서 return
         NumericOperatorType operator = NumericOperatorType.valueOf(opParam); 
         
         CalculateVO calVO = new CalculateVO(leftOp, rightOp, operator);
         req.setAttribute("calVO", calVO); //이걸 json 으로 마샬링할 수 있는 구조가 필요함 (object mapper)
         //calVO 로 이동
         goPage = "/jsonView.view";
      }else {
    	  //비정상상황 (errors 라는 맵을 가지고 감)
         goPage = "/jsonView.view";         
      }
      
      if(goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      }else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
   }
}