package seedu.binbash.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemComparatorByExpiryDateTest {
    ArrayList<Item> itemList;
    String nameOfFirstItem;

    @BeforeEach
    void setUp() {
        itemList = new ArrayList<Item>();
    }

    @Test
    void compare_differentExpiryDateOpToOpAndSorted_noSwapDone() {
        itemList.add(new PerishableOperationalItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10));
        itemList.add(new PerishableOperationalItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 4, 1), 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateOpToOpAndNotSorted_swapDone() {
        itemList.add(new PerishableOperationalItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 4, 1), 1.00, 10));
        itemList.add(new PerishableOperationalItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateSameQtyOpToOp_noSwapDone() {
        itemList.add(new PerishableOperationalItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10));
        itemList.add(new PerishableOperationalItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateOpToReSorted_noSwapDone() {
        itemList.add(new PerishableOperationalItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10));
        itemList.add(new PerishableRetailItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 4, 1), 3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateOpToReNotSorted_swapDone() {
        itemList.add(new PerishableOperationalItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 4, 1), 1.00, 10));
        itemList.add(new PerishableRetailItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateOpToRe_swapDone() {
        itemList.add(new PerishableOperationalItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10));
        itemList.add(new PerishableRetailItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateReToOpSorted_noSwapDone() {
        itemList.add(new PerishableRetailItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));
        itemList.add(new PerishableOperationalItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 4, 1), 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateReToOpNotSorted_swapDone() {
        itemList.add(new PerishableRetailItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 4, 1), 3.00, 1.00, 10));
        itemList.add(new PerishableOperationalItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateReToOp_noSwapDone() {
        itemList.add(new PerishableRetailItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));
        itemList.add(new PerishableOperationalItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateReToReSorted_noSwapDone() {
        itemList.add(new PerishableRetailItem("testItem1", "Test item 1",
                1, LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));
        itemList.add(new PerishableRetailItem("testItem2", "Test item 2",
                1, LocalDate.of(2024, 2, 1), 3.00,1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_differentExpiryDateReToReNotSorted_swapDone() {
        itemList.add(new PerishableRetailItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 2, 1), 3.00, 1.00, 10));
        itemList.add( new PerishableRetailItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateReToReDifferentQtySorted_noSwapDone() {
        itemList.add(new PerishableRetailItem("testItem1", "Test item 1", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));
        itemList.add(new PerishableRetailItem("testItem2", "Test item 2", 4,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem1",nameOfFirstItem);
    }

    @Test
    void compare_sameExpiryDateReToReDifferentQtyNotSorted_swapDone() {
        itemList.add(new PerishableRetailItem("testItem1", "Test item 1", 4,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));
        itemList.add(new PerishableRetailItem("testItem2", "Test item 2", 1,
                LocalDate.of(2024, 1, 1), 3.00, 1.00, 10));

        itemList.sort(new ItemComparatorByExpiryDate());
        nameOfFirstItem = itemList.get(0).getItemName();

        assertEquals("testItem2",nameOfFirstItem);
    }
}
