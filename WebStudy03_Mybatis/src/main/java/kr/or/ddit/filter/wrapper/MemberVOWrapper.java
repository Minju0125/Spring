package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import kr.or.ddit.vo.MemberVO;

public class MemberVOWrapper implements Principal{
	private MemberVO adaptee; //MemberVOWrapper 가 MemberVO를 가지고 있는 구조

	public MemberVOWrapper(MemberVO adaptee) { //기본생성자는 없고 유일한 생성자로 이거만.
		super();
		this.adaptee = adaptee;
	}

	@Override
	public String getName() {
		return adaptee.getMemId(); //한사람의 인증객체를 식별할 수 있는 식별자로 id
	}
	
	public MemberVO getRealUser() {
		return adaptee;
	}
}
