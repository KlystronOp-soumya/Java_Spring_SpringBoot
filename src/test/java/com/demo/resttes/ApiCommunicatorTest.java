package com.demo.resttes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.demo.resttest.ApiConfigMap;
import com.demo.resttest.service.ApiCommunicator;

class ApiCommunicatorTest {
	
	@Test
	void testGetPostsCommunicator(){
		
		ApiConfigMap config = mock(ApiConfigMap.class);
		
		when(config.getPostsApi()).thenReturn("");
		
		ApiCommunicator apiCommunicator = new ApiCommunicator(config);
		
		var respEn = apiCommunicator.getPostsCommunicator();
		
		assertNotNull(respEn);
		assertEquals("GET", respEn.getMethod().name());
		
	}
}
