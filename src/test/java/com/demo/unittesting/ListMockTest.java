package com.demo.unittesting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class ListMockTest {
	List<String> mockList = mock(List.class);

	@Test
	public void test_returnDifferentValues() {

		when(mockList.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mockList.size());
		assertEquals(10, mockList.size());
	}

	@Test
	public void test_returnWithParams() {
		when(this.mockList.get(0)).thenReturn("in28Minutes");

		assertEquals("in28Minutes", this.mockList.get(0));
		assertThat(mockList.get(1)).isNull();
	}

	// test with the any*() method in Mockito
	// using the argument matcher
	@Test
	public void test_returnWithAnyParams() {
		when(this.mockList.get(anyInt())).thenReturn("in28Minutes");
		assertEquals("in28Minutes", this.mockList.get(0));
		assertEquals("in28Minutes", this.mockList.get(1));
	}

	@Test
	public void test_verificationBasics() {
		// System Under test
		String val1 = mockList.get(0);
		String val2 = mockList.get(1);

		// to check whether a method existis or not
		verify(mockList, atLeast(1)).get(0); // default is one but called twice hence threw exceptuon
		verify(mockList, atLeast(1)).get(anyInt());
		// verify(mockList, times(1)).get(anyInt()); // it will fail as above we have
		// called twice
		verify(mockList, times(2)).get(anyInt());
		// called atleast once
		verify(mockList, atLeast(1)).get(anyInt());
		// never called
		verify(mockList, never()).get(2);
		verify(mockList, atLeastOnce()).get(anyInt());

	}

	// finding the argument passed
	@Test
	public void test_argumentCapturing() {
		// SUT
		mockList.add("Something");
		// verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mockList).add(captor.capture()); // capturing the argument passed

		assertEquals("Something", captor.getValue());
	}

	public void test_multipleArgumentCapturing() {
		// SUT
		mockList.add("str1");
		mockList.add("str2");
		// verification
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mockList, times(2)).add(captor.capture()); // capturing the argument passed
		List<String> allValues = captor.getAllValues();
		assertEquals("str1", allValues.get(0));
		assertEquals("str2", allValues.get(1));
	}

	@Test
	public void spying() {
		ArrayList arrayListMock = mock(ArrayList.class);
		arrayListMock.get(0);
		arrayListMock.size();
		arrayListMock.add("str1");
		arrayListMock.add("str2");
		arrayListMock.size(); // the size is still 0 as this is a Mock obj
		when(arrayListMock.size()).thenReturn(5); // here the stub is created
		assertEquals(5, arrayListMock.size());

		// now create the spy
		// if we are creating a spy then the class behaviuor is reatined

		ArrayList arrayListSpy = spy(ArrayList.class);
		assertThat(arrayListSpy.size()).isEqualTo(0);
		arrayListSpy.add("atr1");
		assertThat(arrayListSpy.size()).isEqualTo(1); // as this is not mock so new size will be used
		verify(arrayListSpy).add("str2");
	}

}
