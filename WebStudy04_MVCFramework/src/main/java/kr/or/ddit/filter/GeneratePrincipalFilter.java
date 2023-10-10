package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.filter.wrapper.PrincipalHttpServletRequestWrapper;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인된(sessionScope 에 authId 가 있으면) 사용자가 요청을 발생시킨 경우, 
 * Principal 객체를 생성하고, 현재 요청을 변경하여 principal 을 setting 하려함. (Adapter[Wrapper] requset).
 *
 */
@Slf4j
public class GeneratePrincipalFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 필터 초기화", this.getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		MemberVO authId = (MemberVO)req.getSession().getAttribute("authMember");
		if(authId==null) { //로그인 안한 경우
			chain.doFilter(request, response);
		}else { //로그인 한 경우
			chain.doFilter(new PrincipalHttpServletRequestWrapper(req), response);
		}
	}

	@Override
	public void destroy() {
		log.info("{} 필터 소멸", this.getClass().getSimpleName());
	}

}
