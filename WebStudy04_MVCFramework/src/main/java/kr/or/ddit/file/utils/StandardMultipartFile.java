package kr.or.ddit.file.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class StandardMultipartFile implements MultipartFile{ 
	
	//어댑터로 만들 예정 (어댑티 있어야함!)
	private Part adaptee;
	
	//기본생성자 없어야함.
	public StandardMultipartFile(Part adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public String getContentType() {
		return adaptee.getContentType();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return adaptee.getInputStream();
	}

	@Override
	public String getName() {
		return adaptee.getName();
	}

	@Override
	public String getOriginalFilename() {
		return adaptee.getSubmittedFileName();
	}

	@Override
	public long getSize() {
		return adaptee.getSize();
	}

	@Override
	public boolean isEmpty() {
		return StringUtils.isBlank(getOriginalFilename());//원본파일명이 없음(비어있음)
	}

	@Override
	public void transferTo(File dest) throws IOException {
		adaptee.write(dest.getCanonicalPath());
	}
}
