package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CharacterEncodingFilter implements Filter{
	
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 필터 초기화", this.getClass().getSimpleName());
		encoding = filterConfig.getInitParameter("encoding"); //외부에서 encoding 이라는 파라미터 받아옴
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		req.setCharacterEncoding(encoding);
		log.info("{} 요청 필터링", req.getRequestURI());
		chain.doFilter(request, response); // 이 코드가 없다면, 제어가 여기서 멈춤 -> 필터에서는 항상 이 코드가 존재해야함 !
		log.info("{} 응답 필터링", resp.getContentType());
	}

	@Override
	public void destroy() {
		log.info("{} 필터 소멸", this.getClass().getSimpleName());
	}
	
}
