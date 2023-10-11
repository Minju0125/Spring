package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.PopulateUtils;

public class ModelAttributeMethodProcessor implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		return annotation!=null;
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		String attrName = annotation.value();
		Class<?> commandObjectType = parameter.getType();
		try {
			Object commandObject = commandObjectType.newInstance();
			req.setAttribute(attrName, commandObject);
			
			PopulateUtils.populate(commandObject, req.getParameterMap());
			
			return commandObject;
		} catch (Exception e) {
			throw new ServletException(e);
		} 
	}

}













