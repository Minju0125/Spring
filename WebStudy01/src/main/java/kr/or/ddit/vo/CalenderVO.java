package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Objects;

/*
	VO(Value Object), DTO(Data Transfer Object), Model, Bean(Java bean 규약에 따른 객체)
		- 하나의 명령이 처리되는 과정에서 두개 이상의 객체를 사용하는 경우
		- 객체와 객체 사이에서 전달되는 데이터가 있어야하는 경우 사용
		- VO의 용도는 레이어 사이에서 값을 전달한다.
	== : reference 비교, (객체가 생성되면 힙 메모리에 할당된 주소가 있는데, 이 주소를 비교함 -> 결과가 true 이면 완전히 동일한 객체임)
	equals : 상태 비교	(객체는 다른데 안에 있는 property 가 다름)
	
	equals 메소드에 대한 오버라이딩 필요
*/
public class CalenderVO implements Serializable{
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
		return "calendarVO [locale=" + locale + ", targetMonth=" + targetMonth + ", beforeMonth=" + beforeMonth
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
		CalenderVO other = (CalenderVO) obj;
		return Objects.equals(beforeMonth, other.beforeMonth) && Objects.equals(locale, other.locale)
				&& Objects.equals(nextMonth, other.nextMonth) && Objects.equals(targetMonth, other.targetMonth);
	}
	
	
}
