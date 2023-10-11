package kr.or.ddit.adrs.controller;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class AddressUIController{

	@RequestMapping("/adrs/view")
	public String doGet(){
		return "adrs/adrsView";
	}
}
