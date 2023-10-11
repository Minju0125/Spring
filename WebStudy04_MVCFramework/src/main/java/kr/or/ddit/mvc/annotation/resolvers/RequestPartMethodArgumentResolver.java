package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.file.MultipartFile;
import kr.or.ddit.filter.mvc.wrapper.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter.BadRequestException;

public class RequestPartMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		return annotation!=null && MultipartFile.class.isAssignableFrom(parameter.getType());
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		String name = annotation.value();
		boolean required = annotation.required();
		
		MultipartFile filePart = null;
		if(req instanceof StandardMultipartHttpServletRequest) {
			filePart = ((StandardMultipartHttpServletRequest) req).getFile(name);
		}else {
			throw new BadRequestException("현재 요청이 멀티파트 요청이 아님.");
		}
		
		if(required && filePart==null) {
			throw new BadRequestException(String.format("%s 멀티파트 데이터 전송되지 않았음.", name));
		}		
		
		return filePart;
	}

}















