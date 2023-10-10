package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.filter.wrapper.DummyHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParameterTrickFilter implements Filter{@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpServletRequestWrapper wrapperReq = new DummyHttpServletRequestWrapper(req);
		
		chain.doFilter(wrapperReq, response); //성질이 변경된 req를 다음 filter에게 넘긴 후 컨트롤러로 전달
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
