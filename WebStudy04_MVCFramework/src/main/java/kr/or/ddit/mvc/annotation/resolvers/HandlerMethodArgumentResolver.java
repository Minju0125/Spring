package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 핸들러 어댑터가 핸들러 메소드를 호출(실행)할때, 메소드의 인자(argument) 를 전달해야함.
 * 인자(argument)하나를 처리하기 위한 전략 객체.
 */
public interface HandlerMethodArgumentResolver {
	/**
	 * 현재 파라미터의 조건들이 리졸버에 의해 처리될수 있는지 판단.
	 * @param parameter
	 * @return
	 */
	public boolean supportsParameter(Parameter parameter);
	/**
	 * 현재 파라미터의 조건에 부합하는 인자를 생성하기 위한 메소드.
	 * @param parameter
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException TODO
	 * @throws IOException TODO
	 */
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
