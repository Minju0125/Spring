package kr.or.ddit.bts.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bts/*")
public class BtsContentControllerServlet extends HttpServlet{
	
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//경로의 일부분으로 전달됨 -> 경로 변수 구조
		//request line 의 uri
		String reqUri = req.getRequestURI();
		System.out.printf("request uri : %s\n", reqUri);
		int lastIdx = reqUri.lastIndexOf("/");
		String memCode = reqUri.substring(lastIdx + 1);
		System.out.printf("selected memeber : %s\n", memCode);
		
		//memCode에 대한 검증과정
		if(memCode==null || memCode.trim().isEmpty()) {
			resp.sendError(400, "멤버 코드가 없음.");
			return;
		}
		
		Map<String, String[]> btsMap = (Map)application.getAttribute("btsMap");
		if(!btsMap.containsKey(memCode)) {
			//bts code 1-7 까지 있는데, 클라이언트가 찾으려는 멤버자원이 없음 (404, not found)
			resp.sendError(404, String.format("%s에 해당하는 멤버는 없음",memCode));
			return;
		}
		
		//생성된 쿠키 안에 '누구'라는 정보 들어가 있어야함
		Cookie btsCookie = new Cookie("btsCookie", memCode);
		btsCookie.setMaxAge(60*60*24*3);
		btsCookie.setPath(req.getContextPath()+"/bts");
		resp.addCookie(btsCookie);
		
		String[] memRec = btsMap.get(memCode);
		req.setAttribute("member", memRec);
		
		//String goPage = memRec[1];
		String goPage = "/WEB-INF/views/bts/btsLayout.jsp";
		
		if(goPage.startsWith("redirect")) { 
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else { //dispatch
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}