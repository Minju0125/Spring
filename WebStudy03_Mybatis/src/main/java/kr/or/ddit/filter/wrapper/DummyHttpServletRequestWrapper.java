package kr.or.ddit.filter.wrapper;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class DummyHttpServletRequestWrapper extends HttpServletRequestWrapper{

	private String specificData = "SPECIFIC";
	private Map<String, Cookie> cookieMap;
	
	public DummyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request); //생성자 안에 원본 request가 들어와 있음
		cookieMap = new LinkedHashMap<>();
	    for(Cookie tmp : request.getCookies()) {
	    	cookieMap.put(tmp.getName(), tmp);
	    }
	}
	
	public Cookie getCookie(String cookieName) {
		return cookieMap.get(cookieName);
	}
	
	//성질을 변경할 때에는 오버라이딩
	@Override
	public String getParameter(String name) {
		if("who".equals(name)) {
			return "a001";
		}
		return super.getParameter(name);
	}
	
	public String getSpecificData() {
		return specificData;
	}
}
