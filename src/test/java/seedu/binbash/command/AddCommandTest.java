package seedu.binbash.command;

import org.junit.jupiter.api.Test;

import seedu.binbash.inventory.ItemList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    void execute_item_oneItemInItemList() {
        ItemList itemList = new ItemList();
        AddCommand addCommand = new AddCommand("retail", "testItem", "A test item", 2,
                LocalDate.now(), 4.00, 5.00);

        addCommand.execute(itemList);
        assertEquals(1, itemList.getItemCount());
    }
}
