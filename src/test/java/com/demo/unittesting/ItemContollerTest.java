package com.demo.unittesting;

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

import com.demo.unittesting.controller.ItemContoller;

@WebMvcTest(value = ItemContoller.class)
public class ItemContollerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_items_response() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("Hello World")).andReturn();

		assertThat(result.getResponse().getContentAsString()).isEqualTo("Hello World");
	}

}
