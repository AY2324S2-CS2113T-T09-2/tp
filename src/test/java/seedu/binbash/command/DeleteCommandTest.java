package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.Item;
import seedu.binbash.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

    @Test
    void execute_validItemIndex_itemRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("retail", "test", "A test item", 2,
                LocalDate.now(), 2.00, 1.00);

        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute(itemList);

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void execute_validItemName_itemRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("retail", "test", "A test item", 2,
                LocalDate.now(), 2.00, 1.00);

        DeleteCommand deleteCommand = new DeleteCommand("test");
        deleteCommand.execute(itemList);

        assertEquals(0, itemList.getItemCount());
    }

    @Test
    void execute_inValidItemIndex_itemNotRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("retail", "test", "A test item", 2,
                LocalDate.now(), 2.00, 1.00);

        DeleteCommand deleteCommand = new DeleteCommand(2);
        deleteCommand.execute(itemList);

        assertEquals(1, itemList.getItemCount());
    }

    @Test
    void execute_inValidItemName_itemNotRemovedFromItemList() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        itemList.addItem("retail", "test", "A test item", 2,
                LocalDate.now(), 2.00, 1.00);

        DeleteCommand deleteCommand = new DeleteCommand("invalid item name");
        deleteCommand.execute(itemList);

        assertEquals(1, itemList.getItemCount());
    }
}
