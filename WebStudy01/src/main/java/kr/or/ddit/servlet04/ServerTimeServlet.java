package kr.or.ddit.servlet04;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/08/serverTime")
public class ServerTimeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept");
		String contentType = "text/html;charset=UTF-8";
		if(accept.contains("json")) {
			contentType = "application/json;charset=UTF-8";
		}
		LocalDateTime now = LocalDateTime.now();
		
		Object content = null;
		if(accept.contains("json")) {
			// marshalling : native -> common, unMarshalling : common -> native
			String ptrn = "{\"now\":\"%s\"}";
			content = String.format(ptrn, now.toString());
		}else {
			content = now;
		}
		
		resp.setContentType(contentType);
		resp.setIntHeader("Refresh", 1);
		
		resp.getWriter().print(content);
	}
}














