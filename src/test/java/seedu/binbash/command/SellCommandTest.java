package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SellCommandTest {

    @Test
    void execute_sellExistingItem_quantityUpdated() throws InvalidCommandException {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        String itemName = "testItem";
        itemList.addItem("retail", itemName, "A test item", 9,
                LocalDate.now(), 4.00, 5.00, 6);

        SellCommand sellCommand = new SellCommand(itemName, 5);

        assertTrue(sellCommand.execute(itemList));
        Item updatedItem = itemList.findItemByName(itemName);
        assertEquals(4, updatedItem.getItemQuantity());
    }

    @Test
    void execute_itemNotFound_noChangeInItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        RestockCommand restockCommand = new RestockCommand("nonexistentItem", 5);

        assertTrue(restockCommand.execute(itemList));
        assertEquals(0, itemList.getItemCount());
    }

    @Test
    public void sellItem_decreasesQuantity_validIndexAndQuantity() {
        ItemList itemList = new ItemList(new ArrayList<>());
        itemList.addItem("retail", "Test Item", "Test Description", 10, LocalDate.MIN, 5.0, 2.0, 5);
        SellCommand command = new SellCommand(1, 5);
        command.setIsIndex();
        command.execute(itemList);
        assertEquals(5, itemList.getItemList().get(0).getItemQuantity());
    }

    @Test
    public void sellItem_returnsErrorMessage_nonExistingItem() {
        ItemList itemList = new ItemList(new ArrayList<>());
        SellCommand command = new SellCommand("Non-Existing Item", 5);
        command.execute(itemList);
        assertEquals("Item with name 'Non-Existing Item' not found!", command.getExecutionUiOutput());
    }

    @Test
    public void sellItem_returnsErrorMessage_invalidIndex() {
        ItemList itemList = new ItemList(new ArrayList<>());
        itemList.addItem("retail", "Test Item", "Test Description", 10, LocalDate.MIN, 5.0, 2.0, 5);
        SellCommand command = new SellCommand(2, 5);
        command.setIsIndex();
        command.execute(itemList);
        assertEquals("Index entered is out of bounds!", command.getExecutionUiOutput());
    }
}
