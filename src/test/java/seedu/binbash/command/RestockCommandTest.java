package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestockCommandTest {

    @Test
    void execute_restockExistingItem_quantityUpdated() throws InvalidCommandException {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        String itemName = "testItem";
        itemList.addItem("retail", itemName, "A test item", 2,
                LocalDate.now(), 4.00, 5.00, 6);

        RestockCommand restockCommand = new RestockCommand(itemName, 5);

        assertTrue(restockCommand.execute(itemList));
        Item updatedItem = itemList.findItemByName(itemName);
        assertEquals(7, updatedItem.getItemQuantity());
    }

    @Test
    void execute_itemNotFound_noChangeInItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        RestockCommand restockCommand = new RestockCommand("nonexistentItem", 5);

        assertTrue(restockCommand.execute(itemList));
        assertEquals(0, itemList.getItemCount());
    }

    @Test
    public void restockItem_increasesQuantity_validIndexAndQuantity() {
        ItemList itemList = new ItemList(new ArrayList<>());
        itemList.addItem("retail", "Test Item", "Test Description", 10, LocalDate.MIN, 5.0, 2.0, 5);
        RestockCommand command = new RestockCommand(1, 5);
        command.setIsIndex();
        command.execute(itemList);
        assertEquals(15, itemList.getItemList().get(0).getItemQuantity());
    }

    @Test
    public void restockItem_returnsErrorMessage_nonExistingItem() {
        ItemList itemList = new ItemList(new ArrayList<>());
        RestockCommand command = new RestockCommand("Non-Existing Item", 5);
        command.execute(itemList);
        assertEquals("Item with name 'Non-Existing Item' not found! " +
                        "Consider using the search or the list command to find the exact name of your item!",
                command.getExecutionUiOutput());
    }

    @Test
    public void restockItem_returnsErrorMessage_invalidIndex() {
        ItemList itemList = new ItemList(new ArrayList<>());
        itemList.addItem("retail", "Test Item", "Test Description", 10, LocalDate.MIN, 5.0, 2.0, 5);
        RestockCommand command = new RestockCommand(2, 5);
        command.setIsIndex();
        command.execute(itemList);
        assertEquals("Index entered is out of bounds!", command.getExecutionUiOutput());
    }
}
