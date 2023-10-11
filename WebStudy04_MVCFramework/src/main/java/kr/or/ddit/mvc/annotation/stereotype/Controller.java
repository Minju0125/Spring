package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
	//이 타입의 객체는 인스턴스 생성시, 어떤 대상에 어노테이션 사용하면 자동으로 객체 만들어짐

}
