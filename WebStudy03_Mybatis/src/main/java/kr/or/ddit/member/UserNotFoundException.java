package kr.or.ddit.member;

/**
 * 아이디 조회한 사용자가 존재하지 않는 경우
 * @author PC-13
 *
 */
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String memId) {
		super(String.format("%s 에 해당하는 사용자가 없음.", memId));
	}
}
