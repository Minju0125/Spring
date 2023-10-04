package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서버안에 포함된 웹 리소스 ex) jsp, html을 통해 응답 내보내는 경우
public class InternalResourceViewResolver implements ViewResolver{
	 //상속받은 것중에, setPrefix, setSuffix 존재
	private String prefix;
	private String suffix;
	
	@Override
	public void setPrefix(String prefix) { //ViewResolver에 default로 만든 메소드
		this.prefix = prefix;
	}
		
	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp); //원래 else 안에 있던 코드 !
		//이 resolver는 viewResolverComposite이 사용할 수 있도록 관계설정
	}
	
}
