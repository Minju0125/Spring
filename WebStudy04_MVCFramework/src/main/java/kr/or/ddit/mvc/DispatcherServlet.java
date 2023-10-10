package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.controller.ProdListController;
import kr.or.ddit.prod.controller.ProdListController_Case2;
import kr.or.ddit.prod.controller.ProdListDataController;
import kr.or.ddit.prod.controller.ProdViewController;

/**
 * Front Controllwer Patten 구조의 front controller
 * command handler에 대해 사전작업과 사후작업을 처리할 수 있는 front
 *
 */
public class DispatcherServlet extends HttpServlet{
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      String uri = req.getRequestURI();
      uri = uri.substring(req.getContextPath().length());
      uri = uri.split(";")[0];
      
      String viewName;
      if("/prod/prodList.do".equals(uri)) {
         // 모든 요청은 앞단에서 받기때문에 do계열의 메소드를 사용하지 않음
         ProdListController controller =  new ProdListController();
         viewName = controller.prodList(req, resp);
      }else if("/prod/prodView.do".equals(uri)) {
    	 ProdViewController controller = new ProdViewController();
    	 viewName = controller.prodView(req, resp); 
    	 //정상 viewName 반환 또는 null 반환
      }else if("/prod/ajax/prodListUI.do".equals(uri)) {
    	  ProdListController_Case2 controller = new ProdListController_Case2();
    	  viewName = controller.prodListUI(req, resp);
      }else if("/prod/ajax/prodList.do".equals(uri)) {
    	  ProdListDataController controller = new ProdListDataController();
    	  viewName = controller.prodList(req, resp);
      }else {
         // 404에러 발생 -> 우리가 감당할수 있는 요청
         resp.sendError(404,"처리할 수 없는 요청임!");
         return;
      }
      //vieName null checking
      if(viewName!=null) {
    	  new ViewResolverComposite().resolveView(viewName, req, resp);
      }else {
    	  //true 반환되면 응답 결정
    	  if(!resp.isCommitted()) { 
    		  resp.sendError(500, "logical view name이 결정되지 않았음.");
    	  }
      }
   }
}