package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter.BadRequestException;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Front Controller Patter 구조의 front controller command handler에 대해 사전 작업과 사후
 * 작업을 처리할 수 있는 front
 * 
 */
@Slf4j
public class DispatcherServlet extends HttpServlet {

   private ViewResolver viewResolver;
   private HandlerMapping handlerMapping;
   private HandlerAdapter handlerAdapter;

   @Override
   public void init(ServletConfig config) throws ServletException {

      super.init(config);
      viewResolver = new ViewResolverComposite();
      String prefix = config.getInitParameter("prefix");
      String suffix = config.getInitParameter("suffix");
      
      if(viewResolver instanceof ViewResolverComposite) {
         ((ViewResolverComposite) viewResolver).addViewResolver(new BeanNameViewResolver());
         ((ViewResolverComposite) viewResolver).addViewResolver(new TilesViewResolver());
         InternalResourceViewResolver resolver  =new InternalResourceViewResolver(); //single jsp
         //InternalResourceViewResolver은 우선순위 마지막!!!
         
         resolver.setPrefix(prefix);
         resolver.setSuffix(suffix);
         ((ViewResolverComposite) viewResolver).addViewResolver(resolver);
      
      }


//      viewResolvers.add(resolver);

      handlerMapping = new RequestMappingHandlerMapping("kr.or.ddit");
      handlerAdapter = new RequestMappingHandlerAdapter();      
   }

   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req);

      log.info("검색된 핸들러 정보:{}", mappingInfo);

      String viewName = null;
      if (mappingInfo != null) {
         try {
         // 핸들러를 찾은 경우
         viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp);
         }catch (BadRequestException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
         }
      } else {
         // 요청을 처리할 커맨드 핸들러가 없는 경우 
         resp.sendError(404, "처리할 수 없는 요청이다");
         return;
      }


      if (viewName != null) {
         viewResolver.resolveView(viewName, req, resp);

      } else {
         // 응답데이터가 결정이 되었는가?
         if (!resp.isCommitted()) {
            resp.sendError(500, "logical view name 이 결정되지 않았음");
         }

      }

   }
}