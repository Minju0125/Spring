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

@WebServlet("/calculate/case4")

public class CalculateControllerServlet_case4 extends HttpServlet{
   /**
    * UI 제공
    */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String goPage = "/WEB-INF/views/calculate/case4/calForm.jsp";
      
      if(goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      }else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
   }
   
   /**
    * UI를 통해 입력한 데이터(parameter) 처리
    */
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      boolean valid = true;
      //deSerialize -> unMarshalling 
      try(
      InputStream is = req.getInputStream();
      ){
    	 ObjectMapper mapper = new ObjectMapper();
    	 CalculateVO calVO = mapper.readValue(is, CalculateVO.class);
    	 req.setAttribute("calVO", calVO);
      }catch (Exception e) {
    	 req.setAttribute("errors", e.getMessage());
    	 valid = false;
	  }
      
      String goPage = null;
      if(valid) {
         goPage = "/WEB-INF/views/calculate/case4/calculateView.jsp";
      }else {
         goPage = "/WEB-INF/views/calculate/case4/messageView.jsp";         
      }
      
      if(goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      }else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
   }
}