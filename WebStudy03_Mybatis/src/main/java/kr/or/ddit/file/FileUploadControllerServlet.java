package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandartMultipartHttpServletRequest;
import kr.or.ddit.mvc.ViewResolverComposite;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/15/fileUpload.do")
@MultipartConfig
public class FileUploadControllerServlet extends HttpServlet {

	private String imagesUrl = "/resources/images";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");

		if (req instanceof StandartMultipartHttpServletRequest) {
			// 타입이 맞다면
			MultipartFile uploadFile = ((StandartMultipartHttpServletRequest) req).getFile("uploadFile");

			// 검증 (이미지인지 아닌지)
			if (!uploadFile.getContentType().startsWith("image/")) {
				// 이미지 파일이 아님 = 요구하는 컨텐츠 아님 ! 400 에러코드
				resp.sendError(400);
				return;
			}
			
			log.info("uploader : {}", uploader);
			log.info("업로드 파일 part : {}", uploadFile);

			String realPath = req.getServletContext().getRealPath(imagesUrl);// 가지고 있던 논리주소를 통해 물리주소 얻기
			File imageFolder = new File(realPath); // 저장할 폴더의 위치
			String fileName = uploadFile.getOriginalFilename(); // 업로드되는 파일의 원본파일명 그대로 저장
			File saveFile = new File(imageFolder, fileName);// imageFolder 에 fileName으로 저장

			try (InputStream is = uploadFile.getInputStream();) {
				uploadFile.transferTo(saveFile);
				String fileUrl = imagesUrl + "/" + saveFile.getName();
				HttpSession session = req.getSession();
				session.setAttribute("uploader", uploader);
				session.setAttribute("fileUrl", fileUrl);
			}
		}
		String viewName = "redirect:/15/fileUploadForm.jsp";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}