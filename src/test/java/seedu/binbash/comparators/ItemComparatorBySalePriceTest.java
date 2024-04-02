package seedu.binbash.comparators;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemComparatorBySalePriceTest {

    @Test
    void compare_differentSalePriceAndSorted_noSwapDone() {
        ArrayList<RetailItem> itemList = new ArrayList<RetailItem>();

        RetailItem item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00, 10);
        RetailItem item2 = new RetailItem("testItem2", "Test item 2", 1,
                5.00, 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorBySalePrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentSalePriceAndNotSorted_swapDone() {
        ArrayList<RetailItem> itemList = new ArrayList<RetailItem>();

        RetailItem item1 = new RetailItem("testItem1", "Test item 1", 1,
                5.00, 1.00, 10);
        RetailItem item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorBySalePrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameSalePriceDifferentItemTypeAndSorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new PerishableRetailItem("testItem1", "Test item 1",
                1, LocalDate.of(2024, 1, 1),
                3.00, 1.00, 10);
        Item item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorBySalePrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_sameSalePriceDifferentItemTypeAndNotSorted_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00, 10);
        Item item2 = new PerishableRetailItem("testItem2", "Test item 2",
                1, LocalDate.of(2024, 1, 1),
                3.00, 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorBySalePrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameSalePriceSameItemTypeDifferentQtyAndSorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00, 10);
        Item item2 = new RetailItem("testItem2", "Test item 2", 4,
                3.00, 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorBySalePrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_sameSalePriceSameItemTypeDifferentQtyAndNotSorted_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new RetailItem("testItem1", "Test item 1", 4,
                3.00, 1.00, 10);
        Item item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorBySalePrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameSalePriceSameItemTypeSameQty_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        Item item1 = new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00, 10);
        Item item2 = new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorBySalePrice());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }
}
