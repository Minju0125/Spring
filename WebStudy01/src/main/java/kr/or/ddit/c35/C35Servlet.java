package kr.or.ddit.c35;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.c35") // c35확장자를 가진 요청이 있으면 여기로 들어옴
public class C35Servlet extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.printf("%s 서블릿 초기화 완료\n", this.getClass().getSimpleName());
	}
	
	//request call-back 함수는 service() 라는 함수도 존재함
	//doXXX get 계열 메소드를 실행시켜줌
	//do계열 메소드는 7개 ( 요청이 어떤 조건인지에 따라서 실행하는데, 이 함수가 판단함)
	
	//이걸 오버라이드 하면, get or post 에 상관없이 request 들어왔을 때 동작하는건 이 메소드밖에 없음
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Map<String, Object> dummyData = new HashMap<>();
		//dummyData.put("now", new Date());
		
		
		//서블릿이 받은 요청을 분석
		String servletPath  = req.getServletPath();
		System.out.printf("========= servlet path : %s =========\n", servletPath); //client 가 사용하고 있는 주소
		String realPath = getServletContext().getRealPath(servletPath);
		System.out.printf("========= real path : %s =========\n", realPath); //client 가 사용하고 있는 주소
		
		//한글이 깨지지 않도록, mime 텍스트 설정해야하는데. 이건 동적 자원 사용
		File c35File = new File(realPath);
		
		String templateSource = Files.readAllLines(c35File.toPath()) //집합객체=> stream api 사용  => 여기까지는 list
							  .stream()
							  .collect(Collectors.joining());
	
		//#now#--> 파싱작업 필요 (정규식 사용)
		//1. 전체 템플릿 소스에서 찾아내고
		//2. now 찾아내야함
		
		Pattern regex = Pattern.compile("#(\\w+)#");
		Matcher matcher = regex.matcher(templateSource); //정규식 결과에 대한 모든 데이터를 가지고 있음
		
		StringBuffer content = new StringBuffer();
		
		while(matcher.find()) {
			String name = matcher.group(1); //now 식별자
			//Object value = dummyData.get(name);
			String replaceText = Optional.ofNullable(req.getAttribute(name))
										 .map((v)->v.toString())
										 .orElse("");
			
			matcher.appendReplacement(content, replaceText);
		}
		matcher.appendTail(content);
		
		resp.setContentType("text/html; charset=utf-8");
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(content);
		}
		
	}
}
