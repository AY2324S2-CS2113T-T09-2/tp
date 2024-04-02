package seedu.binbash.comparators;

import org.junit.jupiter.api.Test;

import seedu.binbash.item.Item;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.RetailItem;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemComparatorByCostPriceTest {

    @Test
    void compare_differentCostPriceAndSorted_noSwapDone() {
        ArrayList<RetailItem> itemList = new ArrayList<RetailItem>();

        RetailItem item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00);
        RetailItem item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 5.00);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByCostPrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentCostPriceAndNotSorted_swapDone() {
        ArrayList<RetailItem> itemList = new ArrayList<RetailItem>();

        RetailItem item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 5.00);
        RetailItem item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByCostPrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceDifferentItemTypeAndSorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00);
        Item item2 = new OperationalItem("testItem2", "Test item 2", 1,
                1.00);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByCostPrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceDifferentItemTypeAndNotSorted_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new OperationalItem("testItem1", "Test item 1", 1,
                1.00);
        Item item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByCostPrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceSameItemTypeDifferentQtyAndSorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00);
        Item item2 = new RetailItem("testItem2", "Test item 2", 5,
                3.00, 1.00);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByCostPrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceSameItemTypeDifferentQtyAndNotSorted_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new RetailItem("testItem1", "Test item 1", 5,
                3.00, 1.00);
        Item item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByCostPrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceSameItemTypeSameQtyAndSorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00);
        Item item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByCostPrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }
}
