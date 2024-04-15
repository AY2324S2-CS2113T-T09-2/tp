package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

import seedu.binbash.ui.Ui;

/**
 * Represents the execution of a bye command that quits the program.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        commandLogger = new BinBashLogger(ByeCommand.class.getName());
        commandLogger.info("Creating Bye Command...");
    }

    @Override
    public boolean execute(ItemList itemList) {
        Ui.setUserAsInactive();
        executionUiOutput = "Bye!";
        return true;
    }
}
