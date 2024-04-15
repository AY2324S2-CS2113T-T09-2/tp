package seedu.binbash.command;

import org.junit.jupiter.api.Test;

import seedu.binbash.item.Item;
import seedu.binbash.inventory.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfitCommandTest {

    @Test
    void execute_calculateTotalProfit() {
        ArrayList<Item> emptyItemList = new ArrayList<>();
        ItemList itemList = new ItemList(emptyItemList);

        AddCommand addCommand1 = new AddCommand("retail", "Item 1", "Description 1", 1,
                LocalDate.now(), 5, 3, 1);
        addCommand1.execute(itemList);

        AddCommand addCommand2 = new AddCommand("retail", "Item 2", "Description 2", 1,
                LocalDate.now(), 10, 7, 1);
        addCommand2.execute(itemList);

        SellCommand sellCommand1 = new SellCommand("Item 1", 1);
        sellCommand1.execute(itemList);

        SellCommand sellCommand2 = new SellCommand("Item 2", 1);
        sellCommand2.execute(itemList);

        ProfitCommand profitCommand = new ProfitCommand();
        assertTrue(profitCommand.execute(itemList));
        assertEquals("Total profit: $5.00", profitCommand.getExecutionUiOutput().trim());
    }
}
