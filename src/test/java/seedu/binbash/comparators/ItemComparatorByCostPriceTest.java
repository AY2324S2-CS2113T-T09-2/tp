package seedu.binbash.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.binbash.item.Item;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.RetailItem;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemComparatorByCostPriceTest {
    ArrayList<Item> itemList;
    String nameOfFirstItem;

    @BeforeEach
    void setUp() {
        itemList = new ArrayList<Item>();
    }

    @Test
    void compare_differentCostPriceAndSorted_noSwapDone() {
        itemList.add(new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00, 10));
        itemList.add(new RetailItem("testItem2", "Test item 2", 1,
                3.00, 5.00, 10));

        itemList.sort(new ItemComparatorByCostPrice());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentCostPriceAndNotSorted_swapDone() {
        itemList.add(new RetailItem("testItem1", "Test item 1", 1,
                3.00, 5.00, 10));
        itemList.add(new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByCostPrice());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceDifferentItemTypeAndSorted_noSwapDone() {
        itemList.add( new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00, 10));
        itemList.add(new OperationalItem("testItem2", "Test item 2", 1,
                1.00, 10));

        itemList.sort(new ItemComparatorByCostPrice());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceDifferentItemTypeAndNotSorted_swapDone() {
        itemList.add(new OperationalItem("testItem1", "Test item 1", 1,
                1.00, 10));
        itemList.add(new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByCostPrice());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceSameItemTypeDifferentQtyAndSorted_noSwapDone() {
        itemList.add(new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00, 10));
        itemList.add(new RetailItem("testItem2", "Test item 2", 5,
                3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByCostPrice());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceSameItemTypeDifferentQtyAndNotSorted_swapDone() {
        itemList.add(new RetailItem("testItem1", "Test item 1", 5,
                3.00, 1.00, 10));
        itemList.add(new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByCostPrice());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameCostPriceSameItemTypeSameQtyAndSorted_noSwapDone() {
        itemList.add(new RetailItem("testItem1", "Test item 1", 1,
                3.00, 1.00, 10));
        itemList.add(new RetailItem("testItem2", "Test item 2", 1,
                3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByCostPrice());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }
}
