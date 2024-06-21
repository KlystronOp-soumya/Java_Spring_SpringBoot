package com.demo.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.unittesting.business.BusinessImpl;
import com.demo.unittesting.services.SomeDataService;

//create a stub which returns data to reduce the expensive database calls
class DataServiceStub implements SomeDataService {

	@Override
	public int[] getData() {
		// TODO Auto-generated method stub
		return new int[] { 1, 2, 3 };
	}

}

public class SomeBusinessStubTest {

	@Test
	public void calculateSumUsingServiceStub_basic() {
		DataServiceStub serviceStub = new DataServiceStub();

		BusinessImpl businessImpl = new BusinessImpl(serviceStub);
		int actualResult = businessImpl.calculateSumDataService();
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult, "Wrong sum");

	}

}
