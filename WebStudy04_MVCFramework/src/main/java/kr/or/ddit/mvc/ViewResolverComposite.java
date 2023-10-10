package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolverComposite implements ViewResolver {
	private List<ViewResolver> viewResolvers; //agreegation 관계 형성

	
	//기본생성자
	public ViewResolverComposite() {
		super();
		viewResolvers = new ArrayList<>();
		viewResolvers.add(new BeanNameViewResolver()); //tiles 보다 검색이 빠르므로 가장 처음으로 검색
		viewResolvers.add(new TilesViewResolver()); //tiles definition 용
		
		InternalResourceViewResolver resolver =  new InternalResourceViewResolver(); //single jsp 에서 
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		viewResolvers.add(resolver); //InternalResourceViewResolver 의 우선순위는 항상 마지막 순서.
	}

	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (viewName.startsWith("redirect:")) {
			String location = req.getContextPath() + viewName.substring("redirect:".length());
			resp.sendRedirect(location);
		} else {
			for(ViewResolver single : viewResolvers) { 
				try { //먼저 BeanNameViewResolver
					single.resolveView(viewName, req, resp);
					break;
				}catch (Exception e) { //특정 viewResolver가 view를 해결하지 못한 경우
					System.err.println(e.getMessage());
					continue; //다음 반복으로 넘김
				}
			}
		}
	}
}