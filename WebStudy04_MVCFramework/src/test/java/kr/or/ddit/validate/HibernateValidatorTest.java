package kr.or.ddit.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

public class HibernateValidatorTest {
	
	private static Validator validator;

	@BeforeAll
	static void beforeClass() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();//factory 생성
		validator = factory.getValidator();//검증할 수 있는 validator 받아오기
	}
	
	@Test
	void test() {
		//목적 : validator 통해서 domainVO 검증
		MemberVO member = new MemberVO();
//		member.setMemId("a001");
//		member.setMemPass("asdf");
//		member.setMemMail("aaa@baer.com");
//		member.setMemName("김은대");
//		member.setMemZip("12345");
//		member.setMemAdd1("대전");
//		member.setMemAdd2("오류");
		member.setMemRegno1("1");
		member.setMemRegno2("2");
		Set<ConstraintViolation<MemberVO>> constraintViolations  = validator.validate(member, InsertGroup.class); //ConstraintViolation : 한개의 검증결과
		if(constraintViolations.isEmpty()) {
			System.out.println("검증 통과");
		}else {
			Map<String, List<String>> errors = new HashMap<>(); 
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
			errors.forEach((k,v)->{
				System.err.printf("%s : %s\n", k, v);
			});
		}
	}
}