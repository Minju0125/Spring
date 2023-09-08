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
	//method에 상관없이 처리하려면=> service메소드
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//---------------------언마샬링(컨트롤러)---------------------
		req.setCharacterEncoding("UTF-8"); //post에서 동작
		//super.service(req, resp); 이 코드를 삭제한다면 do계열의 메소드를 사용하지 않겠다!는 의미
		String reqContentType=  req.getContentType(); //post 인 경우에 형성되는 content body 를 가져옴 // 클->서버에게 쓴 편지
		Map reqContent = null;
		
		//json으로 넘어올 수도 있고, 파라미터로 넘어올 수도 있음
		if(reqContentType.contains("json")) {
			//json payload
			//역직렬화, 언마샬링 -> object mapper 필요
			//매체로부터 스트림 읽어야함 (request body에서 읽어들일 input stream)
			InputStream is= req.getInputStream(); //역직렬화 시 사용하는 입력스트림 (역직렬화를 위한 통로)
			reqContent = new ObjectMapper().readValue(is, HashMap.class); //언마샬링,read계열메소드
		}else { //파라미터 일 수도 있음
			reqContent = req.getParameterMap(); //chkParam, multiple 은 여러개를 받을 수 있으니까 String[] 사용
			reqContent.forEach((k,v)->{ //k:String, v:String[]
				System.out.printf("%s : %s\n", k, Arrays.toString((String[])v));
			});
		}

		Map<String, Object> target = new HashMap<>();
		target.put("message", "파라미터 처리 완료");
		target.putAll(reqContent);
		
		//클라이언트는 요청보낼 때 accept 숨겨서, 응답을 줄때 어떤 걸로 줄지 결정
		String accept = req.getHeader("Accept");
		
		
		//---------------------마샬링(뷰)---------------------
		String contentType = null;
		Object content = null;

		//마샬링(information->content)
		if(accept.contains("json")) {
			contentType= "application/json; charset=UTF-8";
			content= new ObjectMapper().writeValueAsString(target);
		}else if(accept.contains("xml")) {
			contentType= "application/xml; charset=UTF-8";
			//태그가 최상위 element 부터 시작해야함
			content ="<root><message>"+ target.get("message") + "</message></root>";
		}else {
			contentType= "text/html; charset=UTF-8";
			content = "<div>"+ target.get("message") + "</div>";
		}
		resp.setContentType(contentType);
		resp.getWriter().print(content); //직렬화
	}
}
