package kr.or.ddit.lang;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOError;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * 예외
 * 		: 프로그램의 정상 실행이 중단될 수 있는 모든 예외적인 상황이나 조건에 대한 표현.
 * 	java.lang.Throwable : throw/catch 가능한 모든 예외의 최상위
 *  java.lang.Error : 시스템 실패 상황으로, 개발자가 직접 처리할 수 없는 에러.
 *  java.lang.Exception : 어떤 상황을 표현하기 위해 의도적으로 throw 되는 예외의 통칭. 필요한 경우, catch 로 처리 가능.
 *  	- checked exception (non RuntimeException) : 발생가능한 코드 불럭 내에서 어떤 형태로 해당 예외를 처리해야 하고,
 *  												 처리하지 않으면, 컴파일 에러가 발생함.
 *  	- unChecked exception : RuntimeException의 하위 예외들로, 주로 JVM이 발생시키고,
 *  							throws 키워드가 없어도 기본으로 thorws 되고 있는 예외들.
 *  							직접 catch 하지 않으면, 호출자에게 전달되고, 최종적으로 JVM 에게 전달됨.
 * 예외 처리 방법
 * 	- 예외 회피 (throws) : 메소드 콜스택에 저장된 복귀 주소에 따라 예외의 처리를 호출자에게 떠넘기는 전략.
 *  - 직접 처리 : try(Closable 객체 생성)-catch(exceptions)-finally
 * try{
 * 		//잠재적 예외 발생 코드
 * }catch(처리할 예외를 선언){
 * 		//직접 처리
 * 		- 예외 전환 : 발생한 예외가 너무 포괄적인 상황을 표현하고 있을 때, 구체적인 상황을 명시할 수 있는 예외
 * 					혹은, checked 예외를 unckecked 예외로 변경 - wrapping(원본 예외)
 * 				ex) 
 * 				catch(IOException e){
 * 					throw new FileCopyFailureException(e);				
 * 				}
 * 		- 예외 복구 : 예외발생 시 일정 시간 지연 후 , 해당 작업 재실행
 * }finally{
 * 		//예외 발생 여부와 무관하게 실행되는 코드
 * }
 * 
 * 커스텀 예외 정의 방법
 * 1. 예외 클래스 선언 시 정의 예외의 종류에 따라 상위 타입을 선택함
 * 		checked - Exception 상속
 * 		unChecked - RuntimeException	
 * 2. throw new 커스텀예외 객체 생성
 * 
 */
class ExceptionDesc {
	
	static class CustomException extends RuntimeException{

		public CustomException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
			// TODO Auto-generated constructor stub
		}

		public CustomException(String message, Throwable cause) {
			super(message, cause);
			// TODO Auto-generated constructor stub
		}

		public CustomException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}

		public CustomException(Throwable cause) {
			super(cause);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	int checkedExceptionGenerate() throws IOException{
		if(1==1) {
			throw new IOException();
		}
		return 10;
	}
	int unCheckedExceptionGenerate(){
		try {
			return 10 + checkedExceptionGenerate(); 	
		}catch(IOException e) {
			throw new CustomException(e);
		}
	}
	@Test								
	void test() throws RuntimeException{ 
		int result = unCheckedExceptionGenerate();
		System.out.println(result);

	}

}
