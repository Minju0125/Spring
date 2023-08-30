package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Model 2 아키텍처 기반의 책임의 분리 구조.
 * 
 * Model 1 : reqest와 response가 하나의 객체(서블릿/JSP)에 의해 처리되는 구조.
 * Model 2 : request(servlet, controller) 처리 객체와 response(servlet/JSP, view) 처리 객체가 분리된 구조.
 * 			 Model : content의 원형이 되는 information -> MVC pattern
 * 
 * Controller의 역할
 * 1. 요청 접수
 * 2. 요청 검증 / 분석
 * 3. Model(information) 생성
 * 4. scope를 통해 model 공유
 * 5. view 선택
 * 6. view 로 이동
 * 
 * View 역할
 * : model로부터 content 생성
 * 
 */
@WebServlet("/ver3/imageForm.do") //Model 2
public class ImageFormServlet_ver3 extends HttpServlet{
	
	private File imageFolder;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		application = getServletContext();
		
		String value = application.getInitParameter("imageFolder");
		
		imageFolder = new File(value);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] imageFileNames = imageFolder.list((d,n)->Optional.ofNullable(application.getMimeType(n))
				.orElse("").startsWith("image/")
		); 
		
		//options와 contextPath 라는 두개의 Model(information) 생성
		String options = Stream.of(imageFileNames)
				.map((in)->MessageFormat.format("<option>{0}</option>", in))
				.collect(Collectors.joining("\n"));
		String contextPath = req.getContextPath();
		
		//scope를 통해 model 공유
		req.setAttribute("cPath", contextPath);
		req.setAttribute("options", options);
		
		req.getRequestDispatcher("/WEB-INF/views/03/imageForm.c35").forward(req, resp);
	}
}
