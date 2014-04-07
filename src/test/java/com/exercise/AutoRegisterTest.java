package com.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoRegisterTest {

    private static final Item[] TEST_ORDER_1_ITEMS = {
            new Item(Product.CAR_WASH_WITH_WAX)};

    private static final Item[] TEST_ORDER_2_ITEMS = {
            new Item(Product.CAR_WASH_WITH_WAX),
            new Item(Product.VALET)
    };

    private static final Item[] TEST_ORDER_3_ITEMS = {
            new Item(Product.CAR_WASH_NORMAL),
            new Item(Product.SERVICING)
    };

    private static final Item[] TEST_ORDER_4_ITEMS = {
            new Item(Product.SERVICING),
            new Item(Product.VALET, 2),
    };

    @Before
    public void setUp() throws Exception {

    }

    /**
     * This tests order parsing
     */
    @Test
    public void testParseOrder() {
        List<Item> expectedItems = Arrays.asList(TEST_ORDER_1_ITEMS);
        Order actualOrder = AutoRegister.parseOrder(new String[]{"CAR_WASH_WITH_WAX"});
        List<Item> actualItems = actualOrder.getItems();
        assertEquals(expectedItems, actualItems);

        expectedItems = Arrays.asList(TEST_ORDER_2_ITEMS);
        actualOrder = AutoRegister.parseOrder(new String[]{"CAR_WASH_WITH_WAX", "VALET"});
        actualItems = actualOrder.getItems();
        assertEquals(expectedItems, actualItems);

        expectedItems = Arrays.asList(TEST_ORDER_3_ITEMS);
        actualOrder = AutoRegister.parseOrder(new String[]{"CAR_WASH_NORMAL", "SERVICING"});
        actualItems = actualOrder.getItems();
        assertEquals(expectedItems, actualItems);

        expectedItems = Arrays.asList(TEST_ORDER_4_ITEMS);
        actualOrder = AutoRegister.parseOrder(new String[]{"SERVICING", "VALET", "VALET"});
        actualItems = actualOrder.getItems();
        assertEquals(expectedItems, actualItems);
    }

    /**
     * This tests order total
     */
    @Test
    public void testGetTotalForOrder() {
        Order order = new Order();
        assertEquals(0.0, AutoRegister.getTotalForOrder(order), 0);

        order.addItem(new Item(Product.CAR_WASH_WITH_WAX));
        assertEquals(18.0, AutoRegister.getTotalForOrder(order), 0);

        order.addItem(new Item(Product.SERVICING));
        order.addItem(new Item(Product.VALET));
        assertEquals(143.0, AutoRegister.getTotalForOrder(order), 0);

        order.addItem(new Item(Product.SERVICING));
        assertEquals(243.0, AutoRegister.getTotalForOrder(order), 0);

        Arrays.asList(Product.values());
        List<Product> products = Arrays.asList(Product.values());
        List<Item> items = new ArrayList<Item>(products.size());
        for (Product product : products) {
            items.add(new Item(product));
        }
        order = new Order(items);
        assertEquals(188.0, AutoRegister.getTotalForOrder(order), 0);
    }

    /**
     * This tests order receipt
     */
    @Test
    public void testGetReceiptForOrder() {
        String expectedReceipt =
                "1 CAR_WASH_WITH_WAX @£18.00: £18.00\n" +
                        "GRAND TOTAL : £18.00\n";
        Order actualOrder = AutoRegister.parseOrder(new String[]{"CAR_WASH_WITH_WAX"});
        String actualReceipt = AutoRegister.getReceiptForOrder(actualOrder);
        assertEquals(expectedReceipt, actualReceipt);


        expectedReceipt = "1 SERVICING @£100.00: £100.00\n" +
                "2 VALET @£25.00: £50.00\n" +
                "GRAND TOTAL : £150.00\n";
        actualOrder = AutoRegister.parseOrder(new String[]{"SERVICING", "VALET", "VALET"});
        actualReceipt = AutoRegister.getReceiptForOrder(actualOrder);
        assertEquals(expectedReceipt, actualReceipt);
    }

    /*Following are some additional tests verifying some restrictions and
      feature. These can be put in a separate class called OrderTest but for
      simplicity have been put here*/

    /**
     * This tests the condition that the list of items cannot have multiple
     * item objects containing the same product.
     */
    @Test
    public void testMultipleOccurrenceOfProduct() {
        Order order = AutoRegister.parseOrder(new String[]{"CAR_WASH_WITH_WAX", "VALET", "VALET"});
        List<Item> items = order.getItems();
        assertEquals(2, items.size());
    }

    /**
     * This tests whether order items list can be modified or not
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testOrderItemsListModification() {
        Order order = AutoRegister.parseOrder(new String[]{"CAR_WASH_WITH_WAX", "VALET"});
        List<Item> items = order.getItems();
        items.add(new Item(Product.VALET));
    }

    /**
     * This tests the update of an items quantities
     */
    @Test
    public void testItemQuantityUpdation() {
        Order order = AutoRegister.parseOrder(new String[]{"CAR_WASH_WITH_WAX", "VALET"});
        List<Item> items = order.getItems();
        for(Item item : items) {
        	item.setQty(10);
        }
        assertEquals(430, order.getTotal(), 0);
    }
}