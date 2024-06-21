package com.demo.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.unittesting.business.BusinessImpl;

class SomeBusinessTest {

	// first unit test to test the BusinessImpl class
	@Test
	public void calculateSum_basic() {
		BusinessImpl businessImpl = new BusinessImpl();
		int actualResult = businessImpl.calculateSum(new int[] { 1, 2, 3 });
		int expectedResult = 7;

		assertEquals(expectedResult, actualResult, "Wrong sum");
		;
	}

	// check for the empty sum
	@Test
	public void calculateSum_empty() {
		BusinessImpl businessImpl = new BusinessImpl();
		int actualResult = businessImpl.calculateSum(new int[] {});
		int expectedResult = 0;

		assertEquals(expectedResult, actualResult, "Sum in not 0");
	}

}
