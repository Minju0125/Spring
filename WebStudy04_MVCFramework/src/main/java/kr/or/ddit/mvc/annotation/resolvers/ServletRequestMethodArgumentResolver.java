package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link HttpServletRequest}, {@link HttpSession}, {@link Principal} 타입의 인자를 생성함.
 *
 */
public class ServletRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		return HttpServletRequest.class.equals(parameterType) 
				|| HttpSession.class.equals(parameterType)
				|| Principal.class.isAssignableFrom(parameterType);
	}

	@Override //argument 하나하나를 처리하는 메소드
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object argument = null;
		if(HttpServletRequest.class.equals(parameterType)) {
			argument = req;
		}else if(HttpSession.class.equals(parameterType)){
			argument = req.getSession();
		}else {
			argument = req.getUserPrincipal();
		}
		return argument;
	}

}