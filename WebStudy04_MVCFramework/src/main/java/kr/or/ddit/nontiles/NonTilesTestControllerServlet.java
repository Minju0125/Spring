package kr.or.ddit.nontiles;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/nontiles")
public class NonTilesTestControllerServlet extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String viewName = "nontiles/nonTilesView";
      new ViewResolverComposite().resolveView(viewName, req, resp);
   }
}