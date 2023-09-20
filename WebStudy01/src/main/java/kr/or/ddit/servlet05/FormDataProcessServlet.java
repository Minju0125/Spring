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

@WebServlet("/09/formDataProcess")
public class FormDataProcessServlet extends HttpServlet{
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
		
		Map<String, Object> target = new HashMap<>();
		target.put("message", "파라미터 처리 완료");
		target.putAll(reqContent);
		
		String accept = req.getHeader("Accept");
		
		
		// information -> content -> serialization
		String contentType = null;
		Object content = null;
		if(accept.contains("json")) {
			contentType = "application/json;charset=UTF-8";
			content = new ObjectMapper().writeValueAsString(target);
		}else if(accept.contains("xml")){
			contentType = "application/xml;charset=UTF-8";
			content = "<root><message>"+target.get("message")+"</message></root>";
		}else {
			contentType = "text/html;charset=UTF-8";
			content = "<div>"+target.get("message")+"</div>";
		}
		
		resp.setContentType(contentType);
		resp.getWriter().print(content);
	}
}
















