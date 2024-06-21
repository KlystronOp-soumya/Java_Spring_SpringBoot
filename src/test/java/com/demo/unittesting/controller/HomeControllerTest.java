package com.demo.unittesting.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@RunWith(SpringRunner.class) -- not required
@WebMvcTest(value = HomeController.class)
public class HomeControllerTest {

	private MockMvc mockMvc;

	// constructor autowiring is required as it is not able to find the param
	@Autowired
	public HomeControllerTest(final MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	public void test_hello_world_endpoint() throws Exception {
		// call hello-world
		// verify response
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello World")).andReturn();

		assertThat(result.getResponse().getContentAsString()).isEqualTo("Hello World");

	}

}
