package seedu.binbash.comparators;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemComparatorByExpiryDateTest {

    @Test
    void compare_differentExpiryDateOpToOpAndSorted_noSwapDone() {
        ArrayList<PerishableOperationalItem> itemList = new ArrayList<PerishableOperationalItem>();

        PerishableOperationalItem item1 = new PerishableOperationalItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10);
        PerishableOperationalItem item2 = new PerishableOperationalItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 4, 1), 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateOpToOpAndNotSorted_swapDone() {
        ArrayList<PerishableOperationalItem> itemList = new ArrayList<PerishableOperationalItem>();

        PerishableOperationalItem item1 = new PerishableOperationalItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 4, 1), 1.00, 10);
        PerishableOperationalItem item2 = new PerishableOperationalItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10);


        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateSameQtyOpToOp_noSwapDone() {
        ArrayList<PerishableOperationalItem> itemList = new ArrayList<PerishableOperationalItem>();

        PerishableOperationalItem item1 = new PerishableOperationalItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10);
        PerishableOperationalItem item2 = new PerishableOperationalItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10);


        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateOpToReSorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableOperationalItem item1 = new PerishableOperationalItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10);
        PerishableRetailItem item2 = new PerishableRetailItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 4, 1), 3.00, 1.00, 10);


        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateOpToReNotSorted_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableOperationalItem item1 = new PerishableOperationalItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 4, 1), 1.00, 10);
        PerishableRetailItem item2 = new PerishableRetailItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10);


        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateOpToRe_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableOperationalItem item1 = new PerishableOperationalItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10);
        PerishableRetailItem item2 = new PerishableRetailItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10);


        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateReToOpSorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableRetailItem item1 = new PerishableRetailItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10);
        PerishableOperationalItem item2 = new PerishableOperationalItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 4, 1), 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateReToOpNotSorted_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableRetailItem item1 = new PerishableRetailItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 4, 1), 3.00, 1.00, 10);
        PerishableOperationalItem item2 = new PerishableOperationalItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateReToOp_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableRetailItem item1 = new PerishableRetailItem("testItem1",
                "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10);
        PerishableOperationalItem item2 = new PerishableOperationalItem("testItem2",
                "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateReToReSorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableRetailItem item1 = new PerishableRetailItem("testItem1", "Test item 1",
                1, LocalDate.of(2024, 1, 1), 3.00, 1.00, 10);
        PerishableRetailItem item2 = new PerishableRetailItem("testItem2", "Test item 2",
                1, LocalDate.of(2024, 2, 1), 3.00,1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateReToReNotSorted_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableRetailItem item1 = new PerishableRetailItem("testItem1", "Test item 1",
                1, LocalDate.of(2024, 2, 1), 3.00,
                1.00, 10);
        PerishableRetailItem item2 = new PerishableRetailItem("testItem2", "Test item 2",
                1, LocalDate.of(2024, 1, 1), 3.00,
                1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateReToReDifferentQtySorted_noSwapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableRetailItem item1 = new PerishableRetailItem("testItem1", "Test item 1",
                1, LocalDate.of(2024, 1, 1), 3.00,
                1.00, 10);
        PerishableRetailItem item2 = new PerishableRetailItem("testItem2", "Test item 2",
                4, LocalDate.of(2024, 1, 1), 3.00,
                1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateReToReDifferentQtyNotSorted_swapDone() {
        ArrayList<Item> itemList = new ArrayList<Item>();

        PerishableRetailItem item1 = new PerishableRetailItem("testItem1", "Test item 1",
                4, LocalDate.of(2024, 1, 1), 3.00,
                1.00, 10);
        PerishableRetailItem item2 = new PerishableRetailItem("testItem2", "Test item 2",
                1, LocalDate.of(2024, 1, 1), 3.00,
                1.00, 10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByExpiryDate());
        String nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }
}
