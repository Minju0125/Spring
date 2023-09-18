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

@WebServlet("/calculate/case6")

public class CalculateControllerServlet_case6 extends HttpServlet{
   /**
    * UI 제공
    */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String goPage = "/WEB-INF/views/calculate/case6/calForm.jsp";
      
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
      int sc = 200;
      String message = null;
      //deSerialize -> unMarshalling 
      try(
      InputStream is = req.getInputStream();
      ){
    	 ObjectMapper mapper = new ObjectMapper();
    	 CalculateVO calVO = mapper.readValue(is, CalculateVO.class);
    	 req.setAttribute("calVO", calVO); //정상 처리시 calVO 존재
      }catch (Exception e) {
    	 sc = 400;
    	 message = e.getMessage();
	  }
      
      if(sc!=200) {
    	  resp.sendError(sc, message);
    	  return;
      }
      
      String goPage = "/jsonView.view";
      
      if(goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      }else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
   }
}