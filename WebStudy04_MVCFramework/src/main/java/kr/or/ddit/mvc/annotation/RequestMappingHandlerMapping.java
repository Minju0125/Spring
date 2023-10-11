package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestMappingHandlerMapping implements HandlerMapping {
	
	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	
	public RequestMappingHandlerMapping(String...basePackages){
		handlerMap = handlerAnnotationTracing(basePackages);
	}
	
	//Map 만드는 메소드
	private Map<RequestMappingCondition, RequestMappingInfo> handlerAnnotationTracing(String...basePackages) {
		Map<RequestMappingCondition, RequestMappingInfo>  handlerMap = new LinkedHashMap<>();
		Map<Class<?>, Controller> handlerClasses = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		handlerClasses.keySet().stream().forEach((handlerClz)->{
			Object handler;
			try {
				handler = handlerClz.newInstance();
				Map<Method, RequestMapping> handlerMethodsMap = 
						ReflectionUtils.getMethodsWithAnnotationAtClass(handlerClz, 
								RequestMapping.class, 
								String.class);
				
				handlerMethodsMap.entrySet().stream().forEach((entry)->{
					Method handlerMethod = entry.getKey();
					RequestMapping annotation = entry.getValue();
					RequestMappingCondition mappingCondition = new RequestMappingCondition(annotation.value(), annotation.method());
					RequestMappingInfo mappingInfo = new RequestMappingInfo(mappingCondition, handler, handlerMethod);
					
					handlerMap.put(mappingCondition, mappingInfo);
					
					log.info("{} : {}", mappingCondition, mappingInfo);
				}); // forEach end
				
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			} // try end
		}); // forEach end
		
		return handlerMap;
	}

	@Override
	public RequestMappingInfo findCommandHandler(HttpServletRequest req) {
		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath()).split(";")[0];
		RequestMethod method = RequestMethod.valueOf(req.getMethod().toUpperCase()); 
		RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, method);
		RequestMappingInfo mappingInfo = handlerMap.get(mappingCondition);
		if(mappingInfo==null) {
			uri = req.getServletPath();
			mappingCondition = new RequestMappingCondition(uri, method);
			mappingInfo = handlerMap.get(mappingCondition);
		}
		log.info("현재 요청 : {}, \n 요청 처리 핸들러 : {}", mappingCondition, mappingInfo);
		return mappingInfo;
	}
}