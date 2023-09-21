package kr.or.ddit.adrs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.vo.AddressVO;

@WebServlet({"/adrs/address", "/adrs/address/*"}) //주소록 하나만을 표현 -> crud 중 어떤걸 할지는 메소드로
public class AddressDataControllerServlet extends HttpServlet{
	private AddressService service = new AddressServiceImpl();
	
	//do계열의 메소드로 crud 처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("Utf-8");
		
		String memId = (String)req.getSession().getAttribute("authId");
		List<AddressVO> adrsList = service.retriveAddressList(memId);
		
		req.setAttribute("adrsList", adrsList);
		
		String goPage = "/jsonView.view";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location); 
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	private ObjectMapper mapper = new ObjectMapper();
	private boolean validate(AddressVO vo, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(vo.getAdrsName())) { //이름(필수파라미터1) 누락된 경우
			valid = false;
			errors.put("adrsName", "이름 누락");
		}
		if(StringUtils.isBlank(vo.getAdrsHp())) { //연락처(필수파라미터2) 누락된 경우
			errors.put("adrsHp", "휴대폰 번호 누락");
			valid = false;
		}
		return valid;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//조건 : json parameter로 데이터 받기
		
		//json 객체로 받아온 데이터를 언마샬링 해야함 -> objectMapper
		try(
				InputStream is = req.getInputStream();
		){
			AddressVO vo = mapper.readValue(is, AddressVO.class);
			req.setAttribute("originalData", vo);
			String authId = (String)req.getSession().getAttribute("authId");
			vo.setMemId(authId);
			Map<String, String> errors = new HashMap<>();
			req.setAttribute("errors", errors); //상황에 따라 엔트리가 있거나 없거나
			//검증과정
			boolean valid = validate(vo, errors);
			//성공여부
			boolean success = false;
			String message = null;
			if(valid) { //검증통과
				if(service.createAddress(vo)) {
					success = true;
				}else {
					message = "등록실패";
				}
			}else { //검증실패
				message = "필수 파라미터 누락";
			}
			
			req.setAttribute("success", success);
			req.setAttribute("message", message);
		}
		
		String goPage = "/jsonView.view";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location); 
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
		System.out.println(uri);
		int lastIdx = uri.lastIndexOf("/");
		int uriLen = uri.length();
		int baseLen = "/adrs/address".length();
		boolean valid = lastIdx >= baseLen && lastIdx < uriLen-1; //크거나 같으면 검증 통과 , lastIdx가 마지막 글자가 되서는 안됨
		System.out.printf("%s : %b\n", uri, valid);
		String adrsNoPart = uri.substring(lastIdx+1);
		
//		숫자인지 검증
//		if(valid) {
//			adrsNoPart = uri.substring(lastIdx + 1);
//			valid = StringUtils.isNumeric(adrsNoPart);
//		}
		int adrsNo = -1;
		try {
			adrsNo = Integer.parseInt(adrsNoPart);
		}catch (NumberFormatException e) {
			valid = false; //위 주석에 숫자인지 검증하는 코드 대신 catch 문에서 잡을 수 잇음
		}
		if(!valid){
			resp.sendError(400, "주소록 번호가 없음.");
			return;
		}
		boolean success = service.removeAddress(adrsNo);
		req.setAttribute("success", success); //true or false
		if(!success) {
			req.setAttribute("message", "삭제 실패");
		};
		
		/*
		req.setCharacterEncoding("Utf-8");
		String reqUri = req.getRequestURI();
		int lastIdx = reqUri.lastIndexOf("/");
		String adrsNoStr = reqUri.substring(lastIdx+1);
		int adrsNo = Integer.parseInt(adrsNoStr);
		boolean success = false;
		String message = null;
		
		if(service.removeAddress(adrsNo)) {
			success = true;
		}else {
			message = "삭제실패";
		}
		req.setAttribute("success", success);
		req.setAttribute("message", message);
	
		 */
		String goPage = "/jsonView.view";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location); 
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(
				InputStream is = req.getInputStream();
		){
			AddressVO vo = mapper.readValue(is, AddressVO.class);
			
			Map<String, String> errors = new HashMap<>();
			req.setAttribute("errors", errors); //상황에 따라 엔트리가 있거나 없거나
			
			//성공여부
			boolean success = false;
			String message = null;
				if(service.modifyAddress(vo)) {
					success = true;
					message = "성공";
				}else {
					message = "수정실패";
				}
			req.setAttribute("success", success);
			req.setAttribute("message", message);
		}
		
		String goPage = "/jsonView.view";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location); 
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}