package seedu.binbash.command;

import org.junit.jupiter.api.Test;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.inventory.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    void execute_item_oneItemInItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        AddCommand addCommand = new AddCommand("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00, 6);

        addCommand.execute(itemList);
        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void execute_multipleItems_multipleItemsInItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        AddCommand addCommand1 = new AddCommand("retail", "item1", "First item", 10,
                LocalDate.now(), 1.00, 2.00, 5);
        AddCommand addCommand2 = new AddCommand("operational", "item2", "Second item", 20,
                LocalDate.now(), 3.00, 4.00, 10);

        addCommand1.execute(itemList);
        addCommand2.execute(itemList);
        assertEquals(2, itemList.getItemCount());
    }

    @Test
    void execute_itemDetails_correctItemDetails() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        AddCommand addCommand = new AddCommand("retail", "testItem", "A test item", 2,
                LocalDate.of(1999, 1, 1), 4.00, 5.00, 6);

        addCommand.execute(itemList);
        PerishableRetailItem addedItem = (PerishableRetailItem) itemList.getItemList().get(0);

        assertEquals("testItem", addedItem.getItemName());
        assertEquals("A test item", addedItem.getItemDescription());
        assertEquals(2, addedItem.getItemQuantity());
        assertEquals(LocalDate.of(1999, 1, 1), addedItem.getLocalDateItemExpirationDate());
        assertEquals(4.00, addedItem.getItemSalePrice());
        assertEquals(5.00, addedItem.getItemCostPrice());
        assertEquals(6, addedItem.getItemThreshold());
    }

    @Test
    void execute_itemNameAlreadyExists_itemNotAdded() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        AddCommand addCommand1 = new AddCommand("retail", "testItem", "A test item", 2,
                LocalDate.of(2024, 12, 12), 4.00, 5.00, 6);
        AddCommand addCommand2 = new AddCommand("retail", "testItem", "Another test item", 3,
                LocalDate.of(2024, 12 ,12), 7.00, 8.00, 9);

        addCommand1.execute(itemList);
        addCommand2.execute(itemList);

        assertEquals(1, itemList.getItemCount());
    }
}
