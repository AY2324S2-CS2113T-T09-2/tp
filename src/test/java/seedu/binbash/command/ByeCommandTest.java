package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.item.Item;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByeCommandTest {
    Command command = new ByeCommand();

    @Test
    void execute_exitBinBash_returnBye() {
        command.execute(new ItemList(new ArrayList<Item>()));
        String actualOutput = command.getExecutionUiOutput(); ;
        String expectedOutput = "Bye!";

        assertEquals(expectedOutput, actualOutput);
    }
}
