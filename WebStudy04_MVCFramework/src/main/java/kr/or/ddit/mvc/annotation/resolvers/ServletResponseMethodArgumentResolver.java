package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link HttpServletResponse} 타입의 인자를 생성함.
 *
 */
public class ServletResponseMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		return HttpServletResponse.class.equals(parameterType);
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return resp;
	}

}
