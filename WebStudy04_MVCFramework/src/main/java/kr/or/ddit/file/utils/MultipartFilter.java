package kr.or.ddit.file.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * multipart request를 {@link StandartMultipartHttpServletRequest| 로 wrapping.
 *
 */
public class MultipartFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType = req.getContentType();
		if(contentType!=null && contentType.startsWith("multipart/")) {
			//multipart 요청
			StandartMultipartHttpServletRequest wrapperReq = new StandartMultipartHttpServletRequest(req);
			chain.doFilter(wrapperReq, response);
		}else {
			//일반 요청
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
