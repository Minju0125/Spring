package kr.or.ddit.calculate.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate/case1")

public class CalculateControllerServlet_case1 extends HttpServlet{
   /**
    * UI 제공
    */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String goPage = "/WEB-INF/views/calculate/case1/calForm.jsp";
      
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
      if(opParam==null || opParam.trim().isEmpty() || !opParam.matches("PLUS|MINUS|MULTIPLY|DIVIDE|MODULAR")) {
         valid &= false;
         errors.put("operator","연산자 오류");
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
         int result = -1;
         String expression = null;
         char sign = '/';
         switch (opParam) {
         case "PLUS":
            result = leftOp + rightOp;
            sign = '+';
            break;
         case "MINUS":
            result = leftOp - rightOp;
            sign = '-';
            break;
         case "MULTIPLY":
            result = leftOp * rightOp;            
            sign = '*';
            break;
         case "MODULAR":
        	 result = leftOp % rightOp;            
        	 sign = '%';
        	 break;
         default:
            result = leftOp / rightOp;
            break;
         }
         
         expression = String.format("%d %c %d = %d", leftOp, sign, rightOp, result);
         req.setAttribute("expression", expression);
         goPage = "/WEB-INF/views/calculate/case1/calculateView.jsp";
      }else {
         goPage = "/WEB-INF/views/calculate/case1/calForm.jsp";         
      }
      
      if(goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      }else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
   }
}