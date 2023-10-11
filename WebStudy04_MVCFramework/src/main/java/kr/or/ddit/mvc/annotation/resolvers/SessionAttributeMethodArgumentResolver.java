package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter.BadRequestException;

public class SessionAttributeMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		SessionAttribute annotation = parameter.getAnnotation(SessionAttribute.class);
		return annotation!=null;
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SessionAttribute annotation = parameter.getAnnotation(SessionAttribute.class);
		String attrName = annotation.value();
		boolean required = annotation.required();
		Object attrValue = req.getSession().getAttribute(attrName);
		if(required && attrValue==null) {
			throw new BadRequestException(String.format("%s 속성이 세션 스코프에 없음.", attrName));
		}
		return attrValue;
	}

}






















