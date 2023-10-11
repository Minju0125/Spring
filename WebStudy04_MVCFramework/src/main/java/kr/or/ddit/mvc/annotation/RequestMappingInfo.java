package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

import lombok.Getter;

/**
 * @Controller 와 @RequestMapping 어노테이션으로 표현된 커맨드 핸들러 하나에 대한 정보를 캡슐화
 * 
 */
@Getter
public class RequestMappingInfo {
	private RequestMappingCondition mappingCondition;
	private Object commandHandler; // ex) IndexController instance
	private Method handlerMethod; // ex) IndexController's index
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}
	
	@Override
	public String toString() {
		return String.format("handler : %s, method : %s(%s)"
						, commandHandler.getClass().getName()
						, handlerMethod.getName()
						, Arrays.toString(handlerMethod.getParameters()));
	}
}