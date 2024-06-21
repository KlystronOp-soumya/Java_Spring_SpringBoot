package com.demo.unittesting.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.demo.unittesting.dao.ItemRepository;
import com.demo.unittesting.entities.Item;

@Service
public class ItemService {

	private static final Logger LOGGER = LogManager.getLogger(ItemService.class);

	private ItemRepository itemRepository;

	// @Autowired
	public ItemService(final ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public Item getItem() {
		LOGGER.info("Get the items from DAO");
		return new Item(113, "MacBook", 39566, 1, 3);
	}

	public List<Item> getItemsList() {
		LOGGER.info("Getting items from database");
		Optional<List<Item>> itemsOptional = Optional.of(this.itemRepository.findAll());
		LOGGER.info("Data found:: " + itemsOptional.isPresent());

		Consumer<Item> getTotalPrice = (Item i) -> i.setValue((int) i.getItemPrice() * i.getItemQuantity());
		itemsOptional.get().forEach(getTotalPrice);
		itemsOptional.get().forEach((i) -> LOGGER.info(i));
		return itemsOptional.orElseThrow(() -> new NullPointerException("No items found"));

	}

}
