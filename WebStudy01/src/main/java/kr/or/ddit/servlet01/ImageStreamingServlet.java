package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @WebServlet 어노테이션으로 등록하는 경우, servlet-name은 CoC에 따라 클래스의 심플 네임이 사용됨.
 * CoC (Convention Over Configuration) : 명시적인 설정이 없는 경우, 일반적인 관행이 코드에 반영됨.
 */
@WebServlet(loadOnStartup = 1, value = "/image.do"
			,initParams = {@WebInitParam(name = "imageFolder", value = "D:/01.medias/images")})
public class ImageStreamingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File imageFolder;
	private ServletContext application;	// singleton
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	// ServletContext 객체가 생성됨
		
		application = getServletContext();
		System.out.printf(" application hash code : %d \n", application.hashCode());

//		String value = config.getInitParameter("imageFolder");
		String value = application.getInitParameter("imageFolder");
		
		this.imageFolder = new File(value);
		System.out.println(MessageFormat.format("{0}서블릿 초기화 완료", this.getClass().getSimpleName()));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageName = request.getParameter("image");
		
		if(imageName==null || imageName.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		File imageFile = new File(imageFolder,imageName);
		
		if(!imageFile.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND
							, MessageFormat.format("{0} 이미지 파일이 없음.", imageName));
			return;
		}
		
		ServletContext application = getServletContext();
		String mime = application.getMimeType(imageFile.getName());
		
		response.setContentType(mime);
		response.setContentLengthLong(imageFile.length());
	
		FileInputStream fis = new FileInputStream(imageFile);
		ServletOutputStream sos = response.getOutputStream();
		
		// EOF(End Of File) 문자를 만날때까지 반복 작업
		/*
		int readByte = -1;
		while((readByte = fis.read())!=-1) {
			sos.write(readByte);
		}
		*/
		byte[] buffer = new byte[1024];
		int length = -1;
		while((length = fis.read(buffer))!=-1) {
			sos.write(buffer, 0, length);
		}
		
		sos.flush();
		
		fis.close();
		sos.close();
	}

}
