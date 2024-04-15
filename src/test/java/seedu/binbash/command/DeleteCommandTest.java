package seedu.binbash.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.binbash.inventory.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteCommandTest {
    ItemList itemList;

    @BeforeEach
    void setUp() {
        itemList = new ItemList(new ArrayList<>());
        itemList.addItem("retail", "test", "A test item", 2,
                LocalDate.now(), 2.00, 1.00, 3);
    }

    @Test
    void execute_validItemIndex_itemRemovedFromItemList() {
        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute(itemList);

        assertEquals(0, itemList.getItemCount());
        assertTrue(deleteCommand.hasToSave());
    }

    @Test
    void execute_validItemName_itemRemovedFromItemList() {
        DeleteCommand deleteCommand = new DeleteCommand("test");
        deleteCommand.execute(itemList);


        assertEquals(0, itemList.getItemCount());
        assertTrue(deleteCommand.hasToSave());
    }

    @Test
    void execute_invalidItemIndex_itemNotRemovedFromItemList() {
        DeleteCommand deleteCommand = new DeleteCommand(2);
        deleteCommand.execute(itemList);

        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void execute_invalidItemName_itemNotRemovedFromItemList() {
        DeleteCommand deleteCommand = new DeleteCommand("invalid item name");
        deleteCommand.execute(itemList);

        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void execute_removeDuplicateNamesOnlyOneRemoved() {
        itemList.addItem("retail", "test", "Another test item", 1,
                LocalDate.now(), 5.00, 2.00, 4); // Adding a duplicate item

        assertEquals(1, itemList.getItemCount());

        DeleteCommand deleteCommand = new DeleteCommand("test");
        deleteCommand.execute(itemList);

        assertEquals(0, itemList.getItemCount());
        assertTrue(deleteCommand.hasToSave());
    }
}
