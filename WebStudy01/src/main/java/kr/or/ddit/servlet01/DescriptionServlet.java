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
 * : 웹(http) 상에서 발생하는 요청(50)을 처리하고, 웹(http)을 통해 전송되는 응답을 생성(50)하기 위한 객체에 대한 요건-> HttpServlet
 * (웹에서 요청 발생할 때에는 http 프로토콜을 통해)
 * 
 * 개발 단계
 * 1. HttpServelt 상속 구현
 * 2. 상속받은 callback 메소드 재정의 
 *		lifecycle callback method : init, destroy
 * 		request callback method : doXXX
 * 3. 서블릿 등록 : container의 서블릿 관리 정채에 대한 설정이 간으.
 * 		web.xml(2.X) : servlet 엘리먼트
 * 		@Webservlet (3.X)
 * 
 * 		- loadOnStartUp : 서블릿의 인스턴스 생성 시점 제어.
 * 		- initParam (초기화파라미터?)
 * 4. 서블릿 매핑
 * 		web.xml(2.X) : servlet-mapping 엘리먼트
 * 		@Webservlet("url-pattern") (3.X)
 * 
 * Servlet Container ⇒ 서블릿의 생명주기 관리자. 싱글톤 기본 정책 활용
 * Container ? : 내부에서 관리되는 객체의 생명주기 제어자
 * 
 * ** 서블릿 스펙에서 자주 활용되는 객체
 * 1. ServletContext : 하나의 컨텍스트를 대상으로 싱글톤으로 운영되는 객체로 주로 서버의 정보를 활용할 때 사용됨.
 * 2. ServletConfig : 하나의 서블릿을 대상으로 1:1로 운영되는 객체로 해당 서블릿에 대한 메타 정보를 가진 객체.
 * 3. HttpServletRequest : 하나의 요청을 대상으로, 해당 요청을 발생시킨 클라이언트와 요청에 대한 정보를 가진 객체.
 * 4. HttpServletResponse : 하나의 응답을 전송하기 위해 응답 컨텐츠와 메타 정보를 가진 객체.
 * 							response 나가고 나면 request 객체는 소멸됨
 * 							응답데이터 나갈 떄는 컨텐츠와 메타데이터 같이 나가는데, 컨텐츠는 없는데 메타데이터만 응답으로 내보내는 경우가 있는데, 상관없이 응답이라는게 나가면 request는 사라짐
 * 5. HttpSession : 한 클라이언트가 하나의 에이전트를 대상으로 운영되는 객체로 한 세션에 대한 정보를 가진 객체.
 */
public class DescriptionServlet extends HttpServlet{
	
	do
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); //init 메소드에서는 이거 지우면 안됨
		ServletContext application = getServletContext();
		System.out.printf(" application hash code : %d \n", application.hashCode()); //int 형으로 10진수 출력
		System.out.println(MessageFormat.format("{0} 서블릿 객체 생성 이후 초기화 완료.", this.getClass().getName())); //qualified Name
		//이게 몇번 실행되는지에 따라 싱글톤 확인 가능
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String method = req.getMethod();
		System.out.println(MessageFormat.format("{0} 요청의  {1} 메소드 처리.", uri, method));
	}
	
	@Override
	public void destroy() {
		System.out.println(MessageFormat.format("{0} 서블릿 객체 소멸.", this.getClass().getName()));
	}
}
