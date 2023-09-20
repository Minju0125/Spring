package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/09/model2/formDataProcess")
public class FormDataProcessControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String reqContentType = req.getContentType();
		Map reqContent = null;
		if(reqContentType.contains("json")) {
			// json payload
			InputStream is = req.getInputStream();
			reqContent = new ObjectMapper().readValue(is, HashMap.class);
		}else {
			reqContent = req.getParameterMap();
			reqContent.forEach((k,v)->{
				System.out.printf("%s : %s\n", k, Arrays.toString((String[])v));
			});
		}
		
//		Map<String, Object> target = new HashMap<>();
//		target.put("message", "파라미터 처리 완료");
//		target.putAll(reqContent);
		req.setAttribute("message", "파라미터 처리 완료");
		req.setAttribute("reqContent", reqContent);
		
		String accept = req.getHeader("Accept");
		
		
		int sc = 200;
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView.view";
		}else if(accept.contains("xml")){
			sc = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}else {
			viewName = "/WEB-INF/views/09/formDataView.jsp";
		}
		if(sc==200) {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			resp.sendError(sc);
		}
	}
}
















