package com.exercise;

/**
 * Class representing item
 * This has following:
 * 1. Product (item type)
 * 2. Quantity
 */
public class Item {

	/*
	 * Product (item type)
	 */
    private Product product;

    /*
     * Quantity
     */
    private int qty;

    /**
     * Constructor using product
     */
    public Item(Product product) {
        this.product = product;
        this.qty = 1;
    }

    /**
     * Constructor using product and quantity.
     */
    public Item(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    /**
     * Returns product
     *
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Returns quantity
     *
     * @return quantity
     */
    public int getQty() {
        return qty;
    }

    /**
     * Sets quantity
     *
     * @param qty quantity
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * Returns product name
     *
     * @return name
     */
    public String getName() {
        return product.name();
    }

    /**
     * Returns product price
     *
     * @return price
     */
    public double getPrice() {
        return product.getPrice();
    }

    /**
     * Returns total price
     *
     * @return total price
     */
    public double getTotalPrice() {
        return product.getPrice() * qty;
    }

    /**
     * This is to check whether item is equal to the object parameter
     * This makes sure following works:
     * BobsAutoRegisterTest->testParseOrder()->assertEquals(expectedItems, actualItems)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (qty != item.qty) return false;
        if (product != item.product) return false;

        return true;
    }
}