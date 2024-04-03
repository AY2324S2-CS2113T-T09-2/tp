package seedu.binbash.inventory;

import org.junit.jupiter.api.Test;

import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.item.Item;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {

    @Test
    void deleteItem_indexOfItemInItemList_itemRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00, 6);

        itemList.deleteItem(1);

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void deleteItem_nameOfItemInItemList_itemRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00,6);

        itemList.deleteItem("testItem");

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void deleteItem_nameOfItemNotInItemList_itemNotRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00, 6);

        itemList.deleteItem("notTestItem");

        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void addItem_noItemInItemList_oneItemInItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());

        itemList.addItem("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00, 6);
        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void addItem_itemInputs_correctItemParameters() {
        ItemList itemList = new ItemList(new ArrayList<Item>());

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
        ItemList itemList = new ItemList(new ArrayList<Item>());

        itemList.addItem("operational", "testItem", "A test item", 2,
                LocalDate.MIN, 0.00, 5.00, 6);
        assertTrue(itemList.getItemList().get(0) instanceof OperationalItem);
    }

    @Test
    void addItem_addPerishableOperationalItem_correctItemType() {
        ItemList itemList = new ItemList(new ArrayList<Item>());

        itemList.addItem("operational", "testItem", "A test item", 2,
                LocalDate.of(1999, 1, 1), 0.00, 5.00, 6);
        assertTrue(itemList.getItemList().get(0) instanceof PerishableOperationalItem);
    }

    @Test
    void printList_twoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList(new ArrayList<Item>());

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

    @Test
    public void updateItemDataByName_validUpdates_success() throws Exception {
        ArrayList<Item> items = new ArrayList<>();
        RetailItem testItem = new RetailItem(
                "Test Item",
                "A test item",
                10,
                20.0,
                15.0,
                5);
        items.add(testItem);
        ItemList itemList = new ItemList(items);

        String newDescription = "An updated test item";
        int newQuantity = 15;
        LocalDate newExpirationDate = LocalDate.MIN; // Non-perishable
        double newSalePrice = 25.0;
        double newCostPrice = 18.0;
        int newThreshold = 7;

        String result = itemList.updateItemDataByName(
                "Test Item",
                newDescription,
                newQuantity,
                newExpirationDate,
                newSalePrice,
                newCostPrice,
                newThreshold);

        RetailItem updatedItem = (RetailItem) itemList.getItemList().get(0);

        assertEquals(newDescription, updatedItem.getItemDescription());
        assertEquals(newQuantity, updatedItem.getItemQuantity());
        assertEquals(newSalePrice, updatedItem.getItemSalePrice());
        assertEquals(newCostPrice, updatedItem.getItemCostPrice());
        assertEquals(newThreshold, updatedItem.getItemThreshold());
    }

    @Test
    public void updateItemDataByName_nonExistingItem_throwsException() {
        ArrayList<Item> items = new ArrayList<>();
        RetailItem testItem = new RetailItem(
                "Test Item",
                "A test item",
                10,
                20.0,
                15.0,
                5);
        items.add(testItem);
        ItemList itemList = new ItemList(items);

        Exception exception = assertThrows(Exception.class, () -> {
            itemList.updateItemDataByName(
                    "Non-existing Item",
                    "New description",
                    10, LocalDate.MIN,
                    30.0,
                    25.0,
                    5);
        });

        assertEquals("Item with name 'Non-existing Item' not found.", exception.getMessage());
    }

    @Test
    public void updateItemDataByIndex_validUpdates_success() throws Exception {
        ArrayList<Item> items = new ArrayList<>();
        RetailItem testItem = new RetailItem(
                "Test Item",
                "A test item",
                10,
                20.0,
                15.0,
                5);
        items.add(testItem);
        ItemList itemList = new ItemList(items);

        String newDescription = "An updated test item";
        int newQuantity = 15;
        LocalDate newExpirationDate = LocalDate.MIN; // Non-perishable
        double newSalePrice = 25.0;
        double newCostPrice = 18.0;
        int newThreshold = 7;

        String result = itemList.updateItemDataByIndex(
                1,
                newDescription,
                newQuantity,
                newExpirationDate,
                newSalePrice,
                newCostPrice,
                newThreshold);

        RetailItem updatedItem = (RetailItem) itemList.getItemList().get(0);

        assertEquals(newDescription, updatedItem.getItemDescription());
        assertEquals(newQuantity, updatedItem.getItemQuantity());
        assertEquals(newSalePrice, updatedItem.getItemSalePrice());
        assertEquals(newCostPrice, updatedItem.getItemCostPrice());
        assertEquals(newThreshold, updatedItem.getItemThreshold());
    }

    @Test
    void updateItemDataByName_invalidItemName_throwsException() {
        ItemList itemList = new ItemList(new ArrayList<>());
        assertThrows(InvalidCommandException.class, () -> itemList.updateItemDataByName(
                "Nonexistent",
                null,
                Integer.MIN_VALUE,
                LocalDate.MIN,
                Double.MIN_VALUE,
                Double.MIN_VALUE,
                Integer.MIN_VALUE));
    }

    @Test
    void updateItemDataByIndex_invalidItemIndex_throwsException() {
        ItemList itemList = new ItemList(new ArrayList<>());
        assertThrows(IndexOutOfBoundsException.class, () -> itemList.updateItemDataByIndex(
                3,
                null,
                Integer.MIN_VALUE,
                LocalDate.MIN,
                Double.MIN_VALUE,
                Double.MIN_VALUE,
                Integer.MIN_VALUE));
    }

    @Test
    void updateItemDataByName_noChanges_noChangesMade() throws InvalidCommandException {
        ItemList itemList = new ItemList(new ArrayList<>());
        itemList.addItem(
                "retail",
                "Item1",
                "Description1",
                10, LocalDate.now(),
                20.0,
                10.0,
                5);
        Item originalItem = itemList.findItemByName("Item1");
        itemList.updateItemDataByName(
                "Item1",
                null,
                Integer.MIN_VALUE,
                LocalDate.MIN,
                Double.MIN_VALUE,
                Double.MIN_VALUE,
                Integer.MIN_VALUE);
        Item updatedItem = itemList.findItemByName("Item1");
        assertEquals(originalItem.toString(), updatedItem.toString());
    }
}
