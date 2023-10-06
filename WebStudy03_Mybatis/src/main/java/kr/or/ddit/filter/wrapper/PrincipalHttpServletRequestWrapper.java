package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import kr.or.ddit.vo.MemberVO;

public class PrincipalHttpServletRequestWrapper extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	
	public PrincipalHttpServletRequestWrapper(HttpServletRequest request) { //원본 요청이면서 adaptee
		super(request);
		this.request=request;
	}
	
	@Override
	public Principal getUserPrincipal() { 
		//request.getSession(); 이렇게 해도 되고,
		MemberVO authMember = (MemberVO)getSession().getAttribute("authMember");
		if(authMember!=null) {
			MemberVOWrapper principal = new MemberVOWrapper(authMember);
			return principal;
		}else {
			return super.getUserPrincipal(); //원래 super 클래스에서 하던 일 그대로.
		}
	}
}
