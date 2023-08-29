package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/imageForm.do")
public class ImageFormServlet extends HttpServlet{
	
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
		//이 이미지 파일의 폴더 위치를 먼저 알아야함
		String[] imageFileNames = imageFolder.list();
		
		StringBuilder options = new StringBuilder();
		for(String imageName:imageFileNames) {
			options.append(
					MessageFormat.format("<option>{0}</option>", imageName)
			);		
		}
		
		StringBuilder content = new StringBuilder();
		content.append("<html>");
		content.append("<body>");
		content.append(String.format("<form onsubmit='submitHandler(event);' action='%s/image.do'>", req.getContextPath()));
		//content.append("<select name='image' onchange='this.form.requestSubmit();'>");
		content.append("<select name='image' onchange='printImage(this);'>");
		content.append(options);
		content.append("</select>");
		content.append("<input type='submit' value='전송'>");
		content.append("</form>");
		
		content.append("<script>");
		content.append("function submitHandler(event) {");
		
		content.append("event.preventDefault();");
		content.append("}");
		content.append("</script>");
		
		content.append("function printImage(this) {                                                  ");
		content.append(MessageFormat.format("<img src= '{0}/{1}'>", imageFolder, this.value));
		content.append("	                                                                     ");
		content.append("}                                                                        ");
		
		
		content.append("</body>");
		content.append("</html>");
		
		System.out.println(imageFolder);
		
						//메인 타입/서브타입 ; 지시자 (쓰고 있는 에디터가 utf-8이기 때문에)
		resp.setContentType("text/html; charset=utf-8"); //mime 설정은 출력스트림 개방 전
		PrintWriter out= resp.getWriter();
		out.println(content);
		out.close();
	}
}
