package com.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing order.
 * It has a list of item objects.
 * Please note that list of items cannot have multiple item objects containing
 * the same product. Item class has product (item type).
 */
public class Order {

	/*
     * Order items
     */
    private List<Item> items;

    /**
     * Default constructor
     */
    public Order() {
        this.items = new ArrayList<Item>();
    }

    /**
     * Constructor with List of Item objects
     */
    public Order(List<Item> newItems) {
        this.items = new ArrayList<Item>(newItems.size());
        addItems(newItems);
    }

    /**
     * This returns unmodifiableList so that list can be updated from outside
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * This adds a new item
     */
    public void addItem(Item newItem) {
    	//Find out whether an item for the given product is already there or not
        Item matchedItem = matchItem(newItem.getProduct());
        if(null == matchedItem) { //No item found
            items.add(newItem);
        }
        else { //Item found
            matchedItem.setQty(matchedItem.getQty() + newItem.getQty());
        }
    }

    /**
     * This adds new items
     */
    public void addItems(List<Item> newItems) {
        for(Item newItem : newItems) {
            addItem(newItem);
        }
    }

    /**
     * This returns order total
     *
     * @return total
     */
    public double getTotal() {
    	double total = 0.0;
    	for(Item item : items) {
    		total += item.getTotalPrice();
    	}
    	return total;
    }

    /**
     * This returns Item object or null depending on whether items list
     * contains an Item object for the given product or not
     */
    private Item matchItem(Product product) {
        Item matchedItem = null;
        for (Item item : items) {
            if (product == item.getProduct()) {
                matchedItem = item;
                break;
            }
        }
        return matchedItem;
    }

}