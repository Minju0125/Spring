package kr.or.ddit.calculate;

@FunctionalInterface
public interface BiOperandOperator {
	public int operate(int leftOp, int rightOp);
}
