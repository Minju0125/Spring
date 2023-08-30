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
 * @WebServlet 어노테이션으로 등록하는 경우, servlet-name은 CoC 에 따라 클래스의 심플 네임이 사용됨.
 * Coc(Convention Over Configuration) 패러다임 : 명시적인 설정이 없는 경우, 일반적인 관행이 코드에 반영됨
 */
@WebServlet(loadOnStartup = 2, value = "/image.do", initParams = {@WebInitParam(name = "imageFolder", value = "D:/01.medias/images")}) 
public class ImageStreamingServlet extends HttpServlet {
	
	private File imageFolder;
	private ServletContext application; // singleton

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); //ServletContext 객체가 생성됨 
		
		//ServletContext 는 싱글톤으로 운영됨
		application = getServletContext();
		System.out.printf(" application hash code : %d \n", application.hashCode()); //int 형으로 10진수 출력
		
		//String value = config.getInitParameter("imageFolder");
		
		String value = application.getInitParameter("imageFolder"); //하나의 어플리케이션에서 init 파라미터 가져오기
		
		this.imageFolder = new File(value);
		System.out.println(MessageFormat.format("{0} 서블릿 초기화 완료.", this.getClass().getSimpleName())); 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageName = request.getParameter("image"); //물리주소
		
		//request에 대한 검증구조 필요함 (진짜 존재하는지 확인해봐야함) //클라이언트가 필수 파일을 제대로 넘겼는지
		if(imageName==null || imageName.isEmpty()) { //null 이거나 비어있으면
			//[요청검증] 정상적인 응답이 아닌, 에러라는 응답정보를 내보내야함 //꼭 응답은 나가야함 일대일 구조이기 때문에 !!!
			//400 실패의 원인 : 클라이언트쪽에 있음 . 요청에 대한 검증 작업
			//				ex) 필수 파라미터 누락, 파라미터 타입,
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		File imageFile = new File(imageFolder, imageName); //이 이미지 폴더(imageFolder)에 있는 ....
		//[요청검증] 
		if(!imageFile.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, MessageFormat.format("{0} 이미지 파일이 없음", imageName)); //"당신이 사용하려고 하는 것은 서버안에 존재하지 않습니다."
			return;
		}
		
		ServletContext application = getServletContext();
		String mime = application.getMimeType(imageFile.getName());
		
		response.setContentType(mime);
		response.setContentLengthLong(imageFile.length());
		
		FileInputStream fis = new FileInputStream(imageFile); //파일 객체 생성 시, 물리주소 사용
		
		//outputStream으로 응답컨텐츠 만들거라서
		ServletOutputStream sos = response.getOutputStream();
		//EOF(End Of File) 문자를 만날때까지 반복 작업
		//int readByte = -1;
		//while((readByte = fis.read())!=-1) {
		//sos.write(readByte); //이미지 파일이 100MB 라면? 100*1000*1000까지 반복 => 여러차례의 반복문 => 효율성 떨어짐 (전송효율 높일때는 버퍼 사용)
		//}
		
		byte[] buffer = new byte[1024]; //1kb 짜리
		int length = -1;
		while((length = fis.read(buffer))!=-1) { //한번에 1kb 읽을 수 있음 => 읽어들인 데이터를 buffer 가 가지고 있고
			sos.write(buffer, 0, length); //length는 읽어들인 길이를 가지고 있음
		}
	
		sos.flush();
		
		fis.close();
		sos.close();

	}
}
