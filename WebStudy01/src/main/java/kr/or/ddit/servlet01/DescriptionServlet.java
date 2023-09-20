package kr.or.ddit.servlet01;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 스펙
 * 	: 웹(http)상에서 발생하는 요청을 처리(50)하고, 웹(http)을 통해 전송되는 응답을 생성(50)하기 위한 객체에 대한 요건 -> HttpServlet
 *	
 * 개발 단계
 * 1. HttpServlet 상속 구현
 * 2. 상속받은 callback 메소드를 재정의
 * 	 lifecycle callback method : init, destroy
 * 	 request callback method : doXXX
 * 3. 서블릿 등록 : container의 서블릿 관리 정책에 대한 설정이 가능.
 * 		web.xml(2.X) : servlet 엘리먼트
 * 		@WebServlet(3.X) 
 *      
 *      - loadOnStartUp : 서블릿의 인스턴스 생성 시점 제어.
 *      - initParam : 서블릿 초기화  시점에 전달되는 파라미터(name/value).
 * 4. 서블릿 매핑
 * 		web.xml(2.X) : serlvet-mapping 엘리먼트
 * 		@WebServlet("url-pattern")(3.X) 
 * 
 * Servlet Container : 서블릿의 생명주기 관리자. 싱글턴 기본 정책 활용.
 * Container ? : 내부에서 관리되는 (싱글턴)객체의 생명주기 제어자. 
 * 
 * ** 서블릿 스펙에서 자주 활용되는 객체
 *  1. ServletContext : 하나의 컨텍스트를 대상으로 싱글턴으로 운영되는 객체로 주로 서버의 정보를 활용할때 사용됨.
 * 	2. ServletConfig : 하나의 서블릿을 대상으로 1:1 운영되는 객체로 해당 서블릿에 대한 메타 정보를 가진 객체.
 *  3. HttpServletRequest : 하나의 요청을 대상으로, 해당 요청을 발생시킨 클라이언트와 요청에 대한 정보를 가진 객체.
 *  4. HttpServletResponse : 하나의 응답을 전송하기 위해 응답 컨텐츠와 메타 정보를 가진 객체.
 *  5. HttpSession : 한클라이언트가 하나의 에이전트를 대상으로 운영되는 객체로 한 세션에 대한 정보를 가진 객체. 
 */
public class DescriptionServlet extends HttpServlet{
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext application = getServletContext();
		
		System.out.printf(" application hash code : %d \n", application.hashCode());
		
		String value = config.getInitParameter("param_name");
		
		System.out.println(MessageFormat.format("{0} 서블릿 객체 생성 이후 초기화 완료. 전달된 파라미터 : {1}"
							, this.getClass().getName(), value));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String method = req.getMethod();
		
		System.out.println(MessageFormat.format("{0} 요청의 {1} 메소드 처리.", uri, method));
	}
	
	@Override
	public void destroy() {
		System.out.println(MessageFormat.format("{0} 서블릿 객체 소멸.", this.getClass().getName()));
	}
}
















