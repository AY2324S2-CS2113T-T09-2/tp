package seedu.binbash;

import org.junit.jupiter.api.Test;

import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemListTest {

    @Test
    void deleteItem_indexOfItemInItemList_itemRemovedFromItemList() {
        ItemList itemList = new ItemList();
        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00, 6);

        itemList.deleteItem(1);

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void deleteItem_nameOfItemInItemList_itemRemovedFromItemList() {
        ItemList itemList = new ItemList();
        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00,6);

        itemList.deleteItem("testItem");

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void deleteItem_nameOfItemNotInItemList_itemNotRemovedFromItemList() {
        ItemList itemList = new ItemList();
        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00, 6);

        itemList.deleteItem("notTestItem");

        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void addItem_noItemInItemList_oneItemInItemList() {
        ItemList itemList = new ItemList();

        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00, 6);
        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void addItem_itemInputs_correctItemParameters() {
        ItemList itemList = new ItemList();

        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.of(1999, 1, 1), 4.00, 5.00, 6);
        PerishableRetailItem item = (PerishableRetailItem) itemList.getItemList().get(0);

        assertEquals(item.getItemName(), "testItem");
        assertEquals(item.getItemDescription(), "A test item");
        assertEquals(item.getItemQuantity(), 2);
        assertEquals(item.getItemExpirationDate(), "01-01-1999");
        assertEquals(item.getItemSalePrice(), 4.00);
        assertEquals(item.getItemCostPrice(), 5.00);
    }

    @Test
    void addItem_addOperationalItem_correctItemType() {
        ItemList itemList = new ItemList();

        itemList.addItem("operational", "testItem", "A test item", 2,
                LocalDate.MIN, 0.00, 5.00, 6);
        assertTrue(itemList.getItemList().get(0) instanceof OperationalItem);
    }

    @Test
    void addItem_addPerishableOperationalItem_correctItemType() {
        ItemList itemList = new ItemList();

        itemList.addItem("operational", "testItem", "A test item", 2,
                LocalDate.of(1999, 1, 1), 0.00, 5.00, 6);
        assertTrue(itemList.getItemList().get(0) instanceof PerishableOperationalItem);
    }

    @Test
    void printList_twoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList();

        itemList.addItem("retail", "testItem1", "Test item 1", 2,
                LocalDate.of(1999, 1, 1), 4.00, 5.00, 6);
        itemList.addItem("retail", "testItem2", "Test item 2", 6,
                LocalDate.of(1999, 1, 1), 8.00, 9.00, 10);

        String actualOutput = itemList.printList(itemList.getItemList());

        String expectedOutput = "1. [P][R] testItem1" + System.lineSeparator() +
                "\tdescription: Test item 1" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\tcost price: $5.00" + System.lineSeparator() +
                "\tsale price: $4.00" + System.lineSeparator() +
                "\tthreshold: 6" + System.lineSeparator() +
                "\texpiry date: 01-01-1999" + System.lineSeparator() +
                System.lineSeparator() +
                "2. [P][R] testItem2" + System.lineSeparator() +
                "\tdescription: Test item 2" + System.lineSeparator() +
                "\tquantity: 6" + System.lineSeparator() +
                "\tcost price: $9.00" + System.lineSeparator() +
                "\tsale price: $8.00" + System.lineSeparator() +
                "\tthreshold: 10" + System.lineSeparator() +
                "\texpiry date: 01-01-1999" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }
}
