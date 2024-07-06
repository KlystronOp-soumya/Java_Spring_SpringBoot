package com.demo.unittesting.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;

import com.demo.unittesting.dao.ItemRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = { "classpath:test-configuration.properties" })
public class ItemControllerIT {

	// get the restTemplate
	@Autowired
	private TestRestTemplate restTemplate;

	// to mock out external dependencies
	@MockBean
	private ItemRepository itemRepository;

	@Test
	private void contextLoads() throws JSONException {
		String response = this.restTemplate.getForObject("/items/allItems", String.class);

		// the strict mode is false as we want to partially match the JSON
		JSONAssert.assertEquals("<<put the actual response from postman>>", response, false);

	}

}
