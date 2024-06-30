package com.demo.unittesting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.unittesting.dao.ItemRepository;
import com.demo.unittesting.entities.Item;
import com.demo.unittesting.services.ItemService;

//@RunWith(MockitoJUnitRunner.class) -- this is for Junit4
@ExtendWith(MockitoExtension.class)
public class ItemBusinessServiceTest {

	@InjectMocks
	private ItemService itemService;

	@Mock
	private ItemRepository itemRepository;

	// write down the test method
	@Test
	public void test_getItemList() {

		when(this.itemRepository.findAll()).thenReturn(Arrays.asList(new Item(1, "Item 1", 19.99F, 10),
				new Item(2, "Item 2", 29.99F, 20), new Item(3, "Item 3", 39.99F, 30), new Item(4, "Item 4", 49.99F, 40),
				new Item(5, "Item 5", 59.99F, 50), new Item(6, "Item 6", 69.99F, 60), new Item(7, "Item 7", 79.99F, 70),
				new Item(8, "Item 8", 89.99F, 80), new Item(9, "Item 9", 99.99F, 90),
				new Item(10, "Item 10", 109.99F, 100), new Item(11, "Item 11", 119.99F, 110)));

		List<Item> items = this.itemService.getItemsList();
		assertEquals(190, items.get(0).getValue()); // using Junit assertion
		assertThat(items.get(1).getValue()).isEqualTo(580); // usign AssertJ assertion
		assertThat(items.get(2)).isEqualTo(300); // this will fail as the actual is 1170
	}

}
