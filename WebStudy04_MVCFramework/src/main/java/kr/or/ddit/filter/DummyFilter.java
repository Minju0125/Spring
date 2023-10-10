package kr.or.ddit.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyFilter implements Filter{
   
   @Override
   public void init(FilterConfig arg0) throws ServletException {
      log.info("{} 필터 초기화", this.getClass().getSimpleName());
   }
   
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest)request;
      HttpServletResponse resp = (HttpServletResponse) response;
      log.info("{} 요청 발생, {}", req.getRequestURI(), LocalDateTime.now());
      chain.doFilter(request, response); // (경계선) 다음번 필터에게 제어권 넘김
      log.info("{} 응답 전송", resp.getContentType()); 
   }

   @Override
   public void destroy() {
      log.info("{} 필터 소멸", this.getClass().getSimpleName());
   }
}