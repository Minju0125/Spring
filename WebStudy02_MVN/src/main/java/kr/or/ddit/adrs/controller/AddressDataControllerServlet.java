package kr.or.ddit.adrs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.vo.AddressVO;

@WebServlet("/adrs/address") //주소록 하나만을 표현 -> crud 중 어떤걸 할지는 메소드로
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
}
