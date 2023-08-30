package kr.or.ddit.servlet01;

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
		//람다 문법안에 한문장만 들어가있으면 바디생략, return 생략 가능
		//안에 메소드 하나밖에 없기 때문에 바로 accept 메소드 적용
		String[] imageFileNames = imageFolder.list((d,n)->Optional.ofNullable(application.getMimeType(n))
				.orElse("").startsWith("image/")
		); 
		
		//집합객체의 요소를 다른 형태로 바꾸고 싶을 때 => map
		String options = Stream.of(imageFileNames).map((in)->MessageFormat.format("<option>{0}</option>", in)
			//원래스트림에서는 이미지 파일명을 가지고 있었지만, 리턴되는 stream 에서는 옵션태그를 가진 이미지파일명
		).collect(Collectors.joining("\n"));
		//Collectors : 모으는 방법 (to 계열의 함수로 또다른 집합 객체를 만들 수 있음)
		//joining 다 모아서 한개의 string 으로 (한줄로 쭉!)
		
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
		//try-with resources 구문
		//try(Closable 객체 생성 구문){}catch(exception...){}
		
		try(
			PrintWriter out	= resp.getWriter(); // 필요가 없어지면 알아서 close 됨
		){
			out.println(content);
		}
	}
}
