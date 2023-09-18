package kr.or.ddit.calculate.controller;

@FunctionalInterface //람다식 적용가능
public interface BiOperandOperator {
	public int operate(int leftOp, int rightOp);
}
