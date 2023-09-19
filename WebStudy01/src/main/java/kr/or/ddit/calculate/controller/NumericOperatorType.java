package kr.or.ddit.calculate.controller;

public enum NumericOperatorType {
	PLUS('+', (l,r)-> l+r),
	MINUS('-',  (l,r)-> l-r),
	MULTIPLY('*',  (l,r)-> l*r), 
	DIVIDE('/',  (l,r)-> {return l/r;}),
	MODULAR('%', (l,r)-> 1%r);
	/*
	자기 내부에 존재하는 상수들을 배열의 형태로 관리함 => INDEX 가 존재함 (선언된 순서대로)
	class=> property 존재가능
	*/

	private NumericOperatorType(char sign, BiOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	private char sign;
	private BiOperandOperator realOperator;
	
	public char getSign() {
		return sign;
	}
	
	public int operate(int leftOp, int rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
	
	public String getExpression(int leftOp, int rightOp) {
		return String.format("%d %c %d = %d", leftOp, sign, rightOp, operate(leftOp, rightOp));
	}
}
