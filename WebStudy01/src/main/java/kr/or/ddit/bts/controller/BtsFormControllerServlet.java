package kr.or.ddit.bts.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/bts", loadOnStartup = 1)
public class BtsFormControllerServlet extends HttpServlet{
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Map<String, String[]> btsMap = new LinkedHashMap<>();
		btsMap.put("B001", new String[] {"RM", "/WEB-INF/views/bts/rm.jsp"});
		btsMap.put("B002", new String[] {"제이홉", "/WEB-INF/views/bts/jhop.jsp"});
		btsMap.put("B003", new String[] {"지민", "/WEB-INF/views/bts/jimin.jsp"});
		btsMap.put("B004", new String[] {"진", "/WEB-INF/views/bts/jin.jsp"});
		btsMap.put("B005", new String[] {"정국", "/WEB-INF/views/bts/jungkuk.jsp"});
		btsMap.put("B006", new String[] {"슈거", "/WEB-INF/views/bts/suga.jsp"});
		btsMap.put("B007", new String[] {"뷔", "/WEB-INF/views/bts/bui.jsp"});
		application = getServletContext();
		application.setAttribute("btsMap", btsMap);		
		System.out.println("btsMap 을 application scope 에 저장했음.");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String savedMemCode = Optional.ofNullable(req.getCookies())
										.map(cs->{
											return Arrays.stream(cs)
														.filter(c->"btsCookie".equals(c.getName()))
														.findFirst()
														.map(fc->fc.getValue())
														.orElse(""); // btsCookie 가 없는 경우.
										}).orElse(""); // cookie 하나도 없는 경우.
		
		req.setAttribute("savedMemCode", savedMemCode);
		
		String goPage = "/WEB-INF/views/bts/btsForm.jsp";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
















