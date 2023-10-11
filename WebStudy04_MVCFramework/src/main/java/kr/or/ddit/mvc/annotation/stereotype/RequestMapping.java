package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import kr.or.ddit.mvc.annotation.RequestMethod;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	String value(); //attribute 선언 시, 이와 같이 선언함! (필수속성)
					//특정 커맨드 핸들러 메소드가 처리할 수 있는 요청의 URI
	
	RequestMethod method() default RequestMethod.GET; //annotation 에서 attribute의 기본값 설정 시에는 default
													  //특정 커맨드 핸들러 메소드가 처리할 수 있는 요청의 method
}
