package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//[Data]
		//Locale => 언어, 나라에 대한 정보
		Locale clientLocale = req.getLocale();//클라이언트 쪽에서 서버까지 전달-> 클라이언트에 대한 정보는 request가 가지고 있음
		Locale serverLocale = Locale.getDefault();
		//작업공간이 투티어 이상임 -> 서버, 클라이언트 o 
		
		//data-> information -> content
		//위의 작업은 데이터까지 얻은 것
		
		//[Information]
		//Locale 객체를 쉽게 텍스트 형태로 읽을 수 있도록
		String clientLocaleText = clientLocale.getDisplayName(clientLocale);
		String serverLocaleText = serverLocale.getDisplayName(clientLocale); //여기 매개변수도 최종 소비자인 clientLocale이 들어가야함
		
		//[Content]
		//String 타입은 immutable 객체 (수정불가능) => 문자열에 + 사용하는건 좋은 방법 아님 -> 스트링빌더 사용
		StringBuilder content = new StringBuilder();
		content.append("<html>");
		content.append("<body>");
		content.append(MessageFormat.format("<h4>client side locale : {0} </h4>", clientLocaleText)); //문자열을 일정한 형식으로 만들어내는 것 
		content.append(MessageFormat.format("<h4>server side locale : {0} </h4>", serverLocaleText)); //중괄호 안에는 아큐먼트 인덱스
		content.append("</body>");
		content.append("</html>");
		
		String mime = "text/html; charset=UTF-8";
		resp.setContentType(mime);
		
		//컨텐트를 응답으로
		//아래 두개의 공통점 : 출력스트림
		//resp.getOutputStream(); // Stream으로 끝나면 바이트 스트림 -> 1바이트로 전송
		//스트리밍 서비스 제공할 때는 서비스하는 데이터의 타입에 따라 다르게 선택할 수 있어야함
		PrintWriter out = resp.getWriter(); // 2바이트 -> 한개의 캐릭터 형태로 전송 => 보내야하는 데이터가 문자열이기 때문에 캐릭터 스트림 필요
		out.print(content);
		out.close();
	}
}
