package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeMethodProcessor;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestPartMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletRequestMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletResponseMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.SessionAttributeMethodArgumentResolver;

public class RequestMappingHandlerAdapter implements HandlerAdapter {
	
	private List<HandlerMethodArgumentResolver> argumentResolvers;
	{
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletRequestMethodArgumentResolver());
		argumentResolvers.add(new ServletResponseMethodArgumentResolver());
		argumentResolvers.add(new ModelAttributeMethodProcessor());
		argumentResolvers.add(new RequestParamMethodArgumentResolver());
		argumentResolvers.add(new SessionAttributeMethodArgumentResolver());
		argumentResolvers.add(new RequestPartMethodArgumentResolver());
	}

	private HandlerMethodArgumentResolver findHandlerMethodArgumentResolver(Parameter parameter) {
		HandlerMethodArgumentResolver finded = null;
		for(HandlerMethodArgumentResolver single : argumentResolvers) {
			if(single.supportsParameter(parameter)) {
				finded = single;
				break;
			}
		}
		return finded;
	}
	
	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object handler = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		String viewName;
		try {
			int pCount = handlerMethod.getParameterCount();
			if(pCount>0) {
				Parameter[] parameters = handlerMethod.getParameters();
				Object[] arguments = new Object[pCount];
				
				for(int idx=0; idx<pCount; idx++) {
					HandlerMethodArgumentResolver finded = findHandlerMethodArgumentResolver(parameters[idx]);
					if(finded==null) {
						throw new BadRequestException(
							String.format("%s 맞는 handle method arugment resolver를 찾지못했음.", parameters[idx])
						);
					}
					arguments[idx] = finded.resolveArgument(parameters[idx], req, resp);						
				}
				
				// 모든 핸들러 메소드의 시그니처가 동일한 파라미터와 동일한 리턴 타입을 갖는다.
				viewName = (String) handlerMethod.invoke(handler, arguments);
			}else {
				viewName = (String) handlerMethod.invoke(handler);
			}
			return viewName;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e);
		}
	}

	public static class BadRequestException extends RuntimeException{

		public BadRequestException(String message) {
			super(message);
		}
		
	}
}












