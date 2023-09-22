package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Layered Architecture(계층형 구조)
 * - 하나의 명령이 처리될 때 단계적인 객체(layer) 들이 순차적으로 동작하는 구조.
 * 
 * 5 layer 구조(S, 단일 책임의 분리)
 * Model layer
 * - ValueObject : domain layer
 * - DataAccessObject : persistence layer (휘발되지 않는) --> data
 * - service (Logic 객체) : business logic layer --> information (model)
 * 
 * - controller 
 * - view : presentation layer(=view layer) --> content (Content-Type)
 *
 */
public class DataBasePropertyVO implements Serializable{ // 직렬화 안되면 마샬링 안됨 
	public String propertyName;
	public String propertyValue; 
	public String description;
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	// 아래 없음 객체의 reference를 통해 비교
	@Override
	public int hashCode() {
		return Objects.hash(propertyName, propertyValue);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataBasePropertyVO other = (DataBasePropertyVO) obj;
		return Objects.equals(propertyName, other.propertyName) && Objects.equals(propertyValue, other.propertyValue);
	}
	
	//객체의 상태비교
	@Override
	public String toString() {
		return "DataBasePropertyVO [propertyName=" + propertyName + ", propertyValue=" + propertyValue
				+ ", description=" + description + "]";
	}
}
