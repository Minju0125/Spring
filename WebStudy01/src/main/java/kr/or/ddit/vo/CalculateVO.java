package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import kr.or.ddit.calculate.controller.NumericOperatorType;

/**
 * 한번의 사칙연산과 관련된 모든 속성을 가진 객체.
 * 
 */
public class CalculateVO implements Serializable{ //직렬화 가능한 VO
	private int leftOp;
	private int rightOp;
	
	private NumericOperatorType operator;

	public CalculateVO() {
		super();
	}

	public CalculateVO(int leftOp, int rightOp, NumericOperatorType operator) {
		super();
		this.leftOp = leftOp;
		this.rightOp = rightOp;
		this.operator = operator;
	}

	public int getLeftOp() { //java bean 의 규약에 따라 자동으로 생성
		return leftOp;
	}

	private void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}

	public int getRightOp() {
		return rightOp;
	}

	private void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}

	public NumericOperatorType getOperator() {
		return operator;
	}

	private void setOperator(NumericOperatorType operator) {
		this.operator = operator;
	}
	
	public int getResult() {
		return Optional.ofNullable(operator)
						.map(o->o.operate(leftOp, rightOp))
						.orElse(0);
	}
	
	public String getExpression() {
		return Optional.ofNullable(operator)
					   .map((o)->o.getExpression(leftOp, rightOp))
					   .orElse("");
	}

	@Override
	public int hashCode() {
		return Objects.hash(leftOp, operator, rightOp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculateVO other = (CalculateVO) obj;
		return leftOp == other.leftOp && operator == other.operator && rightOp == other.rightOp;
	}

	@Override
	public String toString() {
		return String.format("CalculateVO [%s]", getExpression());
	}
}
