package com.demo.unittesting;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemStub {

	private int itemId;
	private String itemName;
	private double itemPrice;
	private int quantity;
	private double total; // Assuming you want to keep the 'total' attribute as well.

	public ItemStub(int itemId, String itemName, double itemPrice, int quantity) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.total = itemPrice * quantity; // Automatically calculate total during instantiation.
	}

	public List<ItemStub> getItemsList() {
		List<ItemStub> items = new ArrayList<>();
		items.add(new ItemStub(1, "ItemStub 1", 19.99, 10));
		items.add(new ItemStub(2, "Item 2", 29.99, 20));
		items.add(new ItemStub(3, "Item 3", 39.99, 30));
		items.add(new ItemStub(4, "Item 4", 49.99, 40));
		items.add(new ItemStub(5, "Item 5", 59.99, 50));
		items.add(new ItemStub(6, "Item 6", 69.99, 60));
		items.add(new ItemStub(7, "Item 7", 79.99, 70));
		items.add(new ItemStub(8, "Item 8", 89.99, 80));
		items.add(new ItemStub(9, "Item 9", 99.99, 90));
		items.add(new ItemStub(10, "Item 10", 109.99, 100));
		items.add(new ItemStub(11, "Item 11", 119.99, 110));
		return items;
	}
}
