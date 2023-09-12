package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Objects;

/**
 * VO(Value Object), DTO(Data Transfer Object), Model, Bean(Java Bean 규약에 따른 객체)
 * == : reference 비교, equals : 상태 비교
 */
public class CalendarVO implements Serializable{
	private Locale locale;
	private YearMonth targetMonth;
	private YearMonth beforeMonth;
	private YearMonth nextMonth;
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public YearMonth getTargetMonth() {
		return targetMonth;
	}
	public void setTargetMonth(YearMonth targetMonth) {
		this.targetMonth = targetMonth;
	}
	public YearMonth getBeforeMonth() {
		return beforeMonth;
	}
	public void setBeforeMonth(YearMonth beforeMonth) {
		this.beforeMonth = beforeMonth;
	}
	public YearMonth getNextMonth() {
		return nextMonth;
	}
	public void setNextMonth(YearMonth nextMonth) {
		this.nextMonth = nextMonth;
	}
	
	@Override
	public String toString() {
		return "CalendarVO [locale=" + locale + ", targetMonth=" + targetMonth + ", beforeMonth=" + beforeMonth
				+ ", nextMonth=" + nextMonth + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(beforeMonth, locale, nextMonth, targetMonth);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalendarVO other = (CalendarVO) obj;
		return Objects.equals(beforeMonth, other.beforeMonth) && Objects.equals(locale, other.locale)
				&& Objects.equals(nextMonth, other.nextMonth) && Objects.equals(targetMonth, other.targetMonth);
	}
	
	
	
}
