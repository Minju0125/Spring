package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.view.JsonView;

public class BeanNameViewResolver implements ViewResolver { //ViewResolver와 realization 관계

	//이 container 가 view 객체를 관리하는 관리자처럼 사용됨.
	private Map<String, View> container; //모든 view 를 일종의 컨테이너로 사용
	{
		container = new HashMap<>();
		container.put("jsonView", new JsonView());
	}
	
	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//viewName을 받아서 container들을 검색
		if(container.containsKey(viewName)) {
			//container 안에서 get을 통해 viewName을 key 로 사용
			View view = container.get(viewName); //view 가 어떤게 될지는 아직 모름 !
			try {
				view.render(req, resp);
			} catch (Exception e) {
				throw new ServletException(e);
			}
			//view 의 구현체에 의해 응답데이터 나감 !
		}else {
			//구현한 view가 없을 때 (ex.xmlView) => tiles에게 넘김
			throw new IOException(String.format("%s에 해당하는 view를 찾지 못했음", viewName));
		}
	}
}
