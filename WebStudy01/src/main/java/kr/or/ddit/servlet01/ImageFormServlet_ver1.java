package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
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

@WebServlet("/ver1/imageForm.do")
public class ImageFormServlet_ver1 extends HttpServlet{
	
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
		String[] imageFileNames = imageFolder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				//마임타입을 가져와서 이게 image 로 시작하는지 확인하기
				String mime = application.getMimeType(name);
				//hwp 는 한국에서만 사용하는 확장자이기 때문에 null 값인지 판단을 해야함
				//기본적인 mime 타입에 hwp 존재 x
				return mime!=null && mime.startsWith("image/");
			}
		});
		StringBuilder options = new StringBuilder();
		for(String imageName : imageFileNames) {
			options.append(
				MessageFormat.format("<option>{0}</option>", imageName)
			);
		}
		
		StringBuilder content = new StringBuilder();
		content.append(" <html>                                   ");
		content.append(" <body>                                   ");
		content.append(String.format("<form onsubmit='submitHandler(event);' action='%s/image.do'>", req.getContextPath()));
		content.append(" <select name='image' onchange='this.form.requestSubmit();'>                                 ");
		content.append(options);
		content.append(" </select>                                ");
		content.append(" <input type='submit' value='전송'>       ");
		content.append(" </form>                                  ");
		
		content.append(" <script>                                   ");
		content.append(" 	function submitHandler(event) {         ");
		content.append(" 		event.preventDefault();             ");
		
		content.append(" 	}                                       ");
		content.append(" </script>                                  ");
		
		content.append(" </body>                                  ");
		content.append(" </html>                                  ");
		
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out =null;
		try {
			out = resp.getWriter();
			out.println(content);
		}finally {
			if(out!=null)
			out.close();
		}
	}
}











