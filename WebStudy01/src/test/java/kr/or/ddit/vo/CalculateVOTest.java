package kr.or.ddit.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.calculate.NumericOperatorType;

class CalculateVOTest {

	@Test
	void test1() {
		CalculateVO dummy = new CalculateVO(3, 5, NumericOperatorType.PLUS);
		System.out.println(dummy);
	}
	
	@Test
	void test2() {
		CalculateVO dummy = new CalculateVO();
		System.out.println(dummy);
	}

}
