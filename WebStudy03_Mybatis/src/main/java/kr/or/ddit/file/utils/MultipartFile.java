package kr.or.ddit.file.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 업로드 파일 하나에 해당하는 객체.
 * (이 인터페이스가 가지고 있는 메소드는 파트가 가지고 있는 메소드와 유사함)
 */
public interface MultipartFile {
	
	public byte[] getBytes() throws IOException; //이진데이터 자체를 이진배열로 가지기 위함

	public String getContentType();
	
	public InputStream getInputStream() throws IOException;

	public String getName();
	
	public String getOriginalFilename();
	
	public long getSize();
	
	public boolean isEmpty();
	
	public void transferTo(File dest) throws IOException;

} 
