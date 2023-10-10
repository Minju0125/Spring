package kr.or.ddit.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationUtils {
	private static Validator validator;

	static { //클래스 로딩타임에 한번만 실행
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();//factory 생성
		validator = factory.getValidator();//검증할 수 있는 validator 받아오기
	}
	
	//generic tpye의 T -> 특정도메인에 종속되지 않음								... -> 가변파라미터
	public static <T> boolean validate(T target, Map<String, List<String>> errors, Class<?>...groupHints) {
		//검증 통과 or 불통
		Set<ConstraintViolation<T>> constraintViolations  = validator.validate(target, groupHints); //ConstraintViolation : 한개의 검증결과
		if(!constraintViolations.isEmpty()) {
			constraintViolations.stream()
					.forEach((cv)->{
						String propName = cv.getPropertyPath().toString();
						String message = cv.getMessage();
						//이전 반복에 나에대한 메시지가 들어있는지
						List<String> already = errors.get(propName);
						if(already==null) {
							already = new ArrayList<String>();
							errors.put(propName, already);
						}
						already.add(message);
					});
		}
		return constraintViolations.isEmpty(); //비어있으면 통과
	}
}
