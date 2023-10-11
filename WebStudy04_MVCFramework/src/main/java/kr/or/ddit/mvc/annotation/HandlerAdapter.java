package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * HandlerMapping 으로 검색한 커맨드 핸들러를 직접 실행하는 전략 객체.
 * 
 */
public interface HandlerAdapter {
	/**
	 * 커맨드 핸들러 실행
	 * @param mappingInfo 실행한 커맨드 핸들러에 대한 정보를 가진 객체
	 * @param req 요청에 대한 정보
	 * @param resp
	 * @return
	 */
	public String invokeHandler(
			RequestMappingInfo mappingInfo, 
			HttpServletRequest req, 
			HttpServletResponse resp
		)throws ServletException, IOException;
}
