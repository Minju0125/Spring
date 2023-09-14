package kr.or.ddit.bts.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/bts", loadOnStartup = 1) //요청이 들어오지 않아도 미리 담아 놓을 수 있음 !
public class BtsFormControllerServlet extends HttpServlet{
	
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//application scope 를 사용해야하므로, 서버 구동시 생성
		
		//LinkedHashMap => 순서가 있는 entry 구조
		Map<String, String[]> btsMap = new LinkedHashMap<>();
		
		//멤버이름, 컨텐츠 페이지의 주소를 컬럼으로 (데이터베이스 역할)
		btsMap.put("B001", new String[] {"RM", "/WEB-INF/views/bts/rm.jsp"}); 
		btsMap.put("B002", new String[] {"뷔", "/WEB-INF/views/bts/bui.jsp"});
		btsMap.put("B003", new String[] {"제이홉", "/WEB-INF/views/bts/jhop.jsp"});
		btsMap.put("B004", new String[] {"지민", "/WEB-INF/views/bts/jimin.jsp"});
		btsMap.put("B005", new String[] {"진", "/WEB-INF/views/bts/jin.jsp"});
		btsMap.put("B006", new String[] {"정국", "/WEB-INF/views/bts/jungkuk.jsp"});
		btsMap.put("B007", new String[] {"슈가", "/WEB-INF/views/bts/suga.jsp"});
		
		application = getServletContext(); //singleton
		application.setAttribute("btsMap", btsMap); 
		
		System.out.println("btsMap을 application scope에 저장했음.");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String goPage = "/WEB-INF/views/bts/btsForm.jsp";
		
		if(goPage.startsWith("redirect")) { 
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else { //dispatch
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
