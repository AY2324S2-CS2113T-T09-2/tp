package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.item.Item;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ByeCommandTest {

    @Test
    void execute_exitBinBash_returnBye() {
        Command command = new ByeCommand();
        command.execute(new ItemList(new ArrayList<Item>()));
        String actualOutput = command.getExecutionUiOutput(); ;
        String expectedOutput = "Bye!";

        assertEquals(expectedOutput, actualOutput);
    }
}