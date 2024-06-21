package com.demo.unittesting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.demo.unittesting.business.BusinessImpl;
import com.demo.unittesting.services.SomeDataService;

@TestInstance(Lifecycle.PER_CLASS)
public class SomeBusinessMockTest {

	private SomeDataService dataServiceMock;

	@BeforeAll
	public void setUp() {
		this.dataServiceMock = mock(SomeDataService.class);
	}

	@Test
	public void calculateSumUsingDataService_basic() {

		// commented as this object was initiated earlier
		// SomeDataService dataServiceMock = mock(SomeDataService.class);
		// dataServiceMock retrieveAllData
		// set the returned elements dynamically into the dataServiceMock
		when(this.dataServiceMock.getData()).thenReturn(new int[] { 1, 2, 3 });
		BusinessImpl businessImpl = new BusinessImpl(this.dataServiceMock); // now inject this object as usual
		int actualResult = businessImpl.calculateSumDataService();
		int expectedResult = 6;

		assertThat(actualResult).isEqualTo(expectedResult);

	}

	@Test
	public void calculateSumUsingDataServiceMock_empty() {
		// configure the object mock to return empty list
		when(this.dataServiceMock.getData()).thenReturn(new int[] {});
		BusinessImpl businessImpl = new BusinessImpl(this.dataServiceMock); // now inject this object as usual
		int actualResult = businessImpl.calculateSumDataService();
		int expectedResult = 0;

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	public void calculateSumUsingDataServiceMock_oneValue() {
		when(this.dataServiceMock.getData()).thenReturn(new int[] { 2 });
		BusinessImpl businessImpl = new BusinessImpl(this.dataServiceMock); // now inject this object as usual
		int actualResult = businessImpl.calculateSumDataService();
		int expectedResult = 2;

		assertThat(actualResult).isEqualTo(expectedResult);
	}
}
