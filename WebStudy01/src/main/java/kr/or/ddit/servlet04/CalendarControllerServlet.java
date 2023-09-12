package kr.or.ddit.servlet04;

import java.io.IOException;
import java.time.Year;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.CalendarVO;

@WebServlet("/calendar")
public class CalendarControllerServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		super.service(req, resp);	//request의 메서드를 판단하고 그에 맞는 do계열의 콜백메서드를 사용
		//request의메서드와 상관없이 처리
		
		String yearParam = request.getParameter("year");
		String monthParam = request.getParameter("month");
		String localeParam = request.getParameter("locale");
		
		Locale locale = Optional.ofNullable(localeParam)
			.map(lp->Locale.forLanguageTag(lp))
			.orElse(request.getLocale());

//	 	Locale locale = request.getLocale();	//reqeust header(Accept-Language)
		
		int year = Optional.ofNullable(yearParam)
						.filter((yp)->yp.matches("\\d{4}"))
						.map((yp)->Integer.parseInt(yp))
						.orElse(Year.now().getValue());

		
		YearMonth targetMonth = Optional.ofNullable(monthParam)
									.filter((mp)->mp.matches("[1-9]|1[0-2]"))
									.map((mp)->Integer.parseInt(mp))
									.map((m)->YearMonth.of(year, m))
									.orElse(YearMonth.now());
		YearMonth beforeMonth = targetMonth.minusMonths(1);
		YearMonth nextMonth = targetMonth.plusMonths(1);
		
		CalendarVO calVO = new CalendarVO();
		calVO.setLocale(locale);
		calVO.setTargetMonth(targetMonth);
		calVO.setBeforeMonth(beforeMonth);
		calVO.setNextMonth(nextMonth);
		request.setAttribute("calVO", calVO);
//		request.setAttribute("locale", locale);
//		request.setAttribute("targetMonth", targetMonth);
//		request.setAttribute("beforeMonth", beforeMonth);
//		request.setAttribute("nextMonth", nextMonth);
		
		String viewName = "/WEB-INF/views/07/calendarView.jsp";
		
		request.getRequestDispatcher(viewName).forward(request, response);
	}
}
