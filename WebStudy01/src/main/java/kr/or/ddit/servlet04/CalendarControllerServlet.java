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

import kr.or.ddit.vo.CalenderVO;

@WebServlet("/calendar") //절대 경로 ! 서버사이드에서는 무조건 절대경로 쓰기 '/'절대경로
public class CalendarControllerServlet extends HttpServlet {
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//   super.service(req, resp);
      
         String yearParam = request.getParameter("year");
         String monthParam = request.getParameter("month");
         String localeParam = request.getParameter("locale");
         
         Locale locale = Optional.ofNullable(localeParam)
            .map(lp->Locale.forLanguageTag(lp))
            .orElse(request.getLocale());

//          Locale locale = request.getLocale();   //reqeust header(Accept-Language)
         
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
      
         //하나의 묶음로 표현하기 위해vo필요         
         CalenderVO calVO = new CalenderVO();
         calVO.setLocale(locale);
         calVO.setTargetMonth(nextMonth);
         calVO.setBeforeMonth(beforeMonth);
         calVO.setNextMonth(nextMonth);
         
         request.setAttribute("calVO", calVO);
//         request.setAttribute("locale", locale);
//         request.setAttribute("targetMonth", targetMonth);
//         request.setAttribute("beforeMonth", beforeMonth);
//         request.setAttribute("nextMonth", nextMonth);  //->하나의 묶음로 표현하기 위해 vo 만들어주기
         
         String viewName="/WEB-INF/views/07/calendarView.jsp";
         request.getRequestDispatcher(viewName).forward(request, resp);
   }

}