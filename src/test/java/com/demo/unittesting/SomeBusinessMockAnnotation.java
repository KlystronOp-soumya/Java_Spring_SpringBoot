package com.demo.unittesting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.unittesting.business.BusinessImpl;
import com.demo.unittesting.services.SomeDataService;

/*
 * If you are using JUnit 5, in the next lecture use

@ExtendWith(MockitoExtension.class)

instead of @RunWith(MockitoJUnitRunner.class)
 * */

@ExtendWith(MockitoExtension.class)
public class SomeBusinessMockAnnotation {

	@InjectMocks
	BusinessImpl someBusinessImpl = new BusinessImpl();

	@Mock
	SomeDataService someDataServiceMock;

	@Test
	public void calculateSumUsingDataService_basic() {
		when(this.someDataServiceMock.getData()).thenReturn(new int[] { 1, 2, 3 });
		// assertThat(this.someBusinessImpl.calculateSum(this.someDataServiceMock.getData())).isEqualTo(6);
		assertThat(this.someBusinessImpl.calculateSumDataService()).isEqualTo(6);

	}

}
