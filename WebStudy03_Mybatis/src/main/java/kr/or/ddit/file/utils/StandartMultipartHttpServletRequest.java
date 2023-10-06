package kr.or.ddit.file.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

/**
 * multipart request 를 wrapping 할 adapter.
 * Part 를 MultipartFile의 구현체로 변환.
 *
 */
public class StandartMultipartHttpServletRequest extends HttpServletRequestWrapper{

	private Map<String, List<MultipartFile>> fileMap;
	
	public StandartMultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		fileMap = new LinkedHashMap<>();
		parseRequest(request);
	}
	
	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		for(Part part : request.getParts()) {
			if(part.getContentType()==null) {
				continue;
			}
			String partName = part.getName();
			MultipartFile file = new StandardMultipartFile(part);
			List<MultipartFile> already = fileMap.get(partName);
			if(already==null) {
				already = new ArrayList<>();
				fileMap.put(partName, already);
			}
			already.add(file);
		}
	}
	
	public MultipartFile getFile(String partName) {
		List<MultipartFile> fileList =  fileMap.get(partName);
		if(fileList!=null && fileList.size() >0 ) {
			return fileList.get(0);
		}else {
			return null;
		}
	}
	
	public List<MultipartFile> getFiles(String partName){
		return fileMap.get(partName);
	}
	
	public Map<String, List<MultipartFile>> getFileMap() {
		return fileMap;
	}
	
}