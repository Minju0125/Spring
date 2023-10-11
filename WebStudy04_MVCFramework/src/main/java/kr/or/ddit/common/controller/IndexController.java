package kr.or.ddit.common.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

//POJO(Plain Old Java Object)
@Controller
public class IndexController{
	
	@RequestMapping("/index.do") //single value 이기 때문에 'value=' 생략 가능
	public String index(HttpServletRequest req) {
		String title = "컨트롤러에서 만든 Model 타이틀";
		
		req.setAttribute("title", title);
		
		return "index";
	}
}