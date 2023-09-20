package kr.or.ddit.login.service;

import kr.or.ddit.vo.MemberVO2;

/**
 * 사용자 인증을 담당하는 Business Logic Layer
 *
 */
public interface AuthenticateService {
	
	public boolean authenticate(MemberVO2 inputData);
}
