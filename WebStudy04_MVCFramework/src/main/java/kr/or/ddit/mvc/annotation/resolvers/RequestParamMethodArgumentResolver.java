package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter.BadRequestException;

/**
 * @RequestParam 어노테이션을 통해 하나의 요청 파라미터를 이용해 핸들러 메소드 인자를 생성함.
 *
 */
public class RequestParamMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		RequestParam annotation = parameter.getAnnotation(RequestParam.class);
		Class<?> parameterType = parameter.getType();
		return annotation != null && (
					//기본형 타입의 파라미터에 사용 가능 	//wrapper 클래스인 경우에 사용가능 //String 타입의 파라미터 저리
					ClassUtils.isPrimitiveOrWrapper(parameterType) || String.class.equals(parameterType)
				);
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestParam annotation = parameter.getAnnotation(RequestParam.class);
		Class<?> parameterType = parameter.getType();
		
		String requestParameterName = annotation.value();
		boolean required = annotation.required();
		String defaultValue = annotation.defaultValue();
		
		String requestParameter = req.getParameter(requestParameterName);
		if(required && StringUtils.isBlank(requestParameter)) {
			throw new BadRequestException(String.format("%s 이름을 가진 필수 파라미터 누락", requestParameterName));
//			resp.sendError(400);
//			return null;
		}else if(StringUtils.isBlank(requestParameter)) {
			requestParameter = defaultValue;
		}
		Object parameterValue = null;
		if(int.class.equals(parameterType) || Integer.class.equals(parameterType)) {
			parameterValue = Integer.parseInt(requestParameter);
		}else if(boolean.class.equals(parameterType) || Boolean.class.equals(parameterType)) {
			parameterValue = Boolean.parseBoolean(requestParameter);
		}else if(String.class.equals(parameterType)) {
			parameterValue = requestParameter;
		}
		return parameterValue;
	}

}





















