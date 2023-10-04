package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 컨트롤러에서 view layer를 선택할 때 사용할 수 있는 전략.
 * @author PC-13
 *
 */
public interface ViewResolver {
	public default void setPrefix(String prefix) {};  //setter
	public default void setSuffix(String suffix) {};  //setter
	//이 인터페이스의 구현체는 resolveView는 꼭 구현해야함 !
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	;
}
