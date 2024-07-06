package com.demo.unittesting.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.unittesting.entities.Item;
import com.demo.unittesting.services.ItemService;

@WebMvcTest(value = ItemContoller.class)
public class ItemControllerTesting {

	private transient MockMvc mockMvc;

	@MockBean
	private transient ItemService itemService; // no need to Inject

	@Autowired
	public ItemControllerTesting(final MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	public void test_items_endpoint() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.json("{" + "    \"itemId\": 123," + "    \"itemName\": \"Apples\"," + "    \"itemPrice\": 12,"
								+ "    \"itemQuantity\": 12" + "}"))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		/*
		 * assertThat(mvcResult.getResponse().getContentAsString()) .isEqualTo("{" +
		 * "    \"itemId\": 123," + "    \"itemName\": \"Apples\"," +
		 * "    \"itemPrice\": 12," + "    \"itemQuantity\": 12" + "}");
		 */
	}

	@Test
	public void test_items_endpoint_jsonAssert() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, "/items")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals("{" + "    \"itemId\": 123," + "    \"itemName\": \"Apples\","
				+ "    \"itemPrice\": 12," + "    \"itemQuantity\": 12" + "}",
				mvcResult.getResponse().getContentAsString(), true); // true to match all the attribute presence in
																		// response
	}

	@Test
	public void test_items_endpoint_with_service() throws Exception {
		// get the Mock api
		when(itemService.getItem()).thenReturn(new Item(113, "MacBook", 39566, 1, 3));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items").accept(MediaType.APPLICATION_JSON);

		MvcResult result = this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content()
						.json("{itemId:113,itemName:MacBook,itemPrice:39566.0,itemQuantity:1}"))
				.andReturn();

	}

	@Test
	public void test_allItems_endPoint_with_stub() throws Exception {

		when(this.itemService.getItemsList()).thenReturn(Arrays.asList(new Item(1, "Item 1", 19.99f, 10),
				new Item(2, "Item 2", 29.99f, 20), new Item(3, "Item 3", 39.99f, 30), new Item(4, "Item 4", 49.99f, 40),
				new Item(5, "Item 5", 59.99f, 50), new Item(6, "Item 6", 69.99f, 60), new Item(7, "Item 7", 79.99f, 70),
				new Item(8, "Item 8", 89.99f, 80), new Item(9, "Item 9", 99.99f, 90),
				new Item(10, "Item 10", 109.99f, 100), new Item(11, "Item 11", 119.99f, 110)));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/allItems")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = this.mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().json(
						"[{\"itemId\":1,\"itemName\":\"item 1\",\"itemPrice\":19.99,\"itemQuantity\":10,\"value\":190},{\"itemId\":2,\"itemName\":\"item 2\",\"itemPrice\":29.99,\"itemQuantity\":20,\"value\":580},{\"itemId\":3,\"itemName\":\"item 3\",\"itemPrice\":39.99,\"itemQuantity\":30,\"value\":1170},{\"itemId\":4,\"itemName\":\"item 4\",\"itemPrice\":49.99,\"itemQuantity\":40,\"value\":1960},{\"itemId\":5,\"itemName\":\"item 5\",\"itemPrice\":59.99,\"itemQuantity\":50,\"value\":2950},{\"itemId\":6,\"itemName\":\"item 6\",\"itemPrice\":69.99,\"itemQuantity\":60,\"value\":4140},{\"itemId\":7,\"itemName\":\"item 7\",\"itemPrice\":79.99,\"itemQuantity\":70,\"value\":5530},{\"itemId\":8,\"itemName\":\"item 8\",\"itemPrice\":89.99,\"itemQuantity\":80,\"value\":7120},{\"itemId\":9,\"itemName\":\"item 9\",\"itemPrice\":99.99,\"itemQuantity\":90,\"value\":8910},{\"itemId\":10,\"itemName\":\"item 10\",\"itemPrice\":109.99,\"itemQuantity\":100,\"value\":10900},{\"itemId\":11,\"itemName\":\"item 11\",\"itemPrice\":119.99,\"itemQuantity\":110,\"value\":13090}]"))
				.andReturn();

	}

	// test method to save an item into the database
	@Test
	public void test_item_saveEndPoint_with_stub() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/items/item").content(
				"{\"itemId\":1224,\"itemName\":\"item 1224\",\"itemPrice\":1224.99,\"itemQuantity\":124,\"value\":112445}")
				.accept(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8");

		MvcResult result = this.mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().json(
						"{\"itemId\":1224,\"itemName\":\"item 1224\",\"itemPrice\":1224.99,\"itemQuantity\":124,\"value\":112445}"))
				.andReturn();

	}

}
