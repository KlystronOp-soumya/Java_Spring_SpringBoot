package com.demo.mockito.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;

import java.util.List;

import javax.swing.ListModel;

import org.junit.Test;

public class ListTest {

	@Test
	public <E> void testListMockSize() {
		List<E> listMock = mock(List.class);
		when(listMock.size()).thenReturn(2) ;
		assertEquals(2, listMock.size());
	}
	
	@Test
	public <E> void testListMockSize_ReturnMultipleValues() {
		List<E> listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3) ;
		assertEquals(2, listMock.size());//first time returns 2
		assertEquals(3, listMock.size());//second time returns 3
	}
	
	@Test
	public  void testListMockGet() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		//Argument matcher anyByte,anyLong,anyObject there are a lot of such matchers
		when(listMock.get(anyInt())).thenReturn("World");
		when(listMock.get(0)).thenReturn("hello") ;
		assertEquals(2, listMock.size());//first time returns 2
		assertEquals("hello", listMock.get(0));
		//assertEquals(null, listMock.get(1));
	}
	@Test(expected = RuntimeException.class)
	public  void testListMockException() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		//Argument matcher anyByte,anyLong,anyObject there are a lot of such matchers
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("This is an runtime issue"));
		listMock.get(0);
	}
}
