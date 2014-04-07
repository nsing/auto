package com.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents cash register.
 * This has functions for following:
 * 1. Accepts purchased products names as argument.
 * 2. Creates an order from it.
 * 3. Prints the order receipt.
 */
public class AutoRegister {

	/**
	 * Main method.
	 * This is used to run the cash register.
	 *
	 * @param productNames product names
	 */
    public static void main(String[] productNames) {
        Order order = parseOrder(productNames);
        //No need to call getTotalForOrder as order class has a method returning total
        String orderReceipt = getReceiptForOrder(order);
        System.out.println(orderReceipt);
    }

    /**
     * This creates order from an array having purchased items names.
     *
     * @param productNames product names
     * @return order
     */
    static protected Order parseOrder(String[] productNames) {
        List<Item> items = new ArrayList<Item>(productNames.length);
        for (String productName : productNames) {
        	try {
        		items.add(new Item(Product.valueOf(productName)));
        	}
        	catch(IllegalArgumentException iae) {
        		System.out.println(String.format("%s is not a valid product " +
        				"name so ignoring it", productName));
        	}
        }
        return new Order(items);
    }

    /**
     * This returns order total.
     *
     * @param order order
     * @return order total
     */
    static protected double getTotalForOrder(Order order) {
        return order.getTotal();
    }

    /**
     * This returns order receipt.
     * It doesn't need order total as parameter as order already got a method
     * for the same
     *
     * @param order order
     * @return total
     */
    static protected String getReceiptForOrder(Order order) {
        StringBuilder receipt = new StringBuilder();
        List<Item> items = order.getItems();
        for (Item item : items) {
            receipt.append(String.format("%d %s @£%.2f: £%.2f\n", item.getQty(),
                    item.getName(), item.getPrice(), item.getTotalPrice()));
        }
        receipt.append(String.format("GRAND TOTAL : £%.2f\n", order.getTotal()));
        return receipt.toString();
    }
}