package com.demo.unittesting.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.unittesting.entities.Item;
import com.demo.unittesting.services.ItemService;

@RestController
public class ItemContoller {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemContoller.class);

	@Autowired
	private transient ItemService itemService;

	/*
	 * { "itemId": 123, "itemName": "Apples", "itemPrice": 12, "itemQuantity": 12 }
	 */
	@GetMapping("/items")
	public Item getItem() {
		LOGGER.info("items endpoint :: getting item");
		return this.itemService.getItem();
	}

	@GetMapping("/items/allItems")
	public List<Item> getAllItems() {
		try {
			return this.itemService.getItemsList();
		} catch (Exception e) {
			LOGGER.debug("Cause: " + e.getCause().toString() + " :: " + e.getMessage());
		}
		return null;
	}

	@PostMapping(path = "/items/item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Item saveAnItem(@RequestBody @NonNull final Item item) {

		try {

			this.itemService.saveAnItem(item);
		} catch (Exception e) {
			LOGGER.debug("the item can not be saved");
			LOGGER.debug("Cause: " + e.getCause().toString() + " :: " + e.getMessage());

		}

		return item;

	}
}
