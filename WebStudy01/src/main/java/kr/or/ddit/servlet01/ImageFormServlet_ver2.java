package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
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

@WebServlet("/ver2/imageForm.do")
public class ImageFormServlet_ver2 extends HttpServlet{
	
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
		String[] imageFileNames = imageFolder.list((d,n)->
								Optional.ofNullable(application.getMimeType(n))
									.orElse("").startsWith("image/"));
		
		
		String options = Stream.of(imageFileNames)
			.map((in)-> MessageFormat.format("<option>{0}</option>", in))
			.collect(Collectors.joining("\n"));
		
		
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
		
		// try with resource 구문
		// try( Closeable 객체 생성 구문 ){ }catch(){}
		//	-->close해야할 대상이 자동으로 close되기 때문에 finally 사용할 필요X
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(content);
		}

	}
}


