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

@WebServlet("*.c35")
public class C35Servlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.printf("%s 서블릿 초기화 완료\n", this.getClass().getSimpleName());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String servletPath = req.getServletPath();
		System.out.printf("=============servlet path : %s===============\n",servletPath);
		String realPath = getServletContext().getRealPath(servletPath);
		System.out.printf("=============real path : %s===============\n",realPath);
		
		File c35File = new File(realPath);
		
		String templateSource = Files.readAllLines(c35File.toPath())
			.stream().collect(Collectors.joining("\n"));
		System.out.println(templateSource);
		
//		#now# --> 파싱 필요
		Pattern regex = Pattern.compile("#(\\w+)#");
		Matcher matcher = regex.matcher(templateSource);
		
		StringBuffer content = new StringBuffer();
		
		while(matcher.find()) {
			String name = matcher.group(1);	// now 식별자
			String replaceText = Optional.ofNullable(req.getAttribute(name))
									.map((v)-> v.toString()).orElse("");
			
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
