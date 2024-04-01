package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

public class ByeCommand extends Command {
    public ByeCommand() {
        commandLogger = new BinBashLogger(ByeCommand.class.getName());
        commandLogger.info("Creating Bye Command...");
    }

    @Override
    public boolean execute(ItemList itemList) {
        executionUiOutput = "Bye!";
        return true;
    }
}
