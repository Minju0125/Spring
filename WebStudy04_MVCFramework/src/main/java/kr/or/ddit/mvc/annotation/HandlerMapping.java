package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

/**
 * 커맨드 핸들러에 대한 정보를 수집하고,
 * 수집된 정보를 기반으로
 * 현재 요청을 처리할 수 있는 핸들러를 검색해줌.
 *
 */
public interface HandlerMapping { 
	public RequestMappingInfo findCommandHandler (HttpServletRequest req);
}