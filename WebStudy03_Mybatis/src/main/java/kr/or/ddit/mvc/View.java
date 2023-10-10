package kr.or.ddit.mvc;import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface View {
	   public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}