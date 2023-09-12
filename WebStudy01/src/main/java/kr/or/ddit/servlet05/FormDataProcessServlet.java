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
		//컨트롤러의 역할
		req.setCharacterEncoding("UTF-8");
		String reqContentType = req.getContentType();
			// 데이터가 파라미터로 넘어오는지 json으로 넘어오는지 확인
		Map reqContent = null;
		if(reqContentType.contains("json")) {
			// json payload가 전송됐을 때 -> 역직렬화와 마셜링 필요
			// request의 body에서 읽을 inputStream이 필요
			InputStream is = req.getInputStream();		//역직렬화 할 때 사용할 수 있는 입력 스트림
				// 역직렬화 통해 byte데이터 읽어들임->읽어들인 json데이터를 Java언어로 번역 : 마셜링
			reqContent = new ObjectMapper().readValue(is, HashMap.class);
							// 클라이언트가 서버에게 쓴 편지 - 역직렬화 + 언마셜링까지 해야함 => read
		}else {
			// 파라미터일 수도 있을 때
			reqContent = req.getParameterMap();
			reqContent.forEach((k,v)->{
				System.out.printf("%s : %s\n", k, Arrays.toString((String[])v));
			});
		}
		
		//모델의 역할
		Map<String, Object> target = new HashMap<String, Object>();
		target.put("message", "파라미터 처리 완료");
		target.putAll(reqContent);
		// 응답데이터를 어떤 형태로 보내야하는지 확인
		String accept = req.getHeader("Accept");
		
		
		//뷰의 역할
		// information -> content 로 바꿈 -> serialization:직렬화(응답으로 내보냄)
		String contentType = null;
		Object content = null;
		if(accept.contains("json")) {
			// Map에 있는 데이터를 Json으로 바꿈 -> 마셜링
			// 네트워크를 통해 가려면 010101~~~로 바뀌어야함 -> 직렬화
			contentType = "application/json; charset=utf-8";
			content = new ObjectMapper().writeValueAsString(target);
		}else if(accept.contains("xml")) {
			contentType = "application/xml; charset=utf-8";	
			content = "<root><message"+target.get("message")+"</message></root>";
		}else {
			contentType = "text/html; charset=utf-8";
			content = "<div>"+target.get("message")+"</div>";
		}
		
		resp.setContentType(contentType);
		resp.getWriter().print(content);
	}
}
