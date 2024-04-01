package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SellCommandTest {

    @Test
    void execute_sellExistingItem_quantityUpdated() throws InvalidArgumentException {
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
}
